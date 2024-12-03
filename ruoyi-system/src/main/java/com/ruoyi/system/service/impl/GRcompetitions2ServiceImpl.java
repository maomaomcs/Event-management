package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GRcompetitions2Mapper;
import com.ruoyi.system.domain.GRcompetitions2;
import com.ruoyi.system.service.IGRcompetitions2Service;

/**
 * 个人赛事副本Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-29
 */
@Service
public class GRcompetitions2ServiceImpl implements IGRcompetitions2Service 
{
    @Autowired
    private GRcompetitions2Mapper gRcompetitions2Mapper;

    /**
     * 查询个人赛事副本
     * 
     * @param id 个人赛事副本主键
     * @return 个人赛事副本
     */
    @Override
    public GRcompetitions2 selectGRcompetitions2ById(Long id)
    {
        return gRcompetitions2Mapper.selectGRcompetitions2ById(id);
    }

    /**
     * 查询个人赛事副本列表
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 个人赛事副本
     */
    @Override
    public List<GRcompetitions2> selectGRcompetitions2List(GRcompetitions2 gRcompetitions2)
    {
        return gRcompetitions2Mapper.selectGRcompetitions2List(gRcompetitions2);
    }

    /**
     * 新增个人赛事副本
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 结果
     */
    @Override
    public int insertGRcompetitions2(GRcompetitions2 gRcompetitions2)
    {
        return gRcompetitions2Mapper.insertGRcompetitions2(gRcompetitions2);
    }

    /**
     * 修改个人赛事副本
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 结果
     */
    @Override
    public int updateGRcompetitions2(GRcompetitions2 gRcompetitions2)
    {
        return gRcompetitions2Mapper.updateGRcompetitions2(gRcompetitions2);
    }

    /**
     * 批量删除个人赛事副本
     * 
     * @param ids 需要删除的个人赛事副本主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions2ByIds(Long[] ids)
    {
        return gRcompetitions2Mapper.deleteGRcompetitions2ByIds(ids);
    }

    /**
     * 删除个人赛事副本信息
     * 
     * @param id 个人赛事副本主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions2ById(Long id)
    {
        return gRcompetitions2Mapper.deleteGRcompetitions2ById(id);
    }
}
