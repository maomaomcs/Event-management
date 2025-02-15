package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CYtdcompetionMapper;
import com.ruoyi.system.domain.CYtdcompetion;
import com.ruoyi.system.service.ICYtdcompetionService;

/**
 * 成员团队赛事Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-20
 */
@Service
public class CYtdcompetionServiceImpl implements ICYtdcompetionService 
{
    @Autowired
    private CYtdcompetionMapper cYtdcompetionMapper;

    /**
     * 查询成员团队赛事
     * 
     * @param competitionId 成员团队赛事主键
     * @return 成员团队赛事
     */
    @Override
    public CYtdcompetion selectCYtdcompetionByCompetitionId(Long competitionId)
    {
        return cYtdcompetionMapper.selectCYtdcompetionByCompetitionId(competitionId);
    }

    /**
     * 查询成员团队赛事列表
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 成员团队赛事
     */
    @Override
    public List<CYtdcompetion> selectCYtdcompetionList(CYtdcompetion cYtdcompetion)
    {
        return cYtdcompetionMapper.selectCYtdcompetionList(cYtdcompetion);
    }

    /**
     * 新增成员团队赛事
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 结果
     */
    @Override
    public int insertCYtdcompetion(CYtdcompetion cYtdcompetion)
    {
        return cYtdcompetionMapper.insertCYtdcompetion(cYtdcompetion);
    }

    /**
     * 修改成员团队赛事
     * 
     * @param cYtdcompetion 成员团队赛事
     * @return 结果
     */
    @Override
    public int updateCYtdcompetion(CYtdcompetion cYtdcompetion)
    {
        return cYtdcompetionMapper.updateCYtdcompetion(cYtdcompetion);
    }

    /**
     * 批量删除成员团队赛事
     * 
     * @param competitionIds 需要删除的成员团队赛事主键
     * @return 结果
     */
    @Override
    public int deleteCYtdcompetionByCompetitionIds(Long[] competitionIds)
    {
        return cYtdcompetionMapper.deleteCYtdcompetionByCompetitionIds(competitionIds);
    }

    /**
     * 删除成员团队赛事信息
     * 
     * @param competitionId 成员团队赛事主键
     * @return 结果
     */
    @Override
    public int deleteCYtdcompetionByCompetitionId(Long competitionId)
    {
        return cYtdcompetionMapper.deleteCYtdcompetionByCompetitionId(competitionId);
    }
}
