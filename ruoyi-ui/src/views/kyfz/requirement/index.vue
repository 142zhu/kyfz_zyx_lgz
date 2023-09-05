<template>
  <div class="app-container">
    <el-form
      v-show="showSearch"
      ref="queryForm"
      :model="queryParams"
      size="small"
      :inline="true"
      label-width="80px"
    >
      <el-form-item label="需求名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入需求名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="client">
        <el-select
          v-model="queryParams.client"
          filterable
          clearable
          placeholder="请选择公司名称"
          popper-class="custom-select"
          @change="handleChange_1"
        >
          <el-option
            v-for="item in enterpriseList"
            :key="item.enterpriseName"
            :label="item.enterpriseName"
            :value="item.enterpriseName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="需求状态" prop="requirementStatus">
        <el-select
          v-model="queryParams.requirementStatus"
          filterable
          placeholder="请选择需求状态"
        >
          <el-option
            v-for="item in dict.type.requirement_status"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间" prop="requirementReleaseTime">
        <el-date-picker
          v-model="queryParams.requirementReleaseTime"
          clearable
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请输入发布时间"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          type="primary"
          icon="el-icon-document"
          size="mini"
          @click="handleMatch_2"
        >匹配</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:requirement:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:requirement:remove']"
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table
      v-loading="loading"
      :data="requirementList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="需求名称" align="center" prop="projectName" />
      <el-table-column label="需求状态" align="center" prop="requirementStatus" />
      <el-table-column label="公司名称" align="center" prop="client" />
      <el-table-column
        label="发布时间"
        align="center"
        prop="requirementReleaseTime"
        width="100"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requirementReleaseTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['kyfz:requirement:edit']"
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="handleUpdate(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleMatch(scope.row)"
          >匹配</el-button>
          <el-button
            v-hasPermi="['kyfz:requirement:remove']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
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

    <!-- 修改需求管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <div class="toggle-edit-button" style="position: absolute; top: 25px; right: 80px">
        <el-button type="primary" @click="toggleEdit">{{
          editable ? "编辑" : "暂存"
        }}</el-button>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="250px">
        <el-form-item label="需求编号" prop="requirementId" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.requirementId"
            placeholder="无需填写（自动生成）"
            :readonly="true"
          />
        </el-form-item>
        <el-form-item label="需求名称" prop="projectName">
          <el-input
            ref="idInput"
            v-model="form.projectName"
            placeholder=""
            :readonly="editable"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求关键词" prop="requirementKeywords">
          <el-input
            ref="idInput"
            v-model="form.requirementKeywords"
            placeholder=""
            :readonly="editable"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求描述" prop="requirementDescription">
          <el-input
            ref="idInput"
            v-model="form.requirementDescription"
            placeholder=""
            :readonly="editable"
            style="width: 600px"
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
          />
        </el-form-item>
        <el-form-item label="需求类型" prop="projectCategory">
          <el-input
            ref="idInput"
            v-model="form.projectCategory"
            placeholder=""
            :readonly="editable"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求发布时间" prop="requirementReleaseTime">
          <el-date-picker
            ref="idInput"
            v-model="form.requirementReleaseTime"
            clearable
            type="date"
            value-format="yyyy-MM-dd"
            placeholder=""
            :readonly="true"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求状态" prop="requirementStatus">
          <el-select
            v-model="form.requirementStatus"
            filterable
            placeholder=""
            :disabled="editable"
            style="width: 600px"
          >
            <el-option
              v-for="item in dict.type.requirement_status"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司名称" prop="client">
          <el-select
            v-model="form.client"
            filterable
            clearable
            placeholder=""
            :disabled="editable"
            style="width: 600px"
            popper-class="custom-select"
            @change="handleChange"
          >
            <el-option
              v-for="item in enterpriseList"
              :key="item.enterpriseName"
              :label="item.enterpriseName"
              :value="item.enterpriseName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司关键词" prop="enterpriseKeywords" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.enterpriseKeywords"
            placeholder=""
            :readonly="true"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="公司简介" prop="enterpriseDescribe" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.enterpriseDescribe"
            type="textarea"
            placeholder=""
            :readonly="true"
            style="width: 600px"
            :autosize="{ minRows: 2, maxRows: 4 }"
          />
        </el-form-item>
        <el-form-item label="注册资本" prop="registeredCapital" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.registeredCapital"
            placeholder=""
            :readonly="true"
            style="width: 600px"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 上传需求管理对话框 -->
    <el-dialog :title="title" :visible.sync="open1" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="250px">
        <el-form-item label="需求名称" prop="projectName">
          <el-input
            v-model="form.projectName"
            placeholder="请输入需求名称"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求关键词" prop="requirementKeywords">
          <el-input
            v-model="form.requirementKeywords"
            placeholder="请输入需求关键词"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="需求描述" prop="requirementDescription">
          <el-input
            v-model="form.requirementDescription"
            placeholder="请输入需求描述"
            style="width: 600px"
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 6 }"
          />
        </el-form-item>
        <el-form-item label="需求类型" prop="projectCategory">
          <el-input
            v-model="form.projectCategory"
            placeholder="请输入需求类型"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item v-if="false" label="需求状态" prop="requirementStatus">
          <el-select
            v-model="form.requirementStatus"
            filterable
            placeholder="请选择需求状态"
            style="width: 600px"
          >
            <el-option
              v-for="item in dict.type.requirement_status"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :selected="index === 1"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司名称" prop="client">
          <el-select
            v-model="form.client"
            filterable
            clearable
            placeholder="请选择已有公司名称"
            style="width: 600px"
            popper-class="custom-select"
            @change="handleChange"
          >
            <el-option
              v-for="item in enterpriseList"
              :key="item.enterpriseName"
              :label="item.enterpriseName"
              :value="item.enterpriseName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="公司关键词" prop="enterpriseKeywords" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.enterpriseKeywords"
            placeholder=""
            :readonly="true"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="公司简介" prop="enterpriseDescribe">
          <el-input
            ref="idInput"
            v-model="form.enterpriseDescribe"
            type="textarea"
            placeholder=""
            :readonly="true"
            style="width: 600px"
            :autosize="{ minRows: 4, maxRows: 6 }"
          />
        </el-form-item>
        <el-form-item label="注册资本" prop="registeredCapital" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.registeredCapital"
            placeholder=""
            :readonly="true"
            style="width: 600px"
          />
          tto
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 需求匹配结果 -->
    <el-dialog
      title="匹配结果"
      :visible.sync="open_match_result"
      width="1000px"
      append-to-body
    >
      <el-table
        v-loading="loading"
        :data="matchResult"
        :default-sort="{ prop: 'matchScore', order: 'descending' }"
        max-height="500"
      >
        <el-table-column label="专家姓名" align="center" prop="expertName" />
        <el-table-column label="研究方向" align="center" prop="researchDirection" />
        <el-table-column label="匹配分值" align="center" prop="matchScore" sortable />
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {
addRequirement,
delRequirement,
getRequirement,
handleMatch,
listRequirement,
listenterprise,
updateRequirement
} from '@/api/kyfz/requirement'
import DictData from '@/components/DictData'
DictData.install()

