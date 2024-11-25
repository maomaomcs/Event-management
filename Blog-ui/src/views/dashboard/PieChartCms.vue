<template>
  <div class="competition-card-container">
    <h3 class="title">比赛报名</h3>
    <div v-if="competitions.length > 0">
      <div v-for="competition in competitions" :key="competition.id" class="competition-card">
        <div class="competition-header">
          <h4 class="competition-title">{{ competition.competitionName }}</h4>
          <span class="competition-level">{{ competition.competitionType }}</span>
        </div>
        <p class="organizer">主办方: {{ competition.organizingBody }}</p>
        <p class="registration-time">
          报名时间: {{ competition.registrationDate }} ~ {{ competition.endDate }}
        </p>
        <div class="action-row">
          <button class="register-button" @click="handleRegistration(competition.id)">立即报名</button>
          <span class="countdown">{{ getDaysLeft(competition.endDate) }}</span>
        </div>
        <div class="details-row">
          <p>竞赛详情 ></p>
          <p>比赛时间: {{ competition.endDate }}</p>
        </div>
      </div>
    </div>
    <div v-else class="no-data">
      <p>暂无比赛数据</p>
    </div>
  </div>
</template>

<script>
import { listCompetitions1 } from "@/api/system/competitions1";

export default {
  data() {
    return {
      competitions: [] // 比赛列表
    };
  },
  methods: {
    async fetchCompetitions() {
      try {
        const response = await listCompetitions1();
        if (response && response.rows) {
          this.competitions = response.rows;
        } else {
          this.competitions = [];
        }
      } catch (error) {
        console.error("获取数据失败", error);
        this.competitions = [];
      }
    },
    handleRegistration(id) {
      // 点击报名按钮的事件
      console.log(`报名比赛 ID: ${id}`);
      alert("报名功能尚未实现");
    },
    getDaysLeft(endDate) {
      const today = new Date();
      const end = new Date(endDate);
      const daysLeft = Math.ceil((end - today) / (1000 * 60 * 60 * 24));
      return daysLeft > 0 ? `距离报名截止还有 ${daysLeft} 天` : "报名已截止";
    }
  },
  mounted() {
    this.fetchCompetitions();
  }
};
</script>

<style scoped>
/* 整体容器样式 */
.competition-card-container {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  box-sizing: border-box;
}

/* 标题样式 */
.title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

/* 卡片样式 */
.competition-card {
  background: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 15px;
  box-sizing: border-box;
  transition: transform 0.2s, box-shadow 0.2s;
}

.competition-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
}

/* 卡片头部样式 */
.competition-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.competition-title {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.competition-level {
  font-size: 14px;
  color: #fff;
  background: #4caf50;
  padding: 2px 8px;
  border-radius: 12px;
}

/* 主办方与报名时间 */
.organizer,
.registration-time {
  font-size: 14px;
  color: #666;
  margin: 5px 0;
}

/* 行为按钮与倒计时 */
.action-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.register-button {
  background: #2196f3;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 20px;
  cursor: pointer;
  transition: background 0.3s;
}

.register-button:hover {
  background: #1976d2;
}

.countdown {
  font-size: 14px;
  color: #f44336;
}

/* 比赛详情与时间 */
.details-row {
  margin-top: 10px;
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  color: #333;
}

.details-row p {
  margin: 0;
}

/* 无数据提示 */
.no-data {
  text-align: center;
  color: #999;
  font-size: 16px;
}
</style>
