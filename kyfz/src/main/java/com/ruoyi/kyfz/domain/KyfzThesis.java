package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 论文信息对象 kyfz_thesis
 * 
 * @author zyx
 * @date 2023-08-19
 */
public class KyfzThesis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 论文id */
    private Long thesisId;

    /** 论文名称 */
    @Excel(name = "论文名称")
    private String thesisName;

    /** 论文类型 */
    @Excel(name = "论文类型")
    private String thesisType;

    /** 发表时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发表时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publicateTime;

    /** 摘要 */
    @Excel(name = "摘要")
    private String thesisAbstract;

    /** 关键词 */
    @Excel(name = "关键词")
    private String thesisKeywords;

    /** 刊物名称 */
    @Excel(name = "刊物名称")
    private String thesisJournal;

    /** 刊物级别 */
    @Excel(name = "刊物级别")
    private String journalLevel;

    /** 论文作者（所有作者名称） */
    @Excel(name = "论文作者", readConverterExp = "所=有作者名称")
    private String thesisAuthor;

    /** 校内作者账号 */
    @Excel(name = "校内作者账号")
    private String schoolAuthorAccount;

    public void setThesisId(Long thesisId) 
    {
        this.thesisId = thesisId;
    }

    public Long getThesisId() 
    {
        return thesisId;
    }
    public void setThesisName(String thesisName) 
    {
        this.thesisName = thesisName;
    }

    public String getThesisName() 
    {
        return thesisName;
    }
    public void setThesisType(String thesisType) 
    {
        this.thesisType = thesisType;
    }

    public String getThesisType() 
    {
        return thesisType;
    }
    public void setPublicateTime(Date publicateTime) 
    {
        this.publicateTime = publicateTime;
    }

    public Date getPublicateTime() 
    {
        return publicateTime;
    }
    public void setThesisAbstract(String thesisAbstract) 
    {
        this.thesisAbstract = thesisAbstract;
    }

    public String getThesisAbstract() 
    {
        return thesisAbstract;
    }
    public void setThesisKeywords(String thesisKeywords) 
    {
        this.thesisKeywords = thesisKeywords;
    }

    public String getThesisKeywords() 
    {
        return thesisKeywords;
    }
    public void setThesisJournal(String thesisJournal) 
    {
        this.thesisJournal = thesisJournal;
    }

    public String getThesisJournal() 
    {
        return thesisJournal;
    }
    public void setJournalLevel(String journalLevel) 
    {
        this.journalLevel = journalLevel;
    }

    public String getJournalLevel() 
    {
        return journalLevel;
    }
    public void setThesisAuthor(String thesisAuthor) 
    {
        this.thesisAuthor = thesisAuthor;
    }

    public String getThesisAuthor() 
    {
        return thesisAuthor;
    }
    public void setSchoolAuthorAccount(String schoolAuthorAccount) 
    {
        this.schoolAuthorAccount = schoolAuthorAccount;
    }

    public String getSchoolAuthorAccount() 
    {
        return schoolAuthorAccount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("thesisId", getThesisId())
            .append("thesisName", getThesisName())
            .append("thesisType", getThesisType())
            .append("publicateTime", getPublicateTime())
            .append("thesisAbstract", getThesisAbstract())
            .append("thesisKeywords", getThesisKeywords())
            .append("thesisJournal", getThesisJournal())
            .append("journalLevel", getJournalLevel())
            .append("thesisAuthor", getThesisAuthor())
            .append("schoolAuthorAccount", getSchoolAuthorAccount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
