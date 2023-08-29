package com.ruoyi.kyfz.domain;

import java.util.ArrayList;

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

    /** 用户编号 */
    private Long userId;

    /** 专家账号 */
    @Excel(name = "专家账号")
    private String expertAccount;

    /** 专家id */
    @Excel(name = "专家id")
    private String expertId;

    /** 需求表的id */
    @Excel(name = "需求表的id")
    private String requirementId;

    /** 匹配分值 */
    @Excel(name = "匹配分值")
    private String matchScore;

    /** 相关论文（多个论文id） */
    @Excel(name = "相关论文", readConverterExp = "多个论文id")
    private String thesisId;

    /** 相关著作（多个著作id） */
    @Excel(name = "相关奖项", readConverterExp = "多个奖项id")
    private String awardId;

    /** 相关证书（多个证书id） */
    @Excel(name = "相关证书", readConverterExp = "多个证书id")
    private String certificateId;

    /** 相关项目（多个项目id） */
    @Excel(name = "相关项目", readConverterExp = "多个项目id")
    private String projectId;

    /** 推送状态 */
    @Excel(name = "推送状态")
    private String pushstatus;

    /** 相关项目（多个项目名） */
    @Excel(name = "相关项目的名称", readConverterExp = "多个项目名称合并在一起")
    private String projectNames;
    /** 相关论文（多个论文） */
    @Excel(name = "相关论文的名称", readConverterExp = "多个论文名称合并在一起")
    private String thesisNames;
    /** 相关著作（多个著作） */
    @Excel(name = "相关著作的名称", readConverterExp = "多个著作名称合并在一起")
    private String workNames;
    /** 相关证书（多个项目名） */
    @Excel(name = "相关证书的名称", readConverterExp = "多个证书名称合并在一起")
    private String certificateNames;
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

    /** 专家所属团队的成员 */
    @Excel(name = "多个项目名")
    private ArrayList strArray;

    /** 专家所属团队的成员 */
    @Excel(name = "多个专利名")
    private ArrayList strArray1;

    /** 专家所属团队的成员 */
    @Excel(name = "多个著作名")
    private ArrayList strArray2;

    /** 专家所属团队的成员 */
    @Excel(name = "多个证书名")
    private ArrayList strArray3;

    /** 最小匹配分值 */
    private double minMatchScore;

    /** 最大匹配分值 */
    private double maxMatchScore;

    private KyfzRequirement kyfzRequirement;

    public KyfzRequirement getKyfzRequirement() {
        return kyfzRequirement;
    }

    public void setMarkAward(String markAward) {
        this.markAward = markAward;
    }

    private String markProject;

    public String getMarkAward() {
        return markAward;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    private String markAward;

    private String markThesis;

    private String markCertificate;

    public double getMinMatchScore() {
        return minMatchScore;
    }

    public void setMinMatchScore(double minMatchScore) {
        this.minMatchScore = minMatchScore;
    }

    public double getMaxMatchScore() {
        return maxMatchScore;
    }

    public void setMaxMatchScore(double maxMatchScore) {
        this.maxMatchScore = maxMatchScore;
    }

    public String getMarkProject() {
        return markProject;
    }

    public void setMarkProject(String markProject) {
        this.markProject = markProject;
    }

    public String getMarkThesis() {
        return markThesis;
    }

    public void setMarkThesis(String markThesis) {
        this.markThesis = markThesis;
    }

    public String getMarkCertificate() {
        return markCertificate;
    }

    public void setMarkCertificate(String markCertificate) {
        this.markCertificate = markCertificate;
    }

    public ArrayList getStrArray1() {
        return strArray1;
    }

    public void setStrArray1(ArrayList strArray1) {
        this.strArray1 = strArray1;
    }

    public ArrayList getStrArray2() {
        return strArray2;
    }

    public void setStrArray2(ArrayList strArray2) {
        this.strArray2 = strArray2;
    }

    public ArrayList getStrArray3() {
        return strArray3;
    }

    public void setStrArray3(ArrayList strArray3) {
        this.strArray3 = strArray3;
    }

    public ArrayList getStrArray() {
        return strArray;
    }

    public void setStrArray(ArrayList strArray) {
        this.strArray = strArray;
    }

    /** 专家所属团队的成员 */
    @Excel(name = "系统评分")
    private String score;

    public String getThesisNames() {
        return thesisNames;
    }

    public void setThesisNames(String thesisNames) {
        this.thesisNames = thesisNames;
    }

    public String getWorkNames() {
        return workNames;
    }

    public void setWorkNames(String workNames) {
        this.workNames = workNames;
    }

    public String getCertificateNames() {
        return certificateNames;
    }

    public void setCertificateNames(String certificateNames) {
        this.certificateNames = certificateNames;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

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

    public void setAwardId(String workId) {
        this.awardId = workId;
    }

    public String getAwardId() {
        return awardId;
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

    public String getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(String pushstatus) {
        this.pushstatus = pushstatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matchId", getMatchId())
                .append("expertAccount", getExpertAccount())
                .append("requirementId", getRequirementId())
                .append("matchScore", getMatchScore())
                .append("thesisId", getThesisId())
                .append("awardId", getAwardId())
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
                .append("certificateNames", getCertificateNames())
                .append("workNames", getWorkNames())
                .append("thesisNames", getThesisNames())
                .append("score", getScore())
                .toString();
    }

    public void setKyfzRequirement(KyfzRequirement kyfzRequirement) {
        this.kyfzRequirement = kyfzRequirement;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }
}
