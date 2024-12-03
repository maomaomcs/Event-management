package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个人赛事11对象 GRcompetitions1
 * 
 * @author maomao
 * @date 2024-11-29
 */
public class GRcompetitions1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 赛事自增id */
    private Long id;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 赛事id */
    @Excel(name = "赛事id")
    private Long competitionId;

    /** 赛事类型 */
    @Excel(name = "赛事类型")
    private String competitionCategory;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionName", getCompetitionName())
            .append("competitionId", getCompetitionId())
            .append("competitionCategory", getCompetitionCategory())
            .append("name", getName())
            .append("studentId", getStudentId())
            .append("major", getMajor())
            .append("status", getStatus())
            .toString();
    }
}
