<template>
  <div class="competition-card-container">
    <div class="navigation-buttons">
      <!-- 导航按钮 -->
      <button @click="switchTab('single')" class="tab-button" :class="{ active: selectedTab === 'single' }">单人赛事</button>
      <button @click="switchTab('team')" class="tab-button" :class="{ active: selectedTab === 'team' }">团队赛事</button>
    </div>
    
    <!-- 单人赛事 -->
    <div v-if="selectedTab === 'single'">
      <h3 class="title">单人赛事报名</h3>
      <div v-if="competitions.length > 0">
        <div
          v-for="competition in competitions.slice().reverse()"
          :key="competition.id"
          class="competition-card"
        >
          <!-- 单人赛事卡片内容 -->
          <div class="competition-header">
            <h4 class="competition-title">{{ competition.competitionName }}</h4>
            <span class="competition-status" :class="getStatusClass(competition.endDate)">
              {{ getCompetitionStatus(competition.endDate) }}
            </span>
          </div>
          <p class="organizer">赛事类别: {{ competition.organizingBody }}</p>
          <p class="competition-level">比赛级别: {{ competition.competitionType }}</p>
          <p class="competition-fee">报名费用: {{ competition.participationFee }}</p>
          <p class="registration-time">
            报名时间: {{ competition.registrationDate }} ~ {{ competition.endDate }}
          </p>
          <div class="action-row">
            <button
              class="register-button"
              :disabled="competition.registered || isCompetitionOver(competition.endDate) || isSubmitting"
              :class="{ disabled: competition.registered || isCompetitionOver(competition.endDate) || isSubmitting }"
              @click="openRegistrationModal(competition)"
            >
              {{ competition.registered ? "已报名" : (isCompetitionOver(competition.endDate) ? "报名已结束" : (isSubmitting ? "提交中..." : "立即报名")) }}
            </button>
            <span class="countdown">{{ getDaysLeft(competition.endDate) }}</span>
          </div>
          <div class="details-row">
            <p>比赛时间: {{ competition.competitionStartTime }} ~ {{ competition.competitionEndTime }}</p>
          </div>
        </div>
      </div>
      <div v-else class="no-data">
        <p>暂无比赛数据</p>
      </div>
    <!-- 报名弹窗 -->
    <el-dialog :visible.sync="open" title="报名比赛" width="50%">
      <el-form :model="form" ref="form" :rules="rules" label-width="120px">
        <el-form-item label="赛事名称" prop="competitionName">
          <el-input v-model="form.competitionName" disabled></el-input>
        </el-form-item>
        <el-form-item label="赛事类别" prop="competitionCategory">
          <el-input v-model="form.competitionCategory" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" disabled></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="默认值: 网络工程"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态" clearable>
            <el-option
              v-for="dictItem in dict.type.GRcompertion"
              :key="dictItem.value"
              :label="dictItem.label"
              :value="dictItem.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submitRegistration">提交报名</el-button>
      </span>
    </el-dialog>
    </div>

    <!-- 团队赛事 -->
    <div v-if="selectedTab === 'team'">
      <h3 class="title">团队赛事报名</h3>
      <div v-if="teamCompetitions.length > 0">
        <div
          v-for="teamCompetition in teamCompetitions.slice().reverse()"
          :key="teamCompetition.id"
          class="competition-card"
        >
          <!-- 团队赛事卡片内容 -->
          <div class="competition-header">
            <h4 class="competition-title">{{ teamCompetition.competitionName }}</h4>
            <span class="competition-status" :class="getStatusClass(teamCompetition.endDate)">
              {{ getCompetitionStatus(teamCompetition.endDate) }}
            </span>
          </div>
          <p class="organizer">赛事类别: {{ teamCompetition.organizingBody }}</p>
          <p class="competition-level">比赛级别: {{ teamCompetition.competitionType }}</p>
          <p class="competition-fee">报名费用: {{ teamCompetition.participationFee }}</p>
          <p class="registration-time">
            报名时间: {{ teamCompetition.registrationDate }} ~ {{ teamCompetition.endDate }}
          </p>
          <div class="action-row">
            <button
              class="register-button"
              :disabled="teamCompetition.registered || isCompetitionOver(teamCompetition.endDate) || isSubmitting"
              :class="{ disabled: teamCompetition.registered || isCompetitionOver(teamCompetition.endDate) || isSubmitting }"
              @click="navigateToTeamRegistration(teamCompetition)"
            >
              {{ teamCompetition.registered ? "已报名" : (isCompetitionOver(teamCompetition.endDate) ? "报名已结束" : (isSubmitting ? "提交中..." : "立即报名")) }}
            </button>
            <span class="countdown">{{ getDaysLeft(teamCompetition.endDate) }}</span>
          </div>
          <div class="details-row">
            <p>比赛时间: {{ teamCompetition.competitionStartTime }} ~ {{ teamCompetition.competitionEndTime }}</p>
          </div>
          <div class="details-row">
            <p>队伍总人数：{{ teamCompetition.memberCount }}人</p>
          </div>
        </div>
      </div>
      <div v-else class="no-data">
        <p>暂无团队比赛数据</p>
      </div>
      <!-- 报名弹窗 -->
      <el-dialog :visible.sync="open" title="报名比赛" width="50%">
      <el-form :model="form1" ref="form1" :rules="rules" label-width="120px">
        <el-form-item label="赛事名称" prop="competitionName">
          <el-input v-model="form1.competitionName" disabled></el-input>
        </el-form-item>
        <el-form-item label="赛事类别" prop="competitionCategory">
          <el-input v-model="form1.competitionCategory" disabled></el-input>
        </el-form-item>
        <el-form-item label="队伍名称" prop="teamName">
          <el-input v-model="form1.teamName"></el-input>
        </el-form-item>
        <el-form-item label="队长姓名" prop="leaderName">
          <el-input v-model="form1.leaderName" disabled></el-input>
        </el-form-item>
        <el-form-item label="队长学号" prop="studentId">
          <el-input v-model="form1.studentId" disabled></el-input>
        </el-form-item>
        <el-form-item label="团队总人数" prop="memberCount">
          <el-input v-model="form1.memberCount" disabled></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form1.status" placeholder="请选择状态" clearable>
            <el-option
              v-for="dictItem in dict.type.GRcompertion"
              :key="dictItem.value"
              :label="dictItem.label"
              :value="dictItem.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submitRegistration1">提交报名</el-button>
      </span>
    </el-dialog>
    </div>
  </div>
