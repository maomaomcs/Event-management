package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Students;

/**
 * 学生列表Mapper接口
 * 
 * @author 毛毛
 * @date 2024-11-10
 */
public interface StudentsMapper 
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
     * 删除学生列表
     * 
     * @param studentId 学生列表主键
     * @return 结果
     */
    public int deleteStudentsByStudentId(String studentId);

    /**
     * 批量删除学生列表
     * 
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentsByStudentIds(String[] studentIds);
}
