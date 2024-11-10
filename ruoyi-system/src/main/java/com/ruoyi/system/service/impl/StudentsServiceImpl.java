package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentsMapper;
import com.ruoyi.system.domain.Students;
import com.ruoyi.system.service.IStudentsService;

/**
 * 学生列表Service业务层处理
 * 
 * @author 毛毛
 * @date 2024-11-10
 */
@Service
public class StudentsServiceImpl implements IStudentsService 
{
    @Autowired
    private StudentsMapper studentsMapper;

    /**
     * 查询学生列表
     * 
     * @param studentId 学生列表主键
     * @return 学生列表
     */
    @Override
    public Students selectStudentsByStudentId(String studentId)
    {
        return studentsMapper.selectStudentsByStudentId(studentId);
    }

    /**
     * 查询学生列表列表
     * 
     * @param students 学生列表
     * @return 学生列表
     */
    @Override
    public List<Students> selectStudentsList(Students students)
    {
        return studentsMapper.selectStudentsList(students);
    }

    /**
     * 新增学生列表
     * 
     * @param students 学生列表
     * @return 结果
     */
    @Override
    public int insertStudents(Students students)
    {
        return studentsMapper.insertStudents(students);
    }

    /**
     * 修改学生列表
     * 
     * @param students 学生列表
     * @return 结果
     */
    @Override
    public int updateStudents(Students students)
    {
        return studentsMapper.updateStudents(students);
    }

    /**
     * 批量删除学生列表
     * 
     * @param studentIds 需要删除的学生列表主键
     * @return 结果
     */
    @Override
    public int deleteStudentsByStudentIds(String[] studentIds)
    {
        return studentsMapper.deleteStudentsByStudentIds(studentIds);
    }

    /**
     * 删除学生列表信息
     * 
     * @param studentId 学生列表主键
     * @return 结果
     */
    @Override
    public int deleteStudentsByStudentId(String studentId)
    {
        return studentsMapper.deleteStudentsByStudentId(studentId);
    }
}
