<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="100px">
      <el-form-item label="企业名" prop="enterpriseName" label-width="120px">
        <el-input v-model="queryParams.enterpriseName" placeholder="请输入企业名" clearable @keyup.enter.native="handleQuery" />
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
      <el-form-item label="企业关键词" prop="enterpriseKeywords">
        <el-input
          v-model="queryParams.enterpriseKeywords"
          placeholder="请输入企业关键词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 级联面板 -->
    <el-row :gutter="5" justify="end" cols="8">
      <el-col :span="2" offset="1" style="margin-top: 40px; margin-bottom: 40px;">
        <span class="unit-tag">所属行业</span>
      </el-col>
      <el-col :span="20">
        <div v-for="item in classificationList" :key="item.categoryId">
          <el-col :span="3" style="margin-top: 20px; margin-bottom: 20px;">
            <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link">
                {{ item.categoryName }}<i class="el-icon-arrow-down el-icon--right" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="childItem in item.children" :key="childItem.categoryId" :command="childItem.categoryId"><span class="el-dropdown-link">
                  {{ childItem.categoryName }}
                </span></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:enterprise:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:enterprise:edit']"
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
          v-hasPermi="['kyfz:enterprise:remove']"
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
          v-hasPermi="['kyfz:enterprise:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 卡片实现 -->
    <div>
      <el-table v-loading="loading" :data="enterpriseList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="企业信息" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-card class="card-item">
              <div class="card-actions">
                <div class="card-content">
                  <div class="card-row">
                    <span class="card-label">企业名:</span>
                    <span class="card-value">{{ scope.row.enterpriseName }}</span>
                  </div>
                  <div class="card-row">
                    <span class="card-label">企业信用代码:</span>
                    <span class="card-value">{{ scope.row.enterpriseCreditCode }}</span>
                  </div>
                  <div class="card-row">
                    <span class="card-label">企业描述:</span>
                    <span class="card-value" :title="scope.row.enterpriseDescribe">
                      {{ scope.row.enterpriseDescribe && scope.row.enterpriseDescribe.length > 13 ?
                        scope.row.enterpriseDescribe.substring(0, 13) + '...' : scope.row.enterpriseDescribe }}
                    </span>
                  </div>
                  <div class="card-row">
                    <span class="card-label">企业注册资本:</span>
                    <span class="card-value">{{ scope.row.registeredCapital }}</span>
                  </div>
                  <div class="card-row">
                    <span class="card-label">所属行业:</span>
                    <span class="card-value" :title="scope.row.categoryNames">
                      {{
                        scope.row.categoryNames &&
                          scope.row.categoryNames.length > 15
                          ? scope.row.categoryNames.substring(0, 15) + "..."
                          : scope.row.categoryNames
                      }}
                    </span>
                  </div>
                  <div class="card-row">
                    <span class="card-label">企业关键词:</span>
                    <span class="card-value">{{ scope.row.enterpriseKeywords }}</span>
                  </div>
                </div>
                <div class="card-actions-right">
                  <div class="buttons-container">
                    <el-button
                      v-hasPermi="['kyfz:enterprise:edit']"
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleUpdate(scope.row)"
                    >修改</el-button>
                    <el-button
                      v-hasPermi="['kyfz:enterprise:remove']"
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      @click="handleDelete(scope.row)"
                    >删除</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 原来表格显示 -->
    <!-- <el-table v-loading="loading" :data="enterpriseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="企业名" align="center" prop="enterpriseName" />
      <el-table-column label="企业信用代码" align="center" prop="enterpriseCreditCode" />
      <el-table-column label="企业描述" align="center" :show-overflow-tooltip="true" prop="enterpriseDescribe" />
      <el-table-column label="企业注册资本" align="center" prop="registeredCapital" />
      <el-table-column label="企业关键词" align="center" prop="enterpriseKeywords" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:enterprise:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:enterprise:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改企业管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="250px">
        <el-form-item label="企业名" prop="enterpriseName">
          <el-input v-model="form.enterpriseName" placeholder="请输入企业名" style="width: 600px" />
        </el-form-item>
        <el-form-item label="企业信用代码" prop="enterpriseCreditCode" label-width="250px">
          <el-input v-model="form.enterpriseCreditCode" placeholder="请输入企业信用代码" style="width: 600px" />
        </el-form-item>
        <el-form-item label="企业描述" prop="enterpriseDescribe" label-width="250px">
          <el-input
            v-model="form.enterpriseDescribe"
            type="textarea"
            placeholder="请输入内容"
            style="width: 600px"
            :autosize="{ minRows: 4, maxRows: 8 }"
          />
        </el-form-item>
        <el-form-item label="企业注册资本" prop="registeredCapital" label-width="250px">
          <el-input v-model="form.registeredCapital" placeholder="请输入企业注册资本" style="width: 600px" />
        </el-form-item>
        <el-form-item label="企业关键词" prop="enterpriseKeywords" label-width="250px">
          <el-input v-model="form.enterpriseKeywords" placeholder="请输入企业关键词" style="width: 600px" />
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
import { listClassification } from '@/api/kyfz/classification'
import {
addEnterprise,
delEnterprise,
getEnterprise,
listEnterprise,
updateEnterprise
} from '@/api/kyfz/enterprise'

