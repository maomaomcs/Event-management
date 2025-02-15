package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamMembers1Mapper;
import com.ruoyi.system.domain.TeamMembers1;
import com.ruoyi.system.service.ITeamMembers1Service;

/**
 * 队伍成员Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-12
 */
@Service
public class TeamMembers1ServiceImpl implements ITeamMembers1Service 
{
    @Autowired
    private TeamMembers1Mapper teamMembers1Mapper;

    /**
     * 查询队伍成员
     * 
     * @param id 队伍成员主键
     * @return 队伍成员
     */
    @Override
    public TeamMembers1 selectTeamMembers1ById(Long id)
    {
        return teamMembers1Mapper.selectTeamMembers1ById(id);
    }

    /**
     * 查询队伍成员列表
     * 
     * @param teamMembers1 队伍成员
     * @return 队伍成员
     */
    @Override
    public List<TeamMembers1> selectTeamMembers1List(TeamMembers1 teamMembers1)
    {
        return teamMembers1Mapper.selectTeamMembers1List(teamMembers1);
    }

    /**
     * 新增队伍成员
     * 
     * @param teamMembers1 队伍成员
     * @return 结果
     */
    @Override
    public int insertTeamMembers1(TeamMembers1 teamMembers1)
    {
        return teamMembers1Mapper.insertTeamMembers1(teamMembers1);
    }

    /**
     * 修改队伍成员
     * 
     * @param teamMembers1 队伍成员
     * @return 结果
     */
    @Override
    public int updateTeamMembers1(TeamMembers1 teamMembers1)
    {
        return teamMembers1Mapper.updateTeamMembers1(teamMembers1);
    }

    /**
     * 批量删除队伍成员
     * 
     * @param ids 需要删除的队伍成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembers1ByIds(Long[] ids)
    {
        return teamMembers1Mapper.deleteTeamMembers1ByIds(ids);
    }

    /**
     * 删除队伍成员信息
     * 
     * @param id 队伍成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembers1ById(Long id)
    {
        return teamMembers1Mapper.deleteTeamMembers1ById(id);
    }
}
