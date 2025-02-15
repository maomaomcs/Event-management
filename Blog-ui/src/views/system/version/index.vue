<template>
    <div class="charts-container">
        <div style="margin-bottom: 20px; font-size: 30px; color: rgb(64, 158, 255);">可视化界面</div>
        <!-- 数据展示部分 -->
        <div class="data-summary">
            <div class="summary-item">
                <div class="summary-icon">👤</div>
                <div class="summary-info">
                    <h3>单人赛事数量</h3>
                    <p>{{ singleCompetitionCount }}</p>
                </div>
            </div>
            <div class="summary-item">
                <div class="summary-icon">🤝</div>
                <div class="summary-info">
                    <h3>团队赛事数量</h3>
                    <p>{{ teamCompetitionCount }}</p>
                </div>
            </div>
            <div class="summary-item">
                <div class="summary-icon">👥</div>
                <div class="summary-info">
                    <h3>用户数量</h3>
                    <p>{{ userCount }}</p>
                </div>
            </div>
            <!-- 新增展示队伍数量的元素 -->
            <div class="summary-item">
                <div class="summary-icon">🚩</div>
                <div class="summary-info">
                    <h3>队伍数量</h3>
                    <p>{{ teamCount }}</p>
                </div>
            </div>
        </div>
        

        <!-- 调整后的布局，让柱状图和表格并排 -->
        <div class="charts-content">
            <!-- 柱状图展示 -->
            <div class="chart-wrapper" style="top: -60px;width: 525px; height: 460px ;">
                <div id="competitionTypeBarChart"></div>
            </div>
            <!-- 滚动表格展示 -->
            <div class="scrollable-table-container" style="width: 50%;">
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                            </tr>
                            <tr>
                                <th>赛事名称</th>
                                <th>赛事类型</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 合并后循环展示所有赛事数据 -->
                            <tr v-for="competition in allCompetitionsForDisplay" :key="competition.id">
                                <td>{{ competition.competitionName }}</td>
                                <td>{{ competition.competitionType }}</td>
                                <td>{{ competition.competitionStartTime }}</td>
                                <td>{{ competition.competitionEndTime }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { listCompetitions2 } from "@/api/system/competitions2"; // 所有单人的赛事接口
import { listTDcompetitions } from "@/api/system/TDcompetitions"; // 所有团队赛事的接口
import { listGRcompetitions3 } from "@/api/system/GRcompetitions3"; // 所有单人赛事成员的参赛记录接口
import { listTeams1 } from "@/api/system/teams1"; // 所有队伍的接口
import { listMembers1 } from "@/api/system/members1"; // 所有队伍成员的接口
import { listUsers } from "@/api/system/users"; // 所有用户的接口
import * as echarts from "echarts";

export default {
    data() {
        return {
            competitionTypeData: [], // 存储赛事类型统计数据（这里可扩展为更丰富的统计维度）
            singleCompetitionCount: 0, // 单人赛事数量
            teamCompetitionCount: 0, // 团队赛事数量
            userCount: 0, // 用户数量
            singleCompetitions: [], // 单独存储单人赛事数据
            teamCompetitions: [], // 单独存储团队赛事数据
            teamCount: 0, // 团队数量
            singleParticipantCount: 0, // 单人参赛人数的数量
            teamParticipantCount: 0, // 团队赛事参赛人数的数量
            allCompetitions: [] // 用于存储合并后的所有赛事数据
        };
    },
    computed: {
        // 处理后用于循环展示的合并后的所有赛事数据
        allCompetitionsForDisplay() {
            return this.allCompetitions;
        }
    },
    mounted() {
        // 获取单人赛事、团队赛事、用户数据以及其他相关统计数据（包含团队数量、参赛人数等统计）
        Promise.all([
            this.getValidCompetitionsData(listCompetitions2),
            this.getValidCompetitionsData(listTDcompetitions),
            this.getValidUsersData(listUsers),
            this.getTeamCount(),
            this.getSingleParticipantCount(),
            this.getTeamParticipantCount()
        ])
  .then(([singleCompetitions, teamCompetitions, users, teamCount, singleParticipantCount, teamParticipantCount]) => {
            this.singleCompetitionCount = singleCompetitions.length;
            this.teamCompetitionCount = teamCompetitions.length;
            this.userCount = users.length;
            this.teamCount = teamCount;
            this.singleParticipantCount = singleParticipantCount;
            this.teamParticipantCount = teamParticipantCount;
            // 合并单人赛事和团队赛事数据到 allCompetitions 数组
            this.allCompetitions = [...singleCompetitions,...teamCompetitions];
            // 设置赛事类型数据，包含单人赛事、多人赛事、用户数量、团队数量、单人参赛人数、团队赛事参赛人数相关统计
            const typeCount = {
                '单人赛事数量': this.singleCompetitionCount,
                '多人赛事数量': this.teamCompetitionCount,
                '用户数量': this.userCount,
                '团队数量': this.teamCount,
                '单人参赛人数': this.singleParticipantCount,
                '团队赛事参赛人数': this.teamParticipantCount,
            };
            this.competitionTypeData = Object.entries(typeCount).map(([type, count]) => ({
                type,
                count,
            }));
            // 渲染柱状图，此处代码保持不变，不做修改
            this.renderCompetitionTypeBarChart();
            // 启动自动滚动定时器，实现循环滚动，每3秒（3000毫秒）滚动一次，可根据需求调整时间间隔
            this.startTableAutoScroll();
        })
  .catch((error) => {
            console.error("获取赛事类型或用户数据失败", error);
        });
    },
    methods: {
        getValidCompetitionsData(apiFunction) {
    return apiFunction().then((response) => {
        if (response && response.rows && Array.isArray(response.rows)) {
            return response.rows;
        } else {
            console.error(`${apiFunction.name}接口返回数据格式不符合预期，期望包含rows数组但实际不是`, response);
            return [];
        }
    }).catch((error) => {
        console.error(`${apiFunction.name}接口请求失败`, error);  // 修正了引号问题
        return [];
    });
},

getValidUsersData(apiFunction) {
    return apiFunction().then((response) => {
        if (response && response.rows && Array.isArray(response.rows)) {
            return response.rows;
        } else {
            console.error(`${apiFunction.name}接口返回数据格式不符合预期，期望包含rows数组但实际不是`, response);
            return [];
        }
    }).catch((error) => {
        console.error(`${apiFunction.name}接口请求失败`, error);  // 修正了引号问题
        return [];
    });
},

        // 新增方法，获取团队数量（简单示例，根据 teams1 表数据统计，实际可能需更复杂逻辑或从后端接口获取已统计好的数据）
        getTeamCount() {
            return listTeams1().then((response) => {
                if (response && response.rows && Array.isArray(response.rows)) {
                    return response.rows.length;
                } else {
                    console.error("获取团队数量接口返回数据格式不符合预期", response);
                    return 0;
                }
            }).catch((error) => {
                console.error("获取团队数量接口请求失败", error);
                return 0;
            });
        },
        // 新增方法，获取单人参赛人数数量（简单示例，假设从 GRcompetitions3 表统计单人参赛人数，实际需按真实业务逻辑完善）
        getSingleParticipantCount() {
    return listGRcompetitions3().then((response) => {
        // 直接获取 total 数量
        if (response && response.total !== undefined) {
            return response.total;
        } else {
            console.error("获取单人参赛人数数量接口返回数据格式不符合预期", response);
            return 0;
        }
    }).catch((error) => {
        console.error("获取单人参赛人数数量接口请求失败", error);
        return 0;
    });
},
        // 新增方法，获取团队赛事参赛人数数量（简单示例，需结合 teams1 和 team_members1 表准确统计，这里只是模拟逻辑）
        getTeamParticipantCount() {
  return Promise.all([listTeams1(), listMembers1()])
    .then(([teamsResponse, membersResponse]) => {
      // 检查接口返回的数据格式是否符合预期
      if (teamsResponse && teamsResponse.total !== undefined &&
          membersResponse && membersResponse.total !== undefined) {
        // 直接获取 total 数值并返回总数
        const totalParticipants = teamsResponse.total + membersResponse.total;
        return totalParticipants;
      } else {
        console.error("获取团队赛事参赛人数接口返回数据格式不符合预期", teamsResponse, membersResponse);
        return 0;
      }
    })
    .catch((error) => {
      console.error("获取团队赛事参赛人数接口请求失败", error);
      return 0;
    });
},  
        renderCompetitionTypeBarChart() {
    const chartLabels = ['单人赛事数量', '多人赛事数量', '团队数量', '单人参赛人数', '团队赛事人数'];
    const chartData = [
        this.singleCompetitionCount,
        this.teamCompetitionCount,
        this.teamCount,
        this.singleParticipantCount,
        this.teamParticipantCount
    ];

    const chart = echarts.init(document.getElementById("competitionTypeBarChart"));
    chart.setOption({
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            formatter: function (params) {
                const data = params[0].data;
                const type = params[0].name;
                return `${type}: ${data}`;
            }
        },
        xAxis: {
            type: 'category',
            data: chartLabels,
            axisLabel: {
                interval: 0,  // 强制显示所有标签
                rotate: 45,  // 左旋转45度
                margin: 8  // 调整标签与坐标轴的间距，可根据实际效果调整数值
            }
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: chartData,
            type: 'bar'
        }]
    });
},
        // 启动表格自动滚动
        startTableAutoScroll() {
            const tableWrapper = document.querySelector('.table-wrapper');
            let scrollPosition = 0;
            const scrollInterval = setInterval(() => {
                if (tableWrapper) {
                    scrollPosition += 1;
                    tableWrapper.scrollTop = scrollPosition;
                    if (scrollPosition >= tableWrapper.scrollHeight - tableWrapper.clientHeight) {
                        scrollPosition = 0;
                    }
                }
            }, 20); // 每30ms滚动
        }
    }
};
</script>

