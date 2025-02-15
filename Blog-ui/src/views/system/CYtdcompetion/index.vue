<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="startDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择${comment}">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="${comment}" prop="endDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择${comment}">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="${comment}" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="teamCount">
        <el-input
          v-model="queryParams.teamCount"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="createdAt">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择${comment}">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="${comment}" prop="updatedAt">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择${comment}">
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
          v-hasPermi="['system:CYtdcompetion:add']"
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
          v-hasPermi="['system:CYtdcompetion:edit']"
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
          v-hasPermi="['system:CYtdcompetion:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:CYtdcompetion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="CYtdcompetionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="competitionId" />
      <el-table-column label="${comment}" align="center" prop="competitionName" />
      <el-table-column label="${comment}" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="location" />
      <el-table-column label="${comment}" align="center" prop="teamCount" />
      <el-table-column label="${comment}" align="center" prop="description" />
      <el-table-column label="${comment}" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:CYtdcompetion:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:CYtdcompetion:remove']"
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

    <!-- 添加或修改成员团队赛事对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="startDate">
          <el-date-picker clearable size="small"
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择${comment}">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="endDate">
          <el-date-picker clearable size="small"
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择${comment}">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="location">
          <el-input v-model="form.location" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="teamCount">
          <el-input v-model="form.teamCount" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="${comment}" prop="createdAt">
          <el-date-picker clearable size="small"
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择${comment}">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="updatedAt">
          <el-date-picker clearable size="small"
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择${comment}">
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
import { listCYtdcompetion, getCYtdcompetion, delCYtdcompetion, addCYtdcompetion, updateCYtdcompetion } from "@/api/system/CYtdcompetion";

export default {
  name: "CYtdcompetion",
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
      // 成员团队赛事表格数据
      CYtdcompetionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionName: null,
        startDate: null,
        endDate: null,
        location: null,
        teamCount: null,
        description: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        competitionName: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询成员团队赛事列表 */
    getList() {
      this.loading = true;
      listCYtdcompetion(this.queryParams).then(response => {
        this.CYtdcompetionList = response.rows;
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
        competitionId: null,
        competitionName: null,
        startDate: null,
        endDate: null,
        location: null,
        teamCount: null,
        description: null,
        createdAt: null,
        updatedAt: null
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
      this.ids = selection.map(item => item.competitionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成员团队赛事";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const competitionId = row.competitionId || this.ids
      getCYtdcompetion(competitionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成员团队赛事";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.competitionId != null) {
            updateCYtdcompetion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCYtdcompetion(this.form).then(response => {
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
      const competitionIds = row.competitionId || this.ids;
      this.$modal.confirm('是否确认删除成员团队赛事编号为"' + competitionIds + '"的数据项？').then(function() {
        return delCYtdcompetion(competitionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/CYtdcompetion/export', {
        ...this.queryParams
      }, `CYtdcompetion_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
