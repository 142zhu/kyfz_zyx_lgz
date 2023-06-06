<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="匹配编号" prop="matchId">
        <el-input v-model="queryParams.matchId" placeholder="请输入匹配编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="需求" prop="projectName">
        <el-input v-model="queryParams.matchId" placeholder="请输入需求" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="专家" prop="expertName">
        <el-input v-model="queryParams.matchId" placeholder="请输入专家名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="反馈状态" prop="feebback">
        <el-input v-model="queryParams.feebback" placeholder="请输入反馈状态" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="推送时间" prop="pushTime">
        <el-date-picker clearable v-model="queryParams.pushTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择推送时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['kyfz:record:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['kyfz:record:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="匹配编号" align="center" prop="matchId" />
      <!-- 按照需求连接的两行 -->
      <el-table-column label="需求" align="center" prop="projectName" />
      <el-table-column label="专家" align="center" prop="expertName" />
      <el-table-column label="反馈状态" align="center" prop="feebback" />
      <el-table-column label="推送时间" align="center" prop="pushTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pushTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:record:edit']">详细</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改推送记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="匹配id" prop="matchId">
          <el-input v-model="form.matchId" placeholder="请输入匹配id" />
        </el-form-item>
        <el-form-item label="推送时间" prop="pushTime">
          <el-date-picker clearable v-model="form.pushTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择推送时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="反馈状态" prop="feebback">
          <el-input v-model="form.feebback" placeholder="请输入反馈状态" />
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
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/kyfz/push_record";

export default {
  name: "Record",
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
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        matchId: null,
        pushTime: null,
        feebback: null,
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
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
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
        pushId: null,
        matchId: null,
        pushTime: null,
        feebback: null,
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
      this.ids = selection.map(item => item.pushId)
      this.single = selection.length !== 1
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
      const pushId = row.pushId || this.ids
      getRecord(pushId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改推送记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pushId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
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
      const pushIds = row.pushId || this.ids;
      this.$modal.confirm('是否确认删除推送记录编号为"' + pushIds + '"的数据项？').then(function () {
        return delRecord(pushIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
