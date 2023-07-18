<template>
  <div class="app-container">
    <el-form
      v-show="showSearch"
      ref="queryForm"
      :model="queryParams"
      size="small"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="姓名" prop="expertName" label-width="120px">
        <el-input
          v-model="queryParams.expertName"
          placeholder="请输入专家姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职称" prop="expertPosition" label-width="120px">
        <el-input
          v-model="queryParams.expertPosition"
          placeholder="请输入专家职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="研究方向" prop="researchDirection" label-width="120px">
        <el-input
          v-model="queryParams.researchDirection"
          placeholder="请输入研究方向"
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
          v-hasPermi="['kyfz:expert:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['kyfz:expert:edit']"
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
          v-hasPermi="['kyfz:expert:remove']"
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
          v-hasPermi="['kyfz:expert:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>
    <!-- 级联面板 -->
    <el-row :gutter="5" justify="end" cols="8">
      <el-col :span="2" offset="1" style="margin-top: 40px; margin-bottom: 40px">
        <span class="unit-tag">所属行业</span>
      </el-col>
      <el-col :span="20">
        <div v-for="item in classificationList" :key="item.categoryId">
          <el-col :span="3" style="margin-top: 20px; margin-bottom: 20px">
            <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link">
                {{ item.categoryName }}<i class="el-icon-arrow-down el-icon--right" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                  v-for="childItem in item.children"
                  :key="childItem.categoryId"
                  :command="childItem.categoryId"
                ><span class="el-dropdown-link">
                  {{ childItem.categoryName }}
                </span></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </div>
      </el-col>
    </el-row>
    <!-- 卡片实现 -->
    <div>
      <el-row :gutter="20">
        <!--学院数据-->
        <el-col :span="6" :xs="24">
          <div class="head-container">
            <el-input
              v-model="filterText"
              placeholder="请输入学院名称"
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
          <div style="height: 800px; overflow: auto">
            <el-scrollbar wrap-class="tree-scrollbar">
              <el-tree
                ref="tree"
                :data="deptOptions"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                node-key="id"
                :default-expand-all="false"
                highlight-current
                @node-click="handleNodeClick"
              />
            </el-scrollbar>
          </div>
        </el-col>
        <!--人才数据-->
        <el-col :span="18" :xs="24">
          <el-table
            v-loading="loading"
            :data="expertList"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column
              label="专家信息"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-card class="card-item">
                  <div class="card-actions">
                    <div class="card-content">
                      <div class="card-row">
                        <span class="card-label">专家姓名:</span>
                        <span class="card-value">{{ scope.row.expertName }}</span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">专家账号:</span>
                        <span class="card-value">{{ scope.row.expertAccount }}</span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">专家职称:</span>
                        <span class="card-value">{{ scope.row.expertPosition }}</span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">所属单位:</span>
                        <span class="card-value" :title="scope.row.expertAffiliation">
                          {{
                            scope.row.expertAffiliation &&
                              scope.row.expertAffiliation.length > 15
                              ? scope.row.expertAffiliation.substring(0, 15) + "..."
                              : scope.row.expertAffiliation
                          }}
                        </span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">研究方向:</span>
                        <span class="card-value">{{ scope.row.researchDirection }}</span>
                      </div>
                    </div>
                    <div class="card-actions-right">
                      <div class="buttons-container">
                        <el-button
                          size="mini"
                          type="text"
                          icon="el-icon-edit"
                          @click="handleDetail(scope.row)"
                        >
                          详情
                        </el-button>
                        <el-button
                          v-hasPermi="['kyfz:expert:edit']"
                          size="mini"
                          type="text"
                          icon="el-icon-edit"
                          @click="handleUpdate(scope.row)"
                        >
                          修改
                        </el-button>
                        <el-button
                          v-hasPermi="['kyfz:expert:remove']"
                          size="mini"
                          type="text"
                          icon="el-icon-delete"
                          @click="handleDelete(scope.row)"
                        >
                          删除
                        </el-button>
                      </div>
                    </div>
                  </div>
                </el-card>
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
        </el-col>
      </el-row>
    </div>
    <!-- 普通表格显示 -->
    <!-- <el-table v-loading="loading" :data="expertList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="专家账号" align="center" prop="expertAccount" />
      <el-table-column label="专家姓名" align="center" prop="expertName" />
      <el-table-column label="专家职称" align="center" prop="expertPosition" />
      <el-table-column label="专家所属单位" align="center" prop="expertAffiliation" />
      <el-table-column label="研究方向" align="center" prop="researchDirection" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleDetail(scope.row)">详情</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:expert:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:expert:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->

    <!-- 添加或修改专家管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="250px">
        <el-form-item label="专家信息状态" prop="expertSignificance" required>
          <el-switch
            v-model="form.expertSignificance"
            active-text="有效"
            inactive-text="无效"
            inactive-color="#FF0000"
            :active-value="'有效'"
            :inactive-value="'无效'"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="专家账号" prop="expertAccount">
          <el-input
            v-model="form.expertAccount"
            placeholder="请输入专家账号"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="专家姓名" prop="expertName">
          <el-input
            v-model="form.expertName"
            placeholder="请输入专家姓名"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="专家职称" prop="expertPosition">
          <el-input
            v-model="form.expertPosition"
            placeholder="请输入专家职称"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="专家所属单位" prop="expertAffiliation">
          <el-input
            v-model="form.expertAffiliation"
            placeholder="请输入专家所属单位"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="一级学科" prop="primaryDiscipline">
          <el-input
            v-model="form.primaryDiscipline"
            placeholder="请输入一级学科"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="二级学科" prop="secondaryDiscipline">
          <el-input
            v-model="form.secondaryDiscipline"
            placeholder="请输入二级学科"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="三级学科" prop="tertiaryDiscipline">
          <el-input
            v-model="form.tertiaryDiscipline"
            placeholder="请输入三级学科"
            style="width: 600px"
          />
        </el-form-item>
        <el-form-item label="研究方向" prop="researchDirection">
          <el-input
            v-model="form.researchDirection"
            placeholder="请输入研究方向"
            style="width: 600px"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 详细信息弹窗 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="1000px" append-to-body>
      <div class="match-detail" style="margin-top: -20px">
        <div class="match-detail-header">
          <h3 class="match-detail-title">专家详细信息</h3>
        </div>
        <el-table :data="[expertDetail]" class="match-detail-table">
          <el-table-column label="专家账号" align="center" prop="expertAccount" />
          <el-table-column label="专家姓名" align="center" prop="expertName" />
          <el-table-column label="专家职称" align="center" prop="expertPosition" />
          <el-table-column label="专家所属单位" align="center" prop="expertAffiliation" />
          <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
          <el-table-column label="一级学科" align="center" prop="primaryDiscipline" />
          <el-table-column label="二级学科" align="center" prop="secondaryDiscipline" />
          <el-table-column label="三级学科" align="center" prop="researchDirection" />
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
                  :content="
                    '项目来源：' + item.dataSource + '，金额：' + item.startFunds + '万元'
                  "
                >
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
                  disabled
                >
                  <span>{{ item }} </span>
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
                  <span>{{ item }} </span>
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
                  <span>{{ item }} </span>
                </el-tooltip>
              </div>
            </div>
          </div>
        </div>
        <div class="match-detail-team">
          <h4>专家团队</h4>
          <div class="match-detail-team-info">
            <span v-for="item in expertDetail.teamMembersArray" :key="item">{{
              item
            }}</span>
            <el-button type="primary" style="float: right" @click="handleECharts(expertDetail)">
              团队关系图
            </el-button>
          </div>
        </div>
      </div>
    </el-dialog>
    <el-dialog :title="chartTitle" :visible.sync="openECharts" append-to-body width="1200px">
      <div id="graph-chart" style="width: 1200px; height: 700px">
        <div :id="echartsId" style="width: 1200px; height: 700px" />
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClassification } from '@/api/kyfz/classification'
import {
addExpert,
delExpert,
getEchartExpertData,
getExpert,
getExpertDetail,
listExpert,
updateExpert
} from '@/api/kyfz/expert'
import * as echarts from 'echarts'
import { departmentOptions } from './departmentOptions'

