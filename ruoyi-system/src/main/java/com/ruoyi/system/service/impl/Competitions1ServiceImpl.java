package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Competitions1Mapper;
import com.ruoyi.system.domain.Competitions1;
import com.ruoyi.system.service.ICompetitions1Service;

/**
 * 赛事列表1Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-15
 */
@Service
public class Competitions1ServiceImpl implements ICompetitions1Service 
{
    @Autowired
    private Competitions1Mapper competitions1Mapper;

    /**
     * 查询赛事列表1
     * 
     * @param id 赛事列表1主键
     * @return 赛事列表1
     */
    @Override
    public Competitions1 selectCompetitions1ById(Long id)
    {
        return competitions1Mapper.selectCompetitions1ById(id);
    }

    /**
     * 查询赛事列表1列表
     * 
     * @param competitions1 赛事列表1
     * @return 赛事列表1
     */
    @Override
    public List<Competitions1> selectCompetitions1List(Competitions1 competitions1)
    {
        return competitions1Mapper.selectCompetitions1List(competitions1);
    }

    /**
     * 新增赛事列表1
     * 
     * @param competitions1 赛事列表1
     * @return 结果
     */
    @Override
    public int insertCompetitions1(Competitions1 competitions1)
    {
        return competitions1Mapper.insertCompetitions1(competitions1);
    }

    /**
     * 修改赛事列表1
     * 
     * @param competitions1 赛事列表1
     * @return 结果
     */
    @Override
    public int updateCompetitions1(Competitions1 competitions1)
    {
        return competitions1Mapper.updateCompetitions1(competitions1);
    }

    /**
     * 批量删除赛事列表1
     * 
     * @param ids 需要删除的赛事列表1主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions1ByIds(Long[] ids)
    {
        return competitions1Mapper.deleteCompetitions1ByIds(ids);
    }

    /**
     * 删除赛事列表1信息
     * 
     * @param id 赛事列表1主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions1ById(Long id)
    {
        return competitions1Mapper.deleteCompetitions1ById(id);
    }
}
