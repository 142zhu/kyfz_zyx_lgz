package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖项信息对象 kyfz_award
 * 
 * @author zyx
 * @date 2023-08-19
 */
public class KyfzAward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 奖项id */
    private Long awardId;

    /** 获奖类别 */
    @Excel(name = "获奖类别")
    private String awardCategory;

    /** 获奖项目 */
    @Excel(name = "获奖项目")
    private String awardProject;

    /** 获奖项目类型 */
    @Excel(name = "获奖项目类型")
    private String typesAwardProjects;

    /** 获奖名称 */
    @Excel(name = "获奖名称")
    private String awardName;

    /** 获奖日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "获奖日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date awardDate;

    /** 奖励级别 */
    @Excel(name = "奖励级别")
    private String rewardLevel;

    /** 奖励等级细分 */
    @Excel(name = "奖励等级细分")
    private String rewardLevelSegmentation;

    /** 颁奖国家/地区 */
    @Excel(name = "颁奖国家/地区")
    private String awardingCountry;

    /** 颁奖单位 */
    @Excel(name = "颁奖单位")
    private String awardingUnit;

    /** 奖励方式 */
    @Excel(name = "奖励方式")
    private String rewardMethod;

    /** 署名单位 */
    @Excel(name = "署名单位")
    private String signatureUnit;

    /** 归属二级单位编码 */
    @Excel(name = "归属二级单位编码")
    private String belongsSecondaryUnitCode;

    /** 归属二级单位编码 */
    @Excel(name = "归属二级单位编码")
    private String nameSecondaryUnit;

    /** 其他署名单位 */
    @Excel(name = "其他署名单位")
    private String otherSignUnit;

    /** 获奖原因 */
    @Excel(name = "获奖原因")
    private String reasonAward;

    /** 业绩状态 */
    @Excel(name = "业绩状态")
    private String performanceStatus;

    /** 成员信息 */
    @Excel(name = "成员信息")
    private String memberInformation;

    /** 成员信息 */
    @Excel(name = "成员信息")
    private String schoolMemberAccount;

    public void setAwardId(Long awardId) 
    {
        this.awardId = awardId;
    }

    public Long getAwardId() 
    {
        return awardId;
    }
    public void setAwardCategory(String awardCategory) 
    {
        this.awardCategory = awardCategory;
    }

    public String getAwardCategory() 
    {
        return awardCategory;
    }
    public void setAwardProject(String awardProject) 
    {
        this.awardProject = awardProject;
    }

    public String getAwardProject() 
    {
        return awardProject;
    }
    public void setTypesAwardProjects(String typesAwardProjects) 
    {
        this.typesAwardProjects = typesAwardProjects;
    }

    public String getTypesAwardProjects() 
    {
        return typesAwardProjects;
    }
    public void setAwardName(String awardName) 
    {
        this.awardName = awardName;
    }

    public String getAwardName() 
    {
        return awardName;
    }
    public void setAwardDate(Date awardDate) 
    {
        this.awardDate = awardDate;
    }

    public Date getAwardDate() 
    {
        return awardDate;
    }
    public void setRewardLevel(String rewardLevel) 
    {
        this.rewardLevel = rewardLevel;
    }

    public String getRewardLevel() 
    {
        return rewardLevel;
    }
    public void setRewardLevelSegmentation(String rewardLevelSegmentation) 
    {
        this.rewardLevelSegmentation = rewardLevelSegmentation;
    }

    public String getRewardLevelSegmentation() 
    {
        return rewardLevelSegmentation;
    }
    public void setAwardingCountry(String awardingCountry) 
    {
        this.awardingCountry = awardingCountry;
    }

    public String getAwardingCountry() 
    {
        return awardingCountry;
    }
    public void setAwardingUnit(String awardingUnit) 
    {
        this.awardingUnit = awardingUnit;
    }

    public String getAwardingUnit() 
    {
        return awardingUnit;
    }
    public void setRewardMethod(String rewardMethod) 
    {
        this.rewardMethod = rewardMethod;
    }

    public String getRewardMethod() 
    {
        return rewardMethod;
    }
    public void setSignatureUnit(String signatureUnit) 
    {
        this.signatureUnit = signatureUnit;
    }

    public String getSignatureUnit() 
    {
        return signatureUnit;
    }
    public void setBelongsSecondaryUnitCode(String belongsSecondaryUnitCode) 
    {
        this.belongsSecondaryUnitCode = belongsSecondaryUnitCode;
    }

    public String getBelongsSecondaryUnitCode() 
    {
        return belongsSecondaryUnitCode;
    }
    public void setNameSecondaryUnit(String nameSecondaryUnit) 
    {
        this.nameSecondaryUnit = nameSecondaryUnit;
    }

    public String getNameSecondaryUnit() 
    {
        return nameSecondaryUnit;
    }
    public void setOtherSignUnit(String otherSignUnit) 
    {
        this.otherSignUnit = otherSignUnit;
    }

    public String getOtherSignUnit() 
    {
        return otherSignUnit;
    }
    public void setReasonAward(String reasonAward) 
    {
        this.reasonAward = reasonAward;
    }

    public String getReasonAward() 
    {
        return reasonAward;
    }
    public void setPerformanceStatus(String performanceStatus) 
    {
        this.performanceStatus = performanceStatus;
    }

    public String getPerformanceStatus() 
    {
        return performanceStatus;
    }
    public void setMemberInformation(String memberInformation) 
    {
        this.memberInformation = memberInformation;
    }

    public String getMemberInformation() 
    {
        return memberInformation;
    }
    public void setSchoolMemberAccount(String schoolMemberAccount) 
    {
        this.schoolMemberAccount = schoolMemberAccount;
    }

    public String getSchoolMemberAccount() 
    {
        return schoolMemberAccount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("awardId", getAwardId())
            .append("awardCategory", getAwardCategory())
            .append("awardProject", getAwardProject())
            .append("typesAwardProjects", getTypesAwardProjects())
            .append("awardName", getAwardName())
            .append("awardDate", getAwardDate())
            .append("rewardLevel", getRewardLevel())
            .append("rewardLevelSegmentation", getRewardLevelSegmentation())
            .append("awardingCountry", getAwardingCountry())
            .append("awardingUnit", getAwardingUnit())
            .append("rewardMethod", getRewardMethod())
            .append("signatureUnit", getSignatureUnit())
            .append("belongsSecondaryUnitCode", getBelongsSecondaryUnitCode())
            .append("nameSecondaryUnit", getNameSecondaryUnit())
            .append("otherSignUnit", getOtherSignUnit())
            .append("reasonAward", getReasonAward())
            .append("remark", getRemark())
            .append("performanceStatus", getPerformanceStatus())
            .append("memberInformation", getMemberInformation())
            .append("schoolMemberAccount", getSchoolMemberAccount())
            .toString();
    }
}
