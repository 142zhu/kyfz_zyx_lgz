<template>
  <div class="app-container">
    <div style="margin-top: 15px">
      <div class="button-container">
        <el-button-group>
          <el-button
            :type="content === '综合搜索' ? 'primary' : 'default'"
            @click="showContent('综合搜索')"
          >综合搜索</el-button>
          <el-button
            :type="content === '搜人才' ? 'primary' : 'default'"
            @click="showContent('搜人才')"
          >搜人才</el-button>
          <el-button
            :type="content === '搜团队' ? 'primary' : 'default'"
            @click="showContent('搜团队')"
          >搜团队</el-button>
          <el-button
            :type="content === '搜企业' ? 'primary' : 'default'"
            @click="showContent('搜企业')"
          >搜企业</el-button>
          <el-button
            :type="content === '搜成果' ? 'primary' : 'default'"
            @click="showContent('搜成果')"
          >搜成果</el-button>
        </el-button-group>
      </div>
      <el-input
        v-model="input3"
        placeholder="请输入内容"
        class="input-with-select"
        style="width: 482px"
      >
        <el-button slot="append" icon="el-icon-search" />
      </el-input>
    </div>
    <div style="margin-top: 40px">
      <div id="content">
        <div v-show="activeTab === '综合搜索'">
          <div v-show="activeTab2 === '行业标签'">
            <el-tabs ref="myTabs" v-model="el_tab_pane1" type="border-card" class="tab-container">
              <el-tab-pane v-for="item in firstTenCategories" :key="item.categoryId" class="tab-pane" :label="item.categoryName" :name="item.categoryId">
                <span slot="label" class="pane-span"><i class="el-icon-date" />{{ item.categoryName }}</span>
                <el-button v-for="childItem in item.children" :key="childItem.categoryId" type="text" class="pane-button" @click="panebuttonClick(childItem.categoryId)">
                  {{ childItem.categoryName }}
                </el-button>
              </el-tab-pane>
            </el-tabs>
            <el-tabs v-model="el_tab_pane2" type="border-card" class="tab-container">
              <el-tab-pane v-for="item in remainingCategories" :key="item.categoryId" :label="item.categoryName" :name="item.categoryId">
                <span slot="label" class="pane-span"><i class="el-icon-date" />{{ item.categoryName }}</span>
                <el-button v-for="childItem in item.children" :key="childItem.categoryId" type="text" class="pane-button" @click="panebuttonClick(childItem.categoryId)">
                  {{ childItem.categoryName }}
                </el-button>
              </el-tab-pane>
            </el-tabs>
          </div>
          <div v-show="activeTab2 === '选择行业后数据显示'">
            <el-table
              v-loading="loading"
              style="width:1300px"
              :data="expertList"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55" align="center" />
              <el-table-column
                label="相关信息"
                align="center"
                class-name="small-padding fixed-width"
              >
                <template slot-scope="scope">
                  <el-card class="card-item">
                    <div class="card-actions">
                      <div class="card-content">
                        <div class="card-row">
                          <span class="card-label">专家姓名:</span>
                          <span class="card-value">{{ scope.row.expertName }}</span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">专家账号:</span>
                          <span class="card-value">{{ scope.row.expertAccount }}</span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">专家职称:</span>
                          <span class="card-value">{{ scope.row.expertPosition }}</span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">所属单位:</span>
                          <span class="card-value" :title="scope.row.expertAffiliation">
                            {{
                              scope.row.expertAffiliation &&
                                scope.row.expertAffiliation.length > 15
                                ? scope.row.expertAffiliation.substring(0, 15) + "..."
                                : scope.row.expertAffiliation
                            }}
                          </span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">所属行业:</span>
                          <span class="card-value" :title="scope.row.categoryNames">
                            {{
                              scope.row.categoryNames &&
                                scope.row.categoryNames.length > 15
                                ? scope.row.categoryNames.substring(0, 15) + "..."
                                : scope.row.categoryNames
                            }}
                          </span>
                        </div>
                        <div class="card-row">
                          <span class="card-label">研究方向:</span>
                          <span class="card-value">{{ scope.row.researchDirection }}</span>
                        </div>
                      </div>
                    </div>
                  </el-card>
                </template>
              </el-table-column>
            </el-table>
            <pagination
              v-show="total >= 0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getexpertList"
            />
          </div>
        </div>
        <div v-show="activeTab === '搜人才'">
          <dl
            v-for="o in 6"
            :key="o"
            style="margin: 50px; block-size: 184px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1392px; line-height: 16.38px; list-style-type: none; outline-color: rgb(48, 49, 51); perspective-origin: 696px 92px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);"
            data-v-34fcf4fd=""
          ><dt style="block-size: 184px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1392px; line-height: 16.38px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); perspective-origin: 696px 92px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 696px 92px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: 100%;" data-v-34fcf4fd=""><h2 style="background-color: rgb(232, 244, 255); block-size: 70px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-bottom-left-radius: 2px; border-bottom-right-radius: 2px; border-end-end-radius: 2px; border-end-start-radius: 2px; border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-start-end-radius: 2px; border-start-start-radius: 2px; border-top-color: rgb(56, 92, 156); border-top-left-radius: 2px; border-top-right-radius: 2px; box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: default; font-family: Microsoft Yahei, arial; font-weight: 400; inline-size: 70px; line-height: 70px; list-style-type: none; margin-block-end: 0px; margin-block-start: 0px; margin-bottom: 0px; margin-inline-end: 12px; margin-left: 0; margin-right: 12px; margin-top: 0px; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); perspective-origin: 35px 35px; text-align: center; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); transform-origin: 35px 35px; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156); width: 70px;" data-v-34fcf4fd="">C</h2> <div style="block-size: 184px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1310px; line-height: 16.38px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); perspective-origin: 655px 92px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 655px 92px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: calc(100% - 82px);" data-v-34fcf4fd="" class="patent-data-box"><h4 style="block-size: 16px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: pointer; font-family: Microsoft Yahei, arial; font-size: 16px; font-weight: 400; inline-size: 1310px; line-height: 16px; list-style-type: none; margin-block-end: 16px; margin-block-start: 0px; margin-bottom: 16px; margin-left: 0; margin-right: 0; margin-top: 0px; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 655px 8px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 655px 8px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" title="C·L·马林斯" class="cursor ellipsis keyword-hover">C·L·马林斯</h4> <div style="block-size: 52px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1310px; line-height: 16.38px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 655px 26px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 655px 26px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" class="patent-data-box-ul"><div style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: border-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; float: left; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 436.615px; line-height: 20px; list-style-type: none; margin-block-end: 12px; margin-bottom: 12px; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); padding-inline-end: 15px; padding-right: 15px; perspective-origin: 218.302px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 218.307px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: 33.33%;" data-v-34fcf4fd=""><span style="block-size: 20px; border-block-end-color: rgb(126, 131, 140); border-block-start-color: rgb(126, 131, 140); border-bottom-color: rgb(126, 131, 140); border-inline-end-color: rgb(126, 131, 140); border-inline-start-color: rgb(126, 131, 140); border-left-color: rgb(126, 131, 140); border-right-color: rgb(126, 131, 140); border-top-color: rgb(126, 131, 140); box-sizing: content-box; caret-color: rgb(126, 131, 140); color: rgb(126, 131, 140); column-rule-color: rgb(126, 131, 140); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 70px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(126, 131, 140); perspective-origin: 35px 10px; text-align: left; text-decoration: none solid rgb(126, 131, 140); text-decoration-color: rgb(126, 131, 140); text-emphasis-color: rgb(126, 131, 140); text-wrap: nowrap; transform-origin: 35px 10px; -webkit-text-fill-color: rgb(126, 131, 140); -webkit-text-stroke-color: rgb(126, 131, 140);" data-v-34fcf4fd="">所属机构：</span> <em style="block-size: 20px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-top-color: rgb(56, 92, 156); box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: pointer; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 168px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); overflow-x: hidden; overflow-y: hidden; perspective-origin: 84px 10px; text-align: left; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 84px 10px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156);" data-v-34fcf4fd="" title="微软技术许可有限责任公司" class="cursor mainColor keyword-hover">微软技术许可有限责任公司</em></div> <div style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: border-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; float: left; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 436.615px; line-height: 20px; list-style-type: none; margin-block-end: 12px; margin-bottom: 12px; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); padding-inline-end: 15px; padding-right: 15px; perspective-origin: 218.302px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 218.307px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: 33.33%;" data-v-34fcf4fd=""><span style="background-image: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjxkZWZzPjxwYXRoIGlkPSJhIiBkPSJNMCAwaDE0djE0SDB6Ii8+PC9kZWZzPjxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+PG1hc2sgaWQ9ImIiIGZpbGw9IiNmZmYiPjx1c2UgeGxpbms6aHJlZj0iI2EiLz48L21hc2s+PHBhdGggZD0iTTYuODMzIDFjMi42NyAwIDQuODM0IDIuMjE5IDQuODM0IDQuOTU2IDAgMi4wODctMS41MjIgNC4xNzQtNC41NjUgNi4yNjFhLjQ3My40NzMgMCAwMS0uNTM3IDBDMy41MjIgMTAuMTI1IDIgOC4wMzggMiA1Ljk1NyAyIDMuMjE4IDQuMTY0IDEgNi44MzMgMXptMCAuOTkxYy0yLjEzNSAwLTMuODY2IDEuNzc1LTMuODY2IDMuOTY1IDAgMS42MjcgMS4yNDggMy4zOTIgMy44NjcgNS4yNUM5LjQ1MiA5LjM1MyAxMC43IDcuNTg4IDEwLjcgNS45NTZjMC0yLjE5LTEuNzMxLTMuOTY1LTMuODY3LTMuOTY1em0wIDEuOTgzYzEuMDY4IDAgMS45MzQuODg3IDEuOTM0IDEuOTgyUzcuOSA3Ljk0IDYuODMzIDcuOTRDNS43NjYgNy45MzkgNC45IDcuMDUgNC45IDUuOTU2YzAtMS4wOTUuODY2LTEuOTgyIDEuOTMzLTEuOTgyem0wIC45OTFhLjk4Ljk4IDAgMDAtLjk2Ni45OTEuOTguOTggMCAwMC45NjYuOTkxLjk4Ljk4IDAgMDAuOTY3LS45OS45OC45OCAwIDAwLS45NjctLjk5MnoiIGZpbGw9IiM3RTgzOEMiIG1hc2s9InVybCgjYikiLz48L2c+PC9zdmc+); background-position: 0px 50%; background-repeat: no-repeat; block-size: 20px; border-block-end-color: rgb(126, 131, 140); border-block-start-color: rgb(126, 131, 140); border-bottom-color: rgb(126, 131, 140); border-inline-end-color: rgb(126, 131, 140); border-inline-start-color: rgb(126, 131, 140); border-left-color: rgb(126, 131, 140); border-right-color: rgb(126, 131, 140); border-top-color: rgb(126, 131, 140); box-sizing: content-box; caret-color: rgb(126, 131, 140); color: rgb(126, 131, 140); column-rule-color: rgb(126, 131, 140); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 0px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(126, 131, 140); padding-inline-start: 18px; padding-left: 18px; perspective-origin: 9px 10px; text-align: left; text-decoration: none solid rgb(126, 131, 140); text-decoration-color: rgb(126, 131, 140); text-emphasis-color: rgb(126, 131, 140); text-wrap: nowrap; transform-origin: 9px 10px; -webkit-text-fill-color: rgb(126, 131, 140); -webkit-text-stroke-color: rgb(126, 131, 140); height: 20px;" data-v-34fcf4fd="" class="address" /> <em style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 56px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 28px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 28px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="">暂无数据</em></div> <div style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: border-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; float: left; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 436.615px; line-height: 20px; list-style-type: none; margin-block-end: 12px; margin-bottom: 12px; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); padding-inline-end: 15px; padding-right: 15px; perspective-origin: 218.302px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 218.307px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: 33.33%;" data-v-34fcf4fd=""><span style="block-size: 20px; border-block-end-color: rgb(126, 131, 140); border-block-start-color: rgb(126, 131, 140); border-bottom-color: rgb(126, 131, 140); border-inline-end-color: rgb(126, 131, 140); border-inline-start-color: rgb(126, 131, 140); border-left-color: rgb(126, 131, 140); border-right-color: rgb(126, 131, 140); border-top-color: rgb(126, 131, 140); box-sizing: content-box; caret-color: rgb(126, 131, 140); color: rgb(126, 131, 140); column-rule-color: rgb(126, 131, 140); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 42px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(126, 131, 140); perspective-origin: 21px 10px; text-align: left; text-decoration: none solid rgb(126, 131, 140); text-decoration-color: rgb(126, 131, 140); text-emphasis-color: rgb(126, 131, 140); text-wrap: nowrap; transform-origin: 21px 10px; -webkit-text-fill-color: rgb(126, 131, 140); -webkit-text-stroke-color: rgb(126, 131, 140);" data-v-34fcf4fd="">职位：</span> <em style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 56px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 28px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 28px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="">暂无数据</em></div> <div style="block-size: 20px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: border-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; float: left; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1310px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); padding-inline-end: 15px; padding-right: 15px; perspective-origin: 655px 10px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 655px 10px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51); width: 100%;" data-v-34fcf4fd=""><span style="block-size: 20px; border-block-end-color: rgb(126, 131, 140); border-block-start-color: rgb(126, 131, 140); border-bottom-color: rgb(126, 131, 140); border-inline-end-color: rgb(126, 131, 140); border-inline-start-color: rgb(126, 131, 140); border-left-color: rgb(126, 131, 140); border-right-color: rgb(126, 131, 140); border-top-color: rgb(126, 131, 140); box-sizing: content-box; caret-color: rgb(126, 131, 140); color: rgb(126, 131, 140); column-rule-color: rgb(126, 131, 140); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 98px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(126, 131, 140); perspective-origin: 49px 10px; text-align: left; text-decoration: none solid rgb(126, 131, 140); text-decoration-color: rgb(126, 131, 140); text-emphasis-color: rgb(126, 131, 140); text-wrap: nowrap; transform-origin: 49px 10px; -webkit-text-fill-color: rgb(126, 131, 140); -webkit-text-stroke-color: rgb(126, 131, 140);" data-v-34fcf4fd="">主要研究方向：</span> <em style="block-size: 20px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-top-color: rgb(56, 92, 156); box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: pointer; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 98px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); overflow-x: hidden; overflow-y: hidden; perspective-origin: 49px 10px; text-align: left; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 49px 10px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156);" data-v-34fcf4fd="" class="cursor mainColor keyword-hover">系统管理软件、</em><em style="block-size: 20px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-top-color: rgb(56, 92, 156); box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: pointer; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 84px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); overflow-x: hidden; overflow-y: hidden; perspective-origin: 42px 10px; text-align: left; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 42px 10px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156);" data-v-34fcf4fd="" class="cursor mainColor keyword-hover">互联网通信、</em><em style="block-size: 20px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-top-color: rgb(56, 92, 156); box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: pointer; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 56px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); overflow-x: hidden; overflow-y: hidden; perspective-origin: 28px 10px; text-align: left; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 28px 10px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156);" data-v-34fcf4fd="" class="cursor mainColor keyword-hover">云计算、</em><em style="block-size: 20px; border-block-end-color: rgb(56, 92, 156); border-block-start-color: rgb(56, 92, 156); border-bottom-color: rgb(56, 92, 156); border-inline-end-color: rgb(56, 92, 156); border-inline-start-color: rgb(56, 92, 156); border-left-color: rgb(56, 92, 156); border-right-color: rgb(56, 92, 156); border-top-color: rgb(56, 92, 156); box-sizing: content-box; caret-color: rgb(56, 92, 156); color: rgb(56, 92, 156); column-rule-color: rgb(56, 92, 156); cursor: pointer; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 84px; line-height: 20px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 90px); max-width: calc(100% - 90px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(56, 92, 156); overflow-x: hidden; overflow-y: hidden; perspective-origin: 42px 10px; text-align: left; text-decoration: none solid rgb(56, 92, 156); text-decoration-color: rgb(56, 92, 156); text-emphasis-color: rgb(56, 92, 156); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 42px 10px; transition-duration: 0.3s; transition-timing-function: ease-out; -webkit-text-fill-color: rgb(56, 92, 156); -webkit-text-stroke-color: rgb(56, 92, 156);" data-v-34fcf4fd="" class="cursor mainColor keyword-hover">输入输出设备</em></div></div> <div style="background-color: rgb(247, 251, 255); background-image: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgZmlsbD0iIzdFODM4QyIgZmlsbC1ydWxlPSJldmVub2RkIj48cGF0aCBmaWxsLW9wYWNpdHk9IjAiIGQ9Ik0wIDBoMTR2MTRIMHoiLz48cGF0aCBkPSJNMTAuODc5IDYuMzMyQTQuNzExIDQuNzExIDAgMTE3LjU1NyAzLjA5bC4yMTEtLjYwOGE1LjM1NCA1LjM1NCAwIDEwMy43MTEgMy42MTVsLS42LjIzNXptLTQuNTI1LS42MTNjLjA5NSAwIC4xOS4wMDcuMjgxLjAybC4yMTQtLjYxNGEyLjU3IDIuNTcgMCAxMDIuMDIgMS45OTRsLS42MS4yMzhhMS45MjcgMS45MjcgMCAxMS0xLjkwNi0xLjYzOHptNi42NDItMi4zNTVoLTIuMzZWMWwtLjY0Mi42NXYuMDAzbC0xLjcwOSAxLjcxaC0uMDA0djEuOTA1TDUuOTI1IDcuNjIzbC40NTIuNDUyIDIuMzU1LTIuMzU2aDEuOTA0di0uMDAybC4wMDMuMDAyIDEuNzEtMS43MS0uMDAyLS4wMDNoLjAwMmwuNjQ3LS42NDJ6bS0yLjYwNCAxLjcxM0g4LjkyM1YzLjYwOGwxLjA3LTEuMDd2MS40NjhoMS40N2wtMS4wNzEgMS4wN3oiIGZpbGwtcnVsZT0ibm9uemVybyIvPjwvZz48L3N2Zz4=); background-position: 6px 10px; background-repeat: no-repeat; block-size: 72px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-bottom-left-radius: 4px; border-bottom-right-radius: 4px; border-end-end-radius: 4px; border-end-start-radius: 4px; border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-start-end-radius: 4px; border-start-start-radius: 4px; border-top-color: rgb(48, 49, 51); border-top-left-radius: 4px; border-top-right-radius: 4px; box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1277px; line-height: 16.38px; list-style-type: none; margin-block-start: 12px; margin-left: 0; margin-right: 0; margin-top: 12px; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; padding-block-end: 8px; padding-block-start: 8px; padding-bottom: 8px; padding-inline-end: 8px; padding-inline-start: 25px; padding-left: 25px; padding-right: 8px; padding-top: 8px; perspective-origin: 655px 44px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); transform-origin: 655px 44px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" class="patent-data-box-keyword"><span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1277px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); perspective-origin: 638.5px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 638.5px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd=""><span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 84px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); perspective-origin: 42px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 42px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="">专利关键词：</span><em style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 143.333px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 100px); max-width: calc(100% - 100px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 71.6667px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 71.6667px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" class="ellipsis"><strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong><strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>文档,<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong>托管系统</em></span> <span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1277px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); perspective-origin: 638.5px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 638.5px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd=""><span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 70px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); perspective-origin: 35px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 35px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="">专利标题：</span><em style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 538.667px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 100px); max-width: calc(100% - 100px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 269.333px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 269.333px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" class="ellipsis">与旧有工具集成的<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong><strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>文档,与旧有工具集成的<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong><strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>文档,<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong>托管系统中的强资源身份</em></span> <span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 1277px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(48, 49, 51); perspective-origin: 638.5px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 638.5px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd=""><span style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: flex; font-family: Microsoft Yahei, arial; font-size: 14px; inline-size: 70px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); perspective-origin: 35px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-wrap: nowrap; transform-origin: 35px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="">专利摘要：</span><em style="block-size: 24px; border-block-end-color: rgb(48, 49, 51); border-block-start-color: rgb(48, 49, 51); border-bottom-color: rgb(48, 49, 51); border-inline-end-color: rgb(48, 49, 51); border-inline-start-color: rgb(48, 49, 51); border-left-color: rgb(48, 49, 51); border-right-color: rgb(48, 49, 51); border-top-color: rgb(48, 49, 51); box-sizing: content-box; caret-color: rgb(48, 49, 51); color: rgb(48, 49, 51); column-rule-color: rgb(48, 49, 51); cursor: default; display: block; font-family: Microsoft Yahei, arial; font-size: 14px; font-style: normal; inline-size: 1177px; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; max-inline-size: calc(100% - 100px); max-width: calc(100% - 100px); min-block-size: auto; min-height: auto; min-inline-size: auto; min-width: auto; outline-color: rgb(48, 49, 51); overflow-x: hidden; overflow-y: hidden; perspective-origin: 588.5px 12px; text-align: left; text-decoration: none solid rgb(48, 49, 51); text-decoration-color: rgb(48, 49, 51); text-emphasis-color: rgb(48, 49, 51); text-overflow: ellipsis; text-wrap: nowrap; transform-origin: 588.5px 12px; -webkit-text-fill-color: rgb(48, 49, 51); -webkit-text-stroke-color: rgb(48, 49, 51);" data-v-34fcf4fd="" class="ellipsis">本发明涉及与旧有工具集成的<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">云</strong><strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>文档。描述了一种用于提供来自服务器的文档内容的设施。所述设施从分别的客户端计算系统接收取回请求以返回由取回请求所包含的文档标识符所标识的文档。,在另外的应用中，响应于接收到所述请求，所述设施使得所述文档内容被取回，并且使得呈现代码呈现所取回的文档内容；该呈现代码既不是所述应用<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>的，也不链接到要执行的应用中。,在另外的应用中，响应于接收到所述请求，所述设施使得所述文档内容被取回，并且使得呈现代码呈现所取回的文档内容；该呈现代码既不是所述应用<strong style="border-block-end-color: rgb(222, 82, 69); border-block-start-color: rgb(222, 82, 69); border-bottom-color: rgb(222, 82, 69); border-inline-end-color: rgb(222, 82, 69); border-inline-start-color: rgb(222, 82, 69); border-left-color: rgb(222, 82, 69); border-right-color: rgb(222, 82, 69); border-top-color: rgb(222, 82, 69); box-sizing: content-box; caret-color: rgb(222, 82, 69); color: rgb(222, 82, 69); column-rule-color: rgb(222, 82, 69); cursor: default; font-family: Microsoft Yahei, arial; font-size: 14px; font-weight: 400; line-height: 24px; list-style-type: none; margin-left: 0; margin-right: 0; outline-color: rgb(222, 82, 69); perspective-origin: 0px 0px; text-align: left; text-decoration: none solid rgb(222, 82, 69); text-decoration-color: rgb(222, 82, 69); text-emphasis-color: rgb(222, 82, 69); text-wrap: nowrap; transform-origin: 0px 0px; -webkit-text-fill-color: rgb(222, 82, 69); -webkit-text-stroke-color: rgb(222, 82, 69);">原生</strong>的，也不链接到要执行的应用中。</em></span></div></div></dt></dl>
        </div>
        <div v-show="activeTab === '搜团队'">
          <el-card
            v-for="o in 6"
            :key="o"
            class="box-card"
            :class="o % 2 === 0 ? 'light-blue' : 'blue'"
            style="margin-top: 20px"
          >
            <el-descriptions
              class="margin-top"
              title="团队信息"
              :column="3"
              direction="vertical"
              style="color: white"
            >
              <el-descriptions-item
                label="团队成员"
                label-class-name="my-label"
                content-class-name="my-content"
              >张三、李四、王五、赵六</el-descriptions-item>
              <el-descriptions-item
                label="研究方向"
                label-class-name="my-label"
                content-class-name="my-content"
              >人工智能、计算机视觉</el-descriptions-item>
              <el-descriptions-item
                label="累计专利"
                :span="2"
                label-class-name="my-label"
                content-class-name="my-content"
              >1000 个</el-descriptions-item>
              <el-descriptions-item
                label="备注"
                label-class-name="my-label"
                content-class-name="my-content"
              >
                <el-tag
                  size="small"
                  style="color: rgb(0, 38, 255)"
                >xxxx</el-tag>
                <el-tag
                  size="small"
                  style="color: rgb(0, 38, 255)"
                >xxxx</el-tag>
              </el-descriptions-item>
              <el-descriptions-item
                label="负责人及联系方式"
                label-class-name="my-label"
                content-class-name="my-content"
              >张三（060-123456）</el-descriptions-item>
              <el-descriptions-item
                label="累计成果"
                label-class-name="my-label"
                content-class-name="my-content"
              > 188 件</el-descriptions-item>
            </el-descriptions>
          </el-card></div>
        <div v-show="activeTab === '搜企业'">
          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item
              name="1"
              class="deploy-setting"
            ><template slot="title">
               <span
                 style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
               >电子行业</span>
             </template>
              <el-card
                v-for="o in 6"
                :key="o"
                class="box-card"
                :class="o % 2 === 0 ? 'light-blue' : 'blue'"
                style="margin-top: 20px"
              >
                <el-descriptions
                  class="margin-top"
                  title="XXX有限公司"
                  :column="3"
                  direction="vertical"
                  style="color: white"
                >
                  <el-descriptions-item
                    label="法定代表人"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >XXX</el-descriptions-item>
                  <el-descriptions-item
                    label="企业信用代码"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >18100000000</el-descriptions-item>
                  <el-descriptions-item
                    label="注册资本"
                    :span="2"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >28,700万人民币</el-descriptions-item>
                  <el-descriptions-item
                    label="备注"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >
                    <el-tag
                      size="small"
                      style="color: rgb(0, 38, 255)"
                    >中关村高兴技术企业</el-tag>
                    <el-tag
                      size="small"
                      style="color: rgb(0, 38, 255)"
                    >世界500强</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item
                    label="联系方式"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >060-123456</el-descriptions-item>
                  <el-descriptions-item
                    label="联系地址"
                    label-class-name="my-label"
                    content-class-name="my-content"
                  >江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                </el-descriptions>
              </el-card></el-collapse-item>
            <el-collapse-item name="2">
              <template slot="title">
                <span
                  style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
                >通信行业</span>
              </template></el-collapse-item>
            <el-collapse-item
              name="3"
            ><template slot="title">
              <span
                style="
                    float: left;
                    font-weight: bold;
                    font-size: 14px;
                    color: #2c8df4;
                  "
              >计算机行业</span>
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
import { listClassification } from '@/api/kyfz/classification'
import {
listExpert
} from '@/api/kyfz/expert'
import {
addSearch,
delSearch,
getSearch,
listSearch,
updateSearch
} from '@/api/kyfz/search'

export default {
  name: 'Search',
  data() {
    return {
      industries: ['电子', '通信', '计算机', '电子', '通信', '计算机'],
      // 专家管理表格数据
      expertList: [],
      // 标签页选中的数据
      el_tab_pane1: [],
      el_tab_pane2: [],
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
      // 数据库行业分类表格数据
      classificationList: [],
      // 弹出层标题
      title: '',
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
        intellectualPropertyId: null,
        awardsId: null,
        requirementId: null,
        expertTeams: null,
        expertSignificance: null,
        categoryId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      activeTab: '综合搜索',
      activeTab2: '行业标签',
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  // 标签页行业分页分行显示
  computed: {
    firstTenCategories() {
      return this.classificationList.slice(0, 10)
    },
    remainingCategories() {
      return this.classificationList.slice(10)
    }
  },
  created() {
    this.getList()
    this.getListClassification()
  },
  mounted() {
    this.setMenuPosition()
  },
  methods: {
    // 专家搜索换页
    getexpertList() {
      this.loading = true
      listExpert(this.queryParams).then((response) => {
        this.expertList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 行业分类下拉菜单触发函数
    panebuttonClick(command) {
      this.reset_queryParams()
      this.queryParams.categoryId = command
      this.activeTab2 = '选择行业后数据显示'
      listExpert(this.queryParams).then((response) => {
        this.expertList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询行业分类列表 */
    getListClassification() {
      this.loading = true
      listClassification(this.queryParams).then((response) => {
        this.classificationList = response.rows
      })
      this.loading = false
    },
    // 未知
    setMenuPosition() {
      const firstDropdown = this.$refs.firstDropdown.$el
      const menu = this.$refs.menu

      if (firstDropdown && menu) {
        const dropdownRect = firstDropdown.getBoundingClientRect()
        menu.style.left = `${dropdownRect.left}px`
      }
    },
    // 学院数据显示
    filterNode(value, data) {
      if (!value) return true
      return data.label.toLowerCase().includes(value.toLowerCase())
    },
    handleNodeClick(data) {
      console.log(data)
    },
    // 点击按钮切换界面
    showContent(tabName) {
      this.activeTab = tabName
      if (this.activeTab === '综合搜索') {
        this.activeTab2 = '行业标签'
      }
    },
    /** 查询检索列表 */
    getList() {
      this.loading = true
      listSearch(this.queryParams).then((response) => {
        this.searchList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        searchId: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.searchId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加检索'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const searchId = row.searchId || this.ids
      getSearch(searchId).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改检索'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.searchId != null) {
            updateSearch(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addSearch(this.form).then((response) => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const searchIds = row.searchId || this.ids
      this.$modal
        .confirm('是否确认删除检索编号为"' + searchIds + '"的数据项？')
        .then(function() {
          return delSearch(searchIds)
        })
        .then(() => {
          this.getList()
          this.$modal.msgSuccess('删除成功')
        })
        .catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        'kyfz/search/export',
        {
          ...this.queryParams
        },
        `search_${new Date().getTime()}.xlsx`
      )
    },
    reset_queryParams() {
      this.queryParams = {
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
        intellectualPropertyId: null,
        awardsId: null,
        requirementId: null,
        expertTeams: null,
        expertSignificance: null,
        categoryId: null
      }
    }
  }
}
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
  clear: both;
}
</style>

<style>
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

<style scoped>
.dropdown-wrapper .image-wrapper {
  margin-bottom: 10px;
}

.custom-dropdown .el-dropdown-menu {
  background-color: white;
}

.horizontal-menu {
  display: flex;
  flex-wrap: wrap;
}

.horizontal-menu .el-dropdown-item {
  flex-basis: 100%;
}

.menu-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 100px;
}

.my-menu {
  left: 300px !important;
  width: 950px;
}
</style>

<style scoped>
/* 卡片定制 */
.button-container {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.card-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
}

.card-content-left {
  flex: 1;
}

.card-content-right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.card-content {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
}

.card-row {
  flex-basis: 33.3%;
  display: flex;
  align-items: center;
  white-space: nowrap;
  margin-bottom: 8px;
}

.card-value {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.card-item {
  background-color: #f7fbff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
  margin-bottom: 16px;
}

.card-label {
  font-weight: bold;
  font-family: Arial, sans-serif;
  color: #333;
}

.card-value {
  font-family: Arial, sans-serif;
  color: #666;
}
</style>

<style scoped>
/* 综合搜索标签页 */
  .tab-container {
    margin-bottom: 100px; /* 添加下间隔 */
    width: 1300px;
    height: 150px;
  }
  .pane-span {
          font-size: 16px; /* 更改字号为20px */
          letter-spacing: 4px; /* 增加字间距为2px */
          width: 130px; /* 设置span宽度为130px */
          color: #000;
      }

 .pane-button {
        margin-right: 10px; /* 增大按钮右侧的间距为10px */
        font-size: 16px; /* 增大按钮字体为16px */
    }
</style>
