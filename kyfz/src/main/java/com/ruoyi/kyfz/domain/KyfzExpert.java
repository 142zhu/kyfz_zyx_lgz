package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专家管理对象 kyfz_expert
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public class KyfzExpert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 专家id */
    private Long expertId;

    /** 专家账号 */
    @Excel(name = "专家账号")
    private String expertAccount;

    /** 专家姓名 */
    @Excel(name = "专家姓名")
    private String expertName;

    /** 专家职称 */
    @Excel(name = "专家职称")
    private String expertPosition;

    /** 专家所属单位 */
    @Excel(name = "专家所属单位")
    private String expertAffiliation;

    /** 一级学科 */
    @Excel(name = "一级学科")
    private String primaryDiscipline;

    /** 二级学科 */
    @Excel(name = "二级学科")
    private String secondaryDiscipline;

    /** 三级学科 */
    @Excel(name = "三级学科")
    private String tertiaryDiscipline;

    /** 研究方向 */
    @Excel(name = "研究方向")
    private String researchDirection;

    /** 论文id(多个) */
    @Excel(name = "论文id(多个)")
    private String thesisId;

    /** 项目id(多个) */
    @Excel(name = "项目id(多个)")
    private String projectId;

    /** 证书id(多个) */
    @Excel(name = "证书id(多个)")
    private String certificateId;

    /** 著作id(多个) */
    @Excel(name = "著作id(多个)")
    private String workId;

    /** 已对接需求（多个需求id） */
    @Excel(name = "已对接需求", readConverterExp = "多=个需求id")
    private String requirementId;

    /** 所属团队id（多个） */
    @Excel(name = "所属团队id", readConverterExp = "多=个")
    private String expertTeams;

    public void setExpertId(Long expertId) 
    {
        this.expertId = expertId;
    }

    public Long getExpertId() 
    {
        return expertId;
    }
    public void setExpertAccount(String expertAccount) 
    {
        this.expertAccount = expertAccount;
    }

    public String getExpertAccount() 
    {
        return expertAccount;
    }
    public void setExpertName(String expertName) 
    {
        this.expertName = expertName;
    }

    public String getExpertName() 
    {
        return expertName;
    }
    public void setExpertPosition(String expertPosition) 
    {
        this.expertPosition = expertPosition;
    }

    public String getExpertPosition() 
    {
        return expertPosition;
    }
    public void setExpertAffiliation(String expertAffiliation) 
    {
        this.expertAffiliation = expertAffiliation;
    }

    public String getExpertAffiliation() 
    {
        return expertAffiliation;
    }
    public void setPrimaryDiscipline(String primaryDiscipline) 
    {
        this.primaryDiscipline = primaryDiscipline;
    }

    public String getPrimaryDiscipline() 
    {
        return primaryDiscipline;
    }
    public void setSecondaryDiscipline(String secondaryDiscipline) 
    {
        this.secondaryDiscipline = secondaryDiscipline;
    }

    public String getSecondaryDiscipline() 
    {
        return secondaryDiscipline;
    }
    public void setTertiaryDiscipline(String tertiaryDiscipline) 
    {
        this.tertiaryDiscipline = tertiaryDiscipline;
    }

    public String getTertiaryDiscipline() 
    {
        return tertiaryDiscipline;
    }
    public void setResearchDirection(String researchDirection) 
    {
        this.researchDirection = researchDirection;
    }

    public String getResearchDirection() 
    {
        return researchDirection;
    }
    public void setThesisId(String thesisId) 
    {
        this.thesisId = thesisId;
    }

    public String getThesisId() 
    {
        return thesisId;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setCertificateId(String certificateId) 
    {
        this.certificateId = certificateId;
    }

    public String getCertificateId() 
    {
        return certificateId;
    }
    public void setWorkId(String workId) 
    {
        this.workId = workId;
    }

    public String getWorkId() 
    {
        return workId;
    }
    public void setRequirementId(String requirementId) 
    {
        this.requirementId = requirementId;
    }

    public String getRequirementId() 
    {
        return requirementId;
    }
    public void setExpertTeams(String expertTeams) 
    {
        this.expertTeams = expertTeams;
    }

    public String getExpertTeams() 
    {
        return expertTeams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expertId", getExpertId())
            .append("expertAccount", getExpertAccount())
            .append("expertName", getExpertName())
            .append("expertPosition", getExpertPosition())
            .append("expertAffiliation", getExpertAffiliation())
            .append("primaryDiscipline", getPrimaryDiscipline())
            .append("secondaryDiscipline", getSecondaryDiscipline())
            .append("tertiaryDiscipline", getTertiaryDiscipline())
            .append("researchDirection", getResearchDirection())
            .append("thesisId", getThesisId())
            .append("projectId", getProjectId())
            .append("certificateId", getCertificateId())
            .append("workId", getWorkId())
            .append("requirementId", getRequirementId())
            .append("expertTeams", getExpertTeams())
            .toString();
    }
}
