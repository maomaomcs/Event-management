package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MyRegistrationMapper;
import com.ruoyi.system.domain.MyRegistration;
import com.ruoyi.system.service.IMyRegistrationService;

/**
 * 个人赛事Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-11
 */
@Service
public class MyRegistrationServiceImpl implements IMyRegistrationService 
{
    @Autowired
    private MyRegistrationMapper myRegistrationMapper;

    /**
     * 查询个人赛事
     * 
     * @param id 个人赛事主键
     * @return 个人赛事
     */
    @Override
    public MyRegistration selectMyRegistrationById(Long id)
    {
        return myRegistrationMapper.selectMyRegistrationById(id);
    }

    /**
     * 查询个人赛事列表
     * 
     * @param myRegistration 个人赛事
     * @return 个人赛事
     */
    @Override
    public List<MyRegistration> selectMyRegistrationList(MyRegistration myRegistration)
    {
        return myRegistrationMapper.selectMyRegistrationList(myRegistration);
    }

    /**
     * 新增个人赛事
     * 
     * @param myRegistration 个人赛事
     * @return 结果
     */
    @Override
    public int insertMyRegistration(MyRegistration myRegistration)
    {
        return myRegistrationMapper.insertMyRegistration(myRegistration);
    }

    /**
     * 修改个人赛事
     * 
     * @param myRegistration 个人赛事
     * @return 结果
     */
    @Override
    public int updateMyRegistration(MyRegistration myRegistration)
    {
        return myRegistrationMapper.updateMyRegistration(myRegistration);
    }

    /**
     * 批量删除个人赛事
     * 
     * @param ids 需要删除的个人赛事主键
     * @return 结果
     */
    @Override
    public int deleteMyRegistrationByIds(Long[] ids)
    {
        return myRegistrationMapper.deleteMyRegistrationByIds(ids);
    }

    /**
     * 删除个人赛事信息
     * 
     * @param id 个人赛事主键
     * @return 结果
     */
    @Override
    public int deleteMyRegistrationById(Long id)
    {
        return myRegistrationMapper.deleteMyRegistrationById(id);
    }
}
