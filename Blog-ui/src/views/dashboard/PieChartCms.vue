<template>
  <div class="competition-card-container">
    <h3 class="title">比赛报名</h3>
    <div v-if="competitions.length > 0">
      <div v-for="competition in competitions.slice().reverse()" :key="competition.id" class="competition-card">
        <div class="competition-header">
          <h4 class="competition-title">{{ competition.competitionName }}</h4>
          <span class="competition-status" :class="getStatusClass(competition.endDate)">
            {{ getCompetitionStatus(competition.endDate) }}
          </span>
        </div>
        <p class="organizer">主办方: {{ competition.organizingBody }}</p>
        <p class="organizer">比赛级别: {{competition.competitionType}}</p>
        <p class="competition-area">报名费用: {{ competition.participationFee }}</p>
        <p class="registration-time">
          报名时间: {{ competition.registrationDate }} ~ {{ competition.endDate }}
        </p>
        <div class="action-row">
          <button
            class="register-button"
            :disabled="isCompetitionOver(competition.endDate)"
            :class="{ disabled: isCompetitionOver(competition.endDate) }"
            @click="handleRegistration(competition.id)"
          >
            {{ isCompetitionOver(competition.endDate) ? "报名已结束" : "立即报名" }}
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
  </div>
</template>

<script>
import { listCompetitions2 } from "@/api/system/competitions2";

export default {
  data() {
    return {
      competitions: [] // 比赛列表
    };
  },
  methods: {
    async fetchCompetitions() {
      try {
        const response = await listCompetitions2();
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
    },
    isCompetitionOver(endDate) {
      const today = new Date();
      const end = new Date(endDate);
      return today > end;
    },
    getCompetitionStatus(endDate) {
      return this.isCompetitionOver(endDate) ? "已结束" : "正在报名";
    },
    getStatusClass(endDate) {
      return this.isCompetitionOver(endDate) ? "status-ended" : "status-active";
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
  width: 212%;
    max-width: 1301px;
    margin: 0 auto;
    padding: 30px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    background-color: #f9f9f9;
    border-radius: 10px;
}

/* 标题样式 */
.title {
  text-align: center;
  font-size: 26px;
  font-weight: bold;
  margin-bottom: 30px;
  color: #2c3e50;
}

/* 卡片样式 */
.competition-card {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
  padding: 20px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.competition-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

/* 卡片头部样式 */
.competition-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.competition-title {
  font-size: 20px;
  font-weight: bold;
  color: #2c3e50;
}

.competition-status {
  font-size: 14px;
  padding: 5px 15px;
  border-radius: 25px;
}

.status-active {
  background: #28a745;
  color: #fff;
}

.status-ended {
  background: #dc3545;
  color: #fff;
}

/* 主办方与报名时间 */
.organizer,
.registration-time,
.competition-area {
  font-size: 15px;
  color: #7f8c8d;
  margin: 5px 0;
}

/* 行为按钮与倒计时 */
.action-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.register-button {
  background: #3498db;
  color: #fff;
  border: none;
  padding: 12px 24px;
  border-radius: 30px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s, transform 0.3s;
}

.register-button:hover {
  background: #2980b9;
  transform: scale(1.05);
}

.register-button.disabled {
  background: #cccccc;
  cursor: not-allowed;
}

.countdown {
  font-size: 14px;
  color: #e74c3c;
  font-weight: bold;
}

/* 比赛详情与时间 */
.details-row {
  margin-top: 15px;
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  color: #2c3e50;
}

.details-row p {
  margin: 0;
  font-weight: normal;
}

/* 无数据提示 */
.no-data {
  text-align: center;
  color: #7f8c8d;
  font-size: 18px;
  font-weight: bold;
}

/* 响应式设计优化 */
@media (max-width: 768px) {
  .competition-card-container {
    padding: 15px;
  }

  .competition-card {
    padding: 15px;
  }

  .competition-title {
    font-size: 18px;
  }

  .register-button {
    padding: 10px 20px;
  }

  .countdown {
    font-size: 12px;
  }

  .details-row p {
    font-size: 14px;
  }
}
</style>