</template>
<script>
import { listCompetitions2 } from "@/api/system/competitions2"; 
import { listTDcompetitions} from "@/api/system/TDcompetitions";//团队赛事列表接口
import { listGRcompetitions3, addGRcompetitions3, updateGRcompetitions3 } from "@/api/system/GRcompetitions3";
import { getUserProfile } from "@/api/system/user";
import { listTeams1,addTeams1, updateTeams1 } from "@/api/system/teams1";//新增团队接口
import { listGRtdcompetitions,addGRtdcompetitions, updateGRtdcompetitions } from "@/api/system/GRtdcompetitions";//团队赛事报名列表主页显示
export default {
  data() {
    return {
      teamCompetitions: [], // 团队赛事数据
      competitions: [], // 比赛列表
      open: false, // 是否显示报名弹窗
      isSubmitting: false, // 控制提交状态
      selectedTab: "single", // 当前选中的 Tab
      form: {
        competitionId: "",
        competitionName: "",
        competitionCategory: "",
        name: "",
        studentId: "",
        major: "",
        status: "",
        memberCount: "",
      },
      form1:{
        competitionId: "",
        competitionName: "",
        competitionCategory: "",
        studentId: "",
        teamName:"",
        leaderName:"",
        memberCount: "",
        teamStatus:"",
      },
      rules: {
        competitionName: [{ required: true, message: "赛事名称不能为空", trigger: "blur" }],
        competitionCategory: [{ required: true, message: "赛事类别不能为空", trigger: "blur" }],
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        studentId: [{ required: true, message: "学号不能为空", trigger: "blur" }],
        major: [{ required: true, message: "专业不能为空", trigger: "blur" }],
        count: [{ required: true, message: "团队总人数不能为空", trigger: "blur" }],
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }],
        teamStatus:[{ required: true, message: "状态不能为空", trigger: "blur" }],
      },
      dict: {
        type: {
          GRcompertion: [
            { value: "0", label: "意向参与" },
            { value: "2", label: "已完成缴费" },
          ],
        },
      },
      user: null,
    };
  },
  created() {
    this.getUser();
    this.fetchCompetitions();
    this.fetchTeamCompetitions();
  },
  methods: {
    switchTab(tab) {
      this.selectedTab = tab;
    },
    navigateToTeamRegistration(teamCompetition) {
  if (this.user) {
    this.form1 = {
      id: teamCompetition.id, // 确保 id 被设置
      competitionId: teamCompetition.id,
      competitionName: teamCompetition.competitionName,
      competitionCategory: teamCompetition.competitionType,
      memberCount: teamCompetition.memberCount,
      studentId: this.user.userName || "",
      teamName: "",
      leaderName: this.user.nickName || "",
      status: "",
    };
    this.open = true;
  }
},
    submitRegistration1() {
  this.isSubmitting = true;
  this.$refs["form1"].validate(async valid => {
    if (valid) {
      try {
        // 提交到 GRtdcompetitions 接口
        await addGRtdcompetitions(this.form1);
        
        // 准备提交到 teams1 接口的数据
        const teamData = {
          id: null, // id 为 null
          eventId: this.form1.competitionId, // 赛事 ID
          teamName: this.form1.teamName, // 队伍名称
          leaderId: this.form1.studentId, // 队长学号
          membersCount: this.form1.memberCount, // 团队总人数
          createdAt: new Date().toISOString(), // 当前时间
          updatedAt: new Date().toISOString(), // 当前时间
        };

        // 提交到 teams1 接口
        await addTeams1(teamData);

        this.$message.success("报名成功！");
        this.open = false;
        this.fetchTeamCompetitions();
      } catch (error) {
        console.error("报名提交失败", error);
        this.$message.error("报名提交失败，请重试！");
      } finally {
        this.isSubmitting = false;
      }
    } else {
      this.isSubmitting = false;
    }
  });
},
    async fetchCompetitions() {
      try {
        const response = await listCompetitions2();
        const registeredResponse = await listGRcompetitions3();
        if (response && response.rows) {
          const registeredIds = registeredResponse.rows.map(item => item.competitionId);
          this.competitions = response.rows.map(competition => ({
            ...competition,
            registered: registeredIds.includes(competition.id),
          }));
        } else {
          this.competitions = [];
        }
      } catch (error) {
        console.error("获取数据失败", error);
      }
    },
    
    async fetchTeamCompetitions() {
  try {
    console.log("Fetching team competitions...");

    // 获取比赛数据
    const response = await listTDcompetitions();
    const registeredResponse = await listGRtdcompetitions();

    // 检查返回的数据结构
    console.log("Response from listTDcompetitions:", response);
    console.log("Response from listGRtdcompetitions:", registeredResponse);

    if (response && response.rows && registeredResponse && registeredResponse.rows) {
      // 提取注册的 competition_id
      const registeredIds = registeredResponse.rows
        .filter(item => item.competitionId !== null) // 过滤 null 值
        .map(item => String(item.competitionId)); // 确保是字符串类型

      console.log("Extracted registered IDs (team):", registeredIds);

      // 映射 teamCompetitions 数据，并检查每个比赛是否已注册
      this.teamCompetitions = response.rows.map(competition => ({
        ...competition,
        // 确保使用一致的字段名进行比较
        registered: registeredIds.includes(String(competition.id)) // 确保使用正确的字段进行匹配
      }));

      // 检查映射后的 teamCompetitions 数据
      console.log("Mapped competitions with registered status (team):", this.teamCompetitions);
    } else {
      console.warn("No rows found in response.");
      this.teamCompetitions = [];
    }
  } catch (error) {
    console.error("Failed to fetch team competitions:", error);
  }
},
    async getUser() {
      try {
        const response = await getUserProfile();
        this.user = response.data;
      } catch (error) {
        console.error("获取用户信息失败", error);
      }
    },
    openRegistrationModal(competition) {
      if (this.user) {
        this.form = {
          competitionId: competition.id,
          competitionName: competition.competitionName,
          competitionCategory: competition.competitionType,
          name: this.user.nickName || "",
          studentId: this.user.userName || "",
          major: "网络工程",
          status: "",
        };
        this.open = true;
      }
    },
    submitRegistration() {
      this.isSubmitting = true;
      this.$refs["form"].validate(valid => {
        if (valid) {
          const action = this.form.id ? updateGRcompetitions3 : addGRcompetitions3;
          action(this.form)
            .then(() => {
              this.$message.success("报名成功！");
              this.open = false;
              this.fetchCompetitions();
            })
            .catch(() => {
              this.isSubmitting = false;
            });
        } else {
          this.isSubmitting = false;
        }
      });
    },
    getDaysLeft(endDate) {
      const today = new Date();
      const end = new Date(endDate);
      const daysLeft = Math.ceil((end - today) / (1000 * 60 * 60 * 24));
      return daysLeft > 0 ? `距离报名截止还有 ${daysLeft} 天` : "报名已截止";
    },
    isCompetitionOver(endDate) {
      return new Date() > new Date(endDate);
    },
    getCompetitionStatus(endDate) {
      return this.isCompetitionOver(endDate) ? "已结束" : "正在报名";
    },
    getStatusClass(endDate) {
      return this.isCompetitionOver(endDate) ? "status-ended" : "status-active";
    },
  },
};
</script>

