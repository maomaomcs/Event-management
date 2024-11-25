package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Newevent;

/**
 * 新建赛事Mapper接口
 * 
 * @author maomao
 * @date 2024-11-11
 */
public interface NeweventMapper 
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
     * 删除新建赛事
     * 
     * @param id 新建赛事主键
     * @return 结果
     */
    public int deleteNeweventById(Long id);

    /**
     * 批量删除新建赛事
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNeweventByIds(Long[] ids);
}
