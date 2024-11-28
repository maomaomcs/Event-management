package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UsersMapper;
import com.ruoyi.system.domain.Users;
import com.ruoyi.system.service.IUsersService;

/**
 * 人员列表Service业务层处理
 * 
 * @author maomao
 * @date 2024-11-28
 */
@Service
public class UsersServiceImpl implements IUsersService 
{
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询人员列表
     * 
     * @param id 人员列表主键
     * @return 人员列表
     */
    @Override
    public Users selectUsersById(Long id)
    {
        return usersMapper.selectUsersById(id);
    }

    /**
     * 查询人员列表列表
     * 
     * @param users 人员列表
     * @return 人员列表
     */
    @Override
    public List<Users> selectUsersList(Users users)
    {
        return usersMapper.selectUsersList(users);
    }

    /**
     * 新增人员列表
     * 
     * @param users 人员列表
     * @return 结果
     */
    @Override
    public int insertUsers(Users users) {
        registerUser(users);  // Call register method
        return usersMapper.insertUsers(users);  // Insert user into the database
    }

    private void registerUser(Users users) {
        String username = users.getStudentId();  // Use student_id as the username
        String password = users.getPassword();  // Use the actual password

        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);  // Set the username to student_id

        // Validate the username and password
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException("学号不能为空");  // Error message for empty student_id
        }
        else if (StringUtils.isEmpty(password)) {
            throw new ServiceException("密码不能为空");  // Error message for empty password
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username))
                || "匿名用户".equals(username)) {
            throw new ServiceException("保存用户'" + username + "'失败，注册账号已存在");
        }
        else {
            sysUser.setPhonenumber(users.getPhoneNumber());
            sysUser.setNickName(users.getName());  // Optionally set the nickname
            sysUser.setPassword(SecurityUtils.encryptPassword(password));  // Encrypt the password before storing
            userService.registerUser(sysUser);  // Register the user
        }
    }
    /**
     * 修改人员列表
     * 
     * @param users 人员列表
     * @return 结果
     */
    @Override
    public int updateUsers(Users users)
    {
        return usersMapper.updateUsers(users);
    }

    /**
     * 批量删除人员列表
     * 
     * @param ids 需要删除的人员列表主键
     * @return 结果
     */
    @Override
    public int deleteUsersByIds(Long[] ids)
    {
        return usersMapper.deleteUsersByIds(ids);
    }

    /**
     * 删除人员列表信息
     * 
     * @param id 人员列表主键
     * @return 结果
     */
    @Override
    public int deleteUsersById(Long id)
    {
        return usersMapper.deleteUsersById(id);
    }
}
