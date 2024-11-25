package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TeamInfo;

/**
 * 队伍信息Service接口
 * 
 * @author maomao
 * @date 2024-11-10
 */
public interface ITeamInfoService 
{
    /**
     * 查询队伍信息
     * 
     * @param teamid 队伍信息主键
     * @return 队伍信息
     */
    public TeamInfo selectTeamInfoByTeamid(Long teamid);

    /**
     * 查询队伍信息列表
     * 
     * @param teamInfo 队伍信息
     * @return 队伍信息集合
     */
    public List<TeamInfo> selectTeamInfoList(TeamInfo teamInfo);

    /**
     * 新增队伍信息
     * 
     * @param teamInfo 队伍信息
     * @return 结果
     */
    public int insertTeamInfo(TeamInfo teamInfo);

    /**
     * 修改队伍信息
     * 
     * @param teamInfo 队伍信息
     * @return 结果
     */
    public int updateTeamInfo(TeamInfo teamInfo);

    /**
     * 批量删除队伍信息
     * 
     * @param teamids 需要删除的队伍信息主键集合
     * @return 结果
     */
    public int deleteTeamInfoByTeamids(Long[] teamids);

    /**
     * 删除队伍信息信息
     * 
     * @param teamid 队伍信息主键
     * @return 结果
     */
    public int deleteTeamInfoByTeamid(Long teamid);
}
