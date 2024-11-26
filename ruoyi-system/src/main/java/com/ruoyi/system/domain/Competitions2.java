package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 赛事登记对象 competitions2
 * 
 * @author maomao
 * @date 2024-11-26
 */
public class Competitions2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 赛事id */
    private Long id;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 赛事级别 */
    @Excel(name = "赛事级别")
    private String organizingBody;

    /** 赛事费用 */
    @Excel(name = "赛事费用")
    private String participationFee;

    /** 赛事类别 */
    @Excel(name = "赛事类别")
    private String competitionType;

    /** 开始报名 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始报名", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationDate;

    /** 截止报名 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止报名", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 开始比赛 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始比赛", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionStartTime;

    /** 截止比赛 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止比赛", width = 30, dateFormat = "yyyy-MM-dd")
    private Date competitionEndTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String actions;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompetitionName(String competitionName) 
    {
        this.competitionName = competitionName;
    }

    public String getCompetitionName() 
    {
        return competitionName;
    }
    public void setOrganizingBody(String organizingBody) 
    {
        this.organizingBody = organizingBody;
    }

    public String getOrganizingBody() 
    {
        return organizingBody;
    }
    public void setParticipationFee(String participationFee) 
    {
        this.participationFee = participationFee;
    }

    public String getParticipationFee() 
    {
        return participationFee;
    }
    public void setCompetitionType(String competitionType) 
    {
        this.competitionType = competitionType;
    }

    public String getCompetitionType() 
    {
        return competitionType;
    }
    public void setRegistrationDate(Date registrationDate) 
    {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() 
    {
        return registrationDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setCompetitionStartTime(Date competitionStartTime) 
    {
        this.competitionStartTime = competitionStartTime;
    }

    public Date getCompetitionStartTime() 
    {
        return competitionStartTime;
    }
    public void setCompetitionEndTime(Date competitionEndTime) 
    {
        this.competitionEndTime = competitionEndTime;
    }

    public Date getCompetitionEndTime() 
    {
        return competitionEndTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setActions(String actions) 
    {
        this.actions = actions;
    }

    public String getActions() 
    {
        return actions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionName", getCompetitionName())
            .append("organizingBody", getOrganizingBody())
            .append("participationFee", getParticipationFee())
            .append("competitionType", getCompetitionType())
            .append("registrationDate", getRegistrationDate())
            .append("endDate", getEndDate())
            .append("competitionStartTime", getCompetitionStartTime())
            .append("competitionEndTime", getCompetitionEndTime())
            .append("status", getStatus())
            .append("actions", getActions())
            .toString();
    }
}
