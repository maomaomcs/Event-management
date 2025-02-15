package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GRtdcompetitions;

/**
 * 个人团队赛事Service接口
 * 
 * @author maomao
 * @date 2024-12-19
 */
public interface IGRtdcompetitionsService 
{
    /**
     * 查询个人团队赛事
     * 
     * @param id 个人团队赛事主键
     * @return 个人团队赛事
     */
    public GRtdcompetitions selectGRtdcompetitionsById(Long id);

    /**
     * 查询个人团队赛事列表
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 个人团队赛事集合
     */
    public List<GRtdcompetitions> selectGRtdcompetitionsList(GRtdcompetitions gRtdcompetitions);

    /**
     * 新增个人团队赛事
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 结果
     */
    public int insertGRtdcompetitions(GRtdcompetitions gRtdcompetitions);

    /**
     * 修改个人团队赛事
     * 
     * @param gRtdcompetitions 个人团队赛事
     * @return 结果
     */
    public int updateGRtdcompetitions(GRtdcompetitions gRtdcompetitions);

    /**
     * 批量删除个人团队赛事
     * 
     * @param ids 需要删除的个人团队赛事主键集合
     * @return 结果
     */
    public int deleteGRtdcompetitionsByIds(Long[] ids);

    /**
     * 删除个人团队赛事信息
     * 
     * @param id 个人团队赛事主键
     * @return 结果
     */
    public int deleteGRtdcompetitionsById(Long id);
}
