package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作站对象 kyfz_workstation
 * 
 * @author zyx
 * @date 2023-07-10
 */
public class KyfzWorkstation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long taskId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务来源 */
    @Excel(name = "任务来源")
    private String taskSource;

    /** 任务数量 */
    @Excel(name = "任务数量")
    private String taskNumber;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    /** 关联用户user_id */
    private Long userId;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskSource(String taskSource) 
    {
        this.taskSource = taskSource;
    }

    public String getTaskSource() 
    {
        return taskSource;
    }
    public void setTaskNumber(String taskNumber) 
    {
        this.taskNumber = taskNumber;
    }

    public String getTaskNumber() 
    {
        return taskNumber;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("taskSource", getTaskSource())
            .append("taskNumber", getTaskNumber())
            .append("userId", getUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
