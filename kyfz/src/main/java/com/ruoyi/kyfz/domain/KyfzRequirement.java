package com.ruoyi.kyfz.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 需求管理对象 kyfz_requirement
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public class KyfzRequirement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long requirementId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractNumber;

    /** 委托单位 */
    @Excel(name = "委托单位")
    private String client;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String projectLeader;

    /** 负责人工号 */
    @Excel(name = "负责人工号")
    private String projectLeaderJobNumber;

    /** 项目成员 */
    @Excel(name = "项目成员")
    private String projectMembers;

    /** 依托单位 */
    @Excel(name = "依托单位")
    private String supportUnit;

    /** 依托单位代码 */
    @Excel(name = "依托单位代码")
    private String supportUnitNumber;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String projectCategory;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 项目分类 */
    @Excel(name = "项目分类")
    private String projectClassification;

    /** 项目性质 */
    @Excel(name = "项目性质")
    private String projectNature;

    /** 服务的国民经济 */
    @Excel(name = "服务的国民经济")
    private String serviceNationalEconomy;

    /** 国民经济子行业 */
    @Excel(name = "国民经济子行业")
    private String nationalEconomySubIndustry;

    /** 社会经济目标 */
    @Excel(name = "社会经济目标")
    private String socioEconomicObjective;

    /** 项目周期始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目周期始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startProjectTime;

    /** 项目周期末 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目周期末", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endProjectTime;

    /** 总经费（万元） */
    @Excel(name = "总经费", readConverterExp = "万=元")
    private String totalBudget;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String contractType;

    /** 一级学科 */
    @Excel(name = "一级学科")
    private String firstDiscipline;

    /** 二级学科 */
    @Excel(name = "二级学科")
    private String secondaryDiscipline;

    /** 项目负责人所属单位 */
    @Excel(name = "项目负责人所属单位")
    private String projectLeaderUnit;

    /** 企业编号 */
    @Excel(name = "企业编号")
    private String enterpriseNumber;

    /** 校内成员账号 */
    @Excel(name = "校内成员账号")
    private String campusMemberAccount;

    /** 需求状态 */
    @Excel(name = "需求状态")
    private String requirementStatus;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;

    /** 需求关键词 */
    @Excel(name = "需求关键词")
    private String requirementKeywords;

    /** 需求详细描述 */
    @Excel(name = "需求详细描述")
    private String requirementDescription;

    /** 需求发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requirementReleaseTime;

    /** 企业名 */
    @Excel(name = "企业名")
    private String enterpriseName;

    /** 企业描述 */
    @Excel(name = "企业描述")
    private String enterpriseDescribe;

    /** 企业注册资本 */
    @Excel(name = "企业注册资本")
    private String registeredCapital;

    /** 用户ID */
    private Long userId;

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient() {
        return client;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeaderJobNumber(String projectLeaderJobNumber) {
        this.projectLeaderJobNumber = projectLeaderJobNumber;
    }

    public String getProjectLeaderJobNumber() {
        return projectLeaderJobNumber;
    }

    public void setProjectMembers(String projectMembers) {
        this.projectMembers = projectMembers;
    }

    public String getProjectMembers() {
        return projectMembers;
    }

    public void setSupportUnit(String supportUnit) {
        this.supportUnit = supportUnit;
    }

    public String getSupportUnit() {
        return supportUnit;
    }

    public void setSupportUnitNumber(String supportUnitNumber) {
        this.supportUnitNumber = supportUnitNumber;
    }

    public String getSupportUnitNumber() {
        return supportUnitNumber;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectClassification(String projectClassification) {
        this.projectClassification = projectClassification;
    }

    public String getProjectClassification() {
        return projectClassification;
    }

    public void setProjectNature(String projectNature) {
        this.projectNature = projectNature;
    }

    public String getProjectNature() {
        return projectNature;
    }

    public void setServiceNationalEconomy(String serviceNationalEconomy) {
        this.serviceNationalEconomy = serviceNationalEconomy;
    }

    public String getServiceNationalEconomy() {
        return serviceNationalEconomy;
    }

    public void setNationalEconomySubIndustry(String nationalEconomySubIndustry) {
        this.nationalEconomySubIndustry = nationalEconomySubIndustry;
    }

    public String getNationalEconomySubIndustry() {
        return nationalEconomySubIndustry;
    }

    public void setSocioEconomicObjective(String socioEconomicObjective) {
        this.socioEconomicObjective = socioEconomicObjective;
    }

    public String getSocioEconomicObjective() {
        return socioEconomicObjective;
    }

    public void setStartProjectTime(Date startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public Date getStartProjectTime() {
        return startProjectTime;
    }

    public void setEndProjectTime(Date endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public Date getEndProjectTime() {
        return endProjectTime;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setFirstDiscipline(String firstDiscipline) {
        this.firstDiscipline = firstDiscipline;
    }

    public String getFirstDiscipline() {
        return firstDiscipline;
    }

    public void setSecondaryDiscipline(String secondaryDiscipline) {
        this.secondaryDiscipline = secondaryDiscipline;
    }

    public String getSecondaryDiscipline() {
        return secondaryDiscipline;
    }

    public void setProjectLeaderUnit(String projectLeaderUnit) {
        this.projectLeaderUnit = projectLeaderUnit;
    }

    public String getProjectLeaderUnit() {
        return projectLeaderUnit;
    }

    public void setEnterpriseNumber(String enterpriseNumber) {
        this.enterpriseNumber = enterpriseNumber;
    }

    public String getEnterpriseNumber() {
        return enterpriseNumber;
    }

    public void setCampusMemberAccount(String campusMemberAccount) {
        this.campusMemberAccount = campusMemberAccount;
    }

    public String getCampusMemberAccount() {
        return campusMemberAccount;
    }

    public void setRequirementStatus(String requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public String getRequirementStatus() {
        return requirementStatus;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setRequirementKeywords(String requirementKeywords) {
        this.requirementKeywords = requirementKeywords;
    }

    public String getRequirementKeywords() {
        return requirementKeywords;
    }

    public void setRequirementDescription(String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }

    public String getRequirementDescription() {
        return requirementDescription;
    }

    public void setRequirementReleaseTime(Date requirementReleaseTime) {
        this.requirementReleaseTime = requirementReleaseTime;
    }

    public Date getRequirementReleaseTime() {
        return requirementReleaseTime;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setEnterpriseDescribe(String enterpriseDescribe) {
        this.enterpriseDescribe = enterpriseDescribe;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getEnterpriseDescribe() {
        return enterpriseDescribe;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("requirementId", getRequirementId())
                .append("projectName", getProjectName())
                .append("contractNumber", getContractNumber())
                .append("client", getClient())
                .append("projectLeader", getProjectLeader())
                .append("projectLeaderJobNumber", getProjectLeaderJobNumber())
                .append("projectMembers", getProjectMembers())
                .append("supportUnit", getSupportUnit())
                .append("supportUnitNumber", getSupportUnitNumber())
                .append("projectCategory", getProjectCategory())
                .append("projectType", getProjectType())
                .append("projectClassification", getProjectClassification())
                .append("projectNature", getProjectNature())
                .append("serviceNationalEconomy", getServiceNationalEconomy())
                .append("nationalEconomySubIndustry", getNationalEconomySubIndustry())
                .append("socioEconomicObjective", getSocioEconomicObjective())
                .append("startProjectTime", getStartProjectTime())
                .append("endProjectTime", getEndProjectTime())
                .append("totalBudget", getTotalBudget())
                .append("contractType", getContractType())
                .append("firstDiscipline", getFirstDiscipline())
                .append("secondaryDiscipline", getSecondaryDiscipline())
                .append("projectLeaderUnit", getProjectLeaderUnit())
                .append("enterpriseNumber", getEnterpriseNumber())
                .append("campusMemberAccount", getCampusMemberAccount())
                .append("requirementStatus", getRequirementStatus())
                .append("creatBy", getCreatBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("requirementKeywords", getRequirementKeywords())
                .append("requirementDescription", getRequirementDescription())
                .append("requirementReleaseTime", getRequirementReleaseTime())
                .toString();
    }
}
