package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TDcompetitionsMapper;
import com.ruoyi.system.domain.TDcompetitions;
import com.ruoyi.system.service.ITDcompetitionsService;

/**
 * 团队赛事登记Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-12
 */
@Service
public class TDcompetitionsServiceImpl implements ITDcompetitionsService 
{
    @Autowired
    private TDcompetitionsMapper tDcompetitionsMapper;

    /**
     * 查询团队赛事登记
     * 
     * @param id 团队赛事登记主键
     * @return 团队赛事登记
     */
    @Override
    public TDcompetitions selectTDcompetitionsById(Long id)
    {
        return tDcompetitionsMapper.selectTDcompetitionsById(id);
    }

    /**
     * 查询团队赛事登记列表
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 团队赛事登记
     */
    @Override
    public List<TDcompetitions> selectTDcompetitionsList(TDcompetitions tDcompetitions)
    {
        return tDcompetitionsMapper.selectTDcompetitionsList(tDcompetitions);
    }

    /**
     * 新增团队赛事登记
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 结果
     */
    @Override
    public int insertTDcompetitions(TDcompetitions tDcompetitions)
    {
        return tDcompetitionsMapper.insertTDcompetitions(tDcompetitions);
    }

    /**
     * 修改团队赛事登记
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 结果
     */
    @Override
    public int updateTDcompetitions(TDcompetitions tDcompetitions)
    {
        return tDcompetitionsMapper.updateTDcompetitions(tDcompetitions);
    }

    /**
     * 批量删除团队赛事登记
     * 
     * @param ids 需要删除的团队赛事登记主键
     * @return 结果
     */
    @Override
    public int deleteTDcompetitionsByIds(Long[] ids)
    {
        return tDcompetitionsMapper.deleteTDcompetitionsByIds(ids);
    }

    /**
     * 删除团队赛事登记信息
     * 
     * @param id 团队赛事登记主键
     * @return 结果
     */
    @Override
    public int deleteTDcompetitionsById(Long id)
    {
        return tDcompetitionsMapper.deleteTDcompetitionsById(id);
    }
}
