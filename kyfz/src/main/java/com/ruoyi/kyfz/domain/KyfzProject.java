package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 kyfz_project
 * 
 * @author ruoyi
 * @date 2023-08-18
 */
public class KyfzProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String projectCategory;

    /** 立项日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "立项日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String projectStatus;

    /** 项目级别 */
    @Excel(name = "项目级别")
    private String projectLevel;

    /** 项目批准号 */
    @Excel(name = "项目批准号")
    private String approvalNumber;

    /** 结题日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结题日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 立项经费 */
    @Excel(name = "立项经费")
    private String startFunds;

    /** 到账经费 */
    @Excel(name = "到账经费")
    private String accountFunds;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 署名单位 */
    @Excel(name = "署名单位")
    private String signUnit;

    /** 其他署名单位 */
    @Excel(name = "其他署名单位")
    private String otherSignUnit;

    /** 归属二级单位 */
    @Excel(name = "归属二级单位")
    private String dept;

    /** 成员信息 */
    @Excel(name = "成员信息")
    private String memberInfo;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String dataSource;

    /** 校内成员账号 */
    @Excel(name = "校内成员账号")
    private String schoolMemberAccount;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setProjectCategory(String projectCategory) 
    {
        this.projectCategory = projectCategory;
    }

    public String getProjectCategory() 
    {
        return projectCategory;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setProjectStatus(String projectStatus) 
    {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatus() 
    {
        return projectStatus;
    }
    public void setProjectLevel(String projectLevel) 
    {
        this.projectLevel = projectLevel;
    }

    public String getProjectLevel() 
    {
        return projectLevel;
    }
    public void setApprovalNumber(String approvalNumber) 
    {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalNumber() 
    {
        return approvalNumber;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setStartFunds(String startFunds) 
    {
        this.startFunds = startFunds;
    }

    public String getStartFunds() 
    {
        return startFunds;
    }
    public void setAccountFunds(String accountFunds) 
    {
        this.accountFunds = accountFunds;
    }

    public String getAccountFunds() 
    {
        return accountFunds;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSignUnit(String signUnit) 
    {
        this.signUnit = signUnit;
    }

    public String getSignUnit() 
    {
        return signUnit;
    }
    public void setOtherSignUnit(String otherSignUnit) 
    {
        this.otherSignUnit = otherSignUnit;
    }

    public String getOtherSignUnit() 
    {
        return otherSignUnit;
    }
    public void setDept(String dept) 
    {
        this.dept = dept;
    }

    public String getDept() 
    {
        return dept;
    }
    public void setMemberInfo(String memberInfo) 
    {
        this.memberInfo = memberInfo;
    }

    public String getMemberInfo() 
    {
        return memberInfo;
    }
    public void setDataSource(String dataSource) 
    {
        this.dataSource = dataSource;
    }

    public String getDataSource() 
    {
        return dataSource;
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
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectType", getProjectType())
            .append("projectCategory", getProjectCategory())
            .append("startDate", getStartDate())
            .append("projectStatus", getProjectStatus())
            .append("projectLevel", getProjectLevel())
            .append("approvalNumber", getApprovalNumber())
            .append("endDate", getEndDate())
            .append("startFunds", getStartFunds())
            .append("accountFunds", getAccountFunds())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("signUnit", getSignUnit())
            .append("otherSignUnit", getOtherSignUnit())
            .append("dept", getDept())
            .append("remark", getRemark())
            .append("memberInfo", getMemberInfo())
            .append("dataSource", getDataSource())
            .append("schoolMemberAccount", getSchoolMemberAccount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
