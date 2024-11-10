package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompetitionsMapper;
import com.ruoyi.system.domain.Competitions;
import com.ruoyi.system.service.ICompetitionsService;

/**
 * 赛事记录1Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-10
 */
@Service
public class CompetitionsServiceImpl implements ICompetitionsService 
{
    @Autowired
    private CompetitionsMapper competitionsMapper;

    /**
     * 查询赛事记录1
     * 
     * @param id 赛事记录1主键
     * @return 赛事记录1
     */
    @Override
    public Competitions selectCompetitionsById(Long id)
    {
        return competitionsMapper.selectCompetitionsById(id);
    }

    /**
     * 查询赛事记录1列表
     * 
     * @param competitions 赛事记录1
     * @return 赛事记录1
     */
    @Override
    public List<Competitions> selectCompetitionsList(Competitions competitions)
    {
        return competitionsMapper.selectCompetitionsList(competitions);
    }

    /**
     * 新增赛事记录1
     * 
     * @param competitions 赛事记录1
     * @return 结果
     */
    @Override
    public int insertCompetitions(Competitions competitions)
    {
        return competitionsMapper.insertCompetitions(competitions);
    }

    /**
     * 修改赛事记录1
     * 
     * @param competitions 赛事记录1
     * @return 结果
     */
    @Override
    public int updateCompetitions(Competitions competitions)
    {
        competitions.setUpdateTime(DateUtils.getNowDate());
        return competitionsMapper.updateCompetitions(competitions);
    }

    /**
     * 批量删除赛事记录1
     * 
     * @param ids 需要删除的赛事记录1主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionsByIds(Long[] ids)
    {
        return competitionsMapper.deleteCompetitionsByIds(ids);
    }

    /**
     * 删除赛事记录1信息
     * 
     * @param id 赛事记录1主键
     * @return 结果
     */
    @Override
    public int deleteCompetitionsById(Long id)
    {
        return competitionsMapper.deleteCompetitionsById(id);
    }
}
