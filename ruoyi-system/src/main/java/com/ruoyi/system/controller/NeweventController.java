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
import com.ruoyi.system.domain.Newevent;
import com.ruoyi.system.service.INeweventService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新建赛事Controller
 * 
 * @author maomao
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/system/newevent")
public class NeweventController extends BaseController
{
    @Autowired
    private INeweventService neweventService;

    /**
     * 查询新建赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:list')")
    @GetMapping("/list")
    public TableDataInfo list(Newevent newevent)
    {
        startPage();
        List<Newevent> list = neweventService.selectNeweventList(newevent);
        return getDataTable(list);
    }

    /**
     * 导出新建赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:export')")
    @Log(title = "新建赛事", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Newevent newevent)
    {
        List<Newevent> list = neweventService.selectNeweventList(newevent);
        ExcelUtil<Newevent> util = new ExcelUtil<Newevent>(Newevent.class);
        util.exportExcel(response, list, "新建赛事数据");
    }

    /**
     * 获取新建赛事详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(neweventService.selectNeweventById(id));
    }

    /**
     * 新增新建赛事
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:add')")
    @Log(title = "新建赛事", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Newevent newevent)
    {
        return toAjax(neweventService.insertNewevent(newevent));
    }

    /**
     * 修改新建赛事
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:edit')")
    @Log(title = "新建赛事", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Newevent newevent)
    {
        return toAjax(neweventService.updateNewevent(newevent));
    }

    /**
     * 删除新建赛事
     */
    @PreAuthorize("@ss.hasPermi('system:newevent:remove')")
    @Log(title = "新建赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(neweventService.deleteNeweventByIds(ids));
    }
}