<style scoped>
/* 整体容器样式 */
/* 样式设置通用按钮 */
.tab-button {
  background-color: #f4f4f9; /* 按钮背景色 */
  border: 2px solid #ddd;    /* 边框 */
  border-radius: 8px;         /* 圆角 */
  padding: 10px 20px;         /* 内边距 */
  font-size: 16px;            /* 字体大小 */
  color: #333;                /* 字体颜色 */
  cursor: pointer;           /* 鼠标指针 */
  transition: all 0.3s ease;  /* 平滑过渡效果 */
  margin: 0 10px;             /* 按钮之间的间距 */
}

/* 激活状态的按钮 */
.tab-button.active {
  background-color: #4CAF50; /* 激活时的背景颜色 */
  color: white;              /* 激活时的字体颜色 */
  border-color: #4CAF50;     /* 激活时的边框颜色 */
  transform: scale(1.05);     /* 激活时放大效果 */
}

/* 鼠标悬停时的按钮样式 */
.tab-button:hover {
  background-color: #e1e1e1; /* 悬停时的背景颜色 */
  border-color: #bbb;         /* 悬停时的边框颜色 */
}

/* 激活按钮的动画效果 */
.tab-button.active {
  box-shadow: 0 4px 8px rgba(0, 128, 0, 0.2); /* 添加阴影效果 */
}

