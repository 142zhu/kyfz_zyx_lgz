<template>
  <div class="app-container">
    <div style="margin-top: 15px">
      <div class="button-container">
        <el-button-group>
          <el-button
            :type="activeTab === '综合搜索' ? 'primary' : 'default'"
            style="width: 90px"
            @click="showContent('综合搜索')"
          >综合搜索</el-button>
          <el-button
            :type="activeTab === '搜人才' ? 'primary' : 'default'"
            style="width: 90px"
            @click="showContent('搜人才')"
          >搜人才</el-button>
          <el-button
            :type="activeTab === '搜团队' ? 'primary' : 'default'"
            style="width: 90px"
            @click="showContent('搜团队')"
          >搜团队</el-button>
          <el-button
            :type="activeTab === '搜企业' ? 'primary' : 'default'"
            s
            style="width: 90px"
            @click="showContent('搜企业')"
          >搜企业</el-button>
          <el-button
            :type="activeTab === '搜成果' ? 'primary' : 'default'"
            style="width: 90px"
            @click="showContent('搜成果')"
          >搜成果</el-button>
        </el-button-group>
      </div>
      <el-input
        v-model="search_queryParams.keyWord"
        placeholder="请输入内容"
        class="input-with-select"
        style="width: 448px"
      >

        <el-button slot="append" type="primary" icon="el-icon-search" @click="searchAll">搜索</el-button>
      </el-input>
    </div>
    <div style="margin-top: 40px">
      <div id="content">
        <div v-show="activeTab === '综合搜索'">
          <div v-show="activeTab2 === '行业标签'">
            <el-tabs
              ref="myTabs"
              v-model="el_tab_pane1"
              type="border-card"
              class="tab-container"
            >
              <el-tab-pane
                v-for="item in firstTenCategories"
                :key="item.categoryId"
                class="tab-pane"
                :label="item.categoryName"
                :name="item.categoryId"
              >
                <span
                  slot="label"
                  class="pane-span"
                ><i class="el-icon-date" />{{ item.categoryName }}</span>
                <el-button
                  v-for="childItem in item.children"
                  :key="childItem.categoryId"
                  type="text"
                  class="pane-button"
                  @click="panebuttonClick(childItem.categoryId)"
                >
                  {{ childItem.categoryName }}
                </el-button>
              </el-tab-pane>
            </el-tabs>
            <el-tabs v-model="el_tab_pane2" type="border-card" class="tab-container">
              <el-tab-pane
                v-for="item in remainingCategories"
                :key="item.categoryId"
                :label="item.categoryName"
                :name="item.categoryId"
              >
                <span
                  slot="label"
                  class="pane-span"
                ><i class="el-icon-date" />{{ item.categoryName }}</span>
                <el-button
                  v-for="childItem in item.children"
                  :key="childItem.categoryId"
                  type="text"
                  class="pane-button"
                  @click="panebuttonClick(childItem.categoryId)"
                >
                  {{ childItem.categoryName }}
                </el-button>
              </el-tab-pane>
            </el-tabs>
          </div>
          <div v-show="activeTab2 === '选择行业后数据显示'">
            <el-table v-loading="loading" style="width: 1000px" :data="expertList">
              <el-table-column
                label="相关信息"
                align="center"
                class-name="small-padding fixed-width"
              >
                <template slot-scope="scope">
                  <el-card class="card-item">
                    <div class="card-actions">
                      <div class="card-content">
                        <div class="card-row">
                          <el-avatar
                            shape="square"
                            :size="100"
                          ><span style="font-size: larger; color: white">{{
                            scope.row.expertName
                          }}</span></el-avatar>
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
                          <span class="card-label">研究方向:</span>
                          <span class="card-value">{{
                            scope.row.researchDirection
                          }}</span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">专家团队:</span>
                          <span class="card-value" style="width: 800px">{{
                            scope.row.teamMembers
                          }}</span>
                        </div>
                      </div>
                    </div>
                  </el-card>
                </template>
              </el-table-column>
            </el-table>
            <pagination
              v-show="total >= 0"
              :total="total"
              :page.sync="search_queryParams.pageNum"
              :limit.sync="search_queryParams.pageSize"
              @pagination="searchAll"
            />
          </div>
        </div>
        <div v-show="activeTab === '搜人才'">
          <el-table
            v-loading="loading"
            :data="expertList"
            style="width: 1000px"
          >
            <el-table-column
              label="人才信息"
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
                        <span class="card-label">所属行业:</span>
                        <span class="card-value" :title="scope.row.categoryNames">
                          {{
                            scope.row.categoryNames && scope.row.categoryNames.length > 15
                              ? scope.row.categoryNames.substring(0, 15) + "..."
                              : scope.row.categoryNames
                          }}
                        </span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">研究方向:</span>
                        <span class="card-value">{{ scope.row.researchDirection }}</span>
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
            :page.sync="search_queryParams.pageNum"
            :limit.sync="search_queryParams.pageSize"
            @pagination="searchAll"
          />
        </div>
        <div v-show="activeTab === '搜团队'">
          <el-card
            v-for="item in teamList"
            :key="item.teamId"
            class="box-card blue"
            style="margin-top: 20px"
          >
            <el-descriptions
              class="margin-top"
              title="团队信息"
              :column="3"
              direction="vertical"
              style="color: white"
            >
              <el-descriptions-item
                label="团队成员"
                label-class-name="my-label"
                content-class-name="my-content"
              >{{ item.teamMembers }}</el-descriptions-item>
              <el-descriptions-item
                label="研究方向"
                label-class-name="my-label"
                content-class-name="my-content"
              >{{ item.reseachDirections }}</el-descriptions-item>
              <el-descriptions-item
                label="累计专利"
                :span="2"
                label-class-name="my-label"
                content-class-name="my-content"
              >1000 个</el-descriptions-item>
              <el-descriptions-item
                label="备注"
                label-class-name="my-label"
                content-class-name="my-content"
              >
                <el-tag size="small" style="color: rgb(0, 38, 255)">xxxx</el-tag>
                <el-tag size="small" style="color: rgb(0, 38, 255)">xxxx</el-tag>
              </el-descriptions-item>
              <el-descriptions-item
                label="成员账号"
                label-class-name="my-label"
                content-class-name="my-content"
              >{{ item.teamAccount }}</el-descriptions-item>
              <el-descriptions-item
                label="累计成果"
                label-class-name="my-label"
                content-class-name="my-content"
              >
                188 件</el-descriptions-item>
            </el-descriptions>
          </el-card>
          <pagination
            v-show="total > 0"
            :total="total"
            :page.sync="search_queryParams.pageNum"
            :limit.sync="search_queryParams.pageSize"
            @pagination="searchAll"
          />
        </div>
        <div v-show="activeTab === '搜企业'">
          <el-table
            v-loading="loading"
            :data="enterpriseList"
            style="width: 1000px"
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              label="企业信息"
              align="center"
              class-name="small-padding fixed-width"
            >
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
                        <span class="card-value">{{
                          scope.row.enterpriseCreditCode
                        }}</span>
                      </div>
                      <div class="card-row">
                        <span class="card-label">企业描述:</span>
                        <span class="card-value" :title="scope.row.enterpriseDescribe">
                          {{
                            scope.row.enterpriseDescribe &&
                              scope.row.enterpriseDescribe.length > 13
                              ? scope.row.enterpriseDescribe.substring(0, 13) + "..."
                              : scope.row.enterpriseDescribe
                          }}
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
                            scope.row.categoryNames && scope.row.categoryNames.length > 15
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
                  </div>
                </el-card>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total > 0"
            :total="total"
            :page.sync="search_queryParams.pageNum"
            :limit.sync="search_queryParams.pageSize"
            @pagination="searchAll"
          />
        </div>
        <div v-show="activeTab === '搜成果'">搜成果的内容</div>
      </div>
    </div>
  </div>
