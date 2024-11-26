package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GRcompetitionsMapper;
import com.ruoyi.system.domain.GRcompetitions;
import com.ruoyi.system.service.IGRcompetitionsService;

/**
 * 赛事报名Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-26
 */
@Service
public class GRcompetitionsServiceImpl implements IGRcompetitionsService 
{
    @Autowired
    private GRcompetitionsMapper gRcompetitionsMapper;

    /**
     * 查询赛事报名
     * 
     * @param id 赛事报名主键
     * @return 赛事报名
     */
    @Override
    public GRcompetitions selectGRcompetitionsById(Long id)
    {
        return gRcompetitionsMapper.selectGRcompetitionsById(id);
    }

    /**
     * 查询赛事报名列表
     * 
     * @param gRcompetitions 赛事报名
     * @return 赛事报名
     */
    @Override
    public List<GRcompetitions> selectGRcompetitionsList(GRcompetitions gRcompetitions)
    {
        return gRcompetitionsMapper.selectGRcompetitionsList(gRcompetitions);
    }

    /**
     * 新增赛事报名
     * 
     * @param gRcompetitions 赛事报名
     * @return 结果
     */
    @Override
    public int insertGRcompetitions(GRcompetitions gRcompetitions)
    {
        return gRcompetitionsMapper.insertGRcompetitions(gRcompetitions);
    }

    /**
     * 修改赛事报名
     * 
     * @param gRcompetitions 赛事报名
     * @return 结果
     */
    @Override
    public int updateGRcompetitions(GRcompetitions gRcompetitions)
    {
        return gRcompetitionsMapper.updateGRcompetitions(gRcompetitions);
    }

    /**
     * 批量删除赛事报名
     * 
     * @param ids 需要删除的赛事报名主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitionsByIds(Long[] ids)
    {
        return gRcompetitionsMapper.deleteGRcompetitionsByIds(ids);
    }

    /**
     * 删除赛事报名信息
     * 
     * @param id 赛事报名主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitionsById(Long id)
    {
        return gRcompetitionsMapper.deleteGRcompetitionsById(id);
    }
}
