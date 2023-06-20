<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="需求编号" prop="requirementId">
        <el-input v-model="queryParams.requirementId" placeholder="请输入需求编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="需求名称" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入需求名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item label="企业名称" prop="client">
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
        <el-button type="primary" plain icon="el-icon-s-promotion" size="mini" :disabled="multiple" @click="handlePush"
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
      <el-table-column label="需求编号" align="center" prop="requirementId" />
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
          <el-button size="mini" type="text" icon="el-icon-document" @click="handleDetail(scope.row)"
            v-hasPermi="['kyfz:match:detail']">详细</el-button>

          <el-button size="mini" type="text" icon="el-icon-s-promotion" @click="handlePush(scope.row)"
            v-hasPermi="['kyfz:match:push']">推送</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 详细信息弹窗 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="1000px" append-to-body>
      <div class="match-detail" style="margin-top: -20px">
        <div class="match-detail-header">
          <h3 class="match-detail-title">匹配详情</h3>
          <div class="match-detail-star">
            <span>为匹配结果评分</span>
            <el-rate v-model="matchDetails.value2" :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
              @change="changeStarValue(matchDetails.matchId, matchDetails.value2)">
            </el-rate>
          </div>
        </div>
        <el-table :data="[matchDetails]" class="match-detail-table">
          <el-table-column label="匹配编号" align="center" prop="matchId" />
          <el-table-column label="需求编号" align="center" prop="requirementId" />
          <el-table-column label="需求" align="center" prop="projectName" />
          <el-table-column label="企业" align="center" prop="client" />
          <el-table-column label="推荐专家" align="center" prop="expertName" />
          <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
          <el-table-column label="匹配分值" align="center" prop="matchScore" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-edit" @click="handlePush(scope.row)"
                v-has-permission="'kyfz:match:push'">推送</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="match-detail-keywords">
          <h4>需求关键词</h4>
          <div class="match-detail-keywords-info">
            <span v-for="item in matchDetails.requirementKeywordsArray" :key="item">{{ item }}<br /></span>
          </div>
        </div>
        <div class="match-detail-result">
          <h4>专家研究成果</h4>
          <div class="match-detail-result-info">
            <div>
              <h5>相关项目</h5>
              <div class="match-detail-decorate">
                <span class="match-detail-item project" v-for="item in matchDetails.strArray" :key="item">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关论文</h5>
              <div class="match-detail-decorate">
                <span class="match-detail-item thesis" v-for="item in matchDetails.strArray1" :key="item">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关著作</h5>
              <div class="match-detail-decorate">
                <span class="match-detail-item work" v-for="item in matchDetails.strArray2" :key="item">{{ item
                }}</span>
              </div>
            </div>
            <div>
              <h5>相关证书</h5>
              <div class="match-detail-decorate">
                <span class="match-detail-item certificate" v-for="item in matchDetails.strArray3" :key="item">{{ item
                }}</span>
              </div>
            </div>
            <div style="padding-bottom:20px;padding-right:0px!important;">
              <el-button type="primary" @click="handleAllAchievement()"
                style="float: right;margin-right:0px;">所有研究成果</el-button>
            </div>
          </div>

        </div>
        <div class="match-detail-team">
          <h4>专家团队</h4>
          <div class="match-detail-team-info">
            <span v-for="item in matchDetails.teamMembersArray" :key="item">{{
              item
            }}</span>
            <el-button type="primary" @click="handleECharts()" style="float: right;margin-right:0px;">
              团队关系图
            </el-button>
          </div>
        </div>
      </div>
    </el-dialog>
    <el-dialog :title="chartTitle" :visible.sync="openECharts" append-to-body>
      <div id="graph-chart" style="width: 500px; height: 500px">
        <div :id="echartsId" style="width: 500px; height: 500px"></div>
      </div>
    </el-dialog>
    <!-- 展示专家所有成果 -->
    <el-dialog :title="ExpertInfo" :visible.sync="openExpert" append-to-body>
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
          <el-table-column label="三级学科" align="center" prop="tertiaryDiscipline" />
        </el-table>
        <div class="match-detail-result">
          <h4>专家研究成果</h4>
          <div class="match-detail-result-info">
            <div>
              <h5>相关项目</h5>
              <div class="match-detail-decorate">
                <ul>
                  <li style="" v-for="item in expertDetail.projectList" :key="item.projectId"
                    @click="sendProjectId(item.projectId)">
                    <span v-bind:class="{ 'highlight': isHighlighted(item.projectId) }">
                      {{ item.projectName }}</span>
                  </li>
                </ul>
              </div>
            </div>
            <div>
              <h5>相关论文</h5>
              <div class="match-detail-decorate">
                <ul>
                  <li style="" v-for="item in expertDetail.thesisList" :key="item.thesisId"
                    @click="sendThesisId(item.thesisId)">
                    <span v-bind:class="{ 'highlight': isHighlighted1(item.thesisId) }">
                      {{ item.thesisName }}</span>
                  </li>
                </ul>
              </div>
            </div>
            <div>
              <h5>相关著作</h5>
              <div class="match-detail-decorate">
                <ul>
                  <li style="" v-for="item in expertDetail.workList" :key="item.workId" @click="sendWorkId(item.workId)">
                    <span v-bind:class="{ 'highlight': isHighlighted2(item.workId) }">
                      {{ item.workName }}</span>
                  </li>
                </ul>
              </div>
            </div>
            <div>
              <h5>相关证书</h5>
              <div class="match-detail-decorate">
                <ul>
                  <li style="" v-for="item in expertDetail.certificateList" :key="item.certificateId"
                    @click="sendCertificateId(item.certificateId)">
                    <span v-bind:class="{ 'highlight': isHighlighted3(item.certificateId) }">
                      {{ item.certificateName }}</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  addMatch,
  delMatch,
  getMatch,
  getMatchDetails,
  listMatch,
  pushMatch,
  updateMatch,
  updatePushRecord,
} from "@/api/kyfz/match";

