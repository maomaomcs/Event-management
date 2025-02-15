package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 队伍对象 teams
 * 
 * @author maomao
 * @date 2024-12-10
 */
public class Teams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 队伍id */
    private Long id;

    /** 赛事id */
    @Excel(name = "赛事id")
    private Long eventId;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String teamName;

    /** 队长id */
    @Excel(name = "队长id")
    private Long leaderId;

    /** 赛事成员数 */
    @Excel(name = "赛事成员数")
    private Long membersCount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间 ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEventId(Long eventId) 
    {
        this.eventId = eventId;
    }

    public Long getEventId() 
    {
        return eventId;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setLeaderId(Long leaderId) 
    {
        this.leaderId = leaderId;
    }

    public Long getLeaderId() 
    {
        return leaderId;
    }
    public void setMembersCount(Long membersCount) 
    {
        this.membersCount = membersCount;
    }

    public Long getMembersCount() 
    {
        return membersCount;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("eventId", getEventId())
            .append("teamName", getTeamName())
            .append("leaderId", getLeaderId())
            .append("membersCount", getMembersCount())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
