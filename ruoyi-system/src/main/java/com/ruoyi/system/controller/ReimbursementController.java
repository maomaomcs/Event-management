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
import com.ruoyi.system.domain.Reimbursement;
import com.ruoyi.system.service.IReimbursementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报销审核Controller
 * 
 * @author maomao
 * @date 2024-11-10
 */
@RestController
@RequestMapping("/system/reimbursement")
public class ReimbursementController extends BaseController
{
    @Autowired
    private IReimbursementService reimbursementService;

    /**
     * 查询报销审核列表
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reimbursement reimbursement)
    {
        startPage();
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        return getDataTable(list);
    }

    /**
     * 导出报销审核列表
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:export')")
    @Log(title = "报销审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reimbursement reimbursement)
    {
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        ExcelUtil<Reimbursement> util = new ExcelUtil<Reimbursement>(Reimbursement.class);
        util.exportExcel(response, list, "报销审核数据");
    }

    /**
     * 获取报销审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reimbursementService.selectReimbursementById(id));
    }

    /**
     * 新增报销审核
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:add')")
    @Log(title = "报销审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.insertReimbursement(reimbursement));
    }

    /**
     * 修改报销审核
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:edit')")
    @Log(title = "报销审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.updateReimbursement(reimbursement));
    }

    /**
     * 删除报销审核
     */
    @PreAuthorize("@ss.hasPermi('system:reimbursement:remove')")
    @Log(title = "报销审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reimbursementService.deleteReimbursementByIds(ids));
    }
}
