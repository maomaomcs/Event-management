package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TeamMembers;

/**
 * 队伍成员Mapper接口
 * 
 * @author maomao
 * @date 2024-12-10
 */
public interface TeamMembersMapper 
{
    /**
     * 查询队伍成员
     * 
     * @param id 队伍成员主键
     * @return 队伍成员
     */
    public TeamMembers selectTeamMembersById(Long id);

    /**
     * 查询队伍成员列表
     * 
     * @param teamMembers 队伍成员
     * @return 队伍成员集合
     */
    public List<TeamMembers> selectTeamMembersList(TeamMembers teamMembers);

    /**
     * 新增队伍成员
     * 
     * @param teamMembers 队伍成员
     * @return 结果
     */
    public int insertTeamMembers(TeamMembers teamMembers);

    /**
     * 修改队伍成员
     * 
     * @param teamMembers 队伍成员
     * @return 结果
     */
    public int updateTeamMembers(TeamMembers teamMembers);

    /**
     * 删除队伍成员
     * 
     * @param id 队伍成员主键
     * @return 结果
     */
    public int deleteTeamMembersById(Long id);

    /**
     * 批量删除队伍成员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamMembersByIds(Long[] ids);
}
