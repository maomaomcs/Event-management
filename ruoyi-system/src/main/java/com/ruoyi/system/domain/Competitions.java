package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 赛事记录1对象 competitions
 * 
 * @author maomao
 * @date 2024-11-10
 */
public class Competitions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账号id */
    private Long id;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String name;

    /** 参赛人 */
    @Excel(name = "参赛人")
    private String participant;

    /** 指导老师 */
    @Excel(name = "指导老师")
    private String mentor;

    /** 成绩 */
    @Excel(name = "成绩")
    private String score;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParticipant(String participant) 
    {
        this.participant = participant;
    }

    public String getParticipant() 
    {
        return participant;
    }
    public void setMentor(String mentor) 
    {
        this.mentor = mentor;
    }

    public String getMentor() 
    {
        return mentor;
    }
    public void setScore(String score) 
    {
        this.score = score;
    }

    public String getScore() 
    {
        return score;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("participant", getParticipant())
            .append("mentor", getMentor())
            .append("score", getScore())
            .append("status", getStatus())
            .append("registrationTime", getRegistrationTime())
            .append("updateTime", getUpdateTime())
            .append("notes", getNotes())
            .toString();
    }
}
