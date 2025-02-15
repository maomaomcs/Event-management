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
import com.ruoyi.system.domain.TDcompetitions;
import com.ruoyi.system.service.ITDcompetitionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队赛事登记Controller
 * 
 * @author maomao
 * @date 2024-12-12
 */
@RestController
@RequestMapping("/system/TDcompetitions")
public class TDcompetitionsController extends BaseController
{
    @Autowired
    private ITDcompetitionsService tDcompetitionsService;

    /**
     * 查询团队赛事登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDcompetitions tDcompetitions)
    {
        startPage();
        List<TDcompetitions> list = tDcompetitionsService.selectTDcompetitionsList(tDcompetitions);
        return getDataTable(list);
    }

    /**
     * 导出团队赛事登记列表
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:export')")
    @Log(title = "团队赛事登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDcompetitions tDcompetitions)
    {
        List<TDcompetitions> list = tDcompetitionsService.selectTDcompetitionsList(tDcompetitions);
        ExcelUtil<TDcompetitions> util = new ExcelUtil<TDcompetitions>(TDcompetitions.class);
        util.exportExcel(response, list, "团队赛事登记数据");
    }

    /**
     * 获取团队赛事登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tDcompetitionsService.selectTDcompetitionsById(id));
    }

    /**
     * 新增团队赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:add')")
    @Log(title = "团队赛事登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDcompetitions tDcompetitions)
    {
        return toAjax(tDcompetitionsService.insertTDcompetitions(tDcompetitions));
    }

    /**
     * 修改团队赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:edit')")
    @Log(title = "团队赛事登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDcompetitions tDcompetitions)
    {
        return toAjax(tDcompetitionsService.updateTDcompetitions(tDcompetitions));
    }

    /**
     * 删除团队赛事登记
     */
    @PreAuthorize("@ss.hasPermi('system:TDcompetitions:remove')")
    @Log(title = "团队赛事登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tDcompetitionsService.deleteTDcompetitionsByIds(ids));
    }
}
