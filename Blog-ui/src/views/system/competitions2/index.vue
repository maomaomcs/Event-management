<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="赛事名称" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入赛事名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛事级别" prop="organizingBody">
        <el-input
          v-model="queryParams.organizingBody"
          placeholder="请输入赛事级别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛事费用" prop="participationFee">
        <el-input
          v-model="queryParams.participationFee"
          placeholder="请输入赛事费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛事类别" prop="competitionType">
        <el-input
          v-model="queryParams.competitionType"
          placeholder="请输入赛事类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始报名" prop="registrationDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.registrationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始报名">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截止报名" prop="endDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择截止报名">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="开始比赛" prop="competitionStartTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.competitionStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始比赛">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截止比赛" prop="competitionEndTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.competitionEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择截止比赛">
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
          v-hasPermi="['system:competitions2:add']"
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
          v-hasPermi="['system:competitions2:edit']"
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
          v-hasPermi="['system:competitions2:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitions2:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitions2List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="赛事id" align="center" prop="id" />
      <el-table-column label="赛事名称" align="center" prop="competitionName" />
      <el-table-column label="赛事级别" align="center" prop="organizingBody" />
      <el-table-column label="赛事费用" align="center" prop="participationFee" />
      <el-table-column label="赛事类别" align="center" prop="competitionType" />
      <el-table-column label="开始报名" align="center" prop="registrationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止报名" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始比赛时间" align="center" prop="competitionStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止比赛时间" align="center" prop="competitionEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionEndTime, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['system:competitions2:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitions2:remove']"
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

    <!-- 添加或修改赛事登记对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事名称" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入赛事名称" />
        </el-form-item>
        <el-form-item label="赛事级别" prop="organizingBody">
          <el-input v-model="form.organizingBody" placeholder="请输入赛事级别" />
        </el-form-item>
        <el-form-item label="赛事费用" prop="participationFee">
          <el-input v-model="form.participationFee" placeholder="请输入赛事费用" />
        </el-form-item>
        <el-form-item label="赛事类别" prop="competitionType">
          <el-input v-model="form.competitionType" placeholder="请输入赛事类别" />
        </el-form-item>
        <el-form-item label="开始报名" prop="registrationDate">
          <el-date-picker clearable size="small"
            v-model="form.registrationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始报名">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止报名" prop="endDate">
          <el-date-picker clearable size="small"
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择截止报名">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始比赛" prop="competitionStartTime">
          <el-date-picker clearable size="small"
            v-model="form.competitionStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始比赛">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止比赛" prop="competitionEndTime">
          <el-date-picker clearable size="small"
            v-model="form.competitionEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择截止比赛">
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
import { listCompetitions2, getCompetitions2, delCompetitions2, addCompetitions2, updateCompetitions2 } from "@/api/system/competitions2";

export default {
  name: "Competitions2",
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
      // 赛事登记表格数据
      competitions2List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionName: null,
        organizingBody: null,
        participationFee: null,
        competitionType: null,
        registrationDate: null,
        endDate: null,
        competitionStartTime: null,
        competitionEndTime: null,
        status: null,
        actions: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        competitionName: [
          { required: true, message: "赛事名称不能为空", trigger: "blur" }
        ],
        organizingBody: [
          { required: true, message: "赛事级别不能为空", trigger: "blur" }
        ],
        participationFee: [
          { required: true, message: "赛事费用不能为空", trigger: "blur" }
        ],
        competitionType: [
          { required: true, message: "赛事类别不能为空", trigger: "blur" }
        ],
        registrationDate: [
          { required: true, message: "开始报名不能为空", trigger: "blur" }
        ],
        endDate: [
          { required: true, message: "截止报名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询赛事登记列表 */
    getList() {
      this.loading = true;
      listCompetitions2(this.queryParams).then(response => {
        this.competitions2List = response.rows;
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
        competitionName: null,
        organizingBody: null,
        participationFee: null,
        competitionType: null,
        registrationDate: null,
        endDate: null,
        competitionStartTime: null,
        competitionEndTime: null,
        status: "0",
        actions: null
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
      this.title = "添加赛事登记";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitions2(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛事登记";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitions2(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitions2(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛事登记编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitions2(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitions2/export', {
        ...this.queryParams
      }, `competitions2_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
