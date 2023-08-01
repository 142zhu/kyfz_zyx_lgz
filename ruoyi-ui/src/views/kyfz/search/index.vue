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
      <el-input v-model="search_queryParams.keyWord" placeholder="请输入内容" class="input-with-select" style="width: 448px">

        <el-button slot="append" type="primary" icon="el-icon-search" @click="searchAll">搜索</el-button>
      </el-input>
    </div>
    <div style="margin-top: 40px">
      <div id="content">
        <div v-show="activeTab === '综合搜索'">
          <div v-show="activeTab2 === '行业标签'">
            <el-tabs ref="myTabs" v-model="el_tab_pane1" type="border-card" class="tab-container">
              <el-tab-pane
                v-for="item in firstTenCategories"
                :key="item.categoryId"
                class="tab-pane"
                :label="item.categoryName"
                :name="item.categoryId"
              >
                <span slot="label" class="pane-span"><i class="el-icon-date" />{{ item.categoryName }}</span>
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
                <span slot="label" class="pane-span"><i class="el-icon-date" />{{ item.categoryName }}</span>
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
              <el-table-column label="相关信息" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-card class="card-item">
                    <el-col :span="4">
                      <el-descriptions>
                        <div class="card-expertName" />
                        <el-descriptions-item>
                          <div class="card-expertName">
                            {{ scope.row.expertName }}
                          </div>
                        </el-descriptions-item>
                      </el-descriptions>
                    </el-col>
                    <el-col :span="20">
                      <el-descriptions>
                        <el-descriptions-item label="专家名称" :span="1">{{ scope.row.expertName }}</el-descriptions-item>
                        <el-descriptions-item label="专家账号">{{ scope.row.expertAccount }}</el-descriptions-item>
                        <el-descriptions-item label="专家职称">{{ scope.row.expertPosition }}</el-descriptions-item>
                        <el-descriptions-item label="所在单位">{{ scope.row.expertAffiliation }}</el-descriptions-item>
                        <el-descriptions-item label="所属行业">{{ scope.row.categoryNames }}</el-descriptions-item>
                        <el-descriptions-item label="研究方向">{{ scope.row.researchDirection }}</el-descriptions-item>
                        <el-descriptions-item label="专家团队">{{ scope.row.teamMembers }}</el-descriptions-item>
                      </el-descriptions>
                    </el-col>
                  </el-card>
                </template>
              </el-table-column>
            </el-table>
            <pagination
              v-show="total >= 0"
              :total="total"
              :page.sync="panebuttonClick_queryParams.pageNum"
              :limit.sync="panebuttonClick_queryParams.pageSize"
              @pagination="Comprehensive_search_page_change"
            />
          </div>
        </div>
        <div v-show="activeTab === '搜人才'">
          <el-table v-loading="loading" :data="expertList" style="width: 1020px">
            <el-table-column label="人才信息" align="center" class-name="small-padding fixed-width">
              <div slot-scope="scope" class="expert-card">
                <div class="card-name">
                  <div class="rounded" />
                  <div class="name">{{ scope.row.expertName }}</div>
                </div>
                <div class="frame-1">
                  <div class="frame-2">
                    <div class="frame-3">
                      <div class="affiliation">所在单位：{{ scope.row.expertAffiliation }}</div>
                      <div class="industry">所属行业：{{ scope.row.categoryNames }}</div>
                      <div class="position">职称：{{ scope.row.expertPosition }}</div>
                      <div class="position">账号：{{ scope.row.expertAccount }}</div>
                    </div>
                    <div class="research-direction">
                      <div class="research-direction-text">{{ scope.row.researchDirection }}</div>
                    </div>
                  </div>
                  <div class="team">
                    <div class="expert-teams">{{ scope.row.teamMembers }}</div>
                  </div>
                </div>
              </div>
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
          <el-card v-for="item in teamList" :key="item.teamId" class="box-card blue" style="margin-top: 20px">
            <el-descriptions class="margin-top" title="团队信息" :column="3" direction="vertical" style="color: white">
              <el-descriptions-item label="团队成员" label-class-name="my-label" content-class-name="my-content">{{ item.teamMembers.slice(0, 18) }}{{ item.teamMembers.length > 18 ? '...' : '' }}</el-descriptions-item>
              <el-descriptions-item label="研究方向" label-class-name="my-label" content-class-name="my-content">{{
                item.reseachDirections }}</el-descriptions-item>
              <el-descriptions-item
                label="累计专利"
                :span="2"
                label-class-name="my-label"
                content-class-name="my-content"
              >1000 个</el-descriptions-item>
              <el-descriptions-item label="操作" label-class-name="my-label" content-class-name="my-content">
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click"
                  :content="item.teamMembers"
                >
                  <el-button slot="reference">查看全部成员</el-button>
                </el-popover> </el-descriptions-item>
              <el-descriptions-item label="备注" label-class-name="my-label" content-class-name="my-content">
                <el-tag size="small" style="color: rgb(0, 38, 255)">xxxx</el-tag>
                <el-tag size="small" style="color: rgb(0, 38, 255)">xxxx</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="累计成果" label-class-name="my-label" content-class-name="my-content">
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
            style="width: 780px"
            @selection-change="handleSelectionChange"
          >
            <el-table-column label="企业信息" align="center" class-name="small-padding fixed-width">
              <div slot-scope="scope" class="enterprise-card">
                <div class="enterprise-1">
                  <div class="enterprise-2">
                    <div class="enterprise-3">
                      <div class="enterprise-name">{{ scope.row.enterpriseName }}</div>

                      <div class="enterprise-4">
                        <div class="industry2">{{ scope.row.categoryNames }}</div>
                      </div>

                      <div class="position-1">{{ scope.row.registeredCapital }}</div>

                      <div class="position-2">{{ scope.row.enterpriseKeywords }}</div>
                    </div>

                    <div class="research-direction2">
                      <div class="research-direction-text2">{{ scope.row.enterpriseDescribe.slice(0, 118) }}{{ scope.row.enterpriseDescribe.length > 118 ? '......' : '' }}                      <el-popover
                        placement="right"
                        width="400"
                        trigger="click"
                        :content="scope.row.enterpriseDescribe"
                      >
                        <el-button slot="reference" type="text">查看全部</el-button>
                      </el-popover></div>
                    </div>
                  </div>
                </div>
              </div>
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
      // 综合搜索两种换页的标记
      Comprehensive_mark: null,
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
      // 行业标签的查询参数
      panebuttonClick_queryParams: {
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
    // 综合搜索两种换页的函数
    Comprehensive_search_page_change() {
      if (this.Comprehensive_mark === '输入框搜索') {
        this.searchAll()
      } else if (this.Comprehensive_mark === '行业标签搜索') {
        this.panebuttonClick_listExpert()
      }
    },
    // 搜索输入框点击函数
    searchAll() {
      this.Comprehensive_mark = '输入框搜索'
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
      this.Comprehensive_mark = '行业标签搜索'
      this.panebuttonClick_queryParams.categoryId = command
      this.activeTab2 = '选择行业后数据显示'
      listExpert(this.panebuttonClick_queryParams).then((response) => {
        this.expertList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 行业标签换页
    panebuttonClick_listExpert() {
      listExpert(this.panebuttonClick_queryParams).then((response) => {
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
        .catch(() => { })
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
  white-space: nowrap;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
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

.card-expertName {
  display: flex;
  /* 使用 flex 布局 */
  align-items: center;
  /* 垂直居中 */
  justify-content: center;
  /* 水平居中 */
  height: 80px;
  width: 80px;
  color: #385c9c;
  background-color: #e8f4ff;
}
</style>

<style scoped>
/* 综合搜索标签页 */
.tab-container {
  margin-bottom: 100px;
  /* 添加下间隔 */
  width: 1100px;
  height: 150px;
}

.pane-span {
  font-size: 14px;
  /* 更改字号为20px */
  letter-spacing: 2px;
  /* 增加字间距为2px */
  width: 80px;
  /* 设置span宽度为130px */
  color: #000;
}

.pane-button {
  margin-right: 10px;
  /* 增大按钮右侧的间距为10px */
  font-size: 16px;
  /* 增大按钮字体为16px */
}
</style>

<style scoped>
/* 专家信息卡片 */
.expert-card,
.expert-card * {
  box-sizing: border-box;
}
.expert-card {
  background: linear-gradient(
    91.14deg,
    rgba(255, 253, 253, 1) 0%,
    rgba(255, 255, 255, 0.74) 100%
  );
  border-radius: 11px;
  border-style: solid;
  border-color: #e0e6ed;
  border-width: 2px;
  padding: 40px 32px 32px 32px;
  display: flex;
  flex-direction: row;
  gap: 86px;
  align-items: center;
  justify-content: center;
  width: 1000px;
  height: 140px;
  position: relative;
  box-shadow: 0px 7px 14px 0px rgba(211, 218, 226, 1);
  overflow: hidden;
}
.card-name {
  flex-shrink: 0;
  width: 94px;
  height: 95px;
  position: relative;
}
.rounded {
  background: rgba(232, 244, 255, 0.8);
  border-radius: 200px;
  border-style: solid;
  border-color: #e8f4ff;
  border-width: 1px;
  width: 94px;
  height: 95px;
  position: absolute;
  left: 0px;
  top: 0px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}
.name {
  color: #385c9c;
  text-align: center;
  font: 600 20px/160% "Open Sans", sans-serif;
  position: absolute;
  left: calc(50% - 56px);
  top: calc(50% - 59px);
  width: 113px;
  height: 119px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.frame-1 {
  display: flex;
  flex-direction: column;
  gap: 17px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 583px;
  position: relative;
}
.frame-2 {
  display: flex;
  flex-direction: column;
  gap: 8px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}
.frame-3 {
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-wrap: wrap;
  flex-shrink: 0;
  width: 583px;
  position: relative;
}
.affiliation {
  color: #3b3f5c;
  text-align: left;
  font: 700 14px/160% "Open Sans", sans-serif;
  position: relative;
  width: 273px;
}
.industry {
  color: #3b3f5c;
  text-align: left;
  font: 700 14px/160% "Open Sans", sans-serif;
  position: relative;
  width: 273px;
}
.position {
  color: #000000;
  text-align: left;
  font: 400 14px/160% "Open Sans", sans-serif;
  opacity: 0.5;
  position: relative;
  width: 273px;
}
.research-direction {
  background: #edf6ff;
  border-radius: 3px;
  padding: 0px 16px 0px 16px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 583px;
  height: 21px;
  position: relative;
  overflow: hidden;
}
.research-direction-text {
  color: #000000;
  text-align: center;
  font: 400 14px/160% "Acme", sans-serif;
  position: relative;
  width: 573px;
}
.team {
  background: #ecf6ff;
  border-radius: 4px;
  padding: 0px 16px 0px 16px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 583px;
  height: 22px;
  position: relative;
  overflow: hidden;
}
.expert-teams {
  color: #000000;
  text-align: center;
  font: 400 14px/160% "Open Sans", sans-serif;
  position: relative;
  width: 583px;
  height: 22px;
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.team .expert-teams {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100%;
}
</style>

<style scoped>
/* 企业卡片 */
.enterprise-card,
.enterprise-card * {
  box-sizing: border-box;
}
.enterprise-card {
  background: linear-gradient(
    91.14deg,
    rgba(255, 253, 253, 1) 0%,
    rgba(255, 255, 255, 0.74) 100%
  );
  border-radius: 11px;
  border-style: solid;
  border-color: #e0e6ed;
  border-width: 2px;
  padding: 40px 32px 32px 61px;
  width: 746px;
  height: 235px;
  position: relative;
  box-shadow: 0px 7px 14px 0px rgba(211, 218, 226, 1);
  overflow: hidden;
}
.enterprise-1 {
  display: flex;
  flex-direction: column;
  gap: 17px;
  align-items: flex-start;
  justify-content: flex-start;
  width: 687px;
  height: 179px;
  position: absolute;
  left: 46px;
  top: 32px;
}
.enterprise-2 {
  display: flex;
  flex-direction: column;
  gap: 8px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}
.enterprise-3 {
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-wrap: wrap;
  flex-shrink: 0;
  width: 583px;
  height: 103px;
  position: relative;
}
.enterprise-name {
  color: #3b3f5c;
  text-align: left;
  font: 700 16px/160% "Open Sans", sans-serif;
  position: relative;
  width: 583px;
  height: 29px;
}
.enterprise-4 {
  border-radius: 6px;
  flex-shrink: 0;
  width: 320px;
  height: 32px;
  position: relative;
}
.industry2 {
  color: #3b3f5c;
  text-align: center;
  font: 600 14px/160% "Open Sans", sans-serif;
  position: absolute;
  left: 0px;
  top: -1px;
  width: 320px;
  height: 33px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.position-1 {
  color: #000000;
  text-align: center;
  font: 600 14px/160% "Open Sans", sans-serif;
  opacity: 0.800000011920929;
  position: relative;
  width: 247px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.position-2 {
  color: rgba(255, 0, 0, 0.8);
  text-align: left;
  font: 400 14px/160% "Open Sans", sans-serif;
  opacity: 0.800000011920929;
  position: relative;
  width: 583px;
  height: 42px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.research-direction2 {
  background: #edf6ff;
  border-radius: 9px;
  padding: 0px 16px 0px 16px;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 583px;
  height: 73px;
  position: relative;
  overflow: hidden;
}
.research-direction-text2 {
  color: #000000;
  text-align: left;
  font: 400 14px/160% "Acme", sans-serif;
  position: relative;
  width: 573px;
  height: 63px;
}

</style>
