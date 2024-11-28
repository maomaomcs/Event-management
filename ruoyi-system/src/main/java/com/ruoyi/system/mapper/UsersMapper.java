package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Users;

/**
 * 人员列表Mapper接口
 * 
 * @author maomao
 * @date 2024-11-28
 */
public interface UsersMapper 
{
    /**
     * 查询人员列表
     * 
     * @param id 人员列表主键
     * @return 人员列表
     */
    public Users selectUsersById(Long id);

    /**
     * 查询人员列表列表
     * 
     * @param users 人员列表
     * @return 人员列表集合
     */
    public List<Users> selectUsersList(Users users);

    /**
     * 新增人员列表
     * 
     * @param users 人员列表
     * @return 结果
     */
    public int insertUsers(Users users);

    /**
     * 修改人员列表
     * 
     * @param users 人员列表
     * @return 结果
     */
    public int updateUsers(Users users);

    /**
     * 删除人员列表
     * 
     * @param id 人员列表主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /**
     * 批量删除人员列表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);
}
