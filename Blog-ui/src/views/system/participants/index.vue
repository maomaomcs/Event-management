<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名字" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛事名称" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入赛事名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获奖等级" prop="awardLevel">
        <el-input
          v-model="queryParams.awardLevel"
          placeholder="请输入获奖等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="指导老师" prop="advisorName">
        <el-input
          v-model="queryParams.advisorName"
          placeholder="请输入指导老师"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请时间" prop="applicationDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.applicationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择申请时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:participants:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:participants:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:participants:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:participants:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="participantsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="名字" align="center" prop="name" />
      <el-table-column label="电话号码" align="center" prop="phoneNumber" />
      <el-table-column label="赛事名称" align="center" prop="competitionName" />
      <el-table-column label="获奖等级" align="center" prop="awardLevel" />
      <el-table-column label="指导老师" align="center" prop="advisorName" />
      <el-table-column label="收款码" align="center" prop="paymentCode" />
      <el-table-column label="申请时间" align="center" prop="applicationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applicationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:participants:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:participants:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改赛事报销对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入名字" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="赛事名称" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入赛事名称" />
        </el-form-item>
        <el-form-item label="获奖等级" prop="awardLevel">
          <el-input v-model="form.awardLevel" placeholder="请输入获奖等级" />
        </el-form-item>
        <el-form-item label="指导老师" prop="advisorName">
          <el-input v-model="form.advisorName" placeholder="请输入指导老师" />
        </el-form-item>
        <el-form-item label="收款码">
          <imageUpload v-model="form.paymentCode"/>
        </el-form-item>
        <el-form-item label="申请时间" prop="applicationDate">
          <el-date-picker clearable size="small"
            v-model="form.applicationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择申请时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listParticipants, getParticipants, delParticipants, addParticipants, updateParticipants } from "@/api/system/participants";

export default {
  name: "Participants",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 赛事报销表格数据
      participantsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        phoneNumber: null,
        competitionName: null,
        awardLevel: null,
        advisorName: null,
        paymentCode: null,
        applicationDate: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名字不能为空", trigger: "blur" }
        ],
        phoneNumber: [
          { required: true, message: "电话号码不能为空", trigger: "blur" }
        ],
        competitionName: [
          { required: true, message: "赛事名称不能为空", trigger: "blur" }
        ],
        awardLevel: [
          { required: true, message: "获奖等级不能为空", trigger: "blur" }
        ],
        advisorName: [
          { required: true, message: "指导老师不能为空", trigger: "blur" }
        ],
        paymentCode: [
          { required: true, message: "收款码不能为空", trigger: "blur" }
        ],
        applicationDate: [
          { required: true, message: "申请时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询赛事报销列表 */
    getList() {
      this.loading = true;
      listParticipants(this.queryParams).then(response => {
        this.participantsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        phoneNumber: null,
        competitionName: null,
        awardLevel: null,
        advisorName: null,
        paymentCode: null,
        applicationDate: null,
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加赛事报销";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getParticipants(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛事报销";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateParticipants(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addParticipants(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除赛事报销编号为"' + ids + '"的数据项？').then(function() {
        return delParticipants(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/participants/export', {
        ...this.queryParams
      }, `participants_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
