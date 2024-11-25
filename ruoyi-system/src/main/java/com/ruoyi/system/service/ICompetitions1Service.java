package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Competitions1;

/**
 * 赛事列表1Service接口
 * 
 * @author maomao
 * @date 2024-11-15
 */
public interface ICompetitions1Service 
{
    /**
     * 查询赛事列表1
     * 
     * @param id 赛事列表1主键
     * @return 赛事列表1
     */
    public Competitions1 selectCompetitions1ById(Long id);

    /**
     * 查询赛事列表1列表
     * 
     * @param competitions1 赛事列表1
     * @return 赛事列表1集合
     */
    public List<Competitions1> selectCompetitions1List(Competitions1 competitions1);

    /**
     * 新增赛事列表1
     * 
     * @param competitions1 赛事列表1
     * @return 结果
     */
    public int insertCompetitions1(Competitions1 competitions1);

    /**
     * 修改赛事列表1
     * 
     * @param competitions1 赛事列表1
     * @return 结果
     */
    public int updateCompetitions1(Competitions1 competitions1);

    /**
     * 批量删除赛事列表1
     * 
     * @param ids 需要删除的赛事列表1主键集合
     * @return 结果
     */
    public int deleteCompetitions1ByIds(Long[] ids);

    /**
     * 删除赛事列表1信息
     * 
     * @param id 赛事列表1主键
     * @return 结果
     */
    public int deleteCompetitions1ById(Long id);
}
