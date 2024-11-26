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
import com.ruoyi.system.domain.Competitions2;
import com.ruoyi.system.service.ICompetitions2Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事登记Controller
 * 
 * @author maomao
 * @date 2024-11-26
 */
@RestController
@RequestMapping("/system/competitions2")
public class Competitions2Controller extends BaseController
{
    @Autowired
    private ICompetitions2Service competitions2Service;

    /**
     * 查询赛事登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:list')")
    @GetMapping("/list")
    public TableDataInfo list(Competitions2 competitions2)
    {
        startPage();
        List<Competitions2> list = competitions2Service.selectCompetitions2List(competitions2);
        return getDataTable(list);
    }

    /**
     * 导出赛事登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:export')")
    @Log(title = "赛事登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competitions2 competitions2)
    {
        List<Competitions2> list = competitions2Service.selectCompetitions2List(competitions2);
        ExcelUtil<Competitions2> util = new ExcelUtil<Competitions2>(Competitions2.class);
        util.exportExcel(response, list, "赛事登记数据");
    }

    /**
     * 获取赛事登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitions2Service.selectCompetitions2ById(id));
    }

    /**
     * 新增赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:add')")
    @Log(title = "赛事登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competitions2 competitions2)
    {
        return toAjax(competitions2Service.insertCompetitions2(competitions2));
    }

    /**
     * 修改赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:edit')")
    @Log(title = "赛事登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competitions2 competitions2)
    {
        return toAjax(competitions2Service.updateCompetitions2(competitions2));
    }

    /**
     * 删除赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:competitions2:remove')")
    @Log(title = "赛事登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitions2Service.deleteCompetitions2ByIds(ids));
    }
}
