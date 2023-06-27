<template>
  <div class="app-container home">
    <div class="header" style="text-align:center;padding-top:100px">
      <div style="display:inline-block">
        <div style="display:inline-block;margin-right:40px">
          <h3 style="font-size:30px">已匹配的需求</h3>
          <!-- <span>{{ finishMatchCount }}</span> -->
          <span style="font-size:25px">20000</span>
        </div>
        <el-progress type="circle" style="transform: scale(1.5);display:inline-block;"
          :percentage="matchPercent"></el-progress>
      </div>

      <div style="display:inline-block;margin-left:50px">
        <div style="display:inline-block;margin-right:40px">
          <h3 style="font-size:30px">已推送匹配</h3>
          <!-- <span>{{ finishMatchCount }}</span> -->
          <span style="font-size:25px">20000</span>
        </div>
        <el-progress type="circle" style="transform: scale(1.5);display:inline-block;"
          :percentage="pushPercent"></el-progress>
      </div>

    </div>
    <div>
      <el-icon name="el-icon-search"></el-icon>
    </div>
    <div class="container" style="padding:20px;">

      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="project" style="color:black" />
        </i>
        <h3>项目情况</h3>
        <p>已入库：{{ infoList.projectCount }}</p>
      </el-card>

      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="thesis" />
        </i>
        <h3>论文情况</h3>
        <p>已入库：{{ infoList.thesisCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="work" />
        </i>
        <h3>著作情况</h3>
        <p>已入库：{{ infoList.workCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="certificate" />
        </i>
        <h3>证书情况</h3>
        <p>已入库：{{ infoList.certificateCount }}</p>
      </el-card>

      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="expert" />
        </i>
        <h3>专家情况</h3>
        <p>已入库：{{ infoList.expertCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="match" style="color:black" />
        </i>
        <h3>匹配情况</h3>
        <p>已入库：{{ infoList.matchCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="requirement" />
        </i>
        <h3>需求情况</h3>
        <p>已入库：{{ infoList.matchCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="push" />
        </i>
        <h3>推送情况</h3>
        <p>已入库：{{ infoList.pushCount }}</p>
      </el-card>
      <el-card class="card" v-hover-shadow>
        <i>
          <svg-icon icon-class="enterprise" style="color:black" />
        </i>
        <h3>企业情况</h3>
        <p>已入库：{{ infoList.enterpriseCount }}</p>
      </el-card>
    </div>
  </div>
</template>

<script>
//import '../assets/icons/svg/bug.svg';
import { getAllCount } from "@/api/kyfz/push_record"
export default {
  name: "Index",
  data() {
    return {
      form: {
        projectCount: null,//已入库项目数量
        thesisCount: null,//已入库论文数量
        workCount: null,//已入库著作数量
        certificateCount: null,//已入库证书数量
        expertCount: null,//已入库专家数目
        matchCount: null,//已匹配的数目
        pushCount: null,//已经推送的数目
        enterpriseCount: null,//已入库的企业数目
        requirementCount: null,//已入库的需求数目
      },
      infoList: [],
      count: null,
    };
  },
  created() {
    this.getCount();
  },
  methods: {
    getCount() {
      getAllCount().then((response) => {
        this.workCount = response.rows.workCount;
        alert(this.workCount)
      });
    }
  },

};
</script>

<style scoped lang="scss">
.header {
  height: 300px;
  border-bottom: 1px solid black;
  margin-bottom: 40px;
}

.header .el-progress__circle {
  transform: scale(4) !important;
  /* 将元素放大为原来的 2 倍 */
}

.header .card .icon {
  display: inline-block;
  width: 1em;
  height: 1em;

}

.container {
  margin: 0 auto;
}

.container .card {
  margin: 25px;
}

.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}

.card {
  position: relative;
  width: 20%;
  display: inline-block;
  overflow: visible;
  /* 默认情况下无阴影 */
  box-shadow: none;
  border-radius: 10px;
  border: 2px solid rgba(1, 1, 1, 0.2);
  transition: box-shadow 0.3s ease-in-out;
}

.card:hover {
  /* 悬停时添加阴影效果 */
  box-shadow: 0px 0px 10px #888888;
}

.card i {
  position: absolute;
  top: -30%;
  left: 5%;

  padding: 10px;
  border-radius: 50%;
}

.card i .svg-icon {
  color: #fff;
  font-size: 35px;
  vertical-align: middle;
  /*可选项，使图标垂直居中*/
}
</style>
