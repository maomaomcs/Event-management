package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Newevent;

/**
 * 新建赛事Service接口
 * 
 * @author maomao
 * @date 2024-11-11
 */
public interface INeweventService 
{
    /**
     * 查询新建赛事
     * 
     * @param id 新建赛事主键
     * @return 新建赛事
     */
    public Newevent selectNeweventById(Long id);

    /**
     * 查询新建赛事列表
     * 
     * @param newevent 新建赛事
     * @return 新建赛事集合
     */
    public List<Newevent> selectNeweventList(Newevent newevent);

    /**
     * 新增新建赛事
     * 
     * @param newevent 新建赛事
     * @return 结果
     */
    public int insertNewevent(Newevent newevent);

    /**
     * 修改新建赛事
     * 
     * @param newevent 新建赛事
     * @return 结果
     */
    public int updateNewevent(Newevent newevent);

    /**
     * 批量删除新建赛事
     * 
     * @param ids 需要删除的新建赛事主键集合
     * @return 结果
     */
    public int deleteNeweventByIds(Long[] ids);

    /**
     * 删除新建赛事信息
     * 
     * @param id 新建赛事主键
     * @return 结果
     */
    public int deleteNeweventById(Long id);
}
