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
import com.ruoyi.system.domain.TeamMembers;
import com.ruoyi.system.service.ITeamMembersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍成员Controller
 * 
 * @author maomao
 * @date 2024-12-10
 */
@RestController
@RequestMapping("/system/members")
public class TeamMembersController extends BaseController
{
    @Autowired
    private ITeamMembersService teamMembersService;

    /**
     * 查询队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:members:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamMembers teamMembers)
    {
        startPage();
        List<TeamMembers> list = teamMembersService.selectTeamMembersList(teamMembers);
        return getDataTable(list);
    }

    /**
     * 导出队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:members:export')")
    @Log(title = "队伍成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamMembers teamMembers)
    {
        List<TeamMembers> list = teamMembersService.selectTeamMembersList(teamMembers);
        ExcelUtil<TeamMembers> util = new ExcelUtil<TeamMembers>(TeamMembers.class);
        util.exportExcel(response, list, "队伍成员数据");
    }

    /**
     * 获取队伍成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:members:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teamMembersService.selectTeamMembersById(id));
    }

    /**
     * 新增队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members:add')")
    @Log(title = "队伍成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamMembers teamMembers)
    {
        return toAjax(teamMembersService.insertTeamMembers(teamMembers));
    }

    /**
     * 修改队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members:edit')")
    @Log(title = "队伍成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamMembers teamMembers)
    {
        return toAjax(teamMembersService.updateTeamMembers(teamMembers));
    }

    /**
     * 删除队伍成员
     */
    @PreAuthorize("@ss.hasPermi('system:members:remove')")
    @Log(title = "队伍成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teamMembersService.deleteTeamMembersByIds(ids));
    }
}