<style scoped>
.charts-container {
    padding: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
}

.data-summary {
    display: flex;
    justify-content: space-between;
    width: 80%;
    margin-bottom: 20px;
}

.summary-item {
    margin: 10px;
    display: flex;
    align-items: center;
    background-color: #f5f5f5;
    padding: 10px 15px;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 30%;
    text-align: center;
}

.summary-icon {
    font-size: 30px;
    margin-right: 15px;
}

.summary-info h3 {
    margin: 0;
    font-size: 16px;
    font-weight: 600;
    color: #333;
}

.summary-info p {
    font-size: 20px;
    font-weight: 700;
    color:rgb(64, 158, 255);
}

.charts-content {
    display: flex;
    width: 80%;
    margin: 20px 0;
}

.chart-wrapper {
    height: 400px;
    margin: 0;
    flex: 1;
}

#competitionTypeBarChart {
    width: 100%;
    height: 100%;
}

.scrollable-table-container {
    margin-left: 20px;
    max-height: 400px;
    overflow-y: auto;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    flex: 1;
    background-color: #fff;
    padding: 10px;
}

.table-wrapper {
    width: 100%;
    max-height: 400px;
    overflow-y: auto;
}

table {
    width: 100%;
    border-collapse: collapse;
    font-size: 14px;
    background-color: #fafafa;
}

th, td {
    padding: 12px 15px;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: rgb(64, 158, 255);
    color: white;
    font-weight: bold;
    position: sticky;
    top: 0;
    z-index: 1;
}

td {
    background-color: #fff;
    color: #333;
    font-size: 14px;
    transition: all 0.3s ease;
}

tbody tr:hover {
    background-color: #f1f1f1;
    cursor: pointer;
}

tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

tbody tr:nth-child(odd) {
    background-color: #fff;
}

tbody tr.active {
    background-color: #e0e0e0;
}

::-webkit-scrollbar {
    width: 6px;
}

::-webkit-scrollbar-track {
    background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
    background-color: #ccc;
    border-radius: 3px;
}

/* Table Cell Hover Effect */
td:hover {
    background-color: #e1f5fe;
    transition: all 0.3s ease;
}

/* Table Header sticky and Shadow */
th {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Scrollbar customization */
::-webkit-scrollbar {
    width: 8px;
}

::-webkit-scrollbar-track {
    background-color: #f9f9f9;
}

::-webkit-scrollbar-thumb {
    background-color: #ccc;
    border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
    background-color: #aaa;
}

</style>