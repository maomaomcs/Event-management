package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Participants;

/**
 * 赛事报销Service接口
 * 
 * @author maomao
 * @date 2024-11-11
 */
public interface IParticipantsService 
{
    /**
     * 查询赛事报销
     * 
     * @param id 赛事报销主键
     * @return 赛事报销
     */
    public Participants selectParticipantsById(Long id);

    /**
     * 查询赛事报销列表
     * 
     * @param participants 赛事报销
     * @return 赛事报销集合
     */
    public List<Participants> selectParticipantsList(Participants participants);

    /**
     * 新增赛事报销
     * 
     * @param participants 赛事报销
     * @return 结果
     */
    public int insertParticipants(Participants participants);

    /**
     * 修改赛事报销
     * 
     * @param participants 赛事报销
     * @return 结果
     */
    public int updateParticipants(Participants participants);

    /**
     * 批量删除赛事报销
     * 
     * @param ids 需要删除的赛事报销主键集合
     * @return 结果
     */
    public int deleteParticipantsByIds(Long[] ids);

    /**
     * 删除赛事报销信息
     * 
     * @param id 赛事报销主键
     * @return 结果
     */
    public int deleteParticipantsById(Long id);
}
