<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专家账号" prop="expertAccount">
        <el-input
          v-model="queryParams.expertAccount"
          placeholder="请输入专家账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家姓名" prop="expertName">
        <el-input
          v-model="queryParams.expertName"
          placeholder="请输入专家姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家职称" prop="expertPosition">
        <el-input
          v-model="queryParams.expertPosition"
          placeholder="请输入专家职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家所属单位" prop="expertAffiliation">
        <el-input
          v-model="queryParams.expertAffiliation"
          placeholder="请输入专家所属单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一级学科" prop="primaryDiscipline">
        <el-input
          v-model="queryParams.primaryDiscipline"
          placeholder="请输入一级学科"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级学科" prop="secondaryDiscipline">
        <el-input
          v-model="queryParams.secondaryDiscipline"
          placeholder="请输入二级学科"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三级学科" prop="tertiaryDiscipline">
        <el-input
          v-model="queryParams.tertiaryDiscipline"
          placeholder="请输入三级学科"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="研究方向" prop="researchDirection">
        <el-input
          v-model="queryParams.researchDirection"
          placeholder="请输入研究方向"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="论文id(多个)" prop="thesisId">
        <el-input
          v-model="queryParams.thesisId"
          placeholder="请输入论文id(多个)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目id(多个)" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目id(多个)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证书id(多个)" prop="certificateId">
        <el-input
          v-model="queryParams.certificateId"
          placeholder="请输入证书id(多个)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="著作id(多个)" prop="workId">
        <el-input
          v-model="queryParams.workId"
          placeholder="请输入著作id(多个)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已对接需求" prop="requirementId">
        <el-input
          v-model="queryParams.requirementId"
          placeholder="请输入已对接需求"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属团队id" prop="expertTeams">
        <el-input
          v-model="queryParams.expertTeams"
          placeholder="请输入所属团队id"
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
          v-hasPermi="['kyfz:expert:add']"
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
          v-hasPermi="['kyfz:expert:edit']"
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
          v-hasPermi="['kyfz:expert:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:expert:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="expertList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="专家id" align="center" prop="expertId" />
      <el-table-column label="专家账号" align="center" prop="expertAccount" />
      <el-table-column label="专家姓名" align="center" prop="expertName" />
      <el-table-column label="专家职称" align="center" prop="expertPosition" />
      <el-table-column label="专家所属单位" align="center" prop="expertAffiliation" />
      <el-table-column label="一级学科" align="center" prop="primaryDiscipline" />
      <el-table-column label="二级学科" align="center" prop="secondaryDiscipline" />
      <el-table-column label="三级学科" align="center" prop="tertiaryDiscipline" />
      <el-table-column label="研究方向" align="center" prop="researchDirection" />
      <el-table-column label="论文id(多个)" align="center" prop="thesisId" />
      <el-table-column label="项目id(多个)" align="center" prop="projectId" />
      <el-table-column label="证书id(多个)" align="center" prop="certificateId" />
      <el-table-column label="著作id(多个)" align="center" prop="workId" />
      <el-table-column label="已对接需求" align="center" prop="requirementId" />
      <el-table-column label="所属团队id" align="center" prop="expertTeams" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:expert:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:expert:remove']"
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

    <!-- 添加或修改专家管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专家账号" prop="expertAccount">
          <el-input v-model="form.expertAccount" placeholder="请输入专家账号" />
        </el-form-item>
        <el-form-item label="专家姓名" prop="expertName">
          <el-input v-model="form.expertName" placeholder="请输入专家姓名" />
        </el-form-item>
        <el-form-item label="专家职称" prop="expertPosition">
          <el-input v-model="form.expertPosition" placeholder="请输入专家职称" />
        </el-form-item>
        <el-form-item label="专家所属单位" prop="expertAffiliation">
          <el-input v-model="form.expertAffiliation" placeholder="请输入专家所属单位" />
        </el-form-item>
        <el-form-item label="一级学科" prop="primaryDiscipline">
          <el-input v-model="form.primaryDiscipline" placeholder="请输入一级学科" />
        </el-form-item>
        <el-form-item label="二级学科" prop="secondaryDiscipline">
          <el-input v-model="form.secondaryDiscipline" placeholder="请输入二级学科" />
        </el-form-item>
        <el-form-item label="三级学科" prop="tertiaryDiscipline">
          <el-input v-model="form.tertiaryDiscipline" placeholder="请输入三级学科" />
        </el-form-item>
        <el-form-item label="研究方向" prop="researchDirection">
          <el-input v-model="form.researchDirection" placeholder="请输入研究方向" />
        </el-form-item>
        <el-form-item label="论文id(多个)" prop="thesisId">
          <el-input v-model="form.thesisId" placeholder="请输入论文id(多个)" />
        </el-form-item>
        <el-form-item label="项目id(多个)" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目id(多个)" />
        </el-form-item>
        <el-form-item label="证书id(多个)" prop="certificateId">
          <el-input v-model="form.certificateId" placeholder="请输入证书id(多个)" />
        </el-form-item>
        <el-form-item label="著作id(多个)" prop="workId">
          <el-input v-model="form.workId" placeholder="请输入著作id(多个)" />
        </el-form-item>
        <el-form-item label="已对接需求" prop="requirementId">
          <el-input v-model="form.requirementId" placeholder="请输入已对接需求" />
        </el-form-item>
        <el-form-item label="所属团队id" prop="expertTeams">
          <el-input v-model="form.expertTeams" placeholder="请输入所属团队id" />
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
import { listExpert, getExpert, delExpert, addExpert, updateExpert } from "@/api/kyfz/expert";

export default {
  name: "Expert",
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
      // 专家管理表格数据
      expertList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        certificateId: null,
        workId: null,
        requirementId: null,
        expertTeams: null
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
    /** 查询专家管理列表 */
    getList() {
      this.loading = true;
      listExpert(this.queryParams).then(response => {
        this.expertList = response.rows;
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
        certificateId: null,
        workId: null,
        requirementId: null,
        expertTeams: null
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
      this.ids = selection.map(item => item.expertId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加专家管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const expertId = row.expertId || this.ids
      getExpert(expertId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专家管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.expertId != null) {
            updateExpert(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExpert(this.form).then(response => {
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
      const expertIds = row.expertId || this.ids;
      this.$modal.confirm('是否确认删除专家管理编号为"' + expertIds + '"的数据项？').then(function() {
        return delExpert(expertIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/expert/export', {
        ...this.queryParams
      }, `expert_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
