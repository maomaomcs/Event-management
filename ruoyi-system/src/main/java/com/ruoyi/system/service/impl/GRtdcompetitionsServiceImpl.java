package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GRtdcompetitionsMapper;
import com.ruoyi.system.domain.GRtdcompetitions;
import com.ruoyi.system.service.IGRtdcompetitionsService;

/**
 * 个人团队赛事Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-19
 */
@Service
public class GRtdcompetitionsServiceImpl implements IGRtdcompetitionsService 
{
    @Autowired
    private GRtdcompetitionsMapper gRtdcompetitionsMapper;

    /**
     * 查询个人团队赛事
     * 
     * @param id 个人团队赛事主键
     * @return 个人团队赛事
     */
    @Override
    public GRtdcompetitions selectGRtdcompetitionsById(Long id)
    {
        return gRtdcompetitionsMapper.selectGRtdcompetitionsById(id);
    }

    /**
     * 查询个人团队赛事列表
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 个人团队赛事
     */
    @Override
    public List<GRtdcompetitions> selectGRtdcompetitionsList(GRtdcompetitions gRtdcompetitions)
    {
        return gRtdcompetitionsMapper.selectGRtdcompetitionsList(gRtdcompetitions);
    }

    /**
     * 新增个人团队赛事
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 结果
     */
    @Override
    public int insertGRtdcompetitions(GRtdcompetitions gRtdcompetitions)
    {
        return gRtdcompetitionsMapper.insertGRtdcompetitions(gRtdcompetitions);
    }

    /**
     * 修改个人团队赛事
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 结果
     */
    @Override
    public int updateGRtdcompetitions(GRtdcompetitions gRtdcompetitions)
    {
        return gRtdcompetitionsMapper.updateGRtdcompetitions(gRtdcompetitions);
    }

    /**
     * 批量删除个人团队赛事
     * 
     * @param ids 需要删除的个人团队赛事主键
     * @return 结果
     */
    @Override
    public int deleteGRtdcompetitionsByIds(Long[] ids)
    {
        return gRtdcompetitionsMapper.deleteGRtdcompetitionsByIds(ids);
    }

    /**
     * 删除个人团队赛事信息
     * 
     * @param id 个人团队赛事主键
     * @return 结果
     */
    @Override
    public int deleteGRtdcompetitionsById(Long id)
    {
        return gRtdcompetitionsMapper.deleteGRtdcompetitionsById(id);
    }
}
