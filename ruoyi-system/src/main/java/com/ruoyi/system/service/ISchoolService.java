package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.School;

/**
 * 报名登记Service接口
 *
 * @author maomao
 * @date 2024-11-11
 */
public interface ISchoolService
{
    /**
     * 查询报名登记
     *
     * @param id 报名登记主键
     * @return 报名登记
     */
    public School selectSchoolById(Long id);

    /**
     * 查询报名登记列表
     *
     * @param school 报名登记
     * @return 报名登记集合
     */
    public List<School> selectSchoolList(School school);

    /**
     * 新增报名登记
     *
     * @param school 报名登记
     * @return 结果
     */
    public int insertSchool(School school);

    /**
     * 修改报名登记
     *
     * @param school 报名登记
     * @return 结果
     */
    public int updateSchool(School school);

    /**
     * 批量删除报名登记
     *
     * @param ids 需要删除的报名登记主键集合
     * @return 结果
     */
    public int deleteSchoolByIds(Long[] ids);

    /**
     * 删除报名登记信息
     *
     * @param id 报名登记主键
     * @return 结果
     */
    public int deleteSchoolById(Long id);
}
