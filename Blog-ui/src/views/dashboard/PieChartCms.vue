<template>
  <div class="competition-card-container">
    <h3 class="title">比赛报名</h3>
    <div v-if="competitions.length > 0">
      <div
        v-for="competition in competitions.slice().reverse()"
        :key="competition.id"
        class="competition-card"
      >
        <div class="competition-header">
          <h4 class="competition-title">{{ competition.competitionName }}</h4>
          <span class="competition-status" :class="getStatusClass(competition.endDate)">
            {{ getCompetitionStatus(competition.endDate) }}
          </span>
        </div>
        <p class="organizer">主办方: {{ competition.organizingBody }}</p>
        <p class="competition-level">比赛级别: {{ competition.competitionType }}</p>
        <p class="competition-fee">报名费用: {{ competition.participationFee }}</p>
        <p class="registration-time">
          报名时间: {{ competition.registrationDate }} ~ {{ competition.endDate }}
        </p>
        <div class="action-row">
          <button
            class="register-button"
            :disabled="isCompetitionOver(competition.endDate) || competition.registered || isSubmitting"
            :class="{ disabled: isCompetitionOver(competition.endDate) || competition.registered || isSubmitting }"
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
        <el-form-item label="姓名" prop="name" v-if="user">
          <el-input v-model="form.name">{{ user.userName }}</el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentId" v-if="user">
          <el-input v-model="form.studentId">{{ user.phonenumber }}</el-input>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major"></el-input>
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
</template>

<script>
import { listCompetitions2 } from "@/api/system/competitions2"; // Fetch competition data
import { listGRcompetitions, getGRcompetitions, delGRcompetitions, addGRcompetitions, updateGRcompetitions } from "@/api/system/GRcompetitions";
import { getUserProfile } from "@/api/system/user";

export default {
  data() {
    return {
      competitions: [], // 比赛列表
      open: false, // 是否显示报名弹窗
      isSubmitting: false, // 控制提交状态
      form: {
        competitionName: "",
        competitionCategory: "",
        name: "",
        studentId: "",
        major: "",
        status: "", // Ensure status is part of the form
      }, // 表单数据
      rules: {
        competitionName: [{ required: true, message: "赛事名称不能为空", trigger: "blur" }],
        competitionCategory: [{ required: true, message: "赛事类别不能为空", trigger: "blur" }],
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        studentId: [{ required: true, message: "学号不能为空", trigger: "blur" }],
        major: [{ required: true, message: "专业不能为空", trigger: "blur" }],
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }]
      },
      dict: {
        type: {
          GRcompertion: [
            { value: '0', label: '意向参与' },
            { value: '2', label: '已完成缴费' },
          ]
        }
      },
      user: null, // Initialize user object
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    async fetchCompetitions() {
      try {
        const response = await listCompetitions2();  // 获取最新的比赛数据
        if (response && response.rows) {
          this.competitions = response.rows.map(competition => ({
            ...competition,
            registered: competition.registered || false  // Ensure competition has registration status
          }));
        } else {
          this.competitions = [];
        }
      } catch (error) {
        console.error("获取数据失败", error);
        this.competitions = [];
      }
    },
    openRegistrationModal(competition) {
      if (this.user) {  // Ensure user data is available
        this.form.competitionName = competition.competitionName;
        this.form.competitionCategory = competition.competitionType;
        this.form.name = this.user.userName || ""; // Default to user name if available
        this.form.studentId = this.user.phonenumber || ""; // Default to user phonenumber if available
        this.form.major = "";
        this.form.status = "";
        this.open = true;
      }
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
      });
    },
    submitRegistration() {
      this.isSubmitting = true; // Disable submit button while submitting
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log('Submitting form:', this.form);
          // Add or update the competition registration
          const action = this.form.id ? updateGRcompetitions : addGRcompetitions;
          action(this.form).then(() => {
            this.$modal.msgSuccess(this.form.id ? "修改成功" : "报名成功");
            this.open = false;
            this.fetchCompetitions(); // Refresh competition data
            this.isSubmitting = false; // Reset submit button state
          }).catch(() => {
            this.isSubmitting = false;
          });
        } else {
          this.isSubmitting = false; // Reset if validation fails
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
    this.fetchCompetitions(); // Load competition data on mount
  }
};
</script>

<style scoped>
/* 整体容器样式 */
.competition-card-container {
  max-width: 1328px;
    margin: 0 auto;
    padding: 30px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    background-color: #f9f9f9;
    border-radius: 10px;
    width: 1325px;
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
