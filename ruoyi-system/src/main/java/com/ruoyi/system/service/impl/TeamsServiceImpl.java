package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamsMapper;
import com.ruoyi.system.domain.Teams;
import com.ruoyi.system.service.ITeamsService;

/**
 * 队伍Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-10
 */
@Service
public class TeamsServiceImpl implements ITeamsService 
{
    @Autowired
    private TeamsMapper teamsMapper;

    /**
     * 查询队伍
     * 
     * @param id 队伍主键
     * @return 队伍
     */
    @Override
    public Teams selectTeamsById(Long id)
    {
        return teamsMapper.selectTeamsById(id);
    }

    /**
     * 查询队伍列表
     * 
     * @param teams 队伍
     * @return 队伍
     */
    @Override
    public List<Teams> selectTeamsList(Teams teams)
    {
        return teamsMapper.selectTeamsList(teams);
    }

    /**
     * 新增队伍
     * 
     * @param teams 队伍
     * @return 结果
     */
    @Override
    public int insertTeams(Teams teams)
    {
        return teamsMapper.insertTeams(teams);
    }

    /**
     * 修改队伍
     * 
     * @param teams 队伍
     * @return 结果
     */
    @Override
    public int updateTeams(Teams teams)
    {
        return teamsMapper.updateTeams(teams);
    }

    /**
     * 批量删除队伍
     * 
     * @param ids 需要删除的队伍主键
     * @return 结果
     */
    @Override
    public int deleteTeamsByIds(Long[] ids)
    {
        return teamsMapper.deleteTeamsByIds(ids);
    }

    /**
     * 删除队伍信息
     * 
     * @param id 队伍主键
     * @return 结果
     */
    @Override
    public int deleteTeamsById(Long id)
    {
        return teamsMapper.deleteTeamsById(id);
    }
}
