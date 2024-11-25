package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MyRegistration;

/**
 * 个人赛事Service接口
 * 
 * @author maomao
 * @date 2024-11-11
 */
public interface IMyRegistrationService 
{
    /**
     * 查询个人赛事
     * 
     * @param id 个人赛事主键
     * @return 个人赛事
     */
    public MyRegistration selectMyRegistrationById(Long id);

    /**
     * 查询个人赛事列表
     * 
     * @param myRegistration 个人赛事
     * @return 个人赛事集合
     */
    public List<MyRegistration> selectMyRegistrationList(MyRegistration myRegistration);

    /**
     * 新增个人赛事
     * 
     * @param myRegistration 个人赛事
     * @return 结果
     */
    public int insertMyRegistration(MyRegistration myRegistration);

    /**
     * 修改个人赛事
     * 
     * @param myRegistration 个人赛事
     * @return 结果
     */
    public int updateMyRegistration(MyRegistration myRegistration);

    /**
     * 批量删除个人赛事
     * 
     * @param ids 需要删除的个人赛事主键集合
     * @return 结果
     */
    public int deleteMyRegistrationByIds(Long[] ids);

    /**
     * 删除个人赛事信息
     * 
     * @param id 个人赛事主键
     * @return 结果
     */
    public int deleteMyRegistrationById(Long id);
}
