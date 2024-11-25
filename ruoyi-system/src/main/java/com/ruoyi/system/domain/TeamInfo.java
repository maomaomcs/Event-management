package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 队伍信息对象 TeamInfo
 * 
 * @author maomao
 * @date 2024-11-10
 */
public class TeamInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 队伍id */
    private Long teamid;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String eventname;

    /** 队伍名称 */
    @Excel(name = "队伍名称")
    private String teamname;

    /** 队伍成员 */
    @Excel(name = "队伍成员")
    private String teammembers;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdat;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedat;

    public void setTeamid(Long teamid) 
    {
        this.teamid = teamid;
    }

    public Long getTeamid() 
    {
        return teamid;
    }
    public void setEventname(String eventname) 
    {
        this.eventname = eventname;
    }

    public String getEventname() 
    {
        return eventname;
    }
    public void setTeamname(String teamname) 
    {
        this.teamname = teamname;
    }

    public String getTeamname() 
    {
        return teamname;
    }
    public void setTeammembers(String teammembers) 
    {
        this.teammembers = teammembers;
    }

    public String getTeammembers() 
    {
        return teammembers;
    }
    public void setCreatedat(Date createdat) 
    {
        this.createdat = createdat;
    }

    public Date getCreatedat() 
    {
        return createdat;
    }
    public void setUpdatedat(Date updatedat) 
    {
        this.updatedat = updatedat;
    }

    public Date getUpdatedat() 
    {
        return updatedat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamid", getTeamid())
            .append("eventname", getEventname())
            .append("teamname", getTeamname())
            .append("teammembers", getTeammembers())
            .append("createdat", getCreatedat())
            .append("updatedat", getUpdatedat())
            .toString();
    }
}
