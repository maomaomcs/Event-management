package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GRcompetitions1Mapper;
import com.ruoyi.system.domain.GRcompetitions1;
import com.ruoyi.system.service.IGRcompetitions1Service;

/**
 * 个人赛事11Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-29
 */
@Service
public class GRcompetitions1ServiceImpl implements IGRcompetitions1Service 
{
    @Autowired
    private GRcompetitions1Mapper gRcompetitions1Mapper;

    /**
     * 查询个人赛事11
     * 
     * @param id 个人赛事11主键
     * @return 个人赛事11
     */
    @Override
    public GRcompetitions1 selectGRcompetitions1ById(Long id)
    {
        return gRcompetitions1Mapper.selectGRcompetitions1ById(id);
    }

    /**
     * 查询个人赛事11列表
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 个人赛事11
     */
    @Override
    public List<GRcompetitions1> selectGRcompetitions1List(GRcompetitions1 gRcompetitions1)
    {
        return gRcompetitions1Mapper.selectGRcompetitions1List(gRcompetitions1);
    }

    /**
     * 新增个人赛事11
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 结果
     */
    @Override
    public int insertGRcompetitions1(GRcompetitions1 gRcompetitions1)
    {
        return gRcompetitions1Mapper.insertGRcompetitions1(gRcompetitions1);
    }

    /**
     * 修改个人赛事11
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 结果
     */
    @Override
    public int updateGRcompetitions1(GRcompetitions1 gRcompetitions1)
    {
        return gRcompetitions1Mapper.updateGRcompetitions1(gRcompetitions1);
    }

    /**
     * 批量删除个人赛事11
     * 
     * @param ids 需要删除的个人赛事11主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions1ByIds(Long[] ids)
    {
        return gRcompetitions1Mapper.deleteGRcompetitions1ByIds(ids);
    }

    /**
     * 删除个人赛事11信息
     * 
     * @param id 个人赛事11主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions1ById(Long id)
    {
        return gRcompetitions1Mapper.deleteGRcompetitions1ById(id);
    }
}
