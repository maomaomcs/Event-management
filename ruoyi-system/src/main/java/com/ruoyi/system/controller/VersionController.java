package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Version;
import com.ruoyi.system.service.IVersionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 可视化界面Controller
 * 
 * @author maomao
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/version")
public class VersionController extends BaseController
{
    @Autowired
    private IVersionService versionService;

    /**
     * 查询可视化界面列表
     */
    @PreAuthorize("@ss.hasPermi('system:version:list')")
    @GetMapping("/list")
    public TableDataInfo list(Version version)
    {
        startPage();
        List<Version> list = versionService.selectVersionList(version);
        return getDataTable(list);
    }

    /**
     * 导出可视化界面列表
     */
    @PreAuthorize("@ss.hasPermi('system:version:export')")
    @Log(title = "可视化界面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Version version)
    {
        List<Version> list = versionService.selectVersionList(version);
        ExcelUtil<Version> util = new ExcelUtil<Version>(Version.class);
        util.exportExcel(response, list, "可视化界面数据");
    }

    /**
     * 获取可视化界面详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:version:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(versionService.selectVersionById(id));
    }

    /**
     * 新增可视化界面
     */
    @PreAuthorize("@ss.hasPermi('system:version:add')")
    @Log(title = "可视化界面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Version version)
    {
        return toAjax(versionService.insertVersion(version));
    }

    /**
     * 修改可视化界面
     */
    @PreAuthorize("@ss.hasPermi('system:version:edit')")
    @Log(title = "可视化界面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Version version)
    {
        return toAjax(versionService.updateVersion(version));
    }

    /**
     * 删除可视化界面
     */
    @PreAuthorize("@ss.hasPermi('system:version:remove')")
    @Log(title = "可视化界面", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(versionService.deleteVersionByIds(ids));
    }
}
