<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="匹配编号" prop="matchId">
        <el-input
          v-model="queryParams.matchId"
          placeholder="请输入匹配编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="需求" prop="projectName">
        <el-input
          v-model="queryParams.matchId"
          placeholder="请输入需求"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="专家" prop="expertName">
        <el-input
          v-model="queryParams.matchId"
          placeholder="请输入专家名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="反馈状态" prop="feebback">
        <el-input
          v-model="queryParams.feebback"
          placeholder="请输入反馈状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推送时间" prop="pushTime">
        <el-date-picker
          clearable
          v-model="queryParams.pushTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择推送时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['kyfz:record:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:record:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="recordList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
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
            size="mini"
            type="text"
            icon="el-icon-document"
            @click="handleDetail(scope.row)"
            v-hasPermi="['kyfz:record:edit']"
            >详情</el-button
          >
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
      <el-table :data="[matchDetails]" style="margin-top: -20px">
        <el-table-column label="匹配编号" align="center" prop="matchId" />
        <!-- 通过需求id获取需求表中的projectName -->
        <el-table-column label="需求" align="center" prop="projectName" />
        <!-- 通过需求id获取需求表中的委托单位 -->
        <el-table-column label="企业" align="center" prop="client" />
        <el-table-column label="推荐专家" align="center" prop="expertName" />
        <el-table-column label="专家研究方向" align="center" prop="researchDirection" />
        <el-table-column label="匹配分值" align="center" prop="matchScore" />
        <el-table-column label="推送时间" align="center" prop="pushTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.pushTime, "{y}-{m}-{d}") }}</span>
          </template>
        </el-table-column>
      </el-table>
      <div
        style="
          border-top: 1px solid black;
          border-bottom: 1px solid black;
          margin-top: 10px;
        "
      >
        <h4>需求关键词</h4>
        <div class="string-info">
          <span v-for="item in matchDetails.requirementKeywordsArray" :key="item"
            >{{ item }}<br
          /></span>
        </div>
      </div>
      <div style="border-bottom: 1px solid black">
        <h4>专家研究成果</h4>
        <div class="string-info">
          <div>
            <h5>相关项目</h5>
            <span v-for="item in matchDetails.projectNamesArray" :key="item">{{
              item
            }}</span>
          </div>
          <div>
            <h5>相关论文</h5>
            <span v-for="item in matchDetails.thesisNamesArray" :key="item">{{
              item
            }}</span>
          </div>
          <div>
            <h5>相关著作</h5>
            <span v-for="item in matchDetails.workNamesArray" :key="item">{{
              item
            }}</span>
          </div>
          <div>
            <h5>相关证书</h5>
            <span v-for="item in matchDetails.certificateNamesArray" :key="item">{{
              item
            }}</span>
          </div>
        </div>
      </div>
      <div>
        <h4>专家团队</h4>
        <div class="string-info">
          <span v-for="item in matchDetails.teamMembersArray" :key="item">{{
            item
          }}</span>
        </div>
      </div>
      <!-- 评分模块 -->
      <div
        class="block"
        style="display: inline-block; position: absolute; top: 30px; right: 60px"
      >
        <span
          style="
            vertical-align: middle;
            display: inline;
            margin-right: 3px;
            line-height: 10px;
            font-size: 10px;
          "
          >反馈状态:&nbsp;</span
        >
        <span style="display: inline; line-height: 10px; font-size: 10px">{{
          matchDetails.feebback
        }}</span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addRecord,
  delRecord,
  getRecord,
  listRecord,
  updateRecord,
} from "@/api/kyfz/push_record";

import { getMatchDetails } from "@/api/kyfz/match";
export default {
  name: "Record",
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
      title: "",
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
        expertName: null,
        feebback: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      matchDetails: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询推送记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then((response) => {
        this.recordList = response.rows;
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
        pushId: null,
        matchId: null,
        pushTime: null,
        feebback: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.ids = selection.map((item) => item.pushId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加推送记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pushId = row.pushId || this.ids;
      getRecord(pushId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改推送记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.pushId != null) {
            updateRecord(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then((response) => {
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
      const pushIds = row.pushId || this.ids;
      this.$modal
        .confirm('是否确认删除推送记录编号为"' + pushIds + '"的数据项？')
        .then(function () {
          return delRecord(pushIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "kyfz/record/export",
        {
          ...this.queryParams,
        },
        `record_${new Date().getTime()}.xlsx`
      );
    },
    /**详细信息按钮操作 */
    handleDetail(row) {
      //表单内容重置
      this.reset();
      //获取到当前行匹配信息的id
      const matchId = row.matchId || this.ids;
      const feebback1 = row.feebback;
      const pushTime = row.pushTime;
      getMatchDetails(matchId).then((response) => {
        this.matchDetails = response.data;
        if (feebback1 == 1) {
          this.matchDetails.feebback = "已点击（有意向）";
        } else if (feebback1 == 1 || feebback1 == null) {
          this.matchDetails.feebback = "未点击";
        } else if (feebback1 == 2) {
          this.matchDetails.feebback = "已点击（无意向）";
        }

        this.matchDetails.pushTime = pushTime;
        this.matchDetails.requirementKeywordsArray = response.data.requirementKeywords
          .trim()
          .split(/[,，、]/);
        this.matchDetails.projectNamesArray = response.data.projectNames
          .trim()
          .split(/[,，、]/);
        this.matchDetails.teamMembersArray = response.data.teamMembers
          .trim()
          .split(/[,，、]/);
        this.matchDetails.thesisNamesArray = response.data.thesisNames
          .trim()
          .split(/[,，、]/);
        this.matchDetails.workNamesArray = response.data.workNames
          .trim()
          .split(/[,，、]/);
        this.matchDetails.certificateNamesArray = response.data.certificateNames
          .trim()
          .split(/[,，、]/);
        this.openDetail = true;
        this.title = "详细信息";
      });
    },
  },
};
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
</style>
