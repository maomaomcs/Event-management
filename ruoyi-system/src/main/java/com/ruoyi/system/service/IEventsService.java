package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Events;

/**
 * 赛事记录Service接口
 * 
 * @author maomao
 * @date 2024-11-10
 */
public interface IEventsService 
{
    /**
     * 查询赛事记录
     * 
     * @param id 赛事记录主键
     * @return 赛事记录
     */
    public Events selectEventsById(Long id);

    /**
     * 查询赛事记录列表
     * 
     * @param events 赛事记录
     * @return 赛事记录集合
     */
    public List<Events> selectEventsList(Events events);

    /**
     * 新增赛事记录
     * 
     * @param events 赛事记录
     * @return 结果
     */
    public int insertEvents(Events events);

    /**
     * 修改赛事记录
     * 
     * @param events 赛事记录
     * @return 结果
     */
    public int updateEvents(Events events);

    /**
     * 批量删除赛事记录
     * 
     * @param ids 需要删除的赛事记录主键集合
     * @return 结果
     */
    public int deleteEventsByIds(Long[] ids);

    /**
     * 删除赛事记录信息
     * 
     * @param id 赛事记录主键
     * @return 结果
     */
    public int deleteEventsById(Long id);
}
