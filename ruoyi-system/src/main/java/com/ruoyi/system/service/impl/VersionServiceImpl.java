package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VersionMapper;
import com.ruoyi.system.domain.Version;
import com.ruoyi.system.service.IVersionService;

/**
 * 可视化界面Service业务层处理
 * 
 * @author maomao
 * @date 2024-12-20
 */
@Service
public class VersionServiceImpl implements IVersionService 
{
    @Autowired
    private VersionMapper versionMapper;

    /**
     * 查询可视化界面
     * 
     * @param id 可视化界面主键
     * @return 可视化界面
     */
    @Override
    public Version selectVersionById(Long id)
    {
        return versionMapper.selectVersionById(id);
    }

    /**
     * 查询可视化界面列表
     * 
     * @param version 可视化界面
     * @return 可视化界面
     */
    @Override
    public List<Version> selectVersionList(Version version)
    {
        return versionMapper.selectVersionList(version);
    }

    /**
     * 新增可视化界面
     * 
     * @param version 可视化界面
     * @return 结果
     */
    @Override
    public int insertVersion(Version version)
    {
        return versionMapper.insertVersion(version);
    }

    /**
     * 修改可视化界面
     * 
     * @param version 可视化界面
     * @return 结果
     */
    @Override
    public int updateVersion(Version version)
    {
        return versionMapper.updateVersion(version);
    }

    /**
     * 批量删除可视化界面
     * 
     * @param ids 需要删除的可视化界面主键
     * @return 结果
     */
    @Override
    public int deleteVersionByIds(Long[] ids)
    {
        return versionMapper.deleteVersionByIds(ids);
    }

    /**
     * 删除可视化界面信息
     * 
     * @param id 可视化界面主键
     * @return 结果
     */
    @Override
    public int deleteVersionById(Long id)
    {
        return versionMapper.deleteVersionById(id);
    }
}
