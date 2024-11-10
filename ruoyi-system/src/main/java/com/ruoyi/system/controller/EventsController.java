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
import com.ruoyi.system.domain.Events;
import com.ruoyi.system.service.IEventsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事记录Controller
 * 
 * @author maomao
 * @date 2024-11-10
 */
@RestController
@RequestMapping("/system/events")
public class EventsController extends BaseController
{
    @Autowired
    private IEventsService eventsService;

    /**
     * 查询赛事记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:events:list')")
    @GetMapping("/list")
    public TableDataInfo list(Events events)
    {
        startPage();
        List<Events> list = eventsService.selectEventsList(events);
        return getDataTable(list);
    }

    /**
     * 导出赛事记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:events:export')")
    @Log(title = "赛事记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Events events)
    {
        List<Events> list = eventsService.selectEventsList(events);
        ExcelUtil<Events> util = new ExcelUtil<Events>(Events.class);
        util.exportExcel(response, list, "赛事记录数据");
    }

    /**
     * 获取赛事记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:events:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eventsService.selectEventsById(id));
    }

    /**
     * 新增赛事记录
     */
    @PreAuthorize("@ss.hasPermi('system:events:add')")
    @Log(title = "赛事记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Events events)
    {
        return toAjax(eventsService.insertEvents(events));
    }

    /**
     * 修改赛事记录
     */
    @PreAuthorize("@ss.hasPermi('system:events:edit')")
    @Log(title = "赛事记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Events events)
    {
        return toAjax(eventsService.updateEvents(events));
    }

    /**
     * 删除赛事记录
     */
    @PreAuthorize("@ss.hasPermi('system:events:remove')")
    @Log(title = "赛事记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventsService.deleteEventsByIds(ids));
    }
}
