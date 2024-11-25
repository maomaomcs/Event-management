package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报销审核对象 reimbursement
 * 
 * @author maomao
 * @date 2024-11-10
 */
public class Reimbursement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @Excel(name = "主键id")
    private Long id;

    /** 付款截图 */
    @Excel(name = "收款截图")
    private String paymentCodeImage;

    /** 申报人姓名 */
    @Excel(name = "申报人姓名")
    private String name;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 比赛等级 */
    @Excel(name = "比赛等级")
    private String awardLevel;

    /** 申报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submissionDate;

    /** 状态信息 */
    @Excel(name = "状态信息")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPaymentCodeImage(String paymentCodeImage) 
    {
        this.paymentCodeImage = paymentCodeImage;
    }

    public String getPaymentCodeImage() 
    {
        return paymentCodeImage;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setSubmissionDate(Date submissionDate) 
    {
        this.submissionDate = submissionDate;
    }

    public Date getSubmissionDate() 
    {
        return submissionDate;
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
            .append("paymentCodeImage", getPaymentCodeImage())
            .append("name", getName())
            .append("competitionName", getCompetitionName())
            .append("awardLevel", getAwardLevel())
            .append("submissionDate", getSubmissionDate())
            .append("status", getStatus())
            .toString();
    }
}
