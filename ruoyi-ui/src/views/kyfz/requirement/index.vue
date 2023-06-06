<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同号" prop="contractNumber">
        <el-input
          v-model="queryParams.contractNumber"
          placeholder="请输入合同号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="委托单位" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入委托单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人" prop="projectLeader">
        <el-input
          v-model="queryParams.projectLeader"
          placeholder="请输入项目负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人工号" prop="projectLeaderJobNumber">
        <el-input
          v-model="queryParams.projectLeaderJobNumber"
          placeholder="请输入负责人工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目成员" prop="projectMembers">
        <el-input
          v-model="queryParams.projectMembers"
          placeholder="请输入项目成员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="依托单位" prop="supportUnit">
        <el-input
          v-model="queryParams.supportUnit"
          placeholder="请输入依托单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="依托单位代码" prop="supportUnitNumber">
        <el-input
          v-model="queryParams.supportUnitNumber"
          placeholder="请输入依托单位代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目类别" prop="projectCategory">
        <el-input
          v-model="queryParams.projectCategory"
          placeholder="请输入项目类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目分类" prop="projectClassification">
        <el-input
          v-model="queryParams.projectClassification"
          placeholder="请输入项目分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目性质" prop="projectNature">
        <el-input
          v-model="queryParams.projectNature"
          placeholder="请输入项目性质"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务的国民经济" prop="serviceNationalEconomy">
        <el-input
          v-model="queryParams.serviceNationalEconomy"
          placeholder="请输入服务的国民经济"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国民经济子行业" prop="nationalEconomySubIndustry">
        <el-input
          v-model="queryParams.nationalEconomySubIndustry"
          placeholder="请输入国民经济子行业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社会经济目标" prop="socioEconomicObjective">
        <el-input
          v-model="queryParams.socioEconomicObjective"
          placeholder="请输入社会经济目标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目周期始" prop="startProjectTime">
        <el-date-picker clearable
          v-model="queryParams.startProjectTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择项目周期始">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目周期末" prop="endProjectTime">
        <el-date-picker clearable
          v-model="queryParams.endProjectTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择项目周期末">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="总经费" prop="totalBudget">
        <el-input
          v-model="queryParams.totalBudget"
          placeholder="请输入总经费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一级学科" prop="firstDiscipline">
        <el-input
          v-model="queryParams.firstDiscipline"
          placeholder="请输入一级学科"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级学科" prop="secondaryDiscipline">
        <el-input
          v-model="queryParams.secondaryDiscipline"
          placeholder="请输入二级学科"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人所属单位" prop="projectLeaderUnit">
        <el-input
          v-model="queryParams.projectLeaderUnit"
          placeholder="请输入项目负责人所属单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业编号" prop="enterpriseNumber">
        <el-input
          v-model="queryParams.enterpriseNumber"
          placeholder="请输入企业编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="校内成员账号" prop="campusMemberAccount">
        <el-input
          v-model="queryParams.campusMemberAccount"
          placeholder="请输入校内成员账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="creatBy">
        <el-input
          v-model="queryParams.creatBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求发布时间" prop="requirementReleaseTime">
        <el-date-picker clearable
          v-model="queryParams.requirementReleaseTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择需求发布时间">
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
          v-hasPermi="['kyfz:requirement:add']"
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
          v-hasPermi="['kyfz:requirement:edit']"
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
          v-hasPermi="['kyfz:requirement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:requirement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="requirementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="requirementId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="委托单位" align="center" prop="client" />
      <el-table-column label="项目负责人" align="center" prop="projectLeader" />
      <el-table-column label="负责人工号" align="center" prop="projectLeaderJobNumber" />
      <el-table-column label="项目成员" align="center" prop="projectMembers" />
      <el-table-column label="依托单位" align="center" prop="supportUnit" />
      <el-table-column label="依托单位代码" align="center" prop="supportUnitNumber" />
      <el-table-column label="项目类别" align="center" prop="projectCategory" />
      <el-table-column label="项目类型" align="center" prop="projectType" />
      <el-table-column label="项目分类" align="center" prop="projectClassification" />
      <el-table-column label="项目性质" align="center" prop="projectNature" />
      <el-table-column label="服务的国民经济" align="center" prop="serviceNationalEconomy" />
      <el-table-column label="国民经济子行业" align="center" prop="nationalEconomySubIndustry" />
      <el-table-column label="社会经济目标" align="center" prop="socioEconomicObjective" />
      <el-table-column label="项目周期始" align="center" prop="startProjectTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startProjectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目周期末" align="center" prop="endProjectTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endProjectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总经费" align="center" prop="totalBudget" />
      <el-table-column label="合同类型" align="center" prop="contractType" />
      <el-table-column label="一级学科" align="center" prop="firstDiscipline" />
      <el-table-column label="二级学科" align="center" prop="secondaryDiscipline" />
      <el-table-column label="项目负责人所属单位" align="center" prop="projectLeaderUnit" />
      <el-table-column label="企业编号" align="center" prop="enterpriseNumber" />
      <el-table-column label="校内成员账号" align="center" prop="campusMemberAccount" />
      <el-table-column label="需求状态" align="center" prop="requirementStatus" />
      <el-table-column label="创建人" align="center" prop="creatBy" />
      <el-table-column label="需求关键词" align="center" prop="requirementKeywords" />
      <el-table-column label="需求详细描述" align="center" prop="requirementDescription" />
      <el-table-column label="需求发布时间" align="center" prop="requirementReleaseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requirementReleaseTime, '{y}-{m}-{d}') }}</span>
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
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:requirement:remove']"
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

    <!-- 添加或修改需求管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="合同号" prop="contractNumber">
          <el-input v-model="form.contractNumber" placeholder="请输入合同号" />
        </el-form-item>
        <el-form-item label="委托单位" prop="client">
          <el-input v-model="form.client" placeholder="请输入委托单位" />
        </el-form-item>
        <el-form-item label="项目负责人" prop="projectLeader">
          <el-input v-model="form.projectLeader" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item label="负责人工号" prop="projectLeaderJobNumber">
          <el-input v-model="form.projectLeaderJobNumber" placeholder="请输入负责人工号" />
        </el-form-item>
        <el-form-item label="项目成员" prop="projectMembers">
          <el-input v-model="form.projectMembers" placeholder="请输入项目成员" />
        </el-form-item>
        <el-form-item label="依托单位" prop="supportUnit">
          <el-input v-model="form.supportUnit" placeholder="请输入依托单位" />
        </el-form-item>
        <el-form-item label="依托单位代码" prop="supportUnitNumber">
          <el-input v-model="form.supportUnitNumber" placeholder="请输入依托单位代码" />
        </el-form-item>
        <el-form-item label="项目类别" prop="projectCategory">
          <el-input v-model="form.projectCategory" placeholder="请输入项目类别" />
        </el-form-item>
        <el-form-item label="项目分类" prop="projectClassification">
          <el-input v-model="form.projectClassification" placeholder="请输入项目分类" />
        </el-form-item>
        <el-form-item label="项目性质" prop="projectNature">
          <el-input v-model="form.projectNature" placeholder="请输入项目性质" />
        </el-form-item>
        <el-form-item label="服务的国民经济" prop="serviceNationalEconomy">
          <el-input v-model="form.serviceNationalEconomy" placeholder="请输入服务的国民经济" />
        </el-form-item>
        <el-form-item label="国民经济子行业" prop="nationalEconomySubIndustry">
          <el-input v-model="form.nationalEconomySubIndustry" placeholder="请输入国民经济子行业" />
        </el-form-item>
        <el-form-item label="社会经济目标" prop="socioEconomicObjective">
          <el-input v-model="form.socioEconomicObjective" placeholder="请输入社会经济目标" />
        </el-form-item>
        <el-form-item label="项目周期始" prop="startProjectTime">
          <el-date-picker clearable
            v-model="form.startProjectTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目周期始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目周期末" prop="endProjectTime">
          <el-date-picker clearable
            v-model="form.endProjectTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目周期末">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总经费" prop="totalBudget">
          <el-input v-model="form.totalBudget" placeholder="请输入总经费" />
        </el-form-item>
        <el-form-item label="一级学科" prop="firstDiscipline">
          <el-input v-model="form.firstDiscipline" placeholder="请输入一级学科" />
        </el-form-item>
        <el-form-item label="二级学科" prop="secondaryDiscipline">
          <el-input v-model="form.secondaryDiscipline" placeholder="请输入二级学科" />
        </el-form-item>
        <el-form-item label="项目负责人所属单位" prop="projectLeaderUnit">
          <el-input v-model="form.projectLeaderUnit" placeholder="请输入项目负责人所属单位" />
        </el-form-item>
        <el-form-item label="企业编号" prop="enterpriseNumber">
          <el-input v-model="form.enterpriseNumber" placeholder="请输入企业编号" />
        </el-form-item>
        <el-form-item label="校内成员账号" prop="campusMemberAccount">
          <el-input v-model="form.campusMemberAccount" placeholder="请输入校内成员账号" />
        </el-form-item>
        <el-form-item label="创建人" prop="creatBy">
          <el-input v-model="form.creatBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="需求关键词" prop="requirementKeywords">
          <el-input v-model="form.requirementKeywords" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="需求详细描述" prop="requirementDescription">
          <el-input v-model="form.requirementDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="需求发布时间" prop="requirementReleaseTime">
          <el-date-picker clearable
            v-model="form.requirementReleaseTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择需求发布时间">
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
import { listRequirement, getRequirement, delRequirement, addRequirement, updateRequirement } from "@/api/kyfz/requirement";

export default {
  name: "Requirement",
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
        requirementReleaseTime: null
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
    /** 查询需求管理列表 */
    getList() {
      this.loading = true;
      listRequirement(this.queryParams).then(response => {
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
        requirementReleaseTime: null
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
      this.ids = selection.map(item => item.requirementId)
      this.single = selection.length!==1
      this.multiple = !selection.length
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
      const requirementId = row.requirementId || this.ids
      getRequirement(requirementId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改需求管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.requirementId != null) {
            updateRequirement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRequirement(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除需求管理编号为"' + requirementIds + '"的数据项？').then(function() {
        return delRequirement(requirementIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/requirement/export', {
        ...this.queryParams
      }, `requirement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
