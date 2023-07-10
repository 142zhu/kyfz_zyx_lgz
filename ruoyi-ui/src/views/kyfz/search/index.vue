<template>
  <div class="app-container">
    <div style="margin-top: 15px">
      <div class="button-container">
        <el-button-group>
          <el-button
            :type="content === '综合搜索' ? 'primary' : 'default'"
            @click="showContent('综合搜索')"
          >综合搜索</el-button>
          <el-button
            :type="content === '搜人才' ? 'primary' : 'default'"
            @click="showContent('搜人才')"
          >搜人才</el-button>
          <el-button
            :type="content === '搜团队' ? 'primary' : 'default'"
            @click="showContent('搜团队')"
          >搜团队</el-button>
          <el-button
            :type="content === '搜企业' ? 'primary' : 'default'"
            @click="showContent('搜企业')"
          >搜企业</el-button>
          <el-button
            :type="content === '搜成果' ? 'primary' : 'default'"
            @click="showContent('搜成果')"
          >搜成果</el-button>
        </el-button-group>
      </div>
      <el-input
        v-model="input3"
        placeholder="请输入内容"
        class="input-with-select"
        style="width: 482px"
      >
        <el-button slot="append" icon="el-icon-search" />
      </el-input>
    </div>
    <div style="margin-top: 40px">
      <div id="content">
        <div v-show="activeTab === '综合搜索'">
          <!-- <el-col :span="3" v-for="(o, index) in 12" :key="o" :offset="1">
            <el-card :body-style="{ padding: '0px' }" style="margin-top: 20px">
              <img src="./test.png" class="image">
              <div style="padding: 14px;">
                <span>计算机行业</span>
              </div>
            </el-card>
          </el-col> -->
          <div>
            <div v-for="row in 2" :key="row" class="menu-row">
              <el-dropdown
                v-for="col in 5"
                :key="col"
                class="custom-dropdown"
                style="margin-right: 80px"
                hide-timeout="800"
              >
                <div class="dropdown-wrapper">
                  <el-button
                    type="primary"
                    style="background-color: #ffffff; color: black"
                  >
                    <div class="image-wrapper">
                      <img src="./test.png" alt="菜单图片" width="50px">
                    </div>
                    计算机行业<i class="el-icon-arrow-down el-icon--right" />
                  </el-button>
                  <el-dropdown-menu
                    ref="menu"
                    slot="dropdown"
                    class="horizontal-menu my-menu"
                  >
                    <el-dropdown-item>计算机硬件</el-dropdown-item>
                    <el-dropdown-item>计算机软件</el-dropdown-item>
                    <el-dropdown-item>计算机应用软件</el-dropdown-item>
                    <el-dropdown-item>云计算</el-dropdown-item>
                    <el-dropdown-item>人工智能</el-dropdown-item>
                    <el-dropdown-item>区块链</el-dropdown-item>
                    <el-dropdown-item>网络空间安全</el-dropdown-item>
                  </el-dropdown-menu>
                </div>
              </el-dropdown>
            </div>
          </div>
        </div>
        <div v-show="activeTab === '搜人才'">
          <div>
            <el-row :gutter="20">
              <!--学院数据-->
              <el-col :span="4" :xs="24">
                <div class="head-container">
                  <el-input
                    v-model="deptName"
                    placeholder="请输入学院名称"
                    clearable
                    size="small"
                    prefix-icon="el-icon-search"
                    style="margin-bottom: 20px"
                  />
                </div>
                <div class="head-container">
                  <el-tree
                    ref="tree"
                    :data="deptOptions"
                    :props="defaultProps"
                    :expand-on-click-node="false"
                    :filter-node-method="filterNode"
                    node-key="id"
                    default-expand-all
                    highlight-current
                    @node-click="handleNodeClick"
                  />
                </div>
              </el-col>
              <!--人才数据-->
              <el-col :span="20" :xs="24">
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
                              <span class="card-value">{{
                                scope.row.expertName
                              }}</span>
                            </div>
                            <div class="card-row">
                              <span class="card-label">专家账号:</span>
                              <span class="card-value">{{
                                scope.row.expertAccount
                              }}</span>
                            </div>
                            <div class="card-row">
                              <span class="card-label">专家职称:</span>
                              <span class="card-value">{{
                                scope.row.expertPosition
                              }}</span>
                            </div>
                            <div class="card-row">
                              <span class="card-label">所属单位:</span>
                              <span
                                class="card-value"
                                :title="scope.row.expertAffiliation"
                              >
                                {{
                                  scope.row.expertAffiliation &&
                                    scope.row.expertAffiliation.length > 15
                                    ? scope.row.expertAffiliation.substring(
                                      0,
                                      15
                                    ) + "..."
                                    : scope.row.expertAffiliation
                                }}
                              </span>
                            </div>
                            <div class="card-row">
                              <span class="card-label">研究方向:</span>
                              <span class="card-value">{{
                                scope.row.researchDirection
                              }}</span>
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
        </div>
        <div v-show="activeTab === '搜团队'"><el-card
          v-for="o in 6"
          :key="o"
          class="box-card"
          :class="o % 2 === 0 ? 'light-blue' : 'blue'"
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
            >张三、李四、王五、赵六</el-descriptions-item>
            <el-descriptions-item
              label="研究方向"
              label-class-name="my-label"
              content-class-name="my-content"
            >人工智能、计算机视觉</el-descriptions-item>
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
              <el-tag
                size="small"
                style="color: rgb(0, 38, 255)"
              >xxxx</el-tag>
              <el-tag
                size="small"
                style="color: rgb(0, 38, 255)"
              >xxxx</el-tag>
            </el-descriptions-item>
            <el-descriptions-item
              label="负责人及联系方式"
              label-class-name="my-label"
              content-class-name="my-content"
            >张三（060-123456）</el-descriptions-item>
            <el-descriptions-item
              label="累计成果"
              label-class-name="my-label"
              content-class-name="my-content"
            > 188 件</el-descriptions-item>
          </el-descriptions>
        </el-card></div>
        <div v-show="activeTab === '搜企业'">
          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item
              name="1"
              class="deploy-setting"
            ><template slot="title">
               <span
                 style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
               >电子行业</span>
             </template>
              <el-card
                v-for="o in 6"
                :key="o"
                class="box-card"
                :class="o % 2 === 0 ? 'light-blue' : 'blue'"
                style="margin-top: 20px"
              >
                <el-descriptions
                  class="margin-top"
                  title="XXX有限公司"
                  :column="3"
                  direction="vertical"
                  style="color: white"
                >
                  <el-descriptions-item
                    label="法定代表人"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >XXX</el-descriptions-item>
                  <el-descriptions-item
                    label="企业信用代码"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >18100000000</el-descriptions-item>
                  <el-descriptions-item
                    label="注册资本"
                    :span="2"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >28,700万人民币</el-descriptions-item>
                  <el-descriptions-item
                    label="备注"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >
                    <el-tag
                      size="small"
                      style="color: rgb(0, 38, 255)"
                    >中关村高兴技术企业</el-tag>
                    <el-tag
                      size="small"
                      style="color: rgb(0, 38, 255)"
                    >世界500强</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item
                    label="联系方式"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >060-123456</el-descriptions-item>
                  <el-descriptions-item
                    label="联系地址"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                </el-descriptions>
              </el-card></el-collapse-item>
            <el-collapse-item name="2">
              <template slot="title">
                <span
                  style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
                >通信行业</span>
              </template></el-collapse-item>
            <el-collapse-item
              name="3"
            ><template slot="title">
              <span
                style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
              >计算机行业</span>
            </template>
            </el-collapse-item>
          </el-collapse>
        </div>
        <div v-show="activeTab === '搜成果'">搜成果的内容</div>
      </div>
    </div>
  </div>
</template>

<script>
import {
addSearch,
delSearch,
getSearch,
listSearch,
updateSearch
} from '@/api/kyfz/search'

export default {
  name: 'Search',
  data() {
    return {
      industries: ['电子', '通信', '计算机', '电子', '通信', '计算机'],
      expertList: [
        {
          expertAccount: 'exp001',
          expertName: '张三',
          expertPosition: '高级研究员',
          expertAffiliation: '计算机学院',
          researchDirection: '人工智能',
          id: 1
        },
        {
          expertAccount: 'exp002',
          expertName: '李四',
          expertPosition: '副教授',
          expertAffiliation: '计算机学院',
          researchDirection: '机器学习',
          id: 2
        }
      ],
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
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      activeTab: '综合搜索',
      deptName: '',
      deptOptions: [
        {
          id: 1,
          label: '中山大学',
          children: [
            {
              id: 2,
              label: '计算机学院'
            },
            {
              id: 3,
              label: '美术学院'
            },
            {
              id: 4,
              label: '音乐学院'
            }
          ]
        }
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  created() {
    this.getList()
  },
  mounted() {
    this.setMenuPosition()
  },
  methods: {
    //
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

.light-blue {
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
