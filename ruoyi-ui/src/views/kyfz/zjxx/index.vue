<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专家工号id" prop="zjId">
        <el-input
          v-model="queryParams.zjId"
          placeholder="请输入专家工号id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求id" prop="xqId">
        <el-input
          v-model="queryParams.xqId"
          placeholder="请输入需求id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="xm">
        <el-input
          v-model="queryParams.xm"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="dh">
        <el-input
          v-model="queryParams.dh"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="yx">
        <el-input
          v-model="queryParams.yx"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院" prop="xy">
        <el-input
          v-model="queryParams.xy"
          placeholder="请输入学院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职称" prop="zc">
        <el-input
          v-model="queryParams.zc"
          placeholder="请输入职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否团队负责人" prop="sffzr">
        <el-select v-model="queryParams.sffzr" placeholder="请选择是否团队负责人" clearable>
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
          v-hasPermi="['kyfz:zjxx:add']"
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
          v-hasPermi="['kyfz:zjxx:edit']"
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
          v-hasPermi="['kyfz:zjxx:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['kyfz:zjxx:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="zjxxList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统id" align="center" prop="id" />
      <el-table-column label="专家工号id" align="center" prop="zjId" />
      <el-table-column label="需求id" align="center" prop="xqId" />
      <el-table-column label="姓名" align="center" prop="xm" />
      <el-table-column label="联系电话" align="center" prop="dh" />
      <el-table-column label="邮箱" align="center" prop="yx" />
      <el-table-column label="学院" align="center" prop="xy" />
      <el-table-column label="研究方向" align="center" prop="yjfx" />
      <el-table-column label="职称" align="center" prop="zc" />
      <el-table-column label="个人简介" align="center" prop="grjj" />
      <el-table-column label="是否团队负责人" align="center" prop="sffzr">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yes_or_no" :value="scope.row.sffzr"/>
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
            v-hasPermi="['kyfz:zjxx:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['kyfz:zjxx:remove']"
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

    <!-- 添加或修改专家信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专家工号id" prop="zjId">
          <el-input v-model="form.zjId" placeholder="请输入专家工号id" />
        </el-form-item>
        <el-form-item label="需求id" prop="xqId">
          <el-input v-model="form.xqId" placeholder="请输入需求id" />
        </el-form-item>
        <el-form-item label="姓名" prop="xm">
          <el-input v-model="form.xm" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="dh">
          <el-input v-model="form.dh" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="yx">
          <el-input v-model="form.yx" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="学院" prop="xy">
          <el-input v-model="form.xy" placeholder="请输入学院" />
        </el-form-item>
        <el-form-item label="研究方向" prop="yjfx">
          <el-input v-model="form.yjfx" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="职称" prop="zc">
          <el-input v-model="form.zc" placeholder="请输入职称" />
        </el-form-item>
        <el-form-item label="个人简介" prop="grjj">
          <el-input v-model="form.grjj" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否团队负责人" prop="sffzr">
          <el-select v-model="form.sffzr" placeholder="请选择是否团队负责人">
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
import { listZjxx, getZjxx, delZjxx, addZjxx, updateZjxx } from "@/api/kyfz/zjxx";

export default {
  name: "Zjxx",
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
      // 专家信息表格数据
      zjxxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        zjId: null,
        xqId: null,
        xm: null,
        dh: null,
        yx: null,
        xy: null,
        yjfx: null,
        zc: null,
        grjj: null,
        sffzr: null,
        creatBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        zjId: [
          { required: true, message: "专家工号id不能为空", trigger: "blur" }
        ],
        xqId: [
          { required: true, message: "需求id不能为空", trigger: "blur" }
        ],
        xm: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        sffzr: [
          { required: true, message: "是否团队负责人不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询专家信息列表 */
    getList() {
      this.loading = true;
      listZjxx(this.queryParams).then(response => {
        this.zjxxList = response.rows;
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
        zjId: null,
        xqId: null,
        xm: null,
        dh: null,
        yx: null,
        xy: null,
        yjfx: null,
        zc: null,
        grjj: null,
        sffzr: null,
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
      this.title = "添加专家信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getZjxx(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专家信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateZjxx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addZjxx(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除专家信息编号为"' + ids + '"的数据项？').then(function() {
        return delZjxx(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('kyfz/zjxx/export', {
        ...this.queryParams
      }, `zjxx_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
