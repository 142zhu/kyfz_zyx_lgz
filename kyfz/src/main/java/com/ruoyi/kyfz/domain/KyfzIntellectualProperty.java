package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识产权对象 kyfz_intellectual_property
 * 
 * @author zyx
 * @date 2023-08-19
 */
public class KyfzIntellectualProperty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 知识产权id */
    private Long intellectualPropertyId;

    /** 知识产权名称 */
    @Excel(name = "知识产权名称")
    private String intellectualPropertyName;

    /** 业绩类别 */
    @Excel(name = "业绩类别")
    private String performanceCategory;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String certificateNumber;

    /** 国家/地区 */
    @Excel(name = "国家/地区")
    private String countryOrRegion;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applicationDate;

    /** 批准日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "批准日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalDate;

    /** 专利类型 */
    @Excel(name = "专利类型")
    private String patentType;

    /** 专利状态 */
    @Excel(name = "专利状态")
    private String patentStatus;

    /** 标准类型 */
    @Excel(name = "标准类型")
    private String standardType;

    /** 署名单位 */
    @Excel(name = "署名单位")
    private String signatureUnit;

    /** 归属二级单位编码 */
    @Excel(name = "归属二级单位编码")
    private String belongsSecondaryUnitCode;

    /** 归属二级单位名称 */
    @Excel(name = "归属二级单位名称")
    private String nameSecondaryUnit;

    /** 其他署名单位 */
    @Excel(name = "其他署名单位")
    private String otherSignUnit;

    /** 成员信息 */
    @Excel(name = "成员信息")
    private String memberInformation;

    /** 校内成员账号 */
    @Excel(name = "校内成员账号")
    private String schoolMemberAccount;

    public void setIntellectualPropertyId(Long intellectualPropertyId) 
    {
        this.intellectualPropertyId = intellectualPropertyId;
    }

    public Long getIntellectualPropertyId() 
    {
        return intellectualPropertyId;
    }
    public void setIntellectualPropertyName(String intellectualPropertyName) 
    {
        this.intellectualPropertyName = intellectualPropertyName;
    }

    public String getIntellectualPropertyName() 
    {
        return intellectualPropertyName;
    }
    public void setPerformanceCategory(String performanceCategory) 
    {
        this.performanceCategory = performanceCategory;
    }

    public String getPerformanceCategory() 
    {
        return performanceCategory;
    }
    public void setCertificateNumber(String certificateNumber) 
    {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateNumber() 
    {
        return certificateNumber;
    }
    public void setCountryOrRegion(String countryOrRegion) 
    {
        this.countryOrRegion = countryOrRegion;
    }

    public String getCountryOrRegion() 
    {
        return countryOrRegion;
    }
    public void setApplicationDate(Date applicationDate) 
    {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() 
    {
        return applicationDate;
    }
    public void setApprovalDate(Date approvalDate) 
    {
        this.approvalDate = approvalDate;
    }

    public Date getApprovalDate() 
    {
        return approvalDate;
    }
    public void setPatentType(String patentType) 
    {
        this.patentType = patentType;
    }

    public String getPatentType() 
    {
        return patentType;
    }
    public void setPatentStatus(String patentStatus) 
    {
        this.patentStatus = patentStatus;
    }

    public String getPatentStatus() 
    {
        return patentStatus;
    }
    public void setStandardType(String standardType) 
    {
        this.standardType = standardType;
    }

    public String getStandardType() 
    {
        return standardType;
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
            .append("intellectualPropertyId", getIntellectualPropertyId())
            .append("intellectualPropertyName", getIntellectualPropertyName())
            .append("performanceCategory", getPerformanceCategory())
            .append("certificateNumber", getCertificateNumber())
            .append("countryOrRegion", getCountryOrRegion())
            .append("applicationDate", getApplicationDate())
            .append("approvalDate", getApprovalDate())
            .append("patentType", getPatentType())
            .append("patentStatus", getPatentStatus())
            .append("standardType", getStandardType())
            .append("signatureUnit", getSignatureUnit())
            .append("belongsSecondaryUnitCode", getBelongsSecondaryUnitCode())
            .append("nameSecondaryUnit", getNameSecondaryUnit())
            .append("otherSignUnit", getOtherSignUnit())
            .append("memberInformation", getMemberInformation())
            .append("schoolMemberAccount", getSchoolMemberAccount())
            .toString();
    }
}
