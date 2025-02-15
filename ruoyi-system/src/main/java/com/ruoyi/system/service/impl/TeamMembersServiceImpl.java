package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamMembersMapper;
import com.ruoyi.system.domain.TeamMembers;
import com.ruoyi.system.service.ITeamMembersService;

/**
 * 队伍成员Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-10
 */
@Service
public class TeamMembersServiceImpl implements ITeamMembersService 
{
    @Autowired
    private TeamMembersMapper teamMembersMapper;

    /**
     * 查询队伍成员
     * 
     * @param id 队伍成员主键
     * @return 队伍成员
     */
    @Override
    public TeamMembers selectTeamMembersById(Long id)
    {
        return teamMembersMapper.selectTeamMembersById(id);
    }

    /**
     * 查询队伍成员列表
     * 
     * @param teamMembers 队伍成员
     * @return 队伍成员
     */
    @Override
    public List<TeamMembers> selectTeamMembersList(TeamMembers teamMembers)
    {
        return teamMembersMapper.selectTeamMembersList(teamMembers);
    }

    /**
     * 新增队伍成员
     * 
     * @param teamMembers 队伍成员
     * @return 结果
     */
    @Override
    public int insertTeamMembers(TeamMembers teamMembers)
    {
        return teamMembersMapper.insertTeamMembers(teamMembers);
    }

    /**
     * 修改队伍成员
     * 
     * @param teamMembers 队伍成员
     * @return 结果
     */
    @Override
    public int updateTeamMembers(TeamMembers teamMembers)
    {
        return teamMembersMapper.updateTeamMembers(teamMembers);
    }

    /**
     * 批量删除队伍成员
     * 
     * @param ids 需要删除的队伍成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembersByIds(Long[] ids)
    {
        return teamMembersMapper.deleteTeamMembersByIds(ids);
    }

    /**
     * 删除队伍成员信息
     * 
     * @param id 队伍成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMembersById(Long id)
    {
        return teamMembersMapper.deleteTeamMembersById(id);
    }
}