</template>

<script>
import { listClassification } from '@/api/kyfz/classification'
import { listEnterprise } from '@/api/kyfz/enterprise'
import { listExpert } from '@/api/kyfz/expert'
import {
addSearch,
clickSearch,
delSearch,
getSearch,
listSearch,
updateSearch
} from '@/api/kyfz/search'
import { listTeam } from '@/api/kyfz/team'

export default {
  name: 'Search',
  data() {
    return {
      // 企业管理表格数据
      enterpriseList: [],
      // 团队列表数据
      teamList: [],
      // 专家管理表格数据
      expertList: [],
      expertDetail: [],
      // 标签页选中的数据
      el_tab_pane1: [],
      el_tab_pane2: [],
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
      // 检索表格数据
      searchList: [],
      // 数据库行业分类表格数据
      classificationList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 搜索框输入查询参数
      search_queryParams: {
        pageNum: 1,
        pageSize: 10,
        keyWord: null,
        mark: '综合搜索'
      },
      // 企业查询参数
      enterprise_queryParams: {
        pageNum: 1,
        pageSize: 10,
        enterpriseName: null,
        enterpriseCreditCode: null,
        enterpriseDescribe: null,
        registeredCapital: null,
        enterpriseKeywords: null,
        categoryId: null
      },
      // 团队查询参数
      team_queryParams: {
        pageNum: 1,
        pageSize: 10,
        teamId: null,
        teamMembers: null,
        teamAccount: null,
        memberScores: null,
        reseachDirections: null
      },
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
      activeTab: '综合搜索',
      activeTab2: '行业标签',
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  // 标签页行业分页分行显示
  computed: {
    firstTenCategories() {
      return this.classificationList.slice(0, 10)
    },
    remainingCategories() {
      return this.classificationList.slice(10)
    }
  },
  created() {
    this.getList()
    this.getListClassification()
    this.getexpertList()
    this.getEnterpriseList()
    this.getTeam()
  },
  mounted() {
    this.setMenuPosition()
  },
  methods: {
    // 搜索输入框点击函数
    searchAll() {
      clickSearch(this.search_queryParams).then((response) => {
        this.loading = true
        if (this.search_queryParams.mark === '综合搜索') {
          this.expertList = response.rows
          this.total = response.total
          this.activeTab2 = '选择行业后数据显示'
          this.loading = false
        } else if (this.search_queryParams.mark === '搜人才') {
          this.expertList = response.rows
          this.total = response.total
          this.loading = false
        } else if (this.search_queryParams.mark === '搜团队') {
          this.teamList = response.rows
          this.total = response.total
          this.loading = false
        } else if (this.search_queryParams.mark === '搜企业') {
          this.enterpriseList = response.rows
          this.total = response.total
          this.loading = false
        }
      })
    },
    // 团队信息
    getTeam() {
      this.loading = true
      listTeam(this.team_queryParams).then((response) => {
        this.teamList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 企业信息
    /** 查询企业管理列表 */
    getEnterpriseList() {
      this.loading = true
      listEnterprise(this.enterprise_queryParams).then((response) => {
        this.enterpriseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 专家信息
    /** 查询专家管理列表 */
    // 专家搜索换页
    getexpertList() {
      this.loading = true
      listExpert(this.queryParams).then((response) => {
        this.expertList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 行业分类下拉菜单触发函数
    panebuttonClick(command) {
      this.reset_queryParams()
      this.queryParams.categoryId = command
      this.activeTab2 = '选择行业后数据显示'
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
    // 未知
    setMenuPosition() {
      const firstDropdown = this.$refs.firstDropdown.$el
      const menu = this.$refs.menu

      if (firstDropdown && menu) {
        const dropdownRect = firstDropdown.getBoundingClientRect()
        menu.style.left = `${dropdownRect.left}px`
      }
    },
    // 学院数据显示
    filterNode(value, data) {
      if (!value) return true
      return data.label.toLowerCase().includes(value.toLowerCase())
    },
    handleNodeClick(data) {
      console.log(data)
    },
    // 点击按钮切换界面
    showContent(tabName) {
      this.activeTab = tabName
      this.search_queryParams.mark = tabName
      if (this.activeTab === '综合搜索') {
        this.activeTab2 = '行业标签'
      }
    },
    /** 查询检索列表 */
    getList() {
      this.loading = true
      listSearch(this.queryParams).then((response) => {
        this.searchList = response.rows
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
        searchId: null
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
      this.ids = selection.map((item) => item.searchId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加检索'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const searchId = row.searchId || this.ids
      getSearch(searchId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改检索'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.searchId != null) {
            updateSearch(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addSearch(this.form).then((response) => {
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
      const searchIds = row.searchId || this.ids
      this.$modal
        .confirm('是否确认删除检索编号为"' + searchIds + '"的数据项？')
        .then(function() {
          return delSearch(searchIds)
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
        'kyfz/search/export',
        {
          ...this.queryParams
        },
        `search_${new Date().getTime()}.xlsx`
      )
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
    }
  }
}
</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  /* 调整垂直位置 */
}

.el-button-group {
  width: 100%;
  /* 设置按钮组宽度为100% */
}

.el-button-group .el-button {
  flex: 1;
  /* 设置按钮的宽度自适应 */
}

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.margin-top .my-label,
.margin-top .my-content {
  color: white;
}

.box-card {
  border-radius: 15px;
}

.el-collapse {
  width: 1200px;
}

.el-collapse-item__header {
  color: black;
}

.el-collapse-item__arrow {
  float: left;
  margin-left: 5px;
  margin-right: 15px;
}

.el-collapse {
  border: 0;
}

.deploy-setting .el-collapse-item__header {
  border-bottom: 1px solid #2c8df4;
}

.deploy-setting .el-collapse-item__wrap {
  border-bottom: 0px;
}

/* 综合搜索 */
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>

<style>
.blue {
  background-color: rgb(41, 64, 106);
}

.my-label {
  background: rgb(41, 64, 106);
}

.my-content {
  background: rgb(41, 64, 106);
}

.margin-top .my-label,
.margin-top .my-content {
  color: white;
}
</style>

<style scoped>
.dropdown-wrapper .image-wrapper {
  margin-bottom: 10px;
}

.custom-dropdown .el-dropdown-menu {
  background-color: white;
}

.horizontal-menu {
  display: flex;
  flex-wrap: wrap;
}

.horizontal-menu .el-dropdown-item {
  flex-basis: 100%;
}

.menu-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 100px;
}

.my-menu {
  left: 300px !important;
  width: 950px;
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
  height: 100px;
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
/* 综合搜索标签页 */
.tab-container {
  margin-bottom: 100px; /* 添加下间隔 */
  width: 1100px;
  height: 150px;
}
.pane-span {
  font-size: 14px; /* 更改字号为20px */
  letter-spacing: 2px; /* 增加字间距为2px */
  width: 80px; /* 设置span宽度为130px */
  color: #000;
}

.pane-button {
  margin-right: 10px; /* 增大按钮右侧的间距为10px */
  font-size: 16px; /* 增大按钮字体为16px */
}
</style>
