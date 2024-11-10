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
import com.ruoyi.system.domain.Competitions;
import com.ruoyi.system.service.ICompetitionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事记录1Controller
 * 
 * @author maomao
 * @date 2024-11-10
 */
@RestController
@RequestMapping("/system/competitions")
public class CompetitionsController extends BaseController
{
    @Autowired
    private ICompetitionsService competitionsService;

    /**
     * 查询赛事记录1列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:list')")
    @GetMapping("/list")
    public TableDataInfo list(Competitions competitions)
    {
        startPage();
        List<Competitions> list = competitionsService.selectCompetitionsList(competitions);
        return getDataTable(list);
    }

    /**
     * 导出赛事记录1列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:export')")
    @Log(title = "赛事记录1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competitions competitions)
    {
        List<Competitions> list = competitionsService.selectCompetitionsList(competitions);
        ExcelUtil<Competitions> util = new ExcelUtil<Competitions>(Competitions.class);
        util.exportExcel(response, list, "赛事记录1数据");
    }

    /**
     * 获取赛事记录1详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitionsService.selectCompetitionsById(id));
    }

    /**
     * 新增赛事记录1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:add')")
    @Log(title = "赛事记录1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competitions competitions)
    {
        return toAjax(competitionsService.insertCompetitions(competitions));
    }

    /**
     * 修改赛事记录1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:edit')")
    @Log(title = "赛事记录1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competitions competitions)
    {
        return toAjax(competitionsService.updateCompetitions(competitions));
    }

    /**
     * 删除赛事记录1
     */
    @PreAuthorize("@ss.hasPermi('system:competitions:remove')")
    @Log(title = "赛事记录1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitionsService.deleteCompetitionsByIds(ids));
    }
}
