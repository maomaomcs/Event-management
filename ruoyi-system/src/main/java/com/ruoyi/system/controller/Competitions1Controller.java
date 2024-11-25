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
import com.ruoyi.system.domain.Competitions1;
import com.ruoyi.system.service.ICompetitions1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事列表1Controller
 * 
 * @author maomao
 * @date 2024-11-15
 */
@RestController
@RequestMapping("/system/competitions1")
public class Competitions1Controller extends BaseController
{
    @Autowired
    private ICompetitions1Service competitions1Service;

    /**
     * 查询赛事列表1列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:list')")
    @GetMapping("/list")
    public TableDataInfo list(Competitions1 competitions1)
    {
        startPage();
        List<Competitions1> list = competitions1Service.selectCompetitions1List(competitions1);
        return getDataTable(list);
    }

    /**
     * 导出赛事列表1列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:export')")
    @Log(title = "赛事列表1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competitions1 competitions1)
    {
        List<Competitions1> list = competitions1Service.selectCompetitions1List(competitions1);
        ExcelUtil<Competitions1> util = new ExcelUtil<Competitions1>(Competitions1.class);
        util.exportExcel(response, list, "赛事列表1数据");
    }

    /**
     * 获取赛事列表1详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitions1Service.selectCompetitions1ById(id));
    }

    /**
     * 新增赛事列表1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:add')")
    @Log(title = "赛事列表1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competitions1 competitions1)
    {
        return toAjax(competitions1Service.insertCompetitions1(competitions1));
    }

    /**
     * 修改赛事列表1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:edit')")
    @Log(title = "赛事列表1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competitions1 competitions1)
    {
        return toAjax(competitions1Service.updateCompetitions1(competitions1));
    }

    /**
     * 删除赛事列表1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions1:remove')")
    @Log(title = "赛事列表1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitions1Service.deleteCompetitions1ByIds(ids));
    }
}
