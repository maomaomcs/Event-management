package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Competitions3Mapper;
import com.ruoyi.system.domain.Competitions3;
import com.ruoyi.system.service.ICompetitions3Service;

/**
 * 赛事登记副本Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-29
 */
@Service
public class Competitions3ServiceImpl implements ICompetitions3Service 
{
    @Autowired
    private Competitions3Mapper competitions3Mapper;

    /**
     * 查询赛事登记副本
     * 
     * @param id 赛事登记副本主键
     * @return 赛事登记副本
     */
    @Override
    public Competitions3 selectCompetitions3ById(Long id)
    {
        return competitions3Mapper.selectCompetitions3ById(id);
    }

    /**
     * 查询赛事登记副本列表
     * 
     * @param competitions3 赛事登记副本
     * @return 赛事登记副本
     */
    @Override
    public List<Competitions3> selectCompetitions3List(Competitions3 competitions3)
    {
        return competitions3Mapper.selectCompetitions3List(competitions3);
    }

    /**
     * 新增赛事登记副本
     * 
     * @param competitions3 赛事登记副本
     * @return 结果
     */
    @Override
    public int insertCompetitions3(Competitions3 competitions3)
    {
        return competitions3Mapper.insertCompetitions3(competitions3);
    }

    /**
     * 修改赛事登记副本
     * 
     * @param competitions3 赛事登记副本
     * @return 结果
     */
    @Override
    public int updateCompetitions3(Competitions3 competitions3)
    {
        return competitions3Mapper.updateCompetitions3(competitions3);
    }

    /**
     * 批量删除赛事登记副本
     * 
     * @param ids 需要删除的赛事登记副本主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions3ByIds(Long[] ids)
    {
        return competitions3Mapper.deleteCompetitions3ByIds(ids);
    }

    /**
     * 删除赛事登记副本信息
     * 
     * @param id 赛事登记副本主键
     * @return 结果
     */
    @Override
    public int deleteCompetitions3ById(Long id)
    {
        return competitions3Mapper.deleteCompetitions3ById(id);
    }
}
