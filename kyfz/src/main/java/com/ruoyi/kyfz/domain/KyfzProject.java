package com.ruoyi.kyfz.domain;

public class KyfzProject {
    private Long projectId;
    private String projectName;

    // 数据来源
    private String dataSource;

    // 立项经费
    private String startFunds;

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void setStartFunds(String startFunds) {
        this.startFunds = startFunds;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getStartFunds() {
        return startFunds;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
