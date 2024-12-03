package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GRcompetitions3;

/**
 * 个人赛事副本Service接口
 * 
 * @author maomao
 * @date 2024-12-01
 */
public interface IGRcompetitions3Service 
{
    /**
     * 查询个人赛事副本
     * 
     * @param id 个人赛事副本主键
     * @return 个人赛事副本
     */
    public GRcompetitions3 selectGRcompetitions3ById(Long id);

    /**
     * 查询个人赛事副本列表
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 个人赛事副本集合
     */
    public List<GRcompetitions3> selectGRcompetitions3List(GRcompetitions3 gRcompetitions3);

    /**
     * 新增个人赛事副本
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 结果
     */
    public int insertGRcompetitions3(GRcompetitions3 gRcompetitions3);

    /**
     * 修改个人赛事副本
     * 
     * @param gRcompetitions3 个人赛事副本
     * @return 结果
     */
    public int updateGRcompetitions3(GRcompetitions3 gRcompetitions3);

    /**
     * 批量删除个人赛事副本
     * 
     * @param ids 需要删除的个人赛事副本主键集合
     * @return 结果
     */
    public int deleteGRcompetitions3ByIds(Long[] ids);

    /**
     * 删除个人赛事副本信息
     * 
     * @param id 个人赛事副本主键
     * @return 结果
     */
    public int deleteGRcompetitions3ById(Long id);
}