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
import com.ruoyi.system.domain.CYtdcompetion;
import com.ruoyi.system.service.ICYtdcompetionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成员团队赛事Controller
 * 
 * @author maomao
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/CYtdcompetion")
public class CYtdcompetionController extends BaseController
{
    @Autowired
    private ICYtdcompetionService cYtdcompetionService;

    /**
     * 查询成员团队赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:list')")
    @GetMapping("/list")
    public TableDataInfo list(CYtdcompetion cYtdcompetion)
    {
        startPage();
        List<CYtdcompetion> list = cYtdcompetionService.selectCYtdcompetionList(cYtdcompetion);
        return getDataTable(list);
    }

    /**
     * 导出成员团队赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:export')")
    @Log(title = "成员团队赛事", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CYtdcompetion cYtdcompetion)
    {
        List<CYtdcompetion> list = cYtdcompetionService.selectCYtdcompetionList(cYtdcompetion);
        ExcelUtil<CYtdcompetion> util = new ExcelUtil<CYtdcompetion>(CYtdcompetion.class);
        util.exportExcel(response, list, "成员团队赛事数据");
    }

    /**
     * 获取成员团队赛事详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:query')")
    @GetMapping(value = "/{competitionId}")
    public AjaxResult getInfo(@PathVariable("competitionId") Long competitionId)
    {
        return AjaxResult.success(cYtdcompetionService.selectCYtdcompetionByCompetitionId(competitionId));
    }

    /**
     * 新增成员团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:add')")
    @Log(title = "成员团队赛事", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CYtdcompetion cYtdcompetion)
    {
        return toAjax(cYtdcompetionService.insertCYtdcompetion(cYtdcompetion));
    }

    /**
     * 修改成员团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:edit')")
    @Log(title = "成员团队赛事", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CYtdcompetion cYtdcompetion)
    {
        return toAjax(cYtdcompetionService.updateCYtdcompetion(cYtdcompetion));
    }

    /**
     * 删除成员团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:CYtdcompetion:remove')")
    @Log(title = "成员团队赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{competitionIds}")
    public AjaxResult remove(@PathVariable Long[] competitionIds)
    {
        return toAjax(cYtdcompetionService.deleteCYtdcompetionByCompetitionIds(competitionIds));
    }
}
