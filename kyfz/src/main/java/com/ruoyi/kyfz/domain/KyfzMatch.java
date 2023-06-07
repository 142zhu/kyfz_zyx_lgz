package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 匹配列表对象 kyfz_match
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
public class KyfzMatch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 匹配编号（专家匹配表的唯一表示符号） */
    private Long matchId;

    /** 专家账号 */
    @Excel(name = "专家账号")
    private String expertAccount;

    /** 需求表的id */
    @Excel(name = "需求表的id")
    private String requirementId;

    /** 匹配分值 */
    @Excel(name = "匹配分值")
    private String matchScore;

    /** 相关论文（多个论文id） */
    @Excel(name = "相关论文", readConverterExp = "多=个论文id")
    private String thesisId;

    /** 相关著作（多个著作id） */
    @Excel(name = "相关著作", readConverterExp = "多=个著作id")
    private String workId;

    /** 相关证书（多个证书id） */
    @Excel(name = "相关证书", readConverterExp = "多=个证书id")
    private String certificateId;

    /** 相关项目（多个项目id） */
    @Excel(name = "相关项目", readConverterExp = "多=个项目id")
    private String projectId;

    /** 相关项目（多个项目名） */
    @Excel(name = "相关项目的名称", readConverterExp = "多个项目名称合并在一起")
    private String projectNames;

    /*********************************************************** */
    /** 根据需求添加的字段 */
    /** 专家姓名 */
    @Excel(name = "专家姓名")
    private String expertName;
    /** 专家研究方向 */
    @Excel(name = "专家研究方向")
    private String researchDirection;
    /** 需求 */
    @Excel(name = "需求")
    private String projectName;
    /** 下发需求的企业 */
    @Excel(name = "企业")
    private String client;
    /** 需求关键词 */
    @Excel(name = "需求关键词")
    private String requirementKeywords;

    /** 专家所属团队的成员 */
    @Excel(name = "专家所属团队的成员")
    private String teamMembers;

    /********************** 按照需求添加的get和set方法 */

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    /* 需求名 */
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /* 相关项目名 */
    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRequirementKeywords() {
        return requirementKeywords;
    }

    public void setRequirementKeywords(String requirementKeywords) {
        this.requirementKeywords = requirementKeywords;
    }

    /******************************************************* */

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setExpertAccount(String expertAccount) {
        this.expertAccount = expertAccount;
    }

    public String getExpertAccount() {
        return expertAccount;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setThesisId(String thesisId) {
        this.thesisId = thesisId;
    }

    public String getThesisId() {
        return thesisId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matchId", getMatchId())
                .append("expertAccount", getExpertAccount())
                .append("requirementId", getRequirementId())
                .append("matchScore", getMatchScore())
                .append("thesisId", getThesisId())
                .append("workId", getWorkId())
                .append("certificateId", getCertificateId())
                .append("projectId", getProjectId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("expertName", getExpertName())
                .append("researchDirection", getResearchDirection())
                .append("projectName", getProjectName())
                .append("client", getClient())
                .append("requirementKeywords", getRequirementKeywords())
                .toString();
    }
}
