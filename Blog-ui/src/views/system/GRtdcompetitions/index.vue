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
      <!-- <el-form-item label="赛事id" prop="competitionId">
        <el-input
          v-model="queryParams.competitionId"
          placeholder="请输入赛事id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="赛事类别" prop="competitionCategory">
        <el-input
          v-model="queryParams.competitionCategory"
          placeholder="请输入赛事类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="团队名称" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入团队名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="队长姓名" prop="leaderName">
        <el-input
          v-model="queryParams.leaderName"
          placeholder="请输入队长姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.GRcompertion"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="指导老师" prop="instructors">
        <el-input
          v-model="queryParams.instructors"
          placeholder="请输入指导老师"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="获奖信息" prop="award">
        <el-input
          v-model="queryParams.award"
          placeholder="请输入获奖信息"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:GRtdcompetitions:add']"
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
          v-hasPermi="['system:GRtdcompetitions:edit']"
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
          v-hasPermi="['system:GRtdcompetitions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:GRtdcompetitions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="GRtdcompetitionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="赛事名称" align="center" prop="competitionName" />
      <!-- <el-table-column label="赛事id" align="center" prop="competitionId" /> -->
      <el-table-column label="赛事类别" align="center" prop="competitionCategory" />
      <el-table-column label="团队名称" align="center" prop="teamName" />
      <el-table-column label="队长姓名" align="center" prop="leaderName" />
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.GRcompertion" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="指导老师" align="center" prop="instructors" />
      <el-table-column label="获奖信息" align="center" prop="award" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:GRtdcompetitions:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:GRtdcompetitions:remove']"
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

    <!-- 添加或修改个人团队赛事对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事名称" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入赛事名称" />
        </el-form-item>
        <el-form-item label="赛事id" prop="competitionId">
          <el-input v-model="form.competitionId" placeholder="请输入赛事id" />
        </el-form-item>
        <el-form-item label="赛事类别" prop="competitionCategory">
          <el-input v-model="form.competitionCategory" placeholder="请输入赛事类别" />
        </el-form-item>
        <el-form-item label="团队名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入团队名称" />
        </el-form-item>
        <el-form-item label="队长姓名" prop="leaderName">
          <el-input v-model="form.leaderName" placeholder="请输入队长姓名" />
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.GRcompertion"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="指导老师" prop="instructors">
          <el-input v-model="form.instructors" placeholder="请输入指导老师" />
        </el-form-item>
        <el-form-item label="获奖信息" prop="award">
          <el-input v-model="form.award" placeholder="请输入获奖信息" />
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
import { listGRtdcompetitions, getGRtdcompetitions, delGRtdcompetitions, addGRtdcompetitions, updateGRtdcompetitions } from "@/api/system/GRtdcompetitions";

export default {
  name: "GRtdcompetitions",
  dicts: ['GRcompertion'],
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
      // 个人团队赛事表格数据
      GRtdcompetitionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionName: null,
        competitionId: null,
        competitionCategory: null,
        teamName: null,
        leaderName: null,
        studentId: null,
        status: null,
        instructors: null,
        award: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        competitionName: [
          { required: true, message: "赛事名称不能为空", trigger: "blur" }
        ],
        competitionCategory: [
          { required: true, message: "赛事类别不能为空", trigger: "blur" }
        ],
        teamName: [
          { required: true, message: "团队名称不能为空", trigger: "blur" }
        ],
        leaderName: [
          { required: true, message: "队长姓名不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询个人团队赛事列表 */
    getList() {
      this.loading = true;
      listGRtdcompetitions(this.queryParams).then(response => {
        this.GRtdcompetitionsList = response.rows;
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
        competitionId: null,
        competitionCategory: null,
        teamName: null,
        leaderName: null,
        studentId: null,
        status: "0",
        instructors: null,
        award: null
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
      this.title = "添加个人团队赛事";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGRtdcompetitions(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改个人团队赛事";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGRtdcompetitions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGRtdcompetitions(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除个人团队赛事编号为"' + ids + '"的数据项？').then(function() {
        return delGRtdcompetitions(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/GRtdcompetitions/export', {
        ...this.queryParams
      }, `GRtdcompetitions_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
