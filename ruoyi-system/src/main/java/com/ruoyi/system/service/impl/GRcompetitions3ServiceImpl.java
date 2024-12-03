package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GRcompetitions3Mapper;
import com.ruoyi.system.domain.GRcompetitions3;
import com.ruoyi.system.service.IGRcompetitions3Service;

/**
 * 个人赛事副本Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-01
 */
@Service
public class GRcompetitions3ServiceImpl implements IGRcompetitions3Service 
{
    @Autowired
    private GRcompetitions3Mapper gRcompetitions3Mapper;

    /**
     * 查询个人赛事副本
     * 
     * @param id 个人赛事副本主键
     * @return 个人赛事副本
     */
    @Override
    public GRcompetitions3 selectGRcompetitions3ById(Long id)
    {
        return gRcompetitions3Mapper.selectGRcompetitions3ById(id);
    }

    /**
     * 查询个人赛事副本列表
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 个人赛事副本
     */
    @Override
    public List<GRcompetitions3> selectGRcompetitions3List(GRcompetitions3 gRcompetitions3)
    {
        return gRcompetitions3Mapper.selectGRcompetitions3List(gRcompetitions3);
    }

    /**
     * 新增个人赛事副本
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 结果
     */
    @Override
    public int insertGRcompetitions3(GRcompetitions3 gRcompetitions3)
    {
        return gRcompetitions3Mapper.insertGRcompetitions3(gRcompetitions3);
    }

    /**
     * 修改个人赛事副本
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 结果
     */
    @Override
    public int updateGRcompetitions3(GRcompetitions3 gRcompetitions3)
    {
        return gRcompetitions3Mapper.updateGRcompetitions3(gRcompetitions3);
    }

    /**
     * 批量删除个人赛事副本
     * 
     * @param ids 需要删除的个人赛事副本主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions3ByIds(Long[] ids)
    {
        return gRcompetitions3Mapper.deleteGRcompetitions3ByIds(ids);
    }

    /**
     * 删除个人赛事副本信息
     * 
     * @param id 个人赛事副本主键
     * @return 结果
     */
    @Override
    public int deleteGRcompetitions3ById(Long id)
    {
        return gRcompetitions3Mapper.deleteGRcompetitions3ById(id);
    }
}
