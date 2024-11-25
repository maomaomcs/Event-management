package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReimbursementMapper;
import com.ruoyi.system.domain.Reimbursement;
import com.ruoyi.system.service.IReimbursementService;

/**
 * 报销审核Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-10
 */
@Service
public class ReimbursementServiceImpl implements IReimbursementService 
{
    @Autowired
    private ReimbursementMapper reimbursementMapper;

    /**
     * 查询报销审核
     * 
     * @param id 报销审核主键
     * @return 报销审核
     */
    @Override
    public Reimbursement selectReimbursementById(Long id)
    {
        return reimbursementMapper.selectReimbursementById(id);
    }

    /**
     * 查询报销审核列表
     * 
     * @param reimbursement 报销审核
     * @return 报销审核
     */
    @Override
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement)
    {
        return reimbursementMapper.selectReimbursementList(reimbursement);
    }

    /**
     * 新增报销审核
     * 
     * @param reimbursement 报销审核
     * @return 结果
     */
    @Override
    public int insertReimbursement(Reimbursement reimbursement)
    {
        return reimbursementMapper.insertReimbursement(reimbursement);
    }

    /**
     * 修改报销审核
     * 
     * @param reimbursement 报销审核
     * @return 结果
     */
    @Override
    public int updateReimbursement(Reimbursement reimbursement)
    {
        return reimbursementMapper.updateReimbursement(reimbursement);
    }

    /**
     * 批量删除报销审核
     * 
     * @param ids 需要删除的报销审核主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementByIds(Long[] ids)
    {
        return reimbursementMapper.deleteReimbursementByIds(ids);
    }

    /**
     * 删除报销审核信息
     * 
     * @param id 报销审核主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementById(Long id)
    {
        return reimbursementMapper.deleteReimbursementById(id);
    }
}
