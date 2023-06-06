package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推送记录对象 kyfz_enterprise
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public class KyfzEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业信息表的唯一表示符号 */
    private Long enterpriseId;

    /** 企业名 */
    @Excel(name = "企业名")
    private String enterpriseName;

    /** 企业信用代码 */
    @Excel(name = "企业信用代码")
    private String enterpriseCreditCode;

    /** 企业描述 */
    @Excel(name = "企业描述")
    private String enterpriseDescribe;

    /** 企业注册资本 */
    @Excel(name = "企业注册资本")
    private String registeredCapital;

    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setEnterpriseCreditCode(String enterpriseCreditCode) 
    {
        this.enterpriseCreditCode = enterpriseCreditCode;
    }

    public String getEnterpriseCreditCode() 
    {
        return enterpriseCreditCode;
    }
    public void setEnterpriseDescribe(String enterpriseDescribe) 
    {
        this.enterpriseDescribe = enterpriseDescribe;
    }

    public String getEnterpriseDescribe() 
    {
        return enterpriseDescribe;
    }
    public void setRegisteredCapital(String registeredCapital) 
    {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital() 
    {
        return registeredCapital;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseId", getEnterpriseId())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseCreditCode", getEnterpriseCreditCode())
            .append("enterpriseDescribe", getEnterpriseDescribe())
            .append("registeredCapital", getRegisteredCapital())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
