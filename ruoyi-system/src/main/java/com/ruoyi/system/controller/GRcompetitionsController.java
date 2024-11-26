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
import com.ruoyi.system.domain.GRcompetitions;
import com.ruoyi.system.service.IGRcompetitionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事报名Controller
 * 
 * @author maomao
 * @date 2024-11-26
 */
@RestController
@RequestMapping("/system/GRcompetitions")
public class GRcompetitionsController extends BaseController
{
    @Autowired
    private IGRcompetitionsService gRcompetitionsService;

    /**
     * 查询赛事报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:list')")
    @GetMapping("/list")
    public TableDataInfo list(GRcompetitions gRcompetitions)
    {
        startPage();
        List<GRcompetitions> list = gRcompetitionsService.selectGRcompetitionsList(gRcompetitions);
        return getDataTable(list);
    }

    /**
     * 导出赛事报名列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:export')")
    @Log(title = "赛事报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GRcompetitions gRcompetitions)
    {
        List<GRcompetitions> list = gRcompetitionsService.selectGRcompetitionsList(gRcompetitions);
        ExcelUtil<GRcompetitions> util = new ExcelUtil<GRcompetitions>(GRcompetitions.class);
        util.exportExcel(response, list, "赛事报名数据");
    }

    /**
     * 获取赛事报名详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gRcompetitionsService.selectGRcompetitionsById(id));
    }

    /**
     * 新增赛事报名
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:add')")
    @Log(title = "赛事报名", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GRcompetitions gRcompetitions)
    {
        return toAjax(gRcompetitionsService.insertGRcompetitions(gRcompetitions));
    }

    /**
     * 修改赛事报名
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:edit')")
    @Log(title = "赛事报名", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GRcompetitions gRcompetitions)
    {
        return toAjax(gRcompetitionsService.updateGRcompetitions(gRcompetitions));
    }

    /**
     * 删除赛事报名
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions:remove')")
    @Log(title = "赛事报名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gRcompetitionsService.deleteGRcompetitionsByIds(ids));
    }
}
