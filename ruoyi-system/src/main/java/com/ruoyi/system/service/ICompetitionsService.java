package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Competitions;

/**
 * 赛事记录1Service接口
 * 
 * @author maomao
 * @date 2024-11-10
 */
public interface ICompetitionsService 
{
    /**
     * 查询赛事记录1
     * 
     * @param id 赛事记录1主键
     * @return 赛事记录1
     */
    public Competitions selectCompetitionsById(Long id);

    /**
     * 查询赛事记录1列表
     * 
     * @param competitions 赛事记录1
     * @return 赛事记录1集合
     */
    public List<Competitions> selectCompetitionsList(Competitions competitions);

    /**
     * 新增赛事记录1
     * 
     * @param competitions 赛事记录1
     * @return 结果
     */
    public int insertCompetitions(Competitions competitions);

    /**
     * 修改赛事记录1
     * 
     * @param competitions 赛事记录1
     * @return 结果
     */
    public int updateCompetitions(Competitions competitions);

    /**
     * 批量删除赛事记录1
     * 
     * @param ids 需要删除的赛事记录1主键集合
     * @return 结果
     */
    public int deleteCompetitionsByIds(Long[] ids);

    /**
     * 删除赛事记录1信息
     * 
     * @param id 赛事记录1主键
     * @return 结果
     */
    public int deleteCompetitionsById(Long id);
}
