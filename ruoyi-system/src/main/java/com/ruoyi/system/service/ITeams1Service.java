package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Teams1;

/**
 * 队伍Service接口
 * 
 * @author maomao
 * @date 2024-12-11
 */
public interface ITeams1Service 
{
    /**
     * 查询队伍
     * 
     * @param id 队伍主键
     * @return 队伍
     */
    public Teams1 selectTeams1ById(Long id);

    /**
     * 查询队伍列表
     * 
     * @param teams1 队伍
     * @return 队伍集合
     */
    public List<Teams1> selectTeams1List(Teams1 teams1);

    /**
     * 新增队伍
     * 
     * @param teams1 队伍
     * @return 结果
     */
    public int insertTeams1(Teams1 teams1);

    /**
     * 修改队伍
     * 
     * @param teams1 队伍
     * @return 结果
     */
    public int updateTeams1(Teams1 teams1);

    /**
     * 批量删除队伍
     * 
     * @param ids 需要删除的队伍主键集合
     * @return 结果
     */
    public int deleteTeams1ByIds(Long[] ids);

    /**
     * 删除队伍信息
     * 
     * @param id 队伍主键
     * @return 结果
     */
    public int deleteTeams1ById(Long id);
}
