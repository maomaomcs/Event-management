package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Competitions2Mapper;
import com.ruoyi.system.domain.Competitions2;
import com.ruoyi.system.service.ICompetitions2Service;

/**
 * 赛事登记Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-26
 */
@Service
public class Competitions2ServiceImpl implements ICompetitions2Service 
{
    @Autowired
    private Competitions2Mapper competitions2Mapper;

    /**
     * 查询赛事登记
     * 
     * @param id 赛事登记主键
     * @return 赛事登记
     */
    @Override
    public Competitions2 selectCompetitions2ById(Long id)
    {
        return competitions2Mapper.selectCompetitions2ById(id);
    }

    /**
     * 查询赛事登记列表
     * 
     * @param competitions2 赛事登记
     * @return 赛事登记
     */
    @Override
    public List<Competitions2> selectCompetitions2List(Competitions2 competitions2)
    {
        return competitions2Mapper.selectCompetitions2List(competitions2);
    }

    /**
     * 新增赛事登记
     * 
     * @param competitions2 赛事登记
     * @return 结果
     */
    @Override
    public int insertCompetitions2(Competitions2 competitions2)
    {
        return competitions2Mapper.insertCompetitions2(competitions2);
    }

    /**
     * 修改赛事登记
     * 
     * @param competitions2 赛事登记
     * @return 结果
     */
    @Override
    public int updateCompetitions2(Competitions2 competitions2)
    {
        return competitions2Mapper.updateCompetitions2(competitions2);
    }

    /**
     * 批量删除赛事登记
     * 
     * @param ids 需要删除的赛事登记主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions2ByIds(Long[] ids)
    {
        return competitions2Mapper.deleteCompetitions2ByIds(ids);
    }

    /**
     * 删除赛事登记信息
     * 
     * @param id 赛事登记主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions2ById(Long id)
    {
        return competitions2Mapper.deleteCompetitions2ById(id);
    }
}
