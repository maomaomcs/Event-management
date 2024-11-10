package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Students;

/**
 * 学生列表Service接口
 * 
 * @author 毛毛
 * @date 2024-11-10
 */
public interface IStudentsService 
{
    /**
     * 查询学生列表
     * 
     * @param studentId 学生列表主键
     * @return 学生列表
     */
    public Students selectStudentsByStudentId(String studentId);

    /**
     * 查询学生列表列表
     * 
     * @param students 学生列表
     * @return 学生列表集合
     */
    public List<Students> selectStudentsList(Students students);

    /**
     * 新增学生列表
     * 
     * @param students 学生列表
     * @return 结果
     */
    public int insertStudents(Students students);

    /**
     * 修改学生列表
     * 
     * @param students 学生列表
     * @return 结果
     */
    public int updateStudents(Students students);

    /**
     * 批量删除学生列表
     * 
     * @param studentIds 需要删除的学生列表主键集合
     * @return 结果
     */
    public int deleteStudentsByStudentIds(String[] studentIds);

    /**
     * 删除学生列表信息
     * 
     * @param studentId 学生列表主键
     * @return 结果
     */
    public int deleteStudentsByStudentId(String studentId);
}
