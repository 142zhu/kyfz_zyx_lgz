package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业管理对象 kyfz_enterprise
 * 
 * @author ruoyi
 * @date 2023-06-09
 */
public class KyfzEnterprise extends BaseEntity {
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

    /** 企业关键词 */
    @Excel(name = "企业关键词")
    private String enterpriseKeywords;

    /** 企业分类(多个) */
    private String categoryId;

    /** 企业分类的名称(多个) */
    private String categoryNames;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCategoryNames() {
        return categoryNames;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseCreditCode(String enterpriseCreditCode) {
        this.enterpriseCreditCode = enterpriseCreditCode;
    }

    public String getEnterpriseCreditCode() {
        return enterpriseCreditCode;
    }

    public void setEnterpriseDescribe(String enterpriseDescribe) {
        this.enterpriseDescribe = enterpriseDescribe;
    }

    public String getEnterpriseDescribe() {
        return enterpriseDescribe;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setEnterpriseKeywords(String enterpriseKeywords) {
        this.enterpriseKeywords = enterpriseKeywords;
    }

    public String getEnterpriseKeywords() {
        return enterpriseKeywords;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("enterpriseId", getEnterpriseId())
                .append("enterpriseName", getEnterpriseName())
                .append("enterpriseCreditCode", getEnterpriseCreditCode())
                .append("enterpriseDescribe", getEnterpriseDescribe())
                .append("registeredCapital", getRegisteredCapital())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("enterpriseKeywords", getEnterpriseKeywords())
                .toString();
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }
}
