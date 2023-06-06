<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <!-- <el-form-item label="需求名称" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入需求名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="合同号" prop="contractNumber">
        <el-input v-model="queryParams.contractNumber" placeholder="请输入合同号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="项目类别" prop="projectCategory">
        <el-input v-model="queryParams.projectCategory" placeholder="请输入项目类别" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="类型" prop="projectType">
        <el-input
          v-model="queryParams.projectType"
          placeholder="请输入项目类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="requirementStatus">
        <el-input
          v-model="queryParams.enterpriseNumber"
          placeholder="请输入项目状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="发布时间" prop="requirementReleaseTime">
        <el-date-picker clearable v-model="queryParams.requirementReleaseTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery"
          >搜索</el-button
        >
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
          v-hasPermi="['kyfz:requirement:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['kyfz:requirement:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['kyfz:requirement:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:requirement:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="requirementList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="需求名称" align="center" prop="projectName" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="项目类别" align="center" prop="projectCategory" />
      <el-table-column label="项目类型" align="center" prop="projectType" />
      <el-table-column label="需求状态" align="center" prop="requirementStatus" />
      <el-table-column label="企业编号" align="center" prop="enterpriseNumber" />
      <el-table-column
        label="发布时间"
        align="center"
        prop="requirementReleaseTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requirementReleaseTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:requirement:edit']"
            >详情</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:requirement:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改需求管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <div class="toggle-edit-button" style="position: absolute; top: 25px; right: 80px">
        <el-button @click="toggleEdit">{{ editable ? "保存" : "编辑" }}</el-button>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="需求编号" prop="requirementId">
          <el-input
            v-model="form.requirementId"
            placeholder="请输入需求编号"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="需求名称" prop="projectName">
          <el-input
            v-model="form.projectName"
            placeholder="请输入需求名称"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="需求关键词" prop="requirementKeywords">
          <el-input
            v-model="form.requirementKeywords"
            type="textarea"
            placeholder="请输入内容"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="需求类型" prop="projectCategory">
          <el-input
            v-model="form.projectCategory"
            placeholder="请输入需求类型"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="需求发布时间" prop="requirementReleaseTime">
          <el-date-picker
            clearable
            v-model="form.requirementReleaseTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择需求发布时间"
            :readonly="editable"
            ref="idInput"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="需求状态" prop="requirementStatus">
          <el-input
            v-model="form.requirementStatus"
            placeholder="请输入需求状态"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="公司名称" prop="enterpriseName">
          <el-input
            v-model="form.enterpriseName"
            placeholder="请输入公司名称"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="公司简介" prop="enterpriseDescribe">
          <el-input
            v-model="form.enterpriseDescribe"
            placeholder="请输入公司简介"
            :readonly="editable"
            ref="idInput"
          />
        </el-form-item>
        <el-form-item label="注册资本" prop="registeredCapital">
          <el-input
            v-model="form.registeredCapital"
            placeholder="请输入注册资本"
            :readonly="editable"
            ref="idInput"
          />
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
import {
  addRequirement,
  delRequirement,
  getRequirement,
  listRequirement,
  updateRequirement,
} from "@/api/kyfz/requirement";

export default {
  name: "Requirement",
  data() {
    return {
      editable: false,
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
      // 需求管理表格数据
      requirementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        contractNumber: null,
        client: null,
        projectLeader: null,
        projectLeaderJobNumber: null,
        projectMembers: null,
        supportUnit: null,
        supportUnitNumber: null,
        projectCategory: null,
        projectType: null,
        projectClassification: null,
        projectNature: null,
        serviceNationalEconomy: null,
        nationalEconomySubIndustry: null,
        socioEconomicObjective: null,
        startProjectTime: null,
        endProjectTime: null,
        totalBudget: null,
        contractType: null,
        firstDiscipline: null,
        secondaryDiscipline: null,
        projectLeaderUnit: null,
        enterpriseNumber: null,
        campusMemberAccount: null,
        requirementStatus: null,
        creatBy: null,
        requirementKeywords: null,
        requirementDescription: null,
        requirementReleaseTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 切换编辑状态 */
    toggleEdit() {
      this.editable = !this.editable;
      this.$nextTick(() => {
        // 延迟调用，确保DOM更新完成
        const input = this.$refs.idInput.$el.querySelector("input");
        input.setAttribute("readonly", this.editable);
      });
    },

    /** 查询需求管理列表 */
    getList() {
      this.loading = true;
      listRequirement(this.queryParams).then((response) => {
        this.requirementList = response.rows;
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
        requirementId: null,
        projectName: null,
        contractNumber: null,
        client: null,
        projectLeader: null,
        projectLeaderJobNumber: null,
        projectMembers: null,
        supportUnit: null,
        supportUnitNumber: null,
        projectCategory: null,
        projectType: null,
        projectClassification: null,
        projectNature: null,
        serviceNationalEconomy: null,
        nationalEconomySubIndustry: null,
        socioEconomicObjective: null,
        startProjectTime: null,
        endProjectTime: null,
        totalBudget: null,
        contractType: null,
        firstDiscipline: null,
        secondaryDiscipline: null,
        projectLeaderUnit: null,
        enterpriseNumber: null,
        campusMemberAccount: null,
        requirementStatus: null,
        creatBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        requirementKeywords: null,
        requirementDescription: null,
        requirementReleaseTime: null,
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
      this.ids = selection.map((item) => item.requirementId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加需求管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const requirementId = row.requirementId || this.ids;
      getRequirement(requirementId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "需求详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.requirementId != null) {
            updateRequirement(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRequirement(this.form).then((response) => {
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
      const requirementIds = row.requirementId || this.ids;
      this.$modal
        .confirm('是否确认删除需求管理编号为"' + requirementIds + '"的数据项？')
        .then(function () {
          return delRequirement(requirementIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "kyfz/requirement/export",
        {
          ...this.queryParams,
        },
        `requirement_${new Date().getTime()}.xlsx`
      );
    },
  },
  mounted() {
    // 初始时设置readonly属性
    this.$nextTick(() => {
      const input = this.$refs.idInput.$el.querySelector("input");
      input.setAttribute("readonly", this.editable);
    });
  },
};
</script>