export default {
  name: 'Enterprise',
  data() {
    return {
      // 数据库行业分类表格数据
      classificationList: [],
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
      // 企业管理表格数据
      enterpriseList: [],
      // 弹出层标题
      title: '',
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
        enterpriseKeywords: null,
        categoryId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    }
  },
  created() {
    this.getList()
    this.getListClassification()
  },
  methods: {
    // 行业分类下拉菜单触发函数
    handleCommand(command) {
      this.reset_queryParams()
      this.queryParams.categoryId = command
      listEnterprise(this.queryParams).then((response) => {
        this.enterpriseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询行业分类列表 */
    getListClassification() {
      this.loading = true
      listClassification(this.queryParams).then((response) => {
        this.classificationList = response.rows
      })
      this.loading = false
    },
    /** 查询企业管理列表 */
    getList() {
      this.loading = true
      listEnterprise(this.queryParams).then((response) => {
        this.enterpriseList = response.rows
        this.total = response.total
        this.loading = false
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
        enterpriseId: null,
        enterpriseName: null,
        enterpriseCreditCode: null,
        enterpriseDescribe: null,
        registeredCapital: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        enterpriseKeywords: null
      }
      this.resetForm('form')
    },
    reset_queryParams() {
      this.form = {
        enterpriseId: null,
        enterpriseName: null,
        enterpriseCreditCode: null,
        enterpriseDescribe: null,
        registeredCapital: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        enterpriseKeywords: null,
        categoryId: null
      }
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
      this.ids = selection.map((item) => item.enterpriseId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加企业管理'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const enterpriseId = row.enterpriseId || this.ids
      getEnterprise(enterpriseId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改企业管理'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.enterpriseId != null) {
            updateEnterprise(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addEnterprise(this.form).then((response) => {
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
      const enterpriseIds = row.enterpriseId || this.ids
      this.$modal
        .confirm('是否确认删除企业管理编号为"' + enterpriseIds + '"的数据项？')
        .then(function() {
          return delEnterprise(enterpriseIds)
        })
        .then(() => {
          this.getList()
          this.$modal.msgSuccess('删除成功')
        })
        .catch(() => { })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        'kyfz/enterprise/export',
        {
          ...this.queryParams
        },
        `enterprise_${new Date().getTime()}.xlsx`
      )
    }
  }
}
</script>

<style>
.el-tooltip__popper {
  max-width: 60%;
}
</style>

<style scoped>
/* 卡片定制 */
.button-container {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.card-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
}

.card-content-left {
  flex: 1;
}

.card-content-right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.card-content {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
}

.card-row {
  flex-basis: 33.3%;
  display: flex;
  align-items: center;
  white-space: nowrap;
  margin-bottom: 8px;
}

.card-value {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.card-item {
  background-color: #f7fbff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
  margin-bottom: 16px;
}

.card-label {
  font-weight: bold;
  font-family: Arial, sans-serif;
  color: #333;
}

.card-value {
  font-family: Arial, sans-serif;
  color: #666;
}
</style>
