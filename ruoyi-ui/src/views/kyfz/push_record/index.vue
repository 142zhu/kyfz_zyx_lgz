<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业信用代码" prop="enterpriseCreditCode">
        <el-input
          v-model="queryParams.enterpriseCreditCode"
          placeholder="请输入企业信用代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业注册资本" prop="registeredCapital">
        <el-input
          v-model="queryParams.registeredCapital"
          placeholder="请输入企业注册资本"
          clearable
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
          v-hasPermi="['kyfz:push_record:add']"
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
          v-hasPermi="['kyfz:push_record:edit']"
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
          v-hasPermi="['kyfz:push_record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:push_record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="push_recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="企业信息表的唯一表示符号" align="center" prop="enterpriseId" />
      <el-table-column label="企业名" align="center" prop="enterpriseName" />
      <el-table-column label="企业信用代码" align="center" prop="enterpriseCreditCode" />
      <el-table-column label="企业描述" align="center" prop="enterpriseDescribe" />
      <el-table-column label="企业注册资本" align="center" prop="registeredCapital" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:push_record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:push_record:remove']"
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

    <!-- 添加或修改推送记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业名" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业名" />
        </el-form-item>
        <el-form-item label="企业信用代码" prop="enterpriseCreditCode">
          <el-input v-model="form.enterpriseCreditCode" placeholder="请输入企业信用代码" />
        </el-form-item>
        <el-form-item label="企业描述" prop="enterpriseDescribe">
          <el-input v-model="form.enterpriseDescribe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="企业注册资本" prop="registeredCapital">
          <el-input v-model="form.registeredCapital" placeholder="请输入企业注册资本" />
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
import { listPush_record, getPush_record, delPush_record, addPush_record, updatePush_record } from "@/api/kyfz/push_record";

export default {
  name: "Push_record",
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
      // 推送记录表格数据
      push_recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enterpriseName: null,
        enterpriseCreditCode: null,
        enterpriseDescribe: null,
        registeredCapital: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询推送记录列表 */
    getList() {
      this.loading = true;
      listPush_record(this.queryParams).then(response => {
        this.push_recordList = response.rows;
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
        enterpriseId: null,
        enterpriseName: null,
        enterpriseCreditCode: null,
        enterpriseDescribe: null,
        registeredCapital: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.enterpriseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加推送记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const enterpriseId = row.enterpriseId || this.ids
      getPush_record(enterpriseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改推送记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.enterpriseId != null) {
            updatePush_record(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPush_record(this.form).then(response => {
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
      const enterpriseIds = row.enterpriseId || this.ids;
      this.$modal.confirm('是否确认删除推送记录编号为"' + enterpriseIds + '"的数据项？').then(function() {
        return delPush_record(enterpriseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/push_record/export', {
        ...this.queryParams
      }, `push_record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
