<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业id" prop="xqId">
        <el-input
          v-model="queryParams.xqId"
          placeholder="请输入企业id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家工号id" prop="zjId">
        <el-input
          v-model="queryParams.zjId"
          placeholder="请输入专家工号id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家匹配分值" prop="ppfz">
        <el-input
          v-model="queryParams.ppfz"
          placeholder="请输入专家匹配分值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专业能力评分" prop="zynlpf">
        <el-input
          v-model="queryParams.zynlpf"
          placeholder="请输入专业能力评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活跃度评分" prop="hydpf">
        <el-input
          v-model="queryParams.hydpf"
          placeholder="请输入活跃度评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="creatBy">
        <el-input
          v-model="queryParams.creatBy"
          placeholder="请输入创建人"
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
          v-hasPermi="['kyfz:Ppxx:add']"
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
          v-hasPermi="['kyfz:Ppxx:edit']"
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
          v-hasPermi="['kyfz:Ppxx:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:Ppxx:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="PpxxList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统id" align="center" prop="id" />
      <el-table-column label="企业id" align="center" prop="xqId" />
      <el-table-column label="专家工号id" align="center" prop="zjId" />
      <el-table-column label="专家匹配分值" align="center" prop="ppfz" />
      <el-table-column label="专业能力评分" align="center" prop="zynlpf" />
      <el-table-column label="活跃度评分" align="center" prop="hydpf" />
      <el-table-column label="创建人" align="center" prop="creatBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:Ppxx:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:Ppxx:remove']"
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
        <el-form-item label="企业id" prop="xqId">
          <el-input v-model="form.xqId" placeholder="请输入企业id" />
        </el-form-item>
        <el-form-item label="专家工号id" prop="zjId">
          <el-input v-model="form.zjId" placeholder="请输入专家工号id" />
        </el-form-item>
        <el-form-item label="专家匹配分值" prop="ppfz">
          <el-input v-model="form.ppfz" placeholder="请输入专家匹配分值" />
        </el-form-item>
        <el-form-item label="专业能力评分" prop="zynlpf">
          <el-input v-model="form.zynlpf" placeholder="请输入专业能力评分" />
        </el-form-item>
        <el-form-item label="活跃度评分" prop="hydpf">
          <el-input v-model="form.hydpf" placeholder="请输入活跃度评分" />
        </el-form-item>
        <el-form-item label="创建人" prop="creatBy">
          <el-input v-model="form.creatBy" placeholder="请输入创建人" />
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
import { listPpxx, getPpxx, delPpxx, addPpxx, updatePpxx } from "@/api/kyfz/Ppxx";

export default {
  name: "Ppxx",
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
      PpxxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xqId: null,
        zjId: null,
        ppfz: null,
        zynlpf: null,
        hydpf: null,
        creatBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        xqId: [
          { required: true, message: "企业id不能为空", trigger: "blur" }
        ],
        zjId: [
          { required: true, message: "专家工号id不能为空", trigger: "blur" }
        ],
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
      listPpxx(this.queryParams).then(response => {
        this.PpxxList = response.rows;
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
        id: null,
        xqId: null,
        zjId: null,
        ppfz: null,
        zynlpf: null,
        hydpf: null,
        creatBy: null,
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
      this.ids = selection.map(item => item.id)
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
      const id = row.id || this.ids
      getPpxx(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改匹配列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePpxx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPpxx(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除匹配列表编号为"' + ids + '"的数据项？').then(function() {
        return delPpxx(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/Ppxx/export', {
        ...this.queryParams
      }, `Ppxx_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
