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
import com.ruoyi.system.domain.GRtdcompetitions;
import com.ruoyi.system.service.IGRtdcompetitionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人团队赛事Controller
 * 
 * @author maomao
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/system/GRtdcompetitions")
public class GRtdcompetitionsController extends BaseController
{
    @Autowired
    private IGRtdcompetitionsService gRtdcompetitionsService;

    /**
     * 查询个人团队赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:list')")
    @GetMapping("/list")
    public TableDataInfo list(GRtdcompetitions gRtdcompetitions)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            gRtdcompetitions.setStudentId(studentId);
            startPage();
        }
        List<GRtdcompetitions> list = gRtdcompetitionsService.selectGRtdcompetitionsList(gRtdcompetitions);
        return getDataTable(list);
    }

    /**
     * 导出个人团队赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:export')")
    @Log(title = "个人团队赛事", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GRtdcompetitions gRtdcompetitions)
    {
        List<GRtdcompetitions> list = gRtdcompetitionsService.selectGRtdcompetitionsList(gRtdcompetitions);
        ExcelUtil<GRtdcompetitions> util = new ExcelUtil<GRtdcompetitions>(GRtdcompetitions.class);
        util.exportExcel(response, list, "个人团队赛事数据");
    }

    /**
     * 获取个人团队赛事详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(gRtdcompetitionsService.selectGRtdcompetitionsById(id));
    }

    /**
     * 新增个人团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:add')")
    @Log(title = "个人团队赛事", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GRtdcompetitions gRtdcompetitions)
    {
        return toAjax(gRtdcompetitionsService.insertGRtdcompetitions(gRtdcompetitions));
    }

    /**
     * 修改个人团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:edit')")
    @Log(title = "个人团队赛事", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GRtdcompetitions gRtdcompetitions)
    {
        return toAjax(gRtdcompetitionsService.updateGRtdcompetitions(gRtdcompetitions));
    }

    /**
     * 删除个人团队赛事
     */
    @PreAuthorize("@ss.hasPermi('system:GRtdcompetitions:remove')")
    @Log(title = "个人团队赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gRtdcompetitionsService.deleteGRtdcompetitionsByIds(ids));
    }
}
