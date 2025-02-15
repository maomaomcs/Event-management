package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TeamMembers1;

/**
 * 队伍成员Mapper接口
 * 
 * @author maomao
 * @date 2024-12-12
 */
public interface TeamMembers1Mapper 
{
    /**
     * 查询队伍成员
     * 
     * @param id 队伍成员主键
     * @return 队伍成员
     */
    public TeamMembers1 selectTeamMembers1ById(Long id);

    /**
     * 查询队伍成员列表
     * 
     * @param teamMembers1 队伍成员
     * @return 队伍成员集合
     */
    public List<TeamMembers1> selectTeamMembers1List(TeamMembers1 teamMembers1);

    /**
     * 新增队伍成员
     * 
     * @param teamMembers1 队伍成员
     * @return 结果
     */
    public int insertTeamMembers1(TeamMembers1 teamMembers1);

    /**
     * 修改队伍成员
     * 
     * @param teamMembers1 队伍成员
     * @return 结果
     */
    public int updateTeamMembers1(TeamMembers1 teamMembers1);

    /**
     * 删除队伍成员
     * 
     * @param id 队伍成员主键
     * @return 结果
     */
    public int deleteTeamMembers1ById(Long id);

    /**
     * 批量删除队伍成员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamMembers1ByIds(Long[] ids);
}
