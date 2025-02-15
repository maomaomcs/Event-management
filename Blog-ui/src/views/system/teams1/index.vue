<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="赛事id" prop="eventId">
        <el-input
          v-model="queryParams.eventId"
          placeholder="请输入赛事id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="队伍名称" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入队伍名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="队长学号" prop="leaderId">
        <el-input
          v-model="queryParams.leaderId"
          placeholder="请输入队长学号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成员人数" prop="membersCount">
        <el-input
          v-model="queryParams.membersCount"
          placeholder="请输入成员人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updatedAt">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedAt"
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
          v-hasPermi="['system:teams1:add']"
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
          v-hasPermi="['system:teams1:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"  key=""
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:teams1:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:teams1:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teams1List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="主键id" align="center" prop="id" />
        <el-table-column label="赛事id" align="center" prop="eventId" /> -->
      <el-table-column label="队伍名称" align="center" prop="teamName" />
      <el-table-column label="队长学号" align="center" prop="leaderId" />
      <el-table-column label="成员人数" align="center" prop="membersCount" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updatedAt" width="180">
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
      v-hasPermi="['system:teams1:edit']"
    >修改</el-button>
    <el-button
      size="mini"
      type="text"
      icon="el-icon-delete"
      @click="handleDelete(scope.row)"
      v-hasPermi="['system:teams1:remove']"
    >删除</el-button>
    <el-button
        size="mini"
        type="text"
        icon="el-icon-plus"
        :disabled="scope.row.membersCount <= maxMembers" 
        @click="showAddMemberDialog(scope.row)"
      >
        添加队员
      </el-button>
        <el-button
          size="mini"
          type="primary"
          icon="el-icon-user"
          @click="showMembers(scope.row)"
        >查看队员</el-button>
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

    <!-- 添加或修改队伍对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="赛事id" prop="eventId" disabled>
          <el-input v-model="form.id" disabled/>
        </el-form-item> -->
        <el-form-item label="队伍名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入队伍名称" />
        </el-form-item>
        <el-form-item label="队长学号" prop="leaderId">
          <el-input v-model="form.leaderId" placeholder="请输入队长学号" />
        </el-form-item>
        <el-form-item label="成员人数" prop="membersCount">
          <el-input v-model="form.membersCount" placeholder="请输入成员人数" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable size="small"
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="updatedAt">
          <el-date-picker clearable size="small"
            v-model="form.updatedAt"
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
        <!-- 新增队员的弹窗 -->
                <el-dialog 
          :title="title" 
          :visible.sync="openAddMemberDialog" 
          width="500px" 
          append-to-body>
          <el-form ref="form1" :model="form1" :rules="rules" label-width="80px">
            <el-form-item label="队伍id" prop="teamId">
              <el-input v-model="form1.teamId" placeholder="请输入队伍id" disabled/>
            </el-form-item>
            <el-form-item label="成员学号" prop="userId">
              <el-input v-model="form1.userId" placeholder="请输入成员学号" />
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form1.name" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="角色" prop="role">
              <el-input v-model="form1.role" placeholder="请输角色" />
            </el-form-item>
            <el-form-item label="专业" prop="major">
              <el-input v-model="form1.major" placeholder="请输入专业" />
            </el-form-item>
            <el-form-item label="创建时间" prop="createdAt">
              <el-date-picker clearable size="small"
                v-model="form1.createdAt"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择创建时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="修改时间" prop="updatedAt">
              <el-date-picker clearable size="small"
                v-model="form1.updatedAt"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择修改时间">
              </el-date-picker>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm1">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
        <!-- 队员表格数据 -->
                  <el-dialog
            title="队员信息"
            :visible.sync="showMembersDialog"
            width="600px"
          >
            <div>
              <p><strong>队伍名称：</strong><span style="color: black; font-size: large;">{{ currentTeamName }}</span></p> 
              <el-table :data="currentMembers" border style="width: 100%">
                <el-table-column prop="userId" label="用户ID" width="120" />
                <el-table-column prop="name" label="姓名" />
                <el-table-column prop="role" label="角色" />
                <el-table-column prop="major" label="专业" />
                <el-table-column prop="createdAt" label="加入时间" />
              </el-table>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="showMembersDialog = false">关闭</el-button>
            </span>
          </el-dialog>
  </div>  
