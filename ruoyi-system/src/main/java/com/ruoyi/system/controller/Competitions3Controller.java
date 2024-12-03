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
import com.ruoyi.system.domain.Competitions3;
import com.ruoyi.system.service.ICompetitions3Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事登记副本Controller
 * 
 * @author maomao
 * @date 2024-11-29
 */
@RestController
@RequestMapping("/system/competitions3")
public class Competitions3Controller extends BaseController
{
    @Autowired
    private ICompetitions3Service competitions3Service;

    /**
     * 查询赛事登记副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:list')")
    @GetMapping("/list")
    public TableDataInfo list(Competitions3 competitions3)
    {
        startPage();
        List<Competitions3> list = competitions3Service.selectCompetitions3List(competitions3);
        return getDataTable(list);
    }

    /**
     * 导出赛事登记副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:export')")
    @Log(title = "赛事登记副本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competitions3 competitions3)
    {
        List<Competitions3> list = competitions3Service.selectCompetitions3List(competitions3);
        ExcelUtil<Competitions3> util = new ExcelUtil<Competitions3>(Competitions3.class);
        util.exportExcel(response, list, "赛事登记副本数据");
    }

    /**
     * 获取赛事登记副本详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(competitions3Service.selectCompetitions3ById(id));
    }

    /**
     * 新增赛事登记副本
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:add')")
    @Log(title = "赛事登记副本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competitions3 competitions3)
    {
        return toAjax(competitions3Service.insertCompetitions3(competitions3));
    }

    /**
     * 修改赛事登记副本
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:edit')")
    @Log(title = "赛事登记副本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competitions3 competitions3)
    {
        return toAjax(competitions3Service.updateCompetitions3(competitions3));
    }

    /**
     * 删除赛事登记副本
     */
    @PreAuthorize("@ss.hasPermi('system:competitions3:remove')")
    @Log(title = "赛事登记副本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(competitions3Service.deleteCompetitions3ByIds(ids));
    }
}
