package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TDcompetitions;

/**
 * 团队赛事登记Service接口
 * 
 * @author maomao
 * @date 2024-12-12
 */
public interface ITDcompetitionsService 
{
    /**
     * 查询团队赛事登记
     * 
     * @param id 团队赛事登记主键
     * @return 团队赛事登记
     */
    public TDcompetitions selectTDcompetitionsById(Long id);

    /**
     * 查询团队赛事登记列表
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 团队赛事登记集合
     */
    public List<TDcompetitions> selectTDcompetitionsList(TDcompetitions tDcompetitions);

    /**
     * 新增团队赛事登记
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 结果
     */
    public int insertTDcompetitions(TDcompetitions tDcompetitions);

    /**
     * 修改团队赛事登记
     * 
     * @param tDcompetitions 团队赛事登记
     * @return 结果
     */
    public int updateTDcompetitions(TDcompetitions tDcompetitions);

    /**
     * 批量删除团队赛事登记
     * 
     * @param ids 需要删除的团队赛事登记主键集合
     * @return 结果
     */
    public int deleteTDcompetitionsByIds(Long[] ids);

    /**
     * 删除团队赛事登记信息
     * 
     * @param id 团队赛事登记主键
     * @return 结果
     */
    public int deleteTDcompetitionsById(Long id);
}
