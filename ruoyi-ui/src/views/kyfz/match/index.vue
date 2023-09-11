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

      <el-form-item label="企业名称" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="推荐专家" prop="expertName">
        <el-input
          v-model="queryParams.expertName"
          placeholder="请输入专家姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="研究方向" prop="researchDirection">
        <el-input
          v-model="queryParams.researchDirection"
          placeholder="请输入专家研究方向"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推送状态" prop="pushstatus">
        <el-input
          v-model="queryParams.pushstatus"
          placeholder="请输入推送状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="匹配分值" prop="matchScore">
        <el-input
          v-model="queryParams.minMatchScore"
          placeholder="最小匹配分值"
          style="width: 140px"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <span>~</span>
        <el-input
          v-model="queryParams.maxMatchScore"
          placeholder="最大匹配分值"
          style="width: 140px"
          clearable
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
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:match:push']"
          type="primary"
          plain
          icon="el-icon-s-promotion"
          size="mini"
          :disabled="multiple"
          @click="handlePush"
        >批量推送</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:match:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table
      v-loading="loading"
      :data="matchList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="匹配编号" align="center" prop="matchId" />
      <el-table-column label="需求编号" align="center" prop="requirementId" /> -->
      <!-- 通过需求id获取需求表中的projectName -->
      <el-table-column label="需求" align="center" prop="projectName" />
      <!-- 通过需求id获取需求表中的委托单位 -->
      <el-table-column label="企业" align="center" prop="client" />
      <!-- 通过专家账号获取专家表中的专家姓名和研究方向 -->
      <el-table-column label="推荐专家" align="center" prop="expertName" />
      <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
      <el-table-column label="匹配分值" align="center" prop="matchScore" />
      <el-table-column label="推送状态" align="center" prop="pushstatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['kyfz:match:detail']"
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleDetail(scope.row)"
          >详细</el-button>

          <el-button
            v-hasPermi="['kyfz:match:push']"
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="handlePush(scope.row)"
          >推送</el-button>
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

    <!-- 详细信息弹窗 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="1000px" append-to-body>
      <div class="match-detail" style="margin-top: -20px">
        <div class="match-detail-header">
          <h3 class="match-detail-title">匹配详情</h3>
        </div>
        <el-table :data="[matchDetails]" class="match-detail-table">
          <el-table-column label="需求" align="center" prop="projectName" />
          <el-table-column label="企业" align="center" prop="client" />
          <el-table-column label="推荐专家" align="center" prop="expertName" />
          <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
          <el-table-column label="匹配分值" align="center" prop="matchScore" />
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
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
        <div class="match-detail-result">
          <h4>专家研究成果</h4>
          <div class="match-detail-result-info">
            <div>
              <h5>相关项目</h5>
              <div class="match-detail-decorate">
                <el-tooltip
                  v-for="item in expertDetail.projectArray"
                  :key="item"
                  placement="top"
                  class="match-detail-item project"
                >
                  <div slot="content">
                    项目类别：{{ item.projectCategory }} ；归属二级单位：
                    {{ item.dept }}
                    <br>
                    开始时间：{{ item.startTime }} ；结束时间：{{ item.endTime }}
                    <br>
                    金额：{{ item.startFunds }} 万元
                  </div>
                  <span>{{ item.projectName }} </span>
                </el-tooltip>
              </div>
            </div>
            <div>
              <h5>相关论文</h5>
              <div class="match-detail-decorate">
                <el-tooltip
                  v-for="item in expertDetail.thesisArray"
                  :key="item"
                  placement="top"
                  class="match-detail-item thesis"
                  content="Top center"
                >
                  <div slot="content">
                    论文类型：{{ item.thesisType }} ；发表时间：
                    {{ item.publicateTime }}
                    <br>
                    刊物名称：{{ item.thesisJournal }} ；刊物级别：{{ item.journalLevel }}
                  </div>
                  <span>{{ item.thesisName }} </span>
                </el-tooltip>
              </div>
            </div>
            <div>
              <h5>知识产权</h5>
              <div class="match-detail-decorate">
                <el-tooltip
                  v-for="item in expertDetail.intellectualPropertArray"
                  :key="item"
                  placement="top"
                  class="match-detail-item work"
                  content="Top center"
                  disabled
                >
                  <el-badge :value="item.patentType" type="primary">
                    <span>{{ item.intellectualPropertyName }} </span>
                  </el-badge>
                </el-tooltip>
              </div>
            </div>
            <div>
              <h5>奖项</h5>
              <div class="match-detail-decorate">
                <el-tooltip
                  v-for="item in expertDetail.awardArray"
                  :key="item"
                  placement="top"
                  class="match-detail-item certificate"
                  content="Top center"
                  disabled
                >
                  <span>{{ item.awardName }} </span>
                </el-tooltip>
              </div>
            </div>
            <div class="match-detail-team">
              <h4>专家团队</h4>
              <div class="match-detail-team-info">
                <span v-for="item in expertDetail.teamMembersArray" :key="item">{{
                  item
                }}</span>
                <el-button
                  type="primary"
                  style="position: absolute; bottom: 10px; right: 10px"
                  @click="handleECharts(expertDetail)"
                >
                  团队关系图
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
    <!-- 团队关系图 -->
    <el-dialog
      :title="chartTitle"
      :visible.sync="openECharts"
      append-to-body
      width="1200px"
    >
      <div id="graph-chart" style="width: 1250px; height: 600px">
        <div :id="echartsId" style="width: 1100px; height: 600px" />
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
addMatch,
delMatch,
getMatch,
listMatch,
pushMatch,
updateMatch,
updatePushRecord
} from '@/api/kyfz/match'

