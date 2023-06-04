<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="需求编号" prop="xqId">
        <el-input
          v-model="queryParams.xqId"
          placeholder="请输入需求编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求名称" prop="xqmc">
        <el-input
          v-model="queryParams.xqmc"
          placeholder="请输入需求名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求类型" prop="xqlx">
        <el-input
          v-model="queryParams.xqlx"
          placeholder="请输入需求类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称" prop="qymc">
        <el-input
          v-model="queryParams.qymc"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业简介" prop="qyjj">
        <el-input
          v-model="queryParams.qyjj"
          placeholder="请输入企业简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册资本" prop="zczb">
        <el-input
          v-model="queryParams.zczb"
          placeholder="请输入注册资本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否已读" prop="sfyd">
        <el-select v-model="queryParams.sfyd" placeholder="请选择是否已读" clearable>
          <el-option
            v-for="dict in dict.type.yes_or_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否已反馈" prop="sffk">
        <el-select v-model="queryParams.sffk" placeholder="请选择是否已反馈" clearable>
          <el-option
            v-for="dict in dict.type.yes_or_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['kyfz:tsjl:add']"
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
          v-hasPermi="['kyfz:tsjl:edit']"
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
          v-hasPermi="['kyfz:tsjl:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:tsjl:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tsjlList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统id" align="center" prop="id" />
      <el-table-column label="需求编号" align="center" prop="xqId" />
      <el-table-column label="需求名称" align="center" prop="xqmc" />
      <el-table-column label="需求类型" align="center" prop="xqlx" />
      <el-table-column label="企业名称" align="center" prop="qymc" />
      <el-table-column label="企业简介" align="center" prop="qyjj" />
      <el-table-column label="企业关键词" align="center" prop="qygjc" />
      <el-table-column label="注册资本" align="center" prop="zczb" />
      <el-table-column label="是否已读" align="center" prop="sfyd">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_or_no" :value="scope.row.sfyd"/>
        </template>
      </el-table-column>
      <el-table-column label="是否已反馈" align="center" prop="sffk">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_or_no" :value="scope.row.sffk"/>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="creatBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['kyfz:tsjl:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:tsjl:remove']"
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

    <!-- 添加或修改推送记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="需求编号" prop="xqId">
          <el-input v-model="form.xqId" placeholder="请输入需求编号" />
        </el-form-item>
        <el-form-item label="需求名称" prop="xqmc">
          <el-input v-model="form.xqmc" placeholder="请输入需求名称" />
        </el-form-item>
        <el-form-item label="需求类型" prop="xqlx">
          <el-input v-model="form.xqlx" placeholder="请输入需求类型" />
        </el-form-item>
        <el-form-item label="企业名称" prop="qymc">
          <el-input v-model="form.qymc" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="企业简介" prop="qyjj">
          <el-input v-model="form.qyjj" placeholder="请输入企业简介" />
        </el-form-item>
        <el-form-item label="企业关键词" prop="qygjc">
          <el-input v-model="form.qygjc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="注册资本" prop="zczb">
          <el-input v-model="form.zczb" placeholder="请输入注册资本" />
        </el-form-item>
        <el-form-item label="是否已读" prop="sfyd">
          <el-select v-model="form.sfyd" placeholder="请选择是否已读">
            <el-option
              v-for="dict in dict.type.yes_or_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否已反馈" prop="sffk">
          <el-select v-model="form.sffk" placeholder="请选择是否已反馈">
            <el-option
              v-for="dict in dict.type.yes_or_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listTsjl, getTsjl, delTsjl, addTsjl, updateTsjl } from "@/api/kyfz/tsjl";

export default {
  name: "Tsjl",
  dicts: ['yes_or_no'],
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
      tsjlList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xqId: null,
        xqmc: null,
        xqlx: null,
        qymc: null,
        qyjj: null,
        qygjc: null,
        zczb: null,
        sfyd: null,
        sffk: null,
        creatBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        xqId: [
          { required: true, message: "需求编号不能为空", trigger: "blur" }
        ],
        xqlx: [
          { required: true, message: "需求类型不能为空", trigger: "blur" }
        ],
        qymc: [
          { required: true, message: "企业名称不能为空", trigger: "blur" }
        ],
        qyjj: [
          { required: true, message: "企业简介不能为空", trigger: "blur" }
        ],
        sfyd: [
          { required: true, message: "是否已读不能为空", trigger: "change" }
        ],
        sffk: [
          { required: true, message: "是否已反馈不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询推送记录列表 */
    getList() {
      this.loading = true;
      listTsjl(this.queryParams).then(response => {
        this.tsjlList = response.rows;
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
        xqmc: null,
        xqlx: null,
        qymc: null,
        qyjj: null,
        qygjc: null,
        zczb: null,
        sfyd: null,
        sffk: null,
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
      this.title = "添加推送记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTsjl(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改推送记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTsjl(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTsjl(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除推送记录编号为"' + ids + '"的数据项？').then(function() {
        return delTsjl(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/tsjl/export', {
        ...this.queryParams
      }, `tsjl_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
