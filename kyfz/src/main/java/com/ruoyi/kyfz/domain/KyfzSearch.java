package com.ruoyi.kyfz.domain;

import java.util.List;

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

    /** 检索关键词 */
    private String keyWord;

    /** 检索标志 */
    private String mark;

    private List<String> Account;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getMark() {
        return mark;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

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

    public void setAccount(List<String> account) {
        Account = account;
    }

    public List<String> getAccount() {
        return Account;
    }
}
