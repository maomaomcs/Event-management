package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GRcompetitions2;

/**
 * 个人赛事副本Mapper接口
 * 
 * @author maomao
 * @date 2024-11-29
 */
public interface GRcompetitions2Mapper 
{
    /**
     * 查询个人赛事副本
     * 
     * @param id 个人赛事副本主键
     * @return 个人赛事副本
     */
    public GRcompetitions2 selectGRcompetitions2ById(Long id);

    /**
     * 查询个人赛事副本列表
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 个人赛事副本集合
     */
    public List<GRcompetitions2> selectGRcompetitions2List(GRcompetitions2 gRcompetitions2);

    /**
     * 新增个人赛事副本
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 结果
     */
    public int insertGRcompetitions2(GRcompetitions2 gRcompetitions2);

    /**
     * 修改个人赛事副本
     * 
     * @param gRcompetitions2 个人赛事副本
     * @return 结果
     */
    public int updateGRcompetitions2(GRcompetitions2 gRcompetitions2);

    /**
     * 删除个人赛事副本
     * 
     * @param id 个人赛事副本主键
     * @return 结果
     */
    public int deleteGRcompetitions2ById(Long id);

    /**
     * 批量删除个人赛事副本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGRcompetitions2ByIds(Long[] ids);
}
