<template>
  <div class="competition-list">
    <div
      v-for="competition in competitions"
      :key="competition.id"
      class="competition-card"
    >
      <div class="status">{{ competition.status }}</div>
      <h2>{{ competition.title }}</h2>
      <p><strong>主办方:</strong> {{ competition.organizer }}</p>
      <p><strong>竞赛类别:</strong> {{ competition.category }}</p>
      <p><strong>报名时间:</strong> {{ competition.registration_start }} ~ {{ competition.registration_end }}</p>
      <p><strong>比赛时间:</strong> {{ competition.competition_date }}</p>
      <button class="register-button">立即报名</button>
      <div class="countdown">距离报名截止还剩 {{ competition.days_remaining }} 天</div>
      <a href="#" class="details-link">竞赛详情</a>
    </div>
  </div>
</template>
<script>
export default {
  name: "CompetitionList",
  data() {
    return {
      competitions: [],
    };
  },
  created() {
    this.fetchCompetitions();
  },
  methods: {
    async fetchCompetitions() {
      try {
        const response = await fetch("https://192.168.86.135/competitions");
        this.competitions = await response.json();
      } catch (error) {
        console.error("Failed to fetch competitions:", error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.competition-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 1rem;
}

.competition-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 1rem;
  background-color: #fff;
}

.status {
  color: #00c853;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

h2 {
  font-size: 1.2rem;
  margin: 0.5rem 0;
}

.register-button {
  background-color: #00e676;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}

.countdown {
  color: #666;
  margin-top: 0.5rem;
}

.details-link {
  color: #00b0ff;
  margin-top: 0.5rem;
  display: inline-block;
}
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
