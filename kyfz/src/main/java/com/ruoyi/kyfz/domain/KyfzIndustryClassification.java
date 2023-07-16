package com.ruoyi.kyfz.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行业分类对象 kyfz_industry_classification
 * 
 * @author zyx
 * @date 2023-07-16
 */
public class KyfzIndustryClassification extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 行业分类id */
    private Long categoryId;

    /** 行业分类名称 */
    @Excel(name = "行业分类名称")
    private String categoryName;

    /** 父id */
    @Excel(name = "父id")
    private Long parentId;

    /** 等级 */
    @Excel(name = "等级")
    private String level;

    // 孩子节点
    private List<KyfzIndustryClassification> children;

    // 增加孩子
    public void addChild(KyfzIndustryClassification child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public List<KyfzIndustryClassification> getChildren() {
        return children;
    }

    public void setChildren(List<KyfzIndustryClassification> children) {
        this.children = children;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryId", getCategoryId())
                .append("categoryName", getCategoryName())
                .append("parentId", getParentId())
                .append("level", getLevel())
                .toString();
    }
}