import {
getEchartExpertData,
getExpertDetail,
getExpertDetailByAccount,
updateMarkCertificate,
updateMarkProject,
updateMarkThesis,
updateMarkWork
} from '@/api/kyfz/expert'
import * as echarts from 'echarts'

const getEchartsId = () => {
  return new Date().getTime()
}

export default {
  name: 'Match',
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
      // 匹配列表表格数据
      matchList: [],
      matchDetails: [],
      strArray: [],
      strArray1: [],
      strArray2: [],
      strArray3: [],
      projectArrary: [],
      projectList: [],
      thesisList: [],
      workList: [],
      certificateList: [],
      expertDetail: [],
      projectIds: [],
      workIds: [],
      certificateIds: [],
      thesisIds: [],
      SearchButton: false,
      relationshipButton: false,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      openDetail: false,
      openECharts: false,
      openExpert: false,
      selectedProjectId: null,
      buttonTitle: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expertAccount: null,
        requirementId: null,
        matchScore: null,
        minMatchScore: null,
        maxMatchScore: null,
        thesisId: null,
        workId: null,
        certificateId: null,
        projectId: null,
        projectName: null, // 存需求
        client: null, // 存有需求的企业
        expertName: null, // 存专家名
        researchDirection: null, // 存专家研究方向
        requirementKeywords: '', // 需求关键词
        projectNames: '', // 专家研究成果：项目（目前就做这个），论文，著作
        expertPosition: null,
        expertAffiliation: null,
        primaryDisclipline: null,
        secondaryDiscipline: null,
        tertiaryDiscipline: null,
        pushstatus: null,
        thesisName: null,
        workName: null,
        certificateName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      value1: null,
      value2: null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
      echartsId: null,
      chartTitle: null,
      selectedId: null
    }
  },
  created() {
    // alert(this.$route.query.id)
    this.getList()
    this.echartsId = getEchartsId()
  },
  methods: {
    // echart
    handleECharts(row) {
      this.chartTitle = '团队成员关系图'
      this.openECharts = true
      this.$nextTick(() => {
        this.initChart()
      })
      this.myChart.resize() // 自适应大小
    },
    selsect_echart_data(row) {
      const expertId = row.expertId || this.ids
      getEchartExpertData(expertId).then((response) => {
        this.jsonData = response.data
        this.load.close()
      })
    },
    // echarts
    initChart: function() {
      const myChart = echarts.init(document.getElementById(this.echartsId))
      myChart.setOption(this.setOption())
      myChart.resize() // 自适应大小
    },
    // echatrs 数据
    setOption: function() {
      const myChart = echarts.init(document.getElementById(this.echartsId))
      myChart.showLoading()
      const graph = this.jsonData // 引入json
      myChart.hideLoading()

      graph.nodes.forEach(function(node) {
        node.label = {
          show: node.symbolSize > 30
        }
      })
      const option = {
        // 添加你的配置
        title: {
          text: '\n\n\n\n研究方向——' + this.expertDetail.researchDirection,
          top: 'top',
          left: 'left',
          fontSize: 10
        },
        tooltip: {},
        legend: [
          {
            // selectedMode: 'single',
            data: graph.categories.map(function(a) {
              return a.name
            })
          }
        ],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            name: '专家',
            type: 'graph',
            layout: 'circular',
            data: graph.nodes,
            links: graph.links,
            categories: graph.categories,
            roam: true,
            label: {
              position: 'right',
              formatter: '{b}'
            },
            lineStyle: {
              color: 'source',
              curveness: 0.3
            },
            emphasis: {
              focus: 'adjacency',
              lineStyle: {
                width: 10
              }
            }
          }
        ]
      }
      return option
    },
    /** 查询匹配列表列表 */
    getList() {
      this.loading = true
      listMatch(this.queryParams).then((response) => {
        this.matchList = response.rows
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
        matchId: null,
        expertAccount: null,
        requirementId: null,
        matchScore: null,
        thesisId: null,
        workId: null,
        certificateId: null,
        projectId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        markProject: null,
        markWork: null,
        markThesis: null,
        markCertificate: null,
        pushstatus: null
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
      this.ids = selection.map((item) => item.matchId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.openDetail = true
      this.title = '添加匹配列表'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const matchId = row.matchId || this.ids
      getMatch(matchId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改匹配列表'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.matchId !== null) {
            updateMatch(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addMatch(this.form).then((response) => {
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
      const matchIds = row.matchId || this.ids
      this.$modal
        .confirm('是否确认删除匹配列表编号为"' + matchIds + '"的数据项？')
        .then(function() {
          return delMatch(matchIds)
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
        'kyfz/match/export',
        {
          ...this.queryParams
        },
        `match_${new Date().getTime()}.xlsx`
      )
    },

    /** 详细信息按钮操作 */
    handleDetail(row) {
      // 表单内容重置
      this.reset()
      this.loading = false
      this.matchDetails = row
      this.expertAccount = row.expertAccount
      getExpertDetail(row.expertId).then((response) => {
        this.expertDetail = response.data
        if (response.data.teamMembers != null) {
          this.expertDetail.teamMembersArray = response.data.teamMembers
            .trim()
            .split(/[,，、]/)
        } else {
          this.expertDetail.teamMembersArray = '无'
        }
        this.selsect_echart_data(row)
      })
      if (
        this.matchDetails.expertName === '' ||
        this.matchDetails.expertName === null ||
        this.matchDetails.expertName === '无'
      ) {
        this.SearchButton = true
        this.relationshipButton = true
        this.buttonTitle = '未匹配专家'
      } else {
        this.SearchButton = false
        this.relationshipButton = false
        this.buttonTitle = null
      }
      this.openDetail = true
      this.title = '详细信息'
    },

    handlePush(row) {
      const matchIds = row.matchId || this.ids
      this.$modal
        .confirm('是否确认推送所选中的匹配列表？')
        .then(function() {
          return pushMatch(matchIds)
        })
        .then(() => {
          this.$modal.msgSuccess('推送成功')
          this.getList()
        })
        .catch(() => {})
    },
    changeStarValue(matchId, value2) {
      const data1 = {}
      data1.matchId = matchId
      data1.score = value2
      updatePushRecord(data1).then((response) => {
        this.$modal.msgSuccess('评分成功')
        this.openDetail = false
        this.openDetail = true
      })
    },

    handleAllAchievement() {
      // 新的弹窗，放专家的信息和所有信息
      this.openExpert = true
      const markProject = this.markProject
      const markWork = this.markWork
      const markCertificate = this.markCertificate
      const markThesis = this.markThesis
      const expertAccount = this.expertAccount
      const data = {}
      data.markProject = markProject
      data.markWork = markWork
      data.markCertificate = markCertificate
      data.markThesis = markThesis
      data.expertAccount = expertAccount
      // alert(data.expertAccount);
      getExpertDetailByAccount(data).then((response) => {
        this.projectIds = []
        this.thesisIds = []
        this.workIds = []
        this.certificateIds = []
        // 人工标注的id（还没处理的）
        if (response.data.markProjectId !== null) {
          this.projectIds = response.data.markProjectId
        }

        if (response.data.markThesisId !== null) {
          this.thesisIds = response.data.markThesisId
        }

        if (response.data.markWorkId !== null) {
          this.workIds = response.data.markWorkId
        }

        if (response.data.markCertificateId !== null) {
          this.certificateIds = response.data.markCertificateId
        }
      })
    },
    sendProjectId(projectId) {
      const data2 = {}
      data2.deleteBool = false
      data2.matchId = this.matchDetails.matchId
      data2.projectId = projectId
      // 删除标记
      if (this.projectIds.includes(projectId)) {
        try {
          data2.deleteBool = true
          const index = this.projectIds.indexOf(projectId)
          if (index !== -1) {
            this.projectIds.splice(index, 1)
          }
          updateMarkProject(data2).then((response) => {
            this.$modal.msgSuccess('去除标记成功')
          })
          return
        } catch (e) {
          alert(e)
        }
      }
      // 添加标记
      this.projectIds.push(projectId)
      updateMarkProject(data2).then((response) => {
        this.$modal.msgSuccess('标记成功')
      })
    },
    sendThesisId(thesisId) {
      const data2 = {}
      data2.deleteBool = false
      data2.matchId = this.matchDetails.matchId
      data2.thesisId = thesisId
      if (this.thesisIds.includes(thesisId)) {
        data2.deleteBool = true
        const index = this.thesisIds.indexOf(thesisId)
        if (index !== -1) {
          this.thesisIds.splice(index, 1)
        }
        updateMarkThesis(data2).then((response) => {
          this.$modal.msgSuccess('去除标记成功')
        })
        return
      }

      this.thesisIds.push(thesisId)
      updateMarkThesis(data2).then((response) => {
        this.$modal.msgSuccess('标记成功')
      })
    },

    sendWorkId(workId) {
      const data2 = {}
      data2.deleteBool = false
      data2.matchId = this.matchDetails.matchId
      data2.workId = workId
      if (this.workIds.includes(workId)) {
        data2.deleteBool = true
        const index = this.workIds.indexOf(workId)
        if (index !== -1) {
          this.workIds.splice(index, 1)
        }
        updateMarkWork(data2).then((response) => {
          this.$modal.msgSuccess('去除标记成功')
        })
        return
      }

      this.workIds.push(workId)
      updateMarkWork(data2).then((response) => {
        this.$modal.msgSuccess('标记成功')
      })
    },

    sendCertificateId(certificateId) {
      const data2 = {}
      data2.deleteBool = false
      data2.matchId = this.matchDetails.matchId
      data2.certificateId = certificateId
      if (this.certificateIds.includes(certificateId)) {
        data2.deleteBool = true
        const index = this.certificateIds.indexOf(certificateId)
        if (index !== -1) {
          this.certificateIds.splice(index, 1)
        }
        updateMarkCertificate(data2).then((response) => {
          this.$modal.msgSuccess('去除标记成功')
        })
        return
      }
      this.certificateIds.push(certificateId)
      updateMarkCertificate(data2).then((response) => {
        this.$modal.msgSuccess('标记成功')
      })
    },
    isHighlighted(projectId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.projectIds.includes(projectId)
    },
    isHighlighted2(workId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.workIds.includes(workId)
    },
    isHighlighted1(thesisId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.thesisIds.includes(thesisId)
    },
    isHighlighted3(certificateId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.certificateIds.includes(certificateId)
    }
  }
}
</script>

<style scoped>
.highlight {
  background-color: yellow;
}

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

.match-detail-result-info > div {
  margin-bottom: 10px;
}

.match-detail-team-info > div > el-button {
  margin-bottom: 10px;
  margin-right: 10px;
}

.match-detail-decorate {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
}

.match-detail-decorate .match-detail-item {
  display: inline-flex;
  align-items: center;
  margin-right: 40px;
  margin-bottom: 10px;
  padding: 10px 10px;
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

.match-detail-team-info > span {
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
</style>
