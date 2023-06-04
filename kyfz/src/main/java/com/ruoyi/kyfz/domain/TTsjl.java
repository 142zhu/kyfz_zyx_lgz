package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推送记录对象 t_tsjl
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public class TTsjl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统id */
    private Long id;

    /** 需求编号 */
    @Excel(name = "需求编号")
    private Long xqId;

    /** 需求名称 */
    @Excel(name = "需求名称")
    private String xqmc;

    /** 需求类型 */
    @Excel(name = "需求类型")
    private String xqlx;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String qymc;

    /** 企业简介 */
    @Excel(name = "企业简介")
    private String qyjj;

    /** 企业关键词 */
    @Excel(name = "企业关键词")
    private String qygjc;

    /** 注册资本 */
    @Excel(name = "注册资本")
    private String zczb;

    /** 是否已读 */
    @Excel(name = "是否已读")
    private String sfyd;

    /** 是否已反馈 */
    @Excel(name = "是否已反馈")
    private String sffk;

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
    public void setXqmc(String xqmc) 
    {
        this.xqmc = xqmc;
    }

    public String getXqmc() 
    {
        return xqmc;
    }
    public void setXqlx(String xqlx) 
    {
        this.xqlx = xqlx;
    }

    public String getXqlx() 
    {
        return xqlx;
    }
    public void setQymc(String qymc) 
    {
        this.qymc = qymc;
    }

    public String getQymc() 
    {
        return qymc;
    }
    public void setQyjj(String qyjj) 
    {
        this.qyjj = qyjj;
    }

    public String getQyjj() 
    {
        return qyjj;
    }
    public void setQygjc(String qygjc) 
    {
        this.qygjc = qygjc;
    }

    public String getQygjc() 
    {
        return qygjc;
    }
    public void setZczb(String zczb) 
    {
        this.zczb = zczb;
    }

    public String getZczb() 
    {
        return zczb;
    }
    public void setSfyd(String sfyd) 
    {
        this.sfyd = sfyd;
    }

    public String getSfyd() 
    {
        return sfyd;
    }
    public void setSffk(String sffk) 
    {
        this.sffk = sffk;
    }

    public String getSffk() 
    {
        return sffk;
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
            .append("xqmc", getXqmc())
            .append("xqlx", getXqlx())
            .append("qymc", getQymc())
            .append("qyjj", getQyjj())
            .append("qygjc", getQygjc())
            .append("zczb", getZczb())
            .append("sfyd", getSfyd())
            .append("sffk", getSffk())
            .append("creatBy", getCreatBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
