package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GRcompetitions;

/**
 * 赛事报名Service接口
 * 
 * @author maomao
 * @date 2024-11-26
 */
public interface IGRcompetitionsService 
{
    /**
     * 查询赛事报名
     * 
     * @param id 赛事报名主键
     * @return 赛事报名
     */
    public GRcompetitions selectGRcompetitionsById(Long id);

    /**
     * 查询赛事报名列表
     * 
     * @param gRcompetitions 赛事报名
     * @return 赛事报名集合
     */
    public List<GRcompetitions> selectGRcompetitionsList(GRcompetitions gRcompetitions);

    /**
     * 新增赛事报名
     * 
     * @param gRcompetitions 赛事报名
     * @return 结果
     */
    public int insertGRcompetitions(GRcompetitions gRcompetitions);

    /**
     * 修改赛事报名
     * 
     * @param gRcompetitions 赛事报名
     * @return 结果
     */
    public int updateGRcompetitions(GRcompetitions gRcompetitions);

    /**
     * 批量删除赛事报名
     * 
     * @param ids 需要删除的赛事报名主键集合
     * @return 结果
     */
    public int deleteGRcompetitionsByIds(Long[] ids);

    /**
     * 删除赛事报名信息
     * 
     * @param id 赛事报名主键
     * @return 结果
     */
    public int deleteGRcompetitionsById(Long id);
}
