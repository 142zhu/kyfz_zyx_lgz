<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务来源" prop="taskSource">
        <el-input
          v-model="queryParams.taskSource"
          placeholder="请输入任务来源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务数量" prop="taskNumber">
        <el-input
          v-model="queryParams.taskNumber"
          placeholder="请输入任务数量"
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
          v-hasPermi="['kyfz:workstation:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:workstation:edit']"
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:workstation:remove']"
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:workstation:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table v-loading="loading" :data="workstationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务名称" align="center" prop="taskName" />
      <el-table-column label="任务来源" align="center" prop="taskSource" />
      <el-table-column label="任务数量" align="center" prop="taskNumber" />
      <el-table-column label="任务状态" align="center" prop="taskStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAddRequirement(scope.row)"
          >添加需求</el-button>
          <el-button
            v-hasPermi="['kyfz:workstation:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['kyfz:workstation:remove']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
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

    <!-- 添加或修改工作站对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务来源" prop="taskSource">
          <el-input v-model="form.taskSource" placeholder="请输入任务来源" />
        </el-form-item>
        <el-form-item label="任务数量" prop="taskNumber">
          <el-input-number v-model="form.taskNumber" :min="0" :step="1" controls-position="right" />
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
          <el-select
            v-model="form.taskStatus"
            filterable
            placeholder=""
            style="width: 300px"
          >
            <el-option
              v-for="item in dict.type.task_status"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm_requirement">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 任务中的需求 -->
    <el-dialog :title="title" :visible.sync="open_requirement" width="800px" append-to-body>
      <el-table
        ref="multipleTable"
        :data="requirementList"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <el-table-column
          prop="projectName"
          label="需求名称"
          width="500"
        />
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="add_requirement">添加需求</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 上传需求管理对话框 -->
    <el-dialog :title="title" :visible.sync="add_requirement_open" width="1000px" append-to-body>
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
        <el-form-item label="公司名称" prop="enterpriseName">
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
        </el-form-item>
        <el-form-item label="" prop="taskId" class="inputDeep">
          <el-input
            ref="idInput"
            v-model="form.taskId"
            style="width: 600px;display: none;"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm_req">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
addRequirement_staging,
listenterprise,
listRequirement_staging,
updateRequirement_staging
} from '@/api/kyfz/requirement'
import { addWorkstation, delWorkstation, getWorkstation, listWorkstation, updateWorkstation } from '@/api/kyfz/workstation'
import DictData from '@/components/DictData'
DictData.install()

export default {
  dicts: ['task_status'],
  name: 'Workstation',
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
      main_taskid: null,
      // 总条数
      total: 0,
      // 需求管理表格数据
      requirementList: [],
      // 工作站表格数据
      workstationList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      open_requirement: false,
      add_requirement_open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        taskSource: null,
        taskNumber: null,
        taskStatus: null,
        taskId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
      /** 查询暂时的需求管理列表 */
      getList_requirement() {
      this.loading = true
      this.queryParams.taskId = this.main_taskid
      listRequirement_staging(this.queryParams).then((response) => {
        this.requirementList = response.rows
        this.queryParams.requirementId = response.rows[0].requirementId
        this.total = response.total
        this.loading = false
      })
    },
      /** 具体需求提交按钮 */
      submitForm_req() {
      this.$refs['form'].validate((valid) => {
        this.form.taskId = this.main_taskid
        if (valid) {
          if (this.form.requirementId != null) {
            updateRequirement_staging(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.add_requirement_open = false
              this.getList_requirement()
            })
          } else {
            addRequirement_staging(this.form).then((response) => {
              this.$modal.msgSuccess('新增成功')
              this.add_requirement_open = false
              this.getList_requirement()
            })
          }
        }
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
    // 新增具体的需求
    add_requirement() {
      this.reset()
      this.add_requirement_open = true
      this.getenterpriseList()
      this.form.requirementStatus = this.dict.type.requirement_status[0].value
      this.add_requirement_open = true
      this.title = '新增需求'
    },
    // 打开添加需求面板
    handleAddRequirement(row) {
      this.main_taskid = row.taskId
      this.reset()
      this.getList_requirement()
      this.open_requirement = true
      this.title = '添加需求'
    },
    /** 查询工作站列表 */
    getList() {
      this.loading = true
      listWorkstation(this.queryParams).then(response => {
        this.workstationList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.open_requirement = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        taskId: null,
        taskName: null,
        taskSource: null,
        taskNumber: null,
        userId: null,
        updateTime: null,
        taskStatus: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加工作站'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const taskId = row.taskId || this.ids
      getWorkstation(taskId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改工作站'
      })
    },
    /** 提交按钮 */
    submitForm_requirement() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateWorkstation(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addWorkstation(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const taskIds = row.taskId || this.ids
      this.$modal.confirm('是否确认删除工作站编号为"' + taskIds + '"的数据项？').then(function() {
        return delWorkstation(taskIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/workstation/export', {
        ...this.queryParams
      }, `workstation_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
