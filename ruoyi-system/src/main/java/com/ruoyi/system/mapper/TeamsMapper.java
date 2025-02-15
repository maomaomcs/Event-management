package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Teams;

/**
 * 队伍Mapper接口
 * 
 * @author maomao
 * @date 2024-12-10
 */
public interface TeamsMapper 
{
    /**
     * 查询队伍
     * 
     * @param id 队伍主键
     * @return 队伍
     */
    public Teams selectTeamsById(Long id);

    /**
     * 查询队伍列表
     * 
     * @param teams 队伍
     * @return 队伍集合
     */
    public List<Teams> selectTeamsList(Teams teams);

    /**
     * 新增队伍
     * 
     * @param teams 队伍
     * @return 结果
     */
    public int insertTeams(Teams teams);

    /**
     * 修改队伍
     * 
     * @param teams 队伍
     * @return 结果
     */
    public int updateTeams(Teams teams);

    /**
     * 删除队伍
     * 
     * @param id 队伍主键
     * @return 结果
     */
    public int deleteTeamsById(Long id);

    /**
     * 批量删除队伍
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamsByIds(Long[] ids);
}