</template>

  <script>
  import { listTeams1, getTeams1, delTeams1, addTeams1, updateTeams1 } from "@/api/system/teams1";
  import { listMembers1, getMembers1, delMembers1, addMembers1, updateMembers1 } from "@/api/system/members1";
  import { listGRcompetitions3, addGRcompetitions3, updateGRcompetitions3 } from "@/api/system/GRcompetitions3";
  export default {
    name: "Teams1",
    data() {
      return {
        teams: [], // 存储队伍数据
        teamMembersCount: {}, // 存储每个队伍的成员数量，队伍ID为键，数量为值
        currentTeamId: '',
        maxMembers: 1, // 默认最大成员数，可以根据实际情况调整
        showMembersDialog: false, // 控制弹窗显示
        currentMembers: [],       // 当前队伍的队员数据
        currentTeamName: '',      // 当前队伍名称（可选）
        openAddMemberDialog: false,
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
        // 队伍表格数据
        teams1List: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          eventId: null,
          teamName: '',
          leaderId: null,
          membersCount: null,
          createdAt: null,
          updatedAt: null
        },
        // 表单参数
        form: {},
        form1: {
          teamId: "",
          userId: "",
          name: "",
          role: "",
          major: "",
          createdAt: "",
          updatedAt: ""
        },
        // 表单校验
        rules: {
          eventId: [
            { required: true, message: "赛事id不能为空", trigger: "blur" }
          ],
          teamName: [
            { required: true, message: "队伍名称不能为空", trigger: "blur" }
          ],
          leaderId: [
            { required: true, message: "队长学号不能为空", trigger: "blur" }
          ],
          membersCount: [
            { required: true, message: "成员人数不能为空", trigger: "blur" }
          ],
          createdAt: [
            { required: true, message: "创建时间不能为空", trigger: "blur" }
          ],
          updatedAt: [
            { required: true, message: "修改时间不能为空", trigger: "blur" }
          ],
          userId: [{ required: true, message: "成员学号不能为空", trigger: "blur" }],
          name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
          role: [{ required: true, message: "角色不能为空", trigger: "change" }],
          major: [{ required: true, message: "专业不能为空", trigger: "blur" }],
        }
      };
    },
    created() {
      this.getList();
      },
    mounted() {
    this.fetchTeams();  // 确保调用了 fetchTeams
    },
    methods: {  
    // 显示添加队员对话框
  showAddMemberDialog(team) {
    // 你的添加队员逻辑
    console.log(`队伍 ${team.teamName} 添加队员`);
  },
    /** 查询队伍列表 */
    getList() {
      this.loading = true;
      listTeams1(this.queryParams).then(response => {
        this.teams1List = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    async showMembers(team) {
  this.currentTeamName = team.teamName; // Set the team name
  try {
    const response = await listMembers1({ teamId: team.id }); // Ensure `team.id` is valid
    console.log("显示队伍成员列表");
    console.log("Team:", team);
    console.log("Response Data:", response); // Check the response data
    if (response.code === 200) { // Ensure response code is 200
      this.currentMembers = response.rows || []; // Ensure the data is assigned properly
      console.log("当前队员数据:", this.currentMembers); // Check if data is being set
      this.showMembersDialog = true; // Open the members dialog
    } else {
      this.$message.error("获取队员信息失败");
    }
  } catch (error) {
    console.error("Error loading members:", error); // Print the error
    this.$message.error("加载队员数据出错");
  }
},
showAddMemberDialog(team) {
  this.form1 = {
    teamId: team.id || "", // Ensure competitionId exists in the `team` object
    userId: "",
    name: "",
    role: "队员", // Default role
    major: "",
    createdAt: new Date().toISOString().split("T")[0],
    updatedAt: new Date().toISOString().split("T")[0]
  };
  this.currentTeamId = team.id; // Set the current team ID
  this.openAddMemberDialog = true; // This will open the dialog
},
// 提交表单
async submitForm1() {
  this.isSubmitting = true;
  this.$refs["form1"].validate(async (valid) => {
    if (valid) {
      try {
        console.log("准备提交表单数据:", this.form1);
        // 提交到 addMembers1 接口
        const memberResponse = await addMembers1(this.form1);
        console.log("addMembers1 返回:", memberResponse);
        if (memberResponse.code !== 200) {
          throw new Error("addMembers1 返回错误: " + memberResponse.msg);
        }
        // 如果成功
        this.$message.success("数据提交成功!");
        this.openAddMemberDialog = false;
      } catch (error) {
        console.error("提交失败:", error);
        this.$message.error("提交失败，请重试！原因：" + error.message);
      } finally {
        this.isSubmitting = false;
      }
    } else {
      this.isSubmitting = false;
    }
  });
},

    // 取消按钮
    cancel() {
      this.openAddMemberDialog = false; // 关闭添加队员弹窗
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        eventId: null,
        teamName: '',
        leaderId: null,
        membersCount: null,
        createdAt: null,
        updatedAt: null,
        teamId: "",
        userId: "",
        name: "",
        role: "",
        major: ""
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
      this.title = "添加队伍";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTeams1(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改队伍";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTeams1(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeams1(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除队伍编号为"' + ids + '"的数据项？').then(function() {
        return delTeams1(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/teams1/export', {
        ...this.queryParams
      }, `teams1_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
