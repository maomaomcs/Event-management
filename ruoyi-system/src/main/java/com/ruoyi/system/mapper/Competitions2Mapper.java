package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Competitions2;

/**
 * 赛事登记Mapper接口
 * 
 * @author maomao
 * @date 2024-11-26
 */
public interface Competitions2Mapper 
{
    /**
     * 查询赛事登记
     * 
     * @param id 赛事登记主键
     * @return 赛事登记
     */
    public Competitions2 selectCompetitions2ById(Long id);

    /**
     * 查询赛事登记列表
     * 
     * @param competitions2 赛事登记
     * @return 赛事登记集合
     */
    public List<Competitions2> selectCompetitions2List(Competitions2 competitions2);

    /**
     * 新增赛事登记
     * 
     * @param competitions2 赛事登记
     * @return 结果
     */
    public int insertCompetitions2(Competitions2 competitions2);

    /**
     * 修改赛事登记
     * 
     * @param competitions2 赛事登记
     * @return 结果
     */
    public int updateCompetitions2(Competitions2 competitions2);

    /**
     * 删除赛事登记
     * 
     * @param id 赛事登记主键
     * @return 结果
     */
    public int deleteCompetitions2ById(Long id);

    /**
     * 批量删除赛事登记
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitions2ByIds(Long[] ids);
}
