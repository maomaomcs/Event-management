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
import com.ruoyi.system.domain.Teams;
import com.ruoyi.system.service.ITeamsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍Controller
 * 
 * @author maomao
 * @date 2024-12-10
 */
@RestController
@RequestMapping("/system/teams")
public class TeamsController extends BaseController
{
    @Autowired
    private ITeamsService teamsService;

    /**
     * 查询队伍列表
     */
    @PreAuthorize("@ss.hasPermi('system:teams:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teams teams)
    {
        startPage();
        List<Teams> list = teamsService.selectTeamsList(teams);
        return getDataTable(list);
    }

    /**
     * 导出队伍列表
     */
    @PreAuthorize("@ss.hasPermi('system:teams:export')")
    @Log(title = "队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teams teams)
    {
        List<Teams> list = teamsService.selectTeamsList(teams);
        ExcelUtil<Teams> util = new ExcelUtil<Teams>(Teams.class);
        util.exportExcel(response, list, "队伍数据");
    }

    /**
     * 获取队伍详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:teams:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teamsService.selectTeamsById(id));
    }

    /**
     * 新增队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams:add')")
    @Log(title = "队伍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teams teams)
    {
        return toAjax(teamsService.insertTeams(teams));
    }

    /**
     * 修改队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams:edit')")
    @Log(title = "队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teams teams)
    {
        return toAjax(teamsService.updateTeams(teams));
    }

    /**
     * 删除队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams:remove')")
    @Log(title = "队伍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teamsService.deleteTeamsByIds(ids));
    }
}
