package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GRcompetitions1;

/**
 * 个人赛事11Service接口
 * 
 * @author maomao
 * @date 2024-11-29
 */
public interface IGRcompetitions1Service 
{
    /**
     * 查询个人赛事11
     * 
     * @param id 个人赛事11主键
     * @return 个人赛事11
     */
    public GRcompetitions1 selectGRcompetitions1ById(Long id);

    /**
     * 查询个人赛事11列表
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 个人赛事11集合
     */
    public List<GRcompetitions1> selectGRcompetitions1List(GRcompetitions1 gRcompetitions1);

    /**
     * 新增个人赛事11
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 结果
     */
    public int insertGRcompetitions1(GRcompetitions1 gRcompetitions1);

    /**
     * 修改个人赛事11
     * 
     * @param gRcompetitions1 个人赛事11
     * @return 结果
     */
    public int updateGRcompetitions1(GRcompetitions1 gRcompetitions1);

    /**
     * 批量删除个人赛事11
     * 
     * @param ids 需要删除的个人赛事11主键集合
     * @return 结果
     */
    public int deleteGRcompetitions1ByIds(Long[] ids);

    /**
     * 删除个人赛事11信息
     * 
     * @param id 个人赛事11主键
     * @return 结果
     */
    public int deleteGRcompetitions1ById(Long id);
}