import { getExpertDetailByAccount, updateMarkProject, updateMarkThesis, updateMarkWork, updateMarkCertificate } from "@/api/kyfz/expert"
import * as echarts from "echarts";

const getEchartsId = () => {
  return new Date().getTime();
};

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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openDetail: false,
      openECharts: false,
      openExpert: false,
      selectedProjectId: null,
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
        projectName: null, //存需求
        client: null, //存有需求的企业
        expertName: null, //存专家名
        researchDirection: null, //存专家研究方向
        requirementKeywords: "", //需求关键词
        projectNames: "", //专家研究成果：项目（目前就做这个），论文，著作
        expertName: null,
        expertPosition: null,
        expertAffiliation: null,
        primaryDisclipline: null,
        secondaryDiscipline: null,
        tertiaryDiscipline: null,

        thesisName: null,
        workName: null,
        certificateName: null,

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      echartsId: null,
      chartTitle: null,
      selectedId: null,
    };
  },
  created() {
    this.getList();
    this.echartsId = getEchartsId();
  },
  methods: {

    // echarts
    initChart: function () {
      const myChart = echarts.init(document.getElementById(this.echartsId));
      myChart.setOption(this.setOption());
      myChart.resize(); //自适应大小
    },

    // echatrs 数据
    setOption: function () {
      let experts = this.matchDetails.teamMembersArray;
      let nodes = [];
      let links = [];

      // 构造nodes数组
      for (let i = 0; i < experts.length; i++) {
        nodes.push({
          name: experts[i],
          category: i >= 1 ? 1 : 0,
          itemStyle: {
            color: experts[i] != this.matchDetails.expertName ? "#5470C6" : "#EE6666",
          },
        });
      }

      // 构造links数组
      for (let j = 1; j < experts.length; j++) {
        links.push({
          source: experts[0],
          target: experts[j],
        });
      }

      let option = {
        title: {
          text: "",
        },
        tooltip: {
          formatter: function (params) {
            if (params.data.category != 0) {
              return "团队成员";
            }
            return "团队负责人";
          },
        }, //提示框
        animationDurationUpdate: 1500,
        animationEasingUpdate: "quinticInOut",
        series: [
          {
            type: "graph",
            layout: "force",
            // symbolSize: 50, //倘若该属性不在link里，则其表示节点的大小；否则即为线两端标记的大小
            symbolSize: (value, params) => {
              switch (params.data.category) {
                case 0:
                  return 100;
                  break;
                case 1:
                  return 50;
                  break;
              }
            },
            roam: true, //鼠标缩放功能
            label: {
              show: true, //是否显示标签
            },
            focusNodeAdjacency: true, //鼠标移到节点上时突出显示结点以及邻节点和边
            edgeSymbol: ["none", "arrow"], //关系两边的展现形式，也即图中线两端的展现形式。arrow为箭头
            edgeSymbolSize: [4, 10],
            draggable: true,
            edgeLabel: {
              fontSize: 20, //关系（也即线）上的标签字体大小
            },
            force: {
              repulsion: 200,
              edgeLength: 120,
            },

            data: nodes,
            links: links,
            lineStyle: {
              opacity: 0.9,
              width: 2,
              curveness: 0,
            },
          },
        ],
      };
      return option;
    },

    /** 查询匹配列表列表 */
    getList() {
      this.loading = true;
      listMatch(this.queryParams).then((response) => {
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
        updateTime: null,
        markProject: null,
        markWork: null,
        markThesis: null,
        markCertificate: null,
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
      this.ids = selection.map((item) => item.matchId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.openDetail = true;
      this.title = "添加匹配列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const matchId = row.matchId || this.ids;
      getMatch(matchId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改匹配列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.matchId != null) {
            updateMatch(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMatch(this.form).then((response) => {
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
      this.$modal
        .confirm('是否确认删除匹配列表编号为"' + matchIds + '"的数据项？')
        .then(function () {
          return delMatch(matchIds);
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
        "kyfz/match/export",
        {
          ...this.queryParams,
        },
        `match_${new Date().getTime()}.xlsx`
      );
    },

    /**详细信息按钮操作 */
    handleDetail(row) {
      //表单内容重置
      this.reset();
      this.loading = false;
      //获取到当前行匹配信息的id
      //const matchId = (number)((String)(row.matchId).trim());
      const matchId = row.matchId;
      this.matchDetails.expertAccount = row.expertAccount;
      this.expertAccount = row.expertAccount;
      //alert(row.expertAccount);
      // alert(this.matchDetails.expertAccount);
      this.matchDetails.expertName = row.expertName;
      getMatchDetails(matchId).then((response) => {
        this.matchDetails = response.data;
        this.markProject = response.data.markProject;
        this.markWork = response.data.markWork;
        this.markCertificate = response.data.markCertificate;
        this.markThesis = response.data.markThesis;

        if (response.data.requirementKeywords != null) {
          this.matchDetails.requirementKeywordsArray = response.data.requirementKeywords
            .trim()
            .split(/[,，、]/);
        } else {
          this.matchDetails.requirementKeywordsArray = "无";
        }
        if (response.data.strArray != null) {
          this.matchDetails.strArray = response.data.strArray;
        } else {
          this.matchDetails.strArray = "无";
        }
        if (response.data.teamMembers != null) {
          this.matchDetails.teamMembersArray = response.data.teamMembers
            .trim()
            .split(/[,，、]/);
        } else {
          this.matchDetails.teamMembersArray = "无";
        }
        if (response.data.strArray1 != null) {
          this.matchDetails.strArray1 = response.data.strArray1
        } else {
          this.matchDetails.strArray1 = "无";
        }
        if (response.data.strArray2 != null) {
          this.matchDetails.strArray2 = response.data.strArray2;
        } else {
          this.matchDetails.strArray2 = "无";
        }
        if (response.data.strArray3 != null) {
          this.matchDetails.strArray3 = response.data.strArray3
        } else {
          this.matchDetails.strArray3 = "无";
        }
        if (response.data.score != null) {
          this.matchDetails.value2 = response.data.score;
        } else {
          this.matchDetails.value2 = null;
        }
        this.openDetail = true;
        this.title = "详细信息";
      });
    },

    handlePush(row) {
      const matchIds = row.matchId || this.ids;
      this.$modal
        .confirm("是否确认推送所选中的匹配列表？")
        .then(function () {
          return pushMatch(matchIds);
        })
        .then(() => {
          this.$modal.msgSuccess("推送成功");
        })
        .catch(() => { });
    },
    changeStarValue(matchId, value2) {
      const data1 = {};
      data1.matchId = matchId;
      data1.score = value2;
      updatePushRecord(data1).then((response) => {
        this.$modal.msgSuccess("评分成功");
        this.openDetail = false;
        for (let i = 0; i < 1000; i++) { }
        this.openDetail = true;
      });
    },
    handleECharts() {
      this.chartTitle = "团队成员关系图";
      this.openECharts = true;
      this.$nextTick(() => {
        this.initChart();
      });
      this.myChart.resize(); //自适应大小
    },

    handleAllAchievement() {

      //新的弹窗，放专家的信息和所有信息
      this.openExpert = true;
      const markProject = this.markProject
      const markWork = this.markWork;
      const markCertificate = this.markCertificate;
      const markThesis = this.markThesis;
      const expertAccount = this.expertAccount;
      const data = {};
      data.markProject = markProject;
      data.markWork = markWork;
      data.markCertificate = markCertificate;
      data.markThesis = markThesis;
      data.expertAccount = expertAccount;
      //alert(data.expertAccount);
      getExpertDetailByAccount(data).then((response) => {
        this.projectIds = [];
        this.thesisIds = [];
        this.workIds = [];
        this.certificateIds = [];
        this.expertDetail = response.data;
        //人工标注的id（还没处理的）
        if (response.data.markProjectId != null) {
          this.projectIds = response.data.markProjectId;
        }

        if (response.data.markThesisId != null) {
          this.thesisIds = response.data.markThesisId;
        }

        if (response.data.markWorkId != null) {
          this.workIds = response.data.markWorkId;
        }

        if (response.data.markCertificateId != null) {
          this.certificateIds = response.data.markCertificateId;
        }

      });

    },
    sendProjectId(projectId) {
      const data2 = {};
      data2.deleteBool = false;
      data2.matchId = this.matchDetails.matchId;
      data2.projectId = projectId;
      //删除标记
      if (this.projectIds.includes(projectId)) {
        try {
          data2.deleteBool = true;
          const index = this.projectIds.indexOf(projectId);
          if (index !== -1) {
            this.projectIds.splice(index, 1);
          }
          updateMarkProject(data2).then((response) => {
            this.$modal.msgSuccess("去除标记成功");
          });
          return;
        }
        catch (e) {
          alert(e)
        }


      }
      //添加标记
      this.projectIds.push(projectId);
      updateMarkProject(data2).then((response) => {
        this.$modal.msgSuccess("标记成功");
      });
    },
    sendThesisId(thesisId) {
      const data2 = {};
      data2.deleteBool = false;
      data2.matchId = this.matchDetails.matchId;
      data2.thesisId = thesisId;
      if (this.thesisIds.includes(thesisId)) {
        data2.deleteBool = true;
        const index = this.thesisIds.indexOf(thesisId);
        if (index !== -1) {
          this.thesisIds.splice(index, 1);
        }
        updateMarkThesis(data2).then((response) => {
          this.$modal.msgSuccess("去除标记成功");
        });
        return;
      }

      this.thesisIds.push(thesisId);
      updateMarkThesis(data2).then((response) => {
        this.$modal.msgSuccess("标记成功");
      });
    },

    sendWorkId(workId) {
      const data2 = {};
      data2.deleteBool = false;
      data2.matchId = this.matchDetails.matchId;
      data2.workId = workId;
      if (this.workIds.includes(workId)) {
        data2.deleteBool = true;
        const index = this.workIds.indexOf(workId);
        if (index !== -1) {
          this.workIds.splice(index, 1);
        }
        updateMarkWork(data2).then((response) => {
          this.$modal.msgSuccess("去除标记成功");
        });
        return;
      }

      this.workIds.push(workId);
      updateMarkWork(data2).then((response) => {
        this.$modal.msgSuccess("标记成功");
      });
    },

    sendCertificateId(certificateId) {
      const data2 = {};
      data2.deleteBool = false;
      data2.matchId = this.matchDetails.matchId;
      data2.certificateId = certificateId;
      if (this.certificateIds.includes(certificateId)) {
        data2.deleteBool = true;
        const index = this.certificateIds.indexOf(certificateId);
        if (index !== -1) {
          this.certificateIds.splice(index, 1);
        }
        updateMarkCertificate(data2).then((response) => {
          this.$modal.msgSuccess("去除标记成功");
        });
        return;
      }
      this.certificateIds.push(certificateId);
      updateMarkCertificate(data2).then((response) => {
        this.$modal.msgSuccess("标记成功");
      });
    },
    isHighlighted(projectId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.projectIds.includes(projectId);
    },
    isHighlighted2(workId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.workIds.includes(workId);
    },
    isHighlighted1(thesisId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.thesisIds.includes(thesisId);
    },
    isHighlighted3(certificateId) {
      // 返回项目 ID 是否存在于 projectIds 数组中
      return this.certificateIds.includes(certificateId);
    },
  },
};
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

.match-detail-result-info>div {
  margin-bottom: 10px;
}

.match-detail-team-info>div>el-button {
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
