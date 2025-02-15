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
import com.ruoyi.system.domain.Teams1;
import com.ruoyi.system.service.ITeams1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 队伍Controller
 * 
 * @author maomao
 * @date 2024-12-11
 */
@RestController
@RequestMapping("/system/teams1")
public class Teams1Controller extends BaseController
{
    @Autowired
    private ITeams1Service teams1Service;

    /**
     * 查询队伍列表
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teams1 teams1)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            teams1.setLeaderId(studentId);
        }
        startPage();
        List<Teams1> list = teams1Service.selectTeams1List(teams1);
        return getDataTable(list);
    }

    /**
     * 导出队伍列表
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:export')")
    @Log(title = "队伍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teams1 teams1)
    {
        List<Teams1> list = teams1Service.selectTeams1List(teams1);
        ExcelUtil<Teams1> util = new ExcelUtil<Teams1>(Teams1.class);
        util.exportExcel(response, list, "队伍数据");
    }

    /**
     * 获取队伍详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teams1Service.selectTeams1ById(id));
    }

    /**
     * 新增队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:add')")
    @Log(title = "队伍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teams1 teams1)
    {
        return toAjax(teams1Service.insertTeams1(teams1));
    }

    /**
     * 修改队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:edit')")
    @Log(title = "队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teams1 teams1)
    {
        return toAjax(teams1Service.updateTeams1(teams1));
    }

    /**
     * 删除队伍
     */
    @PreAuthorize("@ss.hasPermi('system:teams1:remove')")
    @Log(title = "队伍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teams1Service.deleteTeams1ByIds(ids));
    }
}
