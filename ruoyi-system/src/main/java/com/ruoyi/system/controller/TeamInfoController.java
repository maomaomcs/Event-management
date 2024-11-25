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
import com.ruoyi.system.domain.TeamInfo;
import com.ruoyi.system.service.ITeamInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍信息Controller
 * 
 * @author maomao
 * @date 2024-11-10
 */
@RestController
@RequestMapping("/system/TeamInfo")
public class TeamInfoController extends BaseController
{
    @Autowired
    private ITeamInfoService teamInfoService;

    /**
     * 查询队伍信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamInfo teamInfo)
    {
        startPage();
        List<TeamInfo> list = teamInfoService.selectTeamInfoList(teamInfo);
        return getDataTable(list);
    }

    /**
     * 导出队伍信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:export')")
    @Log(title = "队伍信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamInfo teamInfo)
    {
        List<TeamInfo> list = teamInfoService.selectTeamInfoList(teamInfo);
        ExcelUtil<TeamInfo> util = new ExcelUtil<TeamInfo>(TeamInfo.class);
        util.exportExcel(response, list, "队伍信息数据");
    }

    /**
     * 获取队伍信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:query')")
    @GetMapping(value = "/{teamid}")
    public AjaxResult getInfo(@PathVariable("teamid") Long teamid)
    {
        return AjaxResult.success(teamInfoService.selectTeamInfoByTeamid(teamid));
    }

    /**
     * 新增队伍信息
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:add')")
    @Log(title = "队伍信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamInfo teamInfo)
    {
        return toAjax(teamInfoService.insertTeamInfo(teamInfo));
    }

    /**
     * 修改队伍信息
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:edit')")
    @Log(title = "队伍信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamInfo teamInfo)
    {
        return toAjax(teamInfoService.updateTeamInfo(teamInfo));
    }

    /**
     * 删除队伍信息
     */
    @PreAuthorize("@ss.hasPermi('system:TeamInfo:remove')")
    @Log(title = "队伍信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamids}")
    public AjaxResult remove(@PathVariable Long[] teamids)
    {
        return toAjax(teamInfoService.deleteTeamInfoByTeamids(teamids));
    }
}
