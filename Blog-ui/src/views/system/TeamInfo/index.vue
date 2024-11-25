<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="赛事名称" prop="eventname">
        <el-input
          v-model="queryParams.eventname"
          placeholder="请输入赛事名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="队伍名称" prop="teamname">
        <el-input
          v-model="queryParams.teamname"
          placeholder="请输入队伍名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdat">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdat"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updatedat">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedat"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择修改时间">
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
          v-hasPermi="['system:TeamInfo:add']"
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
          v-hasPermi="['system:TeamInfo:edit']"
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
          v-hasPermi="['system:TeamInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:TeamInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="TeamInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="队伍id" align="center" prop="teamid" />
      <el-table-column label="赛事名称" align="center" prop="eventname" />
      <el-table-column label="队伍名称" align="center" prop="teamname" />
      <el-table-column label="队伍成员" align="center" prop="teammembers" />
      <el-table-column label="创建时间" align="center" prop="createdat" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdat, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updatedat" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedat, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:TeamInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:TeamInfo:remove']"
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

    <!-- 添加或修改队伍信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事名称" prop="eventname">
          <el-input v-model="form.eventname" placeholder="请输入赛事名称" />
        </el-form-item>
        <el-form-item label="队伍名称" prop="teamname">
          <el-input v-model="form.teamname" placeholder="请输入队伍名称" />
        </el-form-item>
        <el-form-item label="队伍成员" prop="teammembers">
          <el-input v-model="form.teammembers" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdat">
          <el-date-picker clearable size="small"
            v-model="form.createdat"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="updatedat">
          <el-date-picker clearable size="small"
            v-model="form.updatedat"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择修改时间">
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
import { listTeamInfo, getTeamInfo, delTeamInfo, addTeamInfo, updateTeamInfo } from "@/api/system/TeamInfo";

export default {
  name: "TeamInfo",
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
      // 队伍信息表格数据
      TeamInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventname: null,
        teamname: null,
        teammembers: null,
        createdat: null,
        updatedat: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        eventname: [
          { required: true, message: "赛事名称不能为空", trigger: "blur" }
        ],
        teamname: [
          { required: true, message: "队伍名称不能为空", trigger: "blur" }
        ],
        teammembers: [
          { required: true, message: "队伍成员不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询队伍信息列表 */
    getList() {
      this.loading = true;
      listTeamInfo(this.queryParams).then(response => {
        this.TeamInfoList = response.rows;
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
        teamid: null,
        eventname: null,
        teamname: null,
        teammembers: null,
        createdat: null,
        updatedat: null
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
      this.ids = selection.map(item => item.teamid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加队伍信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teamid = row.teamid || this.ids
      getTeamInfo(teamid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改队伍信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teamid != null) {
            updateTeamInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeamInfo(this.form).then(response => {
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
      const teamids = row.teamid || this.ids;
      this.$modal.confirm('是否确认删除队伍信息编号为"' + teamids + '"的数据项？').then(function() {
        return delTeamInfo(teamids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/TeamInfo/export', {
        ...this.queryParams
      }, `TeamInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
