package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CYtdcompetion;

/**
 * 成员团队赛事Service接口
 * 
 * @author maomao
 * @date 2024-12-20
 */
public interface ICYtdcompetionService 
{
    /**
     * 查询成员团队赛事
     * 
     * @param competitionId 成员团队赛事主键
     * @return 成员团队赛事
     */
    public CYtdcompetion selectCYtdcompetionByCompetitionId(Long competitionId);

    /**
     * 查询成员团队赛事列表
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 成员团队赛事集合
     */
    public List<CYtdcompetion> selectCYtdcompetionList(CYtdcompetion cYtdcompetion);

    /**
     * 新增成员团队赛事
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 结果
     */
    public int insertCYtdcompetion(CYtdcompetion cYtdcompetion);

    /**
     * 修改成员团队赛事
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 结果
     */
    public int updateCYtdcompetion(CYtdcompetion cYtdcompetion);

    /**
     * 批量删除成员团队赛事
     * 
     * @param competitionIds 需要删除的成员团队赛事主键集合
     * @return 结果
     */
    public int deleteCYtdcompetionByCompetitionIds(Long[] competitionIds);

    /**
     * 删除成员团队赛事信息
     * 
     * @param competitionId 成员团队赛事主键
     * @return 结果
     */
    public int deleteCYtdcompetionByCompetitionId(Long competitionId);
}
