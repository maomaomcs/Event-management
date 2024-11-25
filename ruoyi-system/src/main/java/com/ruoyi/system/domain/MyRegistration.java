package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个人赛事对象 my_registration
 * 
 * @author maomao
 * @date 2024-11-11
 */
public class MyRegistration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生id */
    private Long id;

    /** 比赛名称 */
    @Excel(name = "比赛名称")
    private String competitionName;

    /** 比赛地点 */
    @Excel(name = "比赛地点")
    private String competitionLocation;

    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationDate;

    /** 成员信息 */
    @Excel(name = "成员信息")
    private String memberInfo;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String paymentStatus;

    /** 报销状态 */
    @Excel(name = "报销状态")
    private String reimbursementStatus;

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
    public void setCompetitionLocation(String competitionLocation) 
    {
        this.competitionLocation = competitionLocation;
    }

    public String getCompetitionLocation() 
    {
        return competitionLocation;
    }
    public void setRegistrationDate(Date registrationDate) 
    {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() 
    {
        return registrationDate;
    }
    public void setMemberInfo(String memberInfo) 
    {
        this.memberInfo = memberInfo;
    }

    public String getMemberInfo() 
    {
        return memberInfo;
    }
    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }
    public void setReimbursementStatus(String reimbursementStatus) 
    {
        this.reimbursementStatus = reimbursementStatus;
    }

    public String getReimbursementStatus() 
    {
        return reimbursementStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("competitionName", getCompetitionName())
            .append("competitionLocation", getCompetitionLocation())
            .append("registrationDate", getRegistrationDate())
            .append("memberInfo", getMemberInfo())
            .append("paymentStatus", getPaymentStatus())
            .append("reimbursementStatus", getReimbursementStatus())
            .toString();
    }
}
