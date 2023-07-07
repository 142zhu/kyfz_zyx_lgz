<template>
  <div class="app-container">
    <div style="margin-top: 15px">
      <div class="button-container">
        <el-button-group>
          <el-button :type="content === '综合搜索' ? 'primary' : 'default'" @click="showContent('综合搜索')">综合搜索</el-button>
          <el-button :type="content === '搜人才' ? 'primary' : 'default'" @click="showContent('搜人才')">搜人才</el-button>
          <el-button :type="content === '搜团队' ? 'primary' : 'default'" @click="showContent('搜团队')">搜团队</el-button>
          <el-button :type="content === '搜企业' ? 'primary' : 'default'" @click="showContent('搜企业')">搜企业</el-button>
          <el-button :type="content === '搜成果' ? 'primary' : 'default'" @click="showContent('搜成果')">搜成果</el-button>
        </el-button-group>
      </div>
      <el-input placeholder="请输入内容" v-model="input3" class="input-with-select" style="width: 482px">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <div style="margin-top: 40px">
      <div id="content">
        <div v-show="activeTab === '综合搜索'">
          <el-col :span="3" v-for="(o, index) in 12" :key="o" :offset="1">
            <el-card :body-style="{ padding: '0px' }" style="margin-top: 20px">
              <img src="./test.png" class="image">
              <div style="padding: 14px;">
                <span>计算机行业</span>
              </div>
            </el-card>
          </el-col>
        </div>
        <div v-show="activeTab === '搜人才'">
          <div>
            <el-row :gutter="20">
              <!--学院数据-->
              <el-col :span="4" :xs="24">
                <div class="head-container">
                  <el-input v-model="deptName" placeholder="请输入学院名称" clearable size="small" prefix-icon="el-icon-search"
                    style="margin-bottom: 20px" />
                </div>
                <div class="head-container">
                  <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false"
                    :filter-node-method="filterNode" ref="tree" node-key="id" default-expand-all highlight-current
                    @node-click="handleNodeClick" />
                </div>
              </el-col>
              <!--人才数据-->
              <el-col :span="20" :xs="24">
                <el-table v-loading="loading" :data="expertList" @selection-change="handleSelectionChange">
                  <el-table-column type="selection" width="55" align="center" />
                  <el-table-column label="专家账号" align="center" prop="expertAccount" />
                  <el-table-column label="专家姓名" align="center" prop="expertName" />
                  <el-table-column label="专家职称" align="center" prop="expertPosition" />
                  <el-table-column label="专家所属单位" align="center" prop="expertAffiliation" />
                  <el-table-column label="研究方向" align="center" prop="researchDirection" />
                  <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                      <el-button size="mini" type="text" icon="el-icon-edit"
                        @click="handleDetail(scope.row)">详情</el-button>
                      <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['kyfz:expert:edit']">修改</el-button>
                      <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['kyfz:expert:remove']">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize" @pagination="getList" />
              </el-col>
            </el-row>
          </div>
        </div>
        <div v-show="activeTab === '搜团队'">搜团队的内容</div>
        <div v-show="activeTab === '搜企业'">
          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item name="1" class="deploy-setting"><template slot="title">
                <span style="float: left; font-weight: bold; font-size: 14px; color: #2c8df4">电子行业</span>
              </template>
              <el-card class="box-card" v-for="o in 6" :key="o" :class="o % 2 === 0 ? 'light-blue' : 'blue'"
                style="margin-top: 20px">
                <el-descriptions class="margin-top" title="XXX有限公司" :column="3" direction="vertical" style="color: white">
                  <el-descriptions-item label="法定代表人" label-class-name="my-label"
                    content-class-name="my-content">XXX</el-descriptions-item>
                  <el-descriptions-item label="企业信用代码" label-class-name="my-label"
                    content-class-name="my-content">18100000000</el-descriptions-item>
                  <el-descriptions-item label="注册资本" :span="2" label-class-name="my-label"
                    content-class-name="my-content">28,700万人民币</el-descriptions-item>
                  <el-descriptions-item label="备注" label-class-name="my-label" content-class-name="my-content">
                    <el-tag size="small" style="color: rgb(0, 38, 255)">中关村高兴技术企业</el-tag>
                    <el-tag size="small" style="color: rgb(0, 38, 255)">世界500强</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item label="联系方式" label-class-name="my-label"
                    content-class-name="my-content">060-123456</el-descriptions-item>
                  <el-descriptions-item label="联系地址" label-class-name="my-label"
                    content-class-name="my-content">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                </el-descriptions>
              </el-card></el-collapse-item>
            <el-collapse-item name="2">
              <template slot="title">
                <span style="float: left; font-weight: bold; font-size: 14px; color: #2c8df4">通信行业</span>
              </template></el-collapse-item>
            <el-collapse-item name="3"><template slot="title">
                <span style="float: left; font-weight: bold; font-size: 14px; color: #2c8df4">计算机行业</span>
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
  updateSearch,
} from "@/api/kyfz/search";

export default {
  name: "Search",
  data() {
    return {
      industries: ['电子', '通信', '计算机', '电子', '通信', '计算机'],
      expertList: [
        {
          expertAccount: "exp001",
          expertName: "张三",
          expertPosition: "高级研究员",
          expertAffiliation: "计算机学院",
          researchDirection: "人工智能",
          id: 1
        },
        {
          expertAccount: "exp002",
          expertName: "李四",
          expertPosition: "副教授",
          expertAffiliation: "计算机学院",
          researchDirection: "机器学习",
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
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      activeTab: "综合搜索",
      deptName: '',
      deptOptions: [
        {
          id: 1,
          label: '中山大学',
          children: [
            {
              id: 2,
              label: '计算机学院',
            },
            {
              id: 3,
              label: '美术学院',
            },
            {
              id: 4,
              label: '音乐学院',
            },
          ],
        },
      ],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 学院数据显示
    filterNode(value, data) {
      if (!value) return true;
      return data.label.toLowerCase().includes(value.toLowerCase());
    },
    handleNodeClick(data) {
      console.log(data);
    },
    // 点击按钮切换界面
    showContent(tabName) {
      this.activeTab = tabName;
    },
    /** 查询检索列表 */
    getList() {
      this.loading = true;
      listSearch(this.queryParams).then((response) => {
        this.searchList = response.rows;
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
        searchId: null,
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
      this.ids = selection.map((item) => item.searchId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检索";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const searchId = row.searchId || this.ids;
      getSearch(searchId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检索";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.searchId != null) {
            updateSearch(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSearch(this.form).then((response) => {
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
      const searchIds = row.searchId || this.ids;
      this.$modal
        .confirm('是否确认删除检索编号为"' + searchIds + '"的数据项？')
        .then(function () {
          return delSearch(searchIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "kyfz/search/export",
        {
          ...this.queryParams,
        },
        `search_${new Date().getTime()}.xlsx`
      );
    },
  },
};
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
  clear: both
}
</style>

<style >
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
