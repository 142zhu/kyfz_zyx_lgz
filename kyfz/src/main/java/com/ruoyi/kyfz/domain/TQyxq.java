package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业需求信息对象 t_qyxq
 * 
 * @author zhu
 * @date 2023-06-04
 */
public class TQyxq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统id */
    private Long id;

    /** 企业id */
    @Excel(name = "企业id")
    private Long qyId;

    /** 需求名称 */
    @Excel(name = "需求名称")
    private String mc;

    /** 需求关键词 */
    @Excel(name = "需求关键词")
    private String gjc;

    /** 需求详细描述 */
    @Excel(name = "需求详细描述")
    private String xxmc;

    /** 需求发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fbsj;

    /** 需求状态 */
    @Excel(name = "需求状态")
    private String xqzt;

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
    public void setQyId(Long qyId) 
    {
        this.qyId = qyId;
    }

    public Long getQyId() 
    {
        return qyId;
    }
    public void setMc(String mc) 
    {
        this.mc = mc;
    }

    public String getMc() 
    {
        return mc;
    }
    public void setGjc(String gjc) 
    {
        this.gjc = gjc;
    }

    public String getGjc() 
    {
        return gjc;
    }
    public void setXxmc(String xxmc) 
    {
        this.xxmc = xxmc;
    }

    public String getXxmc() 
    {
        return xxmc;
    }
    public void setFbsj(Date fbsj) 
    {
        this.fbsj = fbsj;
    }

    public Date getFbsj() 
    {
        return fbsj;
    }
    public void setXqzt(String xqzt) 
    {
        this.xqzt = xqzt;
    }

    public String getXqzt() 
    {
        return xqzt;
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
            .append("qyId", getQyId())
            .append("mc", getMc())
            .append("gjc", getGjc())
            .append("xxmc", getXxmc())
            .append("fbsj", getFbsj())
            .append("xqzt", getXqzt())
            .append("creatBy", getCreatBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
