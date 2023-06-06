package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 匹配列表对象 kyfz_match
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public class KyfzMatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 匹配编号（专家匹配表的唯一表示符号） */
    private Long matchId;

    /** 需求表的id */
    @Excel(name = "需求表的id")
    private String requirementId;

    /** 匹配分值 */
    @Excel(name = "匹配分值")
    private String matchScore;

    /** 相关论文（多个论文id） */
    @Excel(name = "相关论文", readConverterExp = "多=个论文id")
    private String thesisId;

    /** 相关著作（多个著作id） */
    @Excel(name = "相关著作", readConverterExp = "多=个著作id")
    private String workId;

    /** 相关证书（多个证书id） */
    @Excel(name = "相关证书", readConverterExp = "多=个证书id")
    private String certificateId;

    public void setMatchId(Long matchId) 
    {
        this.matchId = matchId;
    }

    public Long getMatchId() 
    {
        return matchId;
    }
    public void setRequirementId(String requirementId) 
    {
        this.requirementId = requirementId;
    }

    public String getRequirementId() 
    {
        return requirementId;
    }
    public void setMatchScore(String matchScore) 
    {
        this.matchScore = matchScore;
    }

    public String getMatchScore() 
    {
        return matchScore;
    }
    public void setThesisId(String thesisId) 
    {
        this.thesisId = thesisId;
    }

    public String getThesisId() 
    {
        return thesisId;
    }
    public void setWorkId(String workId) 
    {
        this.workId = workId;
    }

    public String getWorkId() 
    {
        return workId;
    }
    public void setCertificateId(String certificateId) 
    {
        this.certificateId = certificateId;
    }

    public String getCertificateId() 
    {
        return certificateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("matchId", getMatchId())
            .append("requirementId", getRequirementId())
            .append("matchScore", getMatchScore())
            .append("thesisId", getThesisId())
            .append("workId", getWorkId())
            .append("certificateId", getCertificateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
