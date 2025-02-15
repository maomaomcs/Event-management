package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个人团队赛事对象 GRtdcompetitions
 * 
 * @author maomao
 * @date 2024-12-19
 */
public class GRtdcompetitions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 赛事id */
    @Excel(name = "赛事id")
    private Long competitionId;

    /** 赛事类别 */
    @Excel(name = "赛事类别")
    private String competitionCategory;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    /** 队长姓名 */
    @Excel(name = "队长姓名")
    private String leaderName;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 指导老师 */
    @Excel(name = "指导老师")
    private String instructors;

    /** 获奖信息 */
    @Excel(name = "获奖信息")
    private String award;

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
    public void setCompetitionId(Long competitionId) 
    {
        this.competitionId = competitionId;
    }

    public Long getCompetitionId() 
    {
        return competitionId;
    }
    public void setCompetitionCategory(String competitionCategory) 
    {
        this.competitionCategory = competitionCategory;
    }

    public String getCompetitionCategory() 
    {
        return competitionCategory;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setLeaderName(String leaderName) 
    {
        this.leaderName = leaderName;
    }

    public String getLeaderName() 
    {
        return leaderName;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setInstructors(String instructors) 
    {
        this.instructors = instructors;
    }

    public String getInstructors() 
    {
        return instructors;
    }
    public void setAward(String award) 
    {
        this.award = award;
    }

    public String getAward() 
    {
        return award;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionName", getCompetitionName())
            .append("competitionId", getCompetitionId())
            .append("competitionCategory", getCompetitionCategory())
            .append("teamName", getTeamName())
            .append("leaderName", getLeaderName())
            .append("studentId", getStudentId())
            .append("status", getStatus())
            .append("instructors", getInstructors())
            .append("award", getAward())
            .toString();
    }
}
