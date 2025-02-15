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
import com.ruoyi.system.domain.TeamMembers1;
import com.ruoyi.system.service.ITeamMembers1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍成员Controller
 * 
 * @author maomao
 * @date 2024-12-12
 */
@RestController
@RequestMapping("/system/members1")
public class TeamMembers1Controller extends BaseController
{
    @Autowired
    private ITeamMembers1Service teamMembers1Service;

    /**
     * 查询队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:members1:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamMembers1 teamMembers1)
    {
        startPage();
        List<TeamMembers1> list = teamMembers1Service.selectTeamMembers1List(teamMembers1);
        return getDataTable(list);
    }

    /**
     * 导出队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:members1:export')")
    @Log(title = "队伍成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamMembers1 teamMembers1)
    {
        List<TeamMembers1> list = teamMembers1Service.selectTeamMembers1List(teamMembers1);
        ExcelUtil<TeamMembers1> util = new ExcelUtil<TeamMembers1>(TeamMembers1.class);
        util.exportExcel(response, list, "队伍成员数据");
    }

    /**
     * 获取队伍成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:members1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teamMembers1Service.selectTeamMembers1ById(id));
    }

    /**
     * 新增队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members1:add')")
    @Log(title = "队伍成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamMembers1 teamMembers1)
    {
        return toAjax(teamMembers1Service.insertTeamMembers1(teamMembers1));
    }

    /**
     * 修改队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members1:edit')")
    @Log(title = "队伍成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamMembers1 teamMembers1)
    {
        return toAjax(teamMembers1Service.updateTeamMembers1(teamMembers1));
    }

    /**
     * 删除队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members1:remove')")
    @Log(title = "队伍成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teamMembers1Service.deleteTeamMembers1ByIds(ids));
    }
}
