package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Teams1Mapper;
import com.ruoyi.system.domain.Teams1;
import com.ruoyi.system.service.ITeams1Service;

/**
 * 队伍Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-11
 */
@Service
public class Teams1ServiceImpl implements ITeams1Service 
{
    @Autowired
    private Teams1Mapper teams1Mapper;

    /**
     * 查询队伍
     * 
     * @param id 队伍主键
     * @return 队伍
     */
    @Override
    public Teams1 selectTeams1ById(Long id)
    {
        return teams1Mapper.selectTeams1ById(id);
    }

    /**
     * 查询队伍列表
     * 
     * @param teams1 队伍
     * @return 队伍
     */
    @Override
    public List<Teams1> selectTeams1List(Teams1 teams1)
    {
        return teams1Mapper.selectTeams1List(teams1);
    }

    /**
     * 新增队伍
     * 
     * @param teams1 队伍
     * @return 结果
     */
    @Override
    public int insertTeams1(Teams1 teams1)
    {
        return teams1Mapper.insertTeams1(teams1);
    }

    /**
     * 修改队伍
     * 
     * @param teams1 队伍
     * @return 结果
     */
    @Override
    public int updateTeams1(Teams1 teams1)
    {
        return teams1Mapper.updateTeams1(teams1);
    }

    /**
     * 批量删除队伍
     * 
     * @param ids 需要删除的队伍主键
     * @return 结果
     */
    @Override
    public int deleteTeams1ByIds(Long[] ids)
    {
        return teams1Mapper.deleteTeams1ByIds(ids);
    }

    /**
     * 删除队伍信息
     * 
     * @param id 队伍主键
     * @return 结果
     */
    @Override
    public int deleteTeams1ById(Long id)
    {
        return teams1Mapper.deleteTeams1ById(id);
    }
}
