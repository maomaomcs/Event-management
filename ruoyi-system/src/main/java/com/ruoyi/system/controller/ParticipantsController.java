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
import com.ruoyi.system.domain.Participants;
import com.ruoyi.system.service.IParticipantsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛事报销Controller
 * 
 * @author maomao
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/system/participants")
public class ParticipantsController extends BaseController
{
    @Autowired
    private IParticipantsService participantsService;

    /**
     * 查询赛事报销列表
     */
    @PreAuthorize("@ss.hasPermi('system:participants:list')")
    @GetMapping("/list")
    public TableDataInfo list(Participants participants)
    {
        startPage();
        List<Participants> list = participantsService.selectParticipantsList(participants);
        return getDataTable(list);
    }

    /**
     * 导出赛事报销列表
     */
    @PreAuthorize("@ss.hasPermi('system:participants:export')")
    @Log(title = "赛事报销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Participants participants)
    {
        List<Participants> list = participantsService.selectParticipantsList(participants);
        ExcelUtil<Participants> util = new ExcelUtil<Participants>(Participants.class);
        util.exportExcel(response, list, "赛事报销数据");
    }

    /**
     * 获取赛事报销详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:participants:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(participantsService.selectParticipantsById(id));
    }

    /**
     * 新增赛事报销
     */
    @PreAuthorize("@ss.hasPermi('system:participants:add')")
    @Log(title = "赛事报销", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Participants participants)
    {
        return toAjax(participantsService.insertParticipants(participants));
    }

    /**
     * 修改赛事报销
     */
    @PreAuthorize("@ss.hasPermi('system:participants:edit')")
    @Log(title = "赛事报销", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Participants participants)
    {
        return toAjax(participantsService.updateParticipants(participants));
    }

    /**
     * 删除赛事报销
     */
    @PreAuthorize("@ss.hasPermi('system:participants:remove')")
    @Log(title = "赛事报销", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(participantsService.deleteParticipantsByIds(ids));
    }
}
