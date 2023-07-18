package com.ruoyi.kyfz.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专家团队对象 kyfz_team
 * 
 * @author zyx
 * @date 2023-07-18
 */
public class KyfzTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团队id（团队的唯一表示符号） */
    private Long teamId;

    /** 团队成员(按照重要程度从左到右排序) */
    @Excel(name = "团队成员(按照重要程度从左到右排序)")
    private String teamMembers;

    /** 团队成员账号 */
    @Excel(name = "团队成员账号")
    private String teamAccount;

    /** 成员的分数 */
    @Excel(name = "成员的分数")
    private String memberScores;

    /** 研究方向 */
    @Excel(name = "研究方向")
    private String reseachDirections;

    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setTeamMembers(String teamMembers) 
    {
        this.teamMembers = teamMembers;
    }

    public String getTeamMembers() 
    {
        return teamMembers;
    }
    public void setTeamAccount(String teamAccount) 
    {
        this.teamAccount = teamAccount;
    }

    public String getTeamAccount() 
    {
        return teamAccount;
    }
    public void setMemberScores(String memberScores) 
    {
        this.memberScores = memberScores;
    }

    public String getMemberScores() 
    {
        return memberScores;
    }
    public void setReseachDirections(String reseachDirections) 
    {
        this.reseachDirections = reseachDirections;
    }

    public String getReseachDirections() 
    {
        return reseachDirections;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamId", getTeamId())
            .append("teamMembers", getTeamMembers())
            .append("teamAccount", getTeamAccount())
            .append("memberScores", getMemberScores())
            .append("reseachDirections", getReseachDirections())
            .toString();
    }
}
