<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="推送编号" prop="pushId">
        <el-input v-model="queryParams.pushId" placeholder="请输入推送编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="匹配编号" prop="matchId">
        <el-input v-model="queryParams.matchId" placeholder="请输入匹配编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="需求" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入需求" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="专家" prop="expertName">
        <el-input v-model="queryParams.expertName" placeholder="请输入专家名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="反馈状态" prop="feebback">
        <el-input v-model="queryParams.feebback" placeholder="请输入反馈状态" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="推送时间" prop="pushTime">
        <el-date-picker
          v-model="queryParams.pushTime"
          clearable
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择推送时间"
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
          v-hasPermi="['kyfz:record:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:record:remove']"
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
          v-hasPermi="['kyfz:record:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="推送编号" align="center" prop="pushId" />
      <el-table-column label="匹配编号" align="center" prop="matchId" />
      <!-- 按照需求连接的两行 -->
      <el-table-column label="需求" align="center" prop="projectName" />
      <el-table-column label="专家" align="center" prop="expertName" />
      <el-table-column label="反馈状态" align="center" prop="feebback" />
      <el-table-column label="推送时间" align="center" prop="pushTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pushTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['kyfz:record:edit']"
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleDetail(scope.row)"
          >详情</el-button>
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

    <!-- 推送详情对话框 -->
    <!-- 详细信息弹窗 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="1000px" append-to-body>
      <div class="match-detail" style="margin-top: -20px">
        <div class="match-detail-header">
          <h3 class="match-detail-title">匹配详情</h3>
        </div>
        <el-table :data="[matchDetails]" class="match-detail-table">
          <el-table-column label="匹配编号" align="center" prop="matchId" />
          <el-table-column label="需求" align="center" prop="projectName" />
          <el-table-column label="企业" align="center" prop="client" />
          <el-table-column label="推荐专家" align="center" prop="expertName" />
          <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
          <el-table-column label="匹配分值" align="center" prop="matchScore" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                v-has-permission="'kyfz:match:push'"
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handlePush(scope.row)"
              >推送</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="match-detail-keywords">
          <h4>需求关键词</h4>
          <div class="match-detail-keywords-info">
            <span v-for="item in matchDetails.requirementKeywordsArray" :key="item">{{ item }}<br></span>
          </div>
        </div>
        <div class="match-detail-result">
          <h4>专家研究成果</h4>
          <div class="match-detail-result-info">
            <div>
              <h5>相关项目</h5>
              <div class="match-detail-decorate">
                <span v-for="item in matchDetails.projectNamesArray" :key="item" class="match-detail-item project">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关论文</h5>
              <div class="match-detail-decorate">
                <span v-for="item in matchDetails.thesisNamesArray" :key="item" class="match-detail-item thesis">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关著作</h5>
              <div class="match-detail-decorate">
                <span v-for="item in matchDetails.workNamesArray" :key="item" class="match-detail-item work">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关证书</h5>
              <div class="match-detail-decorate">
                <span
                  v-for="item in matchDetails.certificateNamesArray"
                  :key="item"
                  class="match-detail-item certificate"
                >{{ item }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="match-detail-team">
          <h4>专家团队</h4>
          <div class="match-detail-team-info">
            <span v-for="item in matchDetails.teamMembersArray" :key="item">{{
              item
            }}</span>
          </div>
        </div>

        <!-- 评分模块 -->
        <div class="feedback-state">
          <div class="feedback-state-label">反馈状态:</div>
          <div class="feedback-state-value">
            {{ matchDetails.feebback }}
          </div>
        </div>
      </div>
    </el-dialog>

    <!-- 添加或修改推送记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-form-item label="匹配编号" prop="matchId">
          <el-input v-model="form.matchId" placeholder="请输入匹配编号" style="width: 500px" />
        </el-form-item>
        <el-form-item label="反馈状态" prop="feebback">
          <el-input v-model="form.feebback" placeholder="请输入反馈状态" style="width: 500px" />
        </el-form-item>
        <el-form-item label="推送时间" prop="pushTime">
          <el-date-picker v-model="form.pushTime" clearable type="date" value-format="yyyy-MM-dd" placeholder="请选择推送时间" />
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
import { getMatchDetails } from '@/api/kyfz/match'
import { addRecord, delRecord, getRecord, listRecord, updateRecord } from '@/api/kyfz/push_record'
export default {
  name: 'Record',
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
      title: '',
      // 是否显示弹出层
      open: false,
      openDetail: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        matchId: null,
        pushTime: null,
        feebback: null,
        projectName: null,
        expertName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      matchDetails: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询推送记录列表 */
    getList() {
      this.loading = true
      listRecord(this.queryParams).then((response) => {
        this.recordList = response.rows
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
        pushId: null,
        matchId: null,
        pushTime: null,
        feebback: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map((item) => item.pushId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加推送记录'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const pushId = row.pushId || this.ids
      getRecord(pushId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改推送记录'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.pushId != null) {
            updateRecord(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addRecord(this.form).then((response) => {
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
      const pushIds = row.pushId || this.ids
      this.$modal
        .confirm('是否确认删除推送记录编号为"' + pushIds + '"的数据项？')
        .then(function() {
          return delRecord(pushIds)
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
        'kyfz/record/export',
        {
          ...this.queryParams
        },
        `record_${new Date().getTime()}.xlsx`
      )
    },
    /** 详细信息按钮操作 */
    handleDetail(row) {
      // 表单内容重置
      this.reset()
      // 获取到当前行匹配信息的id
      const matchId = row.matchId || this.ids
      const feebback1 = row.feebback
      const pushTime = row.pushTime
      getMatchDetails(matchId).then((response) => {
        this.matchDetails = response.data
        if (feebback1 === 1) {
          this.matchDetails.feebback = '已点击（有意向）'
        } else if (feebback1 === 1 || feebback1 == null) {
          this.matchDetails.feebback = '未点击'
        } else if (feebback1 === 2) {
          this.matchDetails.feebback = '已点击（无意向）'
        }

        this.matchDetails.pushTime = pushTime
        if (response.data.requirementKeywords != null) {
          this.matchDetails.requirementKeywordsArray = response.data.requirementKeywords
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.requirementKeywordsArray = '无'
        }
        if (response.data.projectNames != null) {
          this.matchDetails.projectNamesArray = response.data.projectNames
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.projectNamesArray = '无'
        }
        if (response.data.teamMembers != null) {
          this.matchDetails.teamMembersArray = response.data.teamMembers
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.teamMembersArray = '无'
        }
        if (response.data.thesisNames != null) {
          this.matchDetails.thesisNamesArray = response.data.thesisNames
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.thesisNamesArray = '无'
        }
        if (response.data.workNames != null) {
          this.matchDetails.workNamesArray = response.data.workNames
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.workNamesArray = '无'
        }
        if (response.data.certificateNames != null) {
          this.matchDetails.certificateNamesArray = response.data.certificateNames
            .trim()
            .split(/[,，、]/)
        } else {
          this.matchDetails.certificateNamesArray = '无'
        }
        if (response.data.score != null) {
          this.matchDetails.value2 = response.data.score
        } else {
          this.matchDetails.value2 = null
        }
        this.openDetail = true
        this.title = '详细信息'
      })
    }
  }
}
</script>

<style scoped>
.string-info {
  display: flex;
  flex-wrap: wrap;
}

.string-info span {
  display: inline-block;
  margin: 10px;
  border: 1px solid gray;
  white-space: pre-wrap;
}

.match-detail {
  padding: 20px;
}

.match-detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.match-detail-title {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
}

.match-detail-star {
  display: flex;
  align-items: center;
}

.match-detail-table {
  margin: 10px 0;
}

.match-detail-keywords h4,
.match-detail-result h4,
.match-detail-team h4 {
  font-size: 16px;
  font-weight: bold;
}

.match-detail-keywords-info,
.match-detail-result-info,
.match-detail-team-info {
  margin-top: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background-color: #f5f7fa;
}

.match-detail-result-info>div {
  margin-bottom: 10px;
}

.match-detail-decorate {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
}

.match-detail-decorate .match-detail-item {
  display: inline-flex;
  align-items: center;
  margin-right: 10px;
  margin-bottom: 10px;
  padding: 4px 8px;
  border-radius: 16px;
  font-size: 14px;
  color: #333;
}

.match-detail-decorate .match-detail-item:first-child {
  margin-left: 0;
}

.match-detail-decorate .match-detail-item::before {
  content: "";
  display: inline-block;
  width: 8px;
  height: 8px;
  margin-right: 6px;
  border-radius: 50%;
}

.match-detail-decorate .match-detail-item.project::before {
  background-color: #3fb27f;
}

.match-detail-decorate .match-detail-item.thesis::before {
  background-color: #ffc107;
}

.match-detail-decorate .match-detail-item.work::before {
  background-color: #909399;
}

.match-detail-decorate .match-detail-item.certificate::before {
  background-color: #19be6b;
}

.match-detail-team-info {
  display: flex;
  flex-wrap: wrap;
}

.match-detail-team-info>span {
  display: inline-flex;
  align-items: center;
  margin-right: 10px;
  margin-bottom: 10px;
  padding: 4px 8px;
  border-radius: 16px;
  font-size: 14px;
  color: #333;
  background-color: #f5f7fa;
}

.match-detail-keywords-info span {
  display: inline-block;
  padding: 2px 6px;
  border: 1px solid #dcdfe6;
  border-radius: 2px;
  background-color: #f2f6fc;
  color: #606266;
  margin: 5px 5px 5px 0;
}

.match-detail-keywords-info span:hover {
  background-color: #eef1f6;
}

.match-detail-team-info span {
  display: inline-block;
  background-color: #e4e7ed;
  margin: 0 10px 10px 0;
  padding: 6px 10px;
  border-radius: 4px;
  font-size: 14px;
  color: #606266;
}

.match-detail-team-info span:hover {
  display: inline-block;
  background-color: #d8dbe2;
}

/* 调整对话框标题和内容之间的距离 */
.el-dialog__header {
  margin-bottom: 0px !important;
}

/* 在弹窗内容过大时添加垂直滚动条 */
.el-dialog__body .match-detail-team-info,
.el-dialog__body .match-detail-keywords-info {
  max-height: 400px;
  overflow-y: auto;
}

/* 匹配详情示例页面中需要增加的样式 */
.match-detail-keywords-info span {
  padding: 2px 6px;
  border: 1px solid #dcdfe6;
  border-radius: 2px;
  background-color: #f2f6fc;
  color: #606266;
  margin: 5px 5px 5px 0;
}

.match-detail-keywords-info span:hover {
  background-color: #eef1f6;
}

.match-detail-team-info span {
  display: inline-block;
  background-color: #e4e7ed;
  margin: 0 10px 10px 0;
  padding: 6px 10px;
  border-radius: 4px;
  font-size: 14px;
  color: #606266;
}

.match-detail-team-info span:hover {
  background-color: #d8dbe2;
}

#graph-chart {
  height: 1000px;
  width: 1000px;
}

.match-detail-team-info {
  position: relative;
  display: flex;
  align-items: center;
}

.match-detail-team-info button {
  display: flex;
  align-items: center;
  position: absolute;
  right: 5px;
}

.feedback-state {
  display: flex;
  align-items: center;
  position: absolute;
  top: 45px;
  right: 60px;
}

.feedback-state-label {
  margin-right: 3px;
  font-size: 20px;
  font-weight: 700;
}

.feedback-state-value {
  font-size: 20px;
  font-weight: 700;
}
</style>