const getEchartsId = () => {
  return new Date().getTime()
}

export default {
  name: 'Expert',
  inheritAttrs: false,
  data() {
    return {
      // 学院分类
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      filterText: '',
      deptOptions: departmentOptions,
      // echar json数据
      jsonData: {},
      // 行业分类数据
      options: [],
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
      // 专家管理表格数据
      expertList: [],
      // 数据库行业分类表格数据
      classificationList: [],
      expertDetail: [],
      projectList: [],
      thesisList: [],
      workList: [],
      certificateList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      openDetail: false,
      openECharts: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expertAccount: null,
        expertName: null,
        expertPosition: null,
        expertAffiliation: null,
        primaryDiscipline: null,
        secondaryDiscipline: null,
        tertiaryDiscipline: null,
        researchDirection: null,
        thesisId: null,
        projectId: null,
        intellectualPropertyId: null,
        awardsId: null,
        requirementId: null,
        expertTeams: null,
        expertSignificance: null,
        categoryId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      echartsId: null,
      chartTitle: null
    }
  },
  // 学院分类过滤搜索框
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
    this.getList()
    this.getListClassification()
    this.echartsId = getEchartsId()
  },
  methods: {
    // 学院分类触发函数
    handleNodeClick(nodeData) {
      if (this.isLastChildNode(nodeData)) {
        this.reset_queryParams()
        this.queryParams.expertAffiliation = nodeData.label
        listExpert(this.queryParams).then((response) => {
          this.expertList = response.rows
          this.total = response.total
          this.loading = false
        })
      }
    },
    isLastChildNode(nodeData) {
      // 获取当前节点的子节点
      const children = nodeData.children
      // 判断子节点是否为空
      if (!children || children.length === 0) {
        // 如果子节点为空，则当前节点为最后的子节点
        return true
      }
      return false
    },
    // 学院分类过滤搜索框
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 行业分类下拉菜单触发函数
    handleCommand(command) {
      this.reset_queryParams()
      this.queryParams.categoryId = command
      listExpert(this.queryParams).then((response) => {
        this.expertList = response.rows
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
      const graph = this.jsonData // 引入本地json数据文件
      myChart.hideLoading()

      graph.nodes.forEach(function(node) {
        node.label = {
          show: node.symbolSize > 10
        }
      })
      const option = {
        // 添加你的配置
        title: {
          text: '',
          subtext: '',
          top: 'bottom',
          left: 'right'
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
            layout: 'none',
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

    /** 查询专家管理列表 */
    getList() {
      this.loading = true
      listExpert(this.queryParams).then((response) => {
        this.expertList = response.rows
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
        expertId: null,
        expertAccount: null,
        expertName: null,
        expertPosition: null,
        expertAffiliation: null,
        primaryDiscipline: null,
        secondaryDiscipline: null,
        tertiaryDiscipline: null,
        researchDirection: null,
        thesisId: null,
        projectId: null,
        intellectualPropertyId: null,
        awardsId: null,
        requirementId: null,
        expertTeams: null,
        expertSignificance: null
      }
      this.resetForm('form')
    },
    reset_queryParams() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        expertAccount: null,
        expertName: null,
        expertPosition: null,
        expertAffiliation: null,
        primaryDiscipline: null,
        secondaryDiscipline: null,
        tertiaryDiscipline: null,
        researchDirection: null,
        thesisId: null,
        projectId: null,
        intellectualPropertyId: null,
        awardsId: null,
        requirementId: null,
        expertTeams: null,
        expertSignificance: null,
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
      this.ids = selection.map((item) => item.expertId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.form.expertSignificance = '有效'
      this.open = true
      this.title = '添加专家管理'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const expertId = row.expertId || this.ids
      getExpert(expertId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改专家管理'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.expertId != null) {
            updateExpert(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addExpert(this.form).then((response) => {
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
      const expertIds = row.expertId || this.ids
      this.$modal
        .confirm('是否确认删除专家管理编号为"' + expertIds + '"的数据项？')
        .then(function() {
          return delExpert(expertIds)
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
        'kyfz/expert/export',
        {
          ...this.queryParams
        },
        `expert_${new Date().getTime()}.xlsx`
      )
    },
    handleDetail(row) {
      // 表单内容重置
      this.reset()
      this.loading = false
      const expertId = row.expertId
      this.expertDetail.expertName = row.expertName
      this.expertDetail.expertPosition = row.expertPosition // 职称
      this.expertDetail.expertAffiliation = row.expertAffiliation // 所属单位
      this.expertDetail.researchDirection = row.researchDirection // 研究方向
      this.expertDetail.expertAccount = row.expertAccount // 账号

      getExpertDetail(expertId).then((response) => {
        this.expertDetail = response.data
        if (response.data.teamMembers != null) {
          this.expertDetail.teamMembersArray = response.data.teamMembers
            .trim()
            .split(/[,，、]/)
        } else {
          this.expertDetail.teamMembersArray = '无'
        }
        this.openDetail = true
      })
      this.selsect_echart_data(row)
    },
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
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background-color: #f5f7fa;
}

.match-detail-result-info > div {
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
