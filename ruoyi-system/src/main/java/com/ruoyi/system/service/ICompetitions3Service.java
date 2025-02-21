package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Competitions3;

/**
 * 赛事登记副本Service接口
 * 
 * @author maomao
 * @date 2024-11-29
 */
public interface ICompetitions3Service 
{
    /**
     * 查询赛事登记副本
     * 
     * @param id 赛事登记副本主键
     * @return 赛事登记副本
     */
    public Competitions3 selectCompetitions3ById(Long id);

    /**
     * 查询赛事登记副本列表
     * 
     * @param competitions3 赛事登记副本
     * @return 赛事登记副本集合
     */
    public List<Competitions3> selectCompetitions3List(Competitions3 competitions3);

    /**
     * 新增赛事登记副本
     * 
     * @param competitions3 赛事登记副本
     * @return 结果
     */
    public int insertCompetitions3(Competitions3 competitions3);

    /**
     * 修改赛事登记副本
     * 
     * @param competitions3 赛事登记副本
     * @return 结果
     */
    public int updateCompetitions3(Competitions3 competitions3);

    /**
     * 批量删除赛事登记副本
     * 
     * @param ids 需要删除的赛事登记副本主键集合
     * @return 结果
     */
    public int deleteCompetitions3ByIds(Long[] ids);

    /**
     * 删除赛事登记副本信息
     * 
     * @param id 赛事登记副本主键
     * @return 结果
     */
    public int deleteCompetitions3ById(Long id);
}
