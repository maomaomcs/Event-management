package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NeweventMapper;
import com.ruoyi.system.domain.Newevent;
import com.ruoyi.system.service.INeweventService;

/**
 * 新建赛事Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-11
 */
@Service
public class NeweventServiceImpl implements INeweventService 
{
    @Autowired
    private NeweventMapper neweventMapper;

    /**
     * 查询新建赛事
     * 
     * @param id 新建赛事主键
     * @return 新建赛事
     */
    @Override
    public Newevent selectNeweventById(Long id)
    {
        return neweventMapper.selectNeweventById(id);
    }

    /**
     * 查询新建赛事列表
     * 
     * @param newevent 新建赛事
     * @return 新建赛事
     */
    @Override
    public List<Newevent> selectNeweventList(Newevent newevent)
    {
        return neweventMapper.selectNeweventList(newevent);
    }

    /**
     * 新增新建赛事
     * 
     * @param newevent 新建赛事
     * @return 结果
     */
    @Override
    public int insertNewevent(Newevent newevent)
    {
        return neweventMapper.insertNewevent(newevent);
    }

    /**
     * 修改新建赛事
     * 
     * @param newevent 新建赛事
     * @return 结果
     */
    @Override
    public int updateNewevent(Newevent newevent)
    {
        return neweventMapper.updateNewevent(newevent);
    }

    /**
     * 批量删除新建赛事
     * 
     * @param ids 需要删除的新建赛事主键
     * @return 结果
     */
    @Override
    public int deleteNeweventByIds(Long[] ids)
    {
        return neweventMapper.deleteNeweventByIds(ids);
    }

    /**
     * 删除新建赛事信息
     * 
     * @param id 新建赛事主键
     * @return 结果
     */
    @Override
    public int deleteNeweventById(Long id)
    {
        return neweventMapper.deleteNeweventById(id);
    }
}
