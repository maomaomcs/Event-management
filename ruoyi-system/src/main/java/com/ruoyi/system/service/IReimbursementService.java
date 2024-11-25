package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Reimbursement;

/**
 * 报销审核Service接口
 * 
 * @author maomao
 * @date 2024-11-10
 */
public interface IReimbursementService 
{
    /**
     * 查询报销审核
     * 
     * @param id 报销审核主键
     * @return 报销审核
     */
    public Reimbursement selectReimbursementById(Long id);

    /**
     * 查询报销审核列表
     * 
     * @param reimbursement 报销审核
     * @return 报销审核集合
     */
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement);

    /**
     * 新增报销审核
     * 
     * @param reimbursement 报销审核
     * @return 结果
     */
    public int insertReimbursement(Reimbursement reimbursement);

    /**
     * 修改报销审核
     * 
     * @param reimbursement 报销审核
     * @return 结果
     */
    public int updateReimbursement(Reimbursement reimbursement);

    /**
     * 批量删除报销审核
     * 
     * @param ids 需要删除的报销审核主键集合
     * @return 结果
     */
    public int deleteReimbursementByIds(Long[] ids);

    /**
     * 删除报销审核信息
     * 
     * @param id 报销审核主键
     * @return 结果
     */
    public int deleteReimbursementById(Long id);
}
