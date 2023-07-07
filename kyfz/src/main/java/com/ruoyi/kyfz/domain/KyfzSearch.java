package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检索对象 kyfz_search
 * 
 * @author zyx
 * @date 2023-07-05
 */
public class KyfzSearch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 检索id */
    private Long searchId;

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Long getSearchId() {
        return searchId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("searchId", getSearchId())
                .toString();
    }
}