/* 处理按钮的字体 */
.tab-button {
  font-family: 'Arial', sans-serif;  /* 设置字体 */
}
.competition-card-container {
  max-width: 287%;
    margin: 0 auto;
    padding: 30px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    background-color: #f9f9f9;
    border-radius: 10px;
    width: 211%;
}

/* 标题样式 */
.title {
  text-align: center;
  font-size: 26px;
  font-weight: 600;
  margin-bottom: 40px;
  color: #2c3e50;
}

/* 卡片样式 */
.competition-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 25px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.competition-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
}

/* 卡片头部样式 */
.competition-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.competition-title {
  font-size: 20px;
  font-weight: 600;
  color: #34495e;
}

.competition-status {
  font-size: 16px;
  font-weight: 500;
  padding: 5px 10px;
  border-radius: 12px;
  text-align: center;
}

.status-active {
  background-color: #2ecc71;
  color: white;
}

.status-ended {
  background-color: #e74c3c;
  color: white;
}

/* 比赛信息样式 */
.organizer,
.competition-level,
.competition-fee,
.registration-time {
  font-size: 14px;
  color: #7f8c8d;
  margin-bottom: 10px;
}

/* 按钮样式 */
.action-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.register-button {
  padding: 12px 25px;
  background-color: #3498db;
  border: none;
  color: white;
  font-size: 16px;
  font-weight: 500;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.register-button:disabled {
  background-color: #bdc3c7;
  cursor: not-allowed;
}

.register-button:hover {
  background-color: #2980b9;
}

.register-button:active {
  transform: scale(0.98);
}

/* Countdown styling */
.countdown {
  font-size: 14px;
  color: #95a5a6;
  font-weight: 500;
}

/* 表单弹窗样式 */
.details-row {
  font-size: 14px;
  color: #95a5a6;
  margin-top: 10px;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.no-data {
  text-align: center;
  font-size: 16px;
  color: #7f8c8d;
  font-weight: 500;
}

/* 表单样式 */
.el-input, .el-select {
  border-radius: 6px;
  padding: 8px 14px;
  font-size: 14px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-form-item label {
  font-weight: 500;
  color: #34495e;
  font-size: 16px;
}

/* 弹窗的确认按钮样式 */
.dialog-footer .el-button {
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 6px;
}

/* Responsiveness for mobile view */
@media (max-width: 768px) {
  .competition-card-container {
    padding: 20px;
  }

  .competition-card {
    padding: 15px;
  }

  .title {
    font-size: 22px;
  }

  .competition-title {
    font-size: 18px;
  }

  .action-row {
    flex-direction: column;
    align-items: stretch;
  }

  .register-button {
    width: 100%;
    margin-bottom: 10px;
  }

  .countdown {
    margin-top: 10px;
  }
}

</style>
