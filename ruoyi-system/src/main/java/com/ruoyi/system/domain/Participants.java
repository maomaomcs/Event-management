package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 赛事报销对象 participants
 * 
 * @author maomao
 * @date 2024-11-11
 */
public class Participants extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名字 */
    @Excel(name = "名字")
    private String name;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 获奖等级 */
    @Excel(name = "获奖等级")
    private String awardLevel;

    /** 指导老师 */
    @Excel(name = "指导老师")
    private String advisorName;

    /** 收款码 */
    @Excel(name = "收款码")
    private String paymentCode;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applicationDate;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setCompetitionName(String competitionName) 
    {
        this.competitionName = competitionName;
    }

    public String getCompetitionName() 
    {
        return competitionName;
    }
    public void setAwardLevel(String awardLevel) 
    {
        this.awardLevel = awardLevel;
    }

    public String getAwardLevel() 
    {
        return awardLevel;
    }
    public void setAdvisorName(String advisorName) 
    {
        this.advisorName = advisorName;
    }

    public String getAdvisorName() 
    {
        return advisorName;
    }
    public void setPaymentCode(String paymentCode) 
    {
        this.paymentCode = paymentCode;
    }

    public String getPaymentCode() 
    {
        return paymentCode;
    }
    public void setApplicationDate(Date applicationDate) 
    {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() 
    {
        return applicationDate;
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
            .append("name", getName())
            .append("phoneNumber", getPhoneNumber())
            .append("competitionName", getCompetitionName())
            .append("awardLevel", getAwardLevel())
            .append("advisorName", getAdvisorName())
            .append("paymentCode", getPaymentCode())
            .append("applicationDate", getApplicationDate())
            .append("status", getStatus())
            .toString();
    }
}
