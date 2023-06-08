package com.ruoyi.kyfz.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推送记录对象 kyfz_push_record
 * 
 * @author zhu
 * @date 2023-06-07
 */
public class KyfzPushRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long pushId;

    /** 匹配id */
    @Excel(name = "匹配id")
    private Long matchId;

    /** 推送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "推送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pushTime;

    /** 数据字典（未点击、有意向、无意向） */
    @Excel(name = "数据字典", readConverterExp = "未=点击、有意向、无意向")
    private String feebback;

    /**************** 按需求添加的字段 */
    /** 需求 */
    @Excel(name = "需求", readConverterExp = "项目名")
    private String projectName;

    /** 需求 */
    @Excel(name = "专家", readConverterExp = "专家的姓名")
    private String expertName;

    /** 提出需求的企业 */
    @Excel(name = "企业", readConverterExp = "提出需求的企业")
    private String client;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    /**************** **************** */

    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }

    public Long getPushId() {
        return pushId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setFeebback(String feebback) {
        this.feebback = feebback;
    }

    public String getFeebback() {
        return feebback;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pushId", getPushId())
                .append("matchId", getMatchId())
                .append("pushTime", getPushTime())
                .append("feebback", getFeebback())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("projectName", getProjectName())
                .append("expertName", getExpertName())
                .toString();
    }
}
