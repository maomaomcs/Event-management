package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Version;

/**
 * 可视化界面Mapper接口
 * 
 * @author maomao
 * @date 2024-12-20
 */
public interface VersionMapper 
{
    /**
     * 查询可视化界面
     * 
     * @param id 可视化界面主键
     * @return 可视化界面
     */
    public Version selectVersionById(Long id);

    /**
     * 查询可视化界面列表
     * 
     * @param version 可视化界面
     * @return 可视化界面集合
     */
    public List<Version> selectVersionList(Version version);

    /**
     * 新增可视化界面
     * 
     * @param version 可视化界面
     * @return 结果
     */
    public int insertVersion(Version version);

    /**
     * 修改可视化界面
     * 
     * @param version 可视化界面
     * @return 结果
     */
    public int updateVersion(Version version);

    /**
     * 删除可视化界面
     * 
     * @param id 可视化界面主键
     * @return 结果
     */
    public int deleteVersionById(Long id);

    /**
     * 批量删除可视化界面
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVersionByIds(Long[] ids);
}
