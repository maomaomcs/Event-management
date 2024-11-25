package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ParticipantsMapper;
import com.ruoyi.system.domain.Participants;
import com.ruoyi.system.service.IParticipantsService;

/**
 * 赛事报销Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-11
 */
@Service
public class ParticipantsServiceImpl implements IParticipantsService 
{
    @Autowired
    private ParticipantsMapper participantsMapper;

    /**
     * 查询赛事报销
     * 
     * @param id 赛事报销主键
     * @return 赛事报销
     */
    @Override
    public Participants selectParticipantsById(Long id)
    {
        return participantsMapper.selectParticipantsById(id);
    }

    /**
     * 查询赛事报销列表
     * 
     * @param participants 赛事报销
     * @return 赛事报销
     */
    @Override
    public List<Participants> selectParticipantsList(Participants participants)
    {
        return participantsMapper.selectParticipantsList(participants);
    }

    /**
     * 新增赛事报销
     * 
     * @param participants 赛事报销
     * @return 结果
     */
    @Override
    public int insertParticipants(Participants participants)
    {
        return participantsMapper.insertParticipants(participants);
    }

    /**
     * 修改赛事报销
     * 
     * @param participants 赛事报销
     * @return 结果
     */
    @Override
    public int updateParticipants(Participants participants)
    {
        return participantsMapper.updateParticipants(participants);
    }

    /**
     * 批量删除赛事报销
     * 
     * @param ids 需要删除的赛事报销主键
     * @return 结果
     */
    @Override
    public int deleteParticipantsByIds(Long[] ids)
    {
        return participantsMapper.deleteParticipantsByIds(ids);
    }

    /**
     * 删除赛事报销信息
     * 
     * @param id 赛事报销主键
     * @return 结果
     */
    @Override
    public int deleteParticipantsById(Long id)
    {
        return participantsMapper.deleteParticipantsById(id);
    }
}
