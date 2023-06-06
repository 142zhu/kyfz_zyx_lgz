<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="需求表的id" prop="requirementId">
        <el-input
          v-model="queryParams.requirementId"
          placeholder="请输入需求表的id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="匹配分值" prop="matchScore">
        <el-input
          v-model="queryParams.matchScore"
          placeholder="请输入匹配分值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相关论文" prop="thesisId">
        <el-input
          v-model="queryParams.thesisId"
          placeholder="请输入相关论文"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相关著作" prop="workId">
        <el-input
          v-model="queryParams.workId"
          placeholder="请输入相关著作"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相关证书" prop="certificateId">
        <el-input
          v-model="queryParams.certificateId"
          placeholder="请输入相关证书"
          clearable
          @keyup.enter.native="handleQuery"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['kyfz:match:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['kyfz:match:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['kyfz:match:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:match:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="matchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="匹配编号" align="center" prop="matchId" />
      <el-table-column label="需求表的id" align="center" prop="requirementId" />
      <el-table-column label="匹配分值" align="center" prop="matchScore" />
      <el-table-column label="相关论文" align="center" prop="thesisId" />
      <el-table-column label="相关著作" align="center" prop="workId" />
      <el-table-column label="相关证书" align="center" prop="certificateId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:match:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:match:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改匹配列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
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
        requirementId: null,
        matchScore: null,
        thesisId: null,
        workId: null,
        certificateId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
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
        requirementId: null,
        matchScore: null,
        thesisId: null,
        workId: null,
        certificateId: null,
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
      this.single = selection.length!==1
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
      this.$modal.confirm('是否确认删除匹配列表编号为"' + matchIds + '"的数据项？').then(function() {
        return delMatch(matchIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/match/export', {
        ...this.queryParams
      }, `match_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
