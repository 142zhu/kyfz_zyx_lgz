package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 匹配列表对象 t_ppxx
 * 
 * @author zhu
 * @date 2023-06-04
 */
public class Ppxx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统id */
    private Long id;

    /** 企业id */
    @Excel(name = "企业id")
    private Long xqId;

    /** 专家工号id */
    @Excel(name = "专家工号id")
    private Long zjId;

    /** 专家匹配分值 */
    @Excel(name = "专家匹配分值")
    private String ppfz;

    /** 专业能力评分 */
    @Excel(name = "专业能力评分")
    private String zynlpf;

    /** 活跃度评分 */
    @Excel(name = "活跃度评分")
    private String hydpf;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long creatBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setXqId(Long xqId) 
    {
        this.xqId = xqId;
    }

    public Long getXqId() 
    {
        return xqId;
    }
    public void setZjId(Long zjId) 
    {
        this.zjId = zjId;
    }

    public Long getZjId() 
    {
        return zjId;
    }
    public void setPpfz(String ppfz) 
    {
        this.ppfz = ppfz;
    }

    public String getPpfz() 
    {
        return ppfz;
    }
    public void setZynlpf(String zynlpf) 
    {
        this.zynlpf = zynlpf;
    }

    public String getZynlpf() 
    {
        return zynlpf;
    }
    public void setHydpf(String hydpf) 
    {
        this.hydpf = hydpf;
    }

    public String getHydpf() 
    {
        return hydpf;
    }
    public void setCreatBy(Long creatBy) 
    {
        this.creatBy = creatBy;
    }

    public Long getCreatBy() 
    {
        return creatBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("xqId", getXqId())
            .append("zjId", getZjId())
            .append("ppfz", getPpfz())
            .append("zynlpf", getZynlpf())
            .append("hydpf", getHydpf())
            .append("creatBy", getCreatBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
