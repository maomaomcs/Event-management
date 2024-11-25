package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolMapper;
import com.ruoyi.system.domain.School;
import com.ruoyi.system.service.ISchoolService;

/**
 * 报名登记Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-11
 */
@Service
public class SchoolServiceImpl implements ISchoolService 
{
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 查询报名登记
     * 
     * @param id 报名登记主键
     * @return 报名登记
     */
    @Override
    public School selectSchoolById(Long id)
    {
        return schoolMapper.selectSchoolById(id);
    }

    /**
     * 查询报名登记列表
     * 
     * @param school 报名登记
     * @return 报名登记
     */
    @Override
    public List<School> selectSchoolList(School school)
    {
        return schoolMapper.selectSchoolList(school);
    }

    /**
     * 新增报名登记
     * 
     * @param school 报名登记
     * @return 结果
     */
    @Override
    public int insertSchool(School school)
    {
        return schoolMapper.insertSchool(school);
    }

    /**
     * 修改报名登记
     * 
     * @param school 报名登记
     * @return 结果
     */
    @Override
    public int updateSchool(School school)
    {
        return schoolMapper.updateSchool(school);
    }

    /**
     * 批量删除报名登记
     * 
     * @param ids 需要删除的报名登记主键
     * @return 结果
     */
    @Override
    public int deleteSchoolByIds(Long[] ids)
    {
        return schoolMapper.deleteSchoolByIds(ids);
    }

    /**
     * 删除报名登记信息
     * 
     * @param id 报名登记主键
     * @return 结果
     */
    @Override
    public int deleteSchoolById(Long id)
    {
        return schoolMapper.deleteSchoolById(id);
    }
}
