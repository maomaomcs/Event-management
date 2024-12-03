<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
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
      <el-form-item label="电话号" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input
          v-model="queryParams.major"
          placeholder="请输入专业"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级" prop="classes">
        <el-input
          v-model="queryParams.classes"
          placeholder="请输入班级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
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
          v-hasPermi="['system:users:add']"
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
          v-hasPermi="['system:users:edit']"
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
          v-hasPermi="['system:users:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:users:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="usersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="电话号" align="center" prop="phoneNumber" />
      <el-table-column label="专业" align="center" prop="major" />
      <el-table-column label="班级" align="center" prop="classes" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:users:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:users:remove']"
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

    <!-- 添加或修改人员列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="电话号" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话号" />
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="请输入专业" />
        </el-form-item>
        <el-form-item label="班级" prop="classes">
          <el-input v-model="form.classes" placeholder="请输入班级" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable size="small"
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="角色" prop="roles">
          <el-select v-model="form.roles" multiple placeholder="选择角色">
            <el-option
              v-for="role in roleList"
              :key="role.roleId"
              :label="role.roleName"
              :value="role.roleId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="选择状态">
            <el-option label="启用" value="0"></el-option>
            <el-option label="停用" value="1"></el-option>
          </el-select>
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
import { listUsers, getUsers, delUsers, addUsers, updateUsers } from "@/api/system/users";
import { listRole, getRole, delRole, addRole, updateRole, dataScope, changeRoleStatus } from "@/api/system/role";
import { treeselect as menuTreeselect, roleMenuTreeselect } from "@/api/system/menu";
import { treeselect as deptTreeselect, roleDeptTreeselect } from "@/api/system/dept";

export default {
  name: "Users",
  data() {
    return {
      // 用户管理
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      usersList: [],
      title: "",
      open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        studentId: null,
        phoneNumber: null,
        major: null,
        classes: null,
        password: null,
        createdAt: null
      },
      form: {},
      rules: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ]
      },

      // 角色管理
      roleList: [],
      menuOptions: [],
      deptOptions: [],
      openRoleDialog: false,
      roleForm: {},
      roleRules: {
        roleName: [
          { required: true, message: "角色名称不能为空", trigger: "blur" }
        ],
        roleKey: [
          { required: true, message: "权限字符不能为空", trigger: "blur" }
        ],
        roleSort: [
          { required: true, message: "角色顺序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getRoleList();  // Fetch roles when the component is created
  },
  methods: {
    // 用户列表查询
    getList() {
      this.loading = true;
      listUsers(this.queryParams).then(response => {
        this.usersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 角色列表查询
    getRoleList() {
      this.loading = true;
      listRole(this.queryParams).then(response => {
        this.roleList = response.rows;
        this.loading = false;
      });
    },
    // 查询菜单树结构
    getMenuTreeselect() {
      menuTreeselect().then(response => {
        this.menuOptions = response.data;
      });
    },
    // 查询部门树结构
    getDeptTreeselect() {
      deptTreeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 角色状态修改
// 角色状态修改
handleSelectionChange(val) {
      this.ids = val.map(item => item.id);
    },
    handleStatusChange(row) {
      const status = row.status === 0 ? 1 : 0;
      updateUsersStatus([row.id], status)
        .then(() => {
          this.$message.success(`用户状态已${status === 0 ? '启用' : '停用'}`);
        })
        .catch(() => {
          this.$message.error("状态更新失败");
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
        studentId: null,
        phoneNumber: null,
        major: null,
        classes: null,
        password: null,
        createdAt: null
      };
      this.resetForm("form");
    },
    // 搜索按钮操作
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 重置按钮操作
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 新增用户
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加人员列表";
    },
    // 修改用户
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getUsers(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改人员列表";
      });
    },
    // 提交用户表单
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUsers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUsers(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 删除用户
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除人员列表编号为"' + ids + '"的数据项？').then(function() {
        return delUsers(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 导出用户
    handleExport() {
      this.download('system/users/export', {
        ...this.queryParams
      }, `users_${new Date().getTime()}.xlsx`)
    },

    // 角色表单操作
    handleRoleAdd() {
      this.roleForm = {}; // Clear form before adding
      this.openRoleDialog = true;
      this.title = "添加角色";
    },
    handleRoleUpdate(role) {
      this.roleForm = { ...role }; // Fill form with role data
      this.openRoleDialog = true;
      this.title = "修改角色";
    },
    handleRoleSubmit() {
      this.$refs['roleForm'].validate(valid => {
        if (valid) {
          if (this.roleForm.roleId) {
            updateRole(this.roleForm).then(() => {
              this.$modal.msgSuccess("角色更新成功");
              this.openRoleDialog = false;
              this.getRoleList();
            });
          } else {
            addRole(this.roleForm).then(() => {
              this.$modal.msgSuccess("角色创建成功");
              this.openRoleDialog = false;
              this.getRoleList();
            });
          }
        }
      });
    },
    handleRoleDelete(role) {
      this.$modal.confirm('确认要删除角色："' + role.roleName + '"?').then(() => {
        return delRole(role.roleId);
      }).then(() => {
        this.$modal.msgSuccess("删除成功");
        this.getRoleList();
      });
    },
  }
};
</script>
