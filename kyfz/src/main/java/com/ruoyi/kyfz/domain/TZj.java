package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专家信息对象 t_zj
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public class TZj extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统id */
    private Long id;

    /** 专家工号id */
    @Excel(name = "专家工号id")
    private Long zjId;

    /** 需求id */
    @Excel(name = "需求id")
    private Long xqId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String xm;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String dh;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String yx;

    /** 学院 */
    @Excel(name = "学院")
    private String xy;

    /** 研究方向 */
    @Excel(name = "研究方向")
    private String yjfx;

    /** 职称 */
    @Excel(name = "职称")
    private String zc;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String grjj;

    /** 是否团队负责人 */
    @Excel(name = "是否团队负责人")
    private String sffzr;

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
    public void setZjId(Long zjId) 
    {
        this.zjId = zjId;
    }

    public Long getZjId() 
    {
        return zjId;
    }
    public void setXqId(Long xqId) 
    {
        this.xqId = xqId;
    }

    public Long getXqId() 
    {
        return xqId;
    }
    public void setXm(String xm) 
    {
        this.xm = xm;
    }

    public String getXm() 
    {
        return xm;
    }
    public void setDh(String dh) 
    {
        this.dh = dh;
    }

    public String getDh() 
    {
        return dh;
    }
    public void setYx(String yx) 
    {
        this.yx = yx;
    }

    public String getYx() 
    {
        return yx;
    }
    public void setXy(String xy) 
    {
        this.xy = xy;
    }

    public String getXy() 
    {
        return xy;
    }
    public void setYjfx(String yjfx) 
    {
        this.yjfx = yjfx;
    }

    public String getYjfx() 
    {
        return yjfx;
    }
    public void setZc(String zc) 
    {
        this.zc = zc;
    }

    public String getZc() 
    {
        return zc;
    }
    public void setGrjj(String grjj) 
    {
        this.grjj = grjj;
    }

    public String getGrjj() 
    {
        return grjj;
    }
    public void setSffzr(String sffzr) 
    {
        this.sffzr = sffzr;
    }

    public String getSffzr() 
    {
        return sffzr;
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
            .append("zjId", getZjId())
            .append("xqId", getXqId())
            .append("xm", getXm())
            .append("dh", getDh())
            .append("yx", getYx())
            .append("xy", getXy())
            .append("yjfx", getYjfx())
            .append("zc", getZc())
            .append("grjj", getGrjj())
            .append("sffzr", getSffzr())
            .append("creatBy", getCreatBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
