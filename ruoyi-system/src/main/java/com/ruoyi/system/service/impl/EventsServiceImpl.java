package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EventsMapper;
import com.ruoyi.system.domain.Events;
import com.ruoyi.system.service.IEventsService;

/**
 * 赛事记录Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-10
 */
@Service
public class EventsServiceImpl implements IEventsService 
{
    @Autowired
    private EventsMapper eventsMapper;

    /**
     * 查询赛事记录
     * 
     * @param id 赛事记录主键
     * @return 赛事记录
     */
    @Override
    public Events selectEventsById(Long id)
    {
        return eventsMapper.selectEventsById(id);
    }

    /**
     * 查询赛事记录列表
     * 
     * @param events 赛事记录
     * @return 赛事记录
     */
    @Override
    public List<Events> selectEventsList(Events events)
    {
        return eventsMapper.selectEventsList(events);
    }

    /**
     * 新增赛事记录
     * 
     * @param events 赛事记录
     * @return 结果
     */
    @Override
    public int insertEvents(Events events)
    {
        return eventsMapper.insertEvents(events);
    }

    /**
     * 修改赛事记录
     * 
     * @param events 赛事记录
     * @return 结果
     */
    @Override
    public int updateEvents(Events events)
    {
        return eventsMapper.updateEvents(events);
    }

    /**
     * 批量删除赛事记录
     * 
     * @param ids 需要删除的赛事记录主键
     * @return 结果
     */
    @Override
    public int deleteEventsByIds(Long[] ids)
    {
        return eventsMapper.deleteEventsByIds(ids);
    }

    /**
     * 删除赛事记录信息
     * 
     * @param id 赛事记录主键
     * @return 结果
     */
    @Override
    public int deleteEventsById(Long id)
    {
        return eventsMapper.deleteEventsById(id);
    }
}
