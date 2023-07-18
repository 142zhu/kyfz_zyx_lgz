package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专家关系对象 kyfz_relationship
 * 
 * @author zyx
 * @date 2023-07-18
 */
public class KyfzRelationship extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系id */
    private Long relationshipId;

    /** 专家a的id */
    @Excel(name = "专家a的id")
    private Long expertAId;

    /** 专家b的id */
    @Excel(name = "专家b的id")
    private Long expertBId;

    /** 关系类型 */
    @Excel(name = "关系类型")
    private String relationshipType;

    /** 关系描述 */
    @Excel(name = "关系描述")
    private String relationshipDescription;

    /** 关系数值 */
    @Excel(name = "关系数值")
    private String relationshipValue;

    public void setRelationshipId(Long relationshipId) 
    {
        this.relationshipId = relationshipId;
    }

    public Long getRelationshipId() 
    {
        return relationshipId;
    }
    public void setExpertAId(Long expertAId) 
    {
        this.expertAId = expertAId;
    }

    public Long getExpertAId() 
    {
        return expertAId;
    }
    public void setExpertBId(Long expertBId) 
    {
        this.expertBId = expertBId;
    }

    public Long getExpertBId() 
    {
        return expertBId;
    }
    public void setRelationshipType(String relationshipType) 
    {
        this.relationshipType = relationshipType;
    }

    public String getRelationshipType() 
    {
        return relationshipType;
    }
    public void setRelationshipDescription(String relationshipDescription) 
    {
        this.relationshipDescription = relationshipDescription;
    }

    public String getRelationshipDescription() 
    {
        return relationshipDescription;
    }
    public void setRelationshipValue(String relationshipValue) 
    {
        this.relationshipValue = relationshipValue;
    }

    public String getRelationshipValue() 
    {
        return relationshipValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationshipId", getRelationshipId())
            .append("expertAId", getExpertAId())
            .append("expertBId", getExpertBId())
            .append("relationshipType", getRelationshipType())
            .append("relationshipDescription", getRelationshipDescription())
            .append("relationshipValue", getRelationshipValue())
            .toString();
    }
}
