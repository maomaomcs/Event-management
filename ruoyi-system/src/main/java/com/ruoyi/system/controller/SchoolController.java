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
import com.ruoyi.system.domain.School;
import com.ruoyi.system.service.ISchoolService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报名登记Controller
 *
 * @author maomao
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/system/school")
public class SchoolController extends BaseController
{
    @Autowired
    private ISchoolService schoolService;

    /**
     * 查询报名登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:list')")
    @GetMapping("/list")
    public TableDataInfo list(School school)
    {
        startPage();
        List<School> list = schoolService.selectSchoolList(school);
        return getDataTable(list);
    }

    /**
     * 导出报名登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:export')")
    @Log(title = "报名登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, School school)
    {
        List<School> list = schoolService.selectSchoolList(school);
        ExcelUtil<School> util = new ExcelUtil<School>(School.class);
        util.exportExcel(response, list, "报名登记数据");
    }

    /**
     * 获取报名登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(schoolService.selectSchoolById(id));
    }

    /**
     * 新增报名登记
     */
    @PreAuthorize("@ss.hasPermi('system:school:add')")
    @Log(title = "报名登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody School school)
    {
        return toAjax(schoolService.insertSchool(school));
    }

    /**
     * 修改报名登记
     */
    @PreAuthorize("@ss.hasPermi('system:school:edit')")
    @Log(title = "报名登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody School school)
    {
        return toAjax(schoolService.updateSchool(school));
    }

    /**
     * 删除报名登记
     */
    @PreAuthorize("@ss.hasPermi('system:school:remove')")
    @Log(title = "报名登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolService.deleteSchoolByIds(ids));
    }
}
