package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TeamInfoMapper;
import com.ruoyi.system.domain.TeamInfo;
import com.ruoyi.system.service.ITeamInfoService;

/**
 * 队伍信息Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-10
 */
@Service
public class TeamInfoServiceImpl implements ITeamInfoService 
{
    @Autowired
    private TeamInfoMapper teamInfoMapper;

    /**
     * 查询队伍信息
     * 
     * @param teamid 队伍信息主键
     * @return 队伍信息
     */
    @Override
    public TeamInfo selectTeamInfoByTeamid(Long teamid)
    {
        return teamInfoMapper.selectTeamInfoByTeamid(teamid);
    }

    /**
     * 查询队伍信息列表
     * 
     * @param teamInfo 队伍信息
     * @return 队伍信息
     */
    @Override
    public List<TeamInfo> selectTeamInfoList(TeamInfo teamInfo)
    {
        return teamInfoMapper.selectTeamInfoList(teamInfo);
    }

    /**
     * 新增队伍信息
     * 
     * @param teamInfo 队伍信息
     * @return 结果
     */
    @Override
    public int insertTeamInfo(TeamInfo teamInfo)
    {
        return teamInfoMapper.insertTeamInfo(teamInfo);
    }

    /**
     * 修改队伍信息
     * 
     * @param teamInfo 队伍信息
     * @return 结果
     */
    @Override
    public int updateTeamInfo(TeamInfo teamInfo)
    {
        return teamInfoMapper.updateTeamInfo(teamInfo);
    }

    /**
     * 批量删除队伍信息
     * 
     * @param teamids 需要删除的队伍信息主键
     * @return 结果
     */
    @Override
    public int deleteTeamInfoByTeamids(Long[] teamids)
    {
        return teamInfoMapper.deleteTeamInfoByTeamids(teamids);
    }

    /**
     * 删除队伍信息信息
     * 
     * @param teamid 队伍信息主键
     * @return 结果
     */
    @Override
    public int deleteTeamInfoByTeamid(Long teamid)
    {
        return teamInfoMapper.deleteTeamInfoByTeamid(teamid);
    }
}