export default {
  name: 'Requirement',
  dicts: ['requirement_status'],
  data() {
    return {
      editable: true,
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
      // 匹配结果
      matchResult: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      open1: false,
      open_match_result: false,
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
        enterpriseName: null,
        requirementId: null
      },
      // 表单参数
      form: {
        requirementStatus: ''
      },
      // 企业列表
      enterpriseList: [],
      // 表单校验
      rules: {}
    }
  },
  created() {
    this.getList()
    this.getenterpriseList()
  },
  mounted() {
    // 初始时设置readonly属性
    this.$nextTick(() => {
      const input = this.$refs.idInput.$el.querySelector('input')
      input.setAttribute('readonly', this.editable)
    })
  },
  methods: {
    /** 切换编辑状态 */
    toggleEdit() {
      this.editable = !this.editable
      this.$nextTick(() => {
        // 延迟调用，确保DOM更新完成
        const input = this.$refs.idInput.$el.querySelector('input')
        input.setAttribute('readonly', this.editable)
      })
    },
    /** 查询所有企业名*/
    getenterpriseList() {
      this.loading = true
      listenterprise(this.queryParams).then((response) => {
        this.enterpriseList = response.rows
        this.loading = false
      })
    },
    /** 选择企业列表后自动填充企业信息列表 */
    handleChange(value) {
      const selectedEnterprise = this.enterpriseList.find(
        (item) => item.enterpriseName === value
      )
      this.form.enterpriseDescribe = selectedEnterprise.enterpriseDescribe
      this.form.registeredCapital = selectedEnterprise.registeredCapital
      this.form.enterpriseNumber = selectedEnterprise.enterpriseId
    },

    handleChange_1(value) {
      const selectedEnterprise1 = this.enterpriseList.find(
        (item) => item.enterpriseName === value
      )
      this.queryParams.enterpriseName = selectedEnterprise1.enterpriseName
      this.queryParams.enterpriseDescribe = selectedEnterprise1.enterpriseDescribe
      this.queryParams.registeredCapital = selectedEnterprise1.registeredCapital
      this.queryParams.enterpriseNumber = selectedEnterprise1.enterpriseId
    },
    /** 查询需求管理列表 */
    getList() {
      this.loading = true
      listRequirement(this.queryParams).then((response) => {
        if (response.rows.length !== 0) {
          this.requirementList = response.rows
          this.queryParams.requirementId = response.rows[0].requirementId
          this.total = response.total
          this.loading = false
        } else {
          this.requirementList = null
          this.loading = false
        }
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
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
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
      this.queryParams.client = null
    },

    // 搜索框配匹配按钮，新建项目并匹配
    handleMatch_2() {
      addRequirement(this.queryParams).then((response) => {
        this.$modal.msgSuccess('新增成功,正在匹配中')
        this.open1 = false
        this.getList()
        handleMatch(response.data[0])
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.requirementId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.getenterpriseList()
      this.form.requirementStatus = this.dict.type.requirement_status[0].value
      this.open1 = true
      this.title = '新增需求管理'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.getenterpriseList()
      const requirementId = row.requirementId || this.ids
      getRequirement(requirementId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '需求详情'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.requirementId != null) {
            updateRequirement(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addRequirement(this.form).then((response) => {
              this.$modal.msgSuccess('新增成功')
              this.open1 = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const requirementIds = row.requirementId || this.ids
      this.$modal
        .confirm('是否确认删除需求管理编号为"' + requirementIds + '"的数据项？')
        .then(function() {
          return delRequirement(requirementIds)
        })
        .then(() => {
          this.getList()
          this.$modal.msgSuccess('删除成功')
        })
        .catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        'kyfz/requirement/export',
        {
          ...this.queryParams
        },
        `requirement_${new Date().getTime()}.xlsx`
      )
    },

    async handleMatch(row) {
      debugger
      var messageInstance = this.$message({
        message: '正在匹配中，请稍等',
        duration: 0
      })
      handleMatch({ requirementId: row.requirementId })
        .then((res) => {
          if (res.code === 200) {
            messageInstance.close()
            this.matchResult = res.data
            this.$modal.msgSuccess('算法调用成功')
            this.open_match_result = true
          } else {
            messageInstance.close()
            this.$modal.msgError('算法调用失败')
          }
        })
        .catch((e) => {
          messageInstance.close()
          this.$modal.msgError('算法调用失败')
        })
    }
  }
}
</script>

<style scoped>
.inputDeep >>> .el-input__inner {
  border: 0px;
  box-shadow: 0 0 0 0px;
}
</style>

<style>
.custom-select {
  width: 450px; /* 自定义搜索框宽度 */
}
</style>
