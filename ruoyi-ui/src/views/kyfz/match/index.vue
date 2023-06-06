<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="匹配编号" prop="matchId">
        <el-input v-model="queryParams.matchId" placeholder="请输入匹配编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="需求" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入需求名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="企业" prop="client">
        <el-input v-model="queryParams.client" placeholder="请输入企业名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="推荐专家" prop="expertName">
        <el-input v-model="queryParams.expertName" placeholder="请输入专家姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="研究方向" prop="researchDirection">
        <el-input v-model="queryParams.researchDirection" placeholder="请输入专家研究方向" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="匹配分值" prop="matchScore">
        <el-input v-model="queryParams.matchScore" placeholder="请输入匹配分值" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handlePush"
          v-hasPermi="['kyfz:match:push']">批量推送</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['kyfz:match:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="matchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="匹配编号" align="center" prop="matchId" />
      <!-- 通过需求id获取需求表中的projectName -->
      <el-table-column label="需求" align="center" prop="projectName" />
      <!-- 通过需求id获取需求表中的委托单位 -->
      <el-table-column label="企业" align="center" prop="client" />
      <!-- 通过专家账号获取专家表中的专家姓名和研究方向 -->
      <el-table-column label="推荐专家" align="center" prop="expertName" />
      <el-table-column label="专家研究方向" align="center" prop="researchDirection" />

      <el-table-column label="匹配分值" align="center" prop="matchScore" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:match:detail']">详细</el-button>

          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:match:push']">推送</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 详细信息弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-table :data="gridData">
        <el-table-column property="matchId" label="匹配编号" width="150"></el-table-column>
        <el-table-column property="projectName" label="需求" width="200"></el-table-column>
        <el-table-column property="client" label="企业"></el-table-column>
        <el-table-column property="expertName" label="专家姓名" width="150"></el-table-column>
        <el-table-column property="researchDirection" label="研究方向" width="200"></el-table-column>
        <el-table-column property="matchScore" label="匹配分值"></el-table-column>
      </el-table>
      <div style="border-top: 2px solid black;border-bottom: 2px solid black;padding:10px 10px">
        <h4>需求关键词</h4>
        <div class="string-info">
          <span v-for="item in stringArray" :key="item">{{ item }}</span>
        </div>
      </div>

      <div style="border-bottom: 2px solid black;padding:10px 10px;">
        <h4>专家研究成果</h4>

      </div>
      <div style="padding:10px 10px;">
        <h4>专家团队</h4>

      </div>
    </el-dialog>

    <!-- 添加或修改匹配列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专家账号" prop="expertAccount">
          <el-input v-model="form.expertAccount" placeholder="请输入专家账号" />
        </el-form-item>
        <el-form-item label="需求表的id" prop="requirementId">
          <el-input v-model="form.requirementId" placeholder="请输入需求表的id" />
        </el-form-item>
        <el-form-item label="匹配分值" prop="matchScore">
          <el-input v-model="form.matchScore" placeholder="请输入匹配分值" />
        </el-form-item>
        <el-form-item label="相关论文" prop="thesisId">
          <el-input v-model="form.thesisId" placeholder="请输入相关论文" />
        </el-form-item>
        <el-form-item label="相关著作" prop="workId">
          <el-input v-model="form.workId" placeholder="请输入相关著作" />
        </el-form-item>
        <el-form-item label="相关证书" prop="certificateId">
          <el-input v-model="form.certificateId" placeholder="请输入相关证书" />
        </el-form-item>
        <el-form-item label="相关项目" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入相关项目" />
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
import { listMatch, getMatch, delMatch, addMatch, updateMatch } from "@/api/kyfz/match";

export default {
  name: "Match",
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expertAccount: null,
        requirementId: null,
        matchScore: null,
        thesisId: null,
        workId: null,
        certificateId: null,
        projectId: null,
        projectName: null,//存需求
        client: null,//存有需求的企业
        expertName: null,//存专家名
        researchDirection: null,//存专家研究方向

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },

      //这里先写一些临时数据，还没从后端获取
      stringInfo: "新材料，鉴别信息，再回收材料",
      stringArray: [],
      gridData: [{
        matchId: '1220',
        projectName: '张三',
        client: '公司名字',
        expertName: '科研辅助系统',
        researchDirection: '计算机视觉',
        matchScore: '9.9',
      }],
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.stringArray = this.stringInfo.split("，");
  },
  methods: {
    /** 查询匹配列表列表 */
    getList() {
      this.loading = true;
      listMatch(this.queryParams).then(response => {
        this.matchList = response.rows;
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
        updateTime: null
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
      this.ids = selection.map(item => item.matchId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加匹配列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const matchId = row.matchId || this.ids
      getMatch(matchId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改匹配列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.matchId != null) {
            updateMatch(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMatch(this.form).then(response => {
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
      const matchIds = row.matchId || this.ids;
      this.$modal.confirm('是否确认删除匹配列表编号为"' + matchIds + '"的数据项？').then(function () {
        return delMatch(matchIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/match/export', {
        ...this.queryParams
      }, `match_${new Date().getTime()}.xlsx`)
    },

    /**详细信息按钮操作 */
    handleDetail() {
      //表单内容重置
      this.reset();
      //获取到当前行匹配信息的id
      const matchId = row.matchId || this.ids

      this.openDetail = true;
      this.title = "详细信息";
      //通过匹配id索引出弹窗需要的信息，因为接口还没做，先弹出对话框先
      /*
      getMatchDetails(matchId).then(response => {
        this.form = response.data;
        this.openDetail = true;
        this.title = "";
      });*/
    }
  }
};
</script>

<style scoped>
.string-info {
  display: flex;
  flex-wrap: wrap;
}

.string-info span {
  margin-right: 10px;
  margin-bottom: 10px;
  border: 1px solid gray;
  padding: 5px;
}
</style>