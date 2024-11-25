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
import com.ruoyi.system.domain.MyRegistration;
import com.ruoyi.system.service.IMyRegistrationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人赛事Controller
 * 
 * @author maomao
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/system/registration")
public class MyRegistrationController extends BaseController
{
    @Autowired
    private IMyRegistrationService myRegistrationService;

    /**
     * 查询个人赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:registration:list')")
    @GetMapping("/list")
    public TableDataInfo list(MyRegistration myRegistration)
    {
        startPage();
        List<MyRegistration> list = myRegistrationService.selectMyRegistrationList(myRegistration);
        return getDataTable(list);
    }

    /**
     * 导出个人赛事列表
     */
    @PreAuthorize("@ss.hasPermi('system:registration:export')")
    @Log(title = "个人赛事", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MyRegistration myRegistration)
    {
        List<MyRegistration> list = myRegistrationService.selectMyRegistrationList(myRegistration);
        ExcelUtil<MyRegistration> util = new ExcelUtil<MyRegistration>(MyRegistration.class);
        util.exportExcel(response, list, "个人赛事数据");
    }

    /**
     * 获取个人赛事详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:registration:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(myRegistrationService.selectMyRegistrationById(id));
    }

    /**
     * 新增个人赛事
     */
    @PreAuthorize("@ss.hasPermi('system:registration:add')")
    @Log(title = "个人赛事", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyRegistration myRegistration)
    {
        return toAjax(myRegistrationService.insertMyRegistration(myRegistration));
    }

    /**
     * 修改个人赛事
     */
    @PreAuthorize("@ss.hasPermi('system:registration:edit')")
    @Log(title = "个人赛事", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyRegistration myRegistration)
    {
        return toAjax(myRegistrationService.updateMyRegistration(myRegistration));
    }

    /**
     * 删除个人赛事
     */
    @PreAuthorize("@ss.hasPermi('system:registration:remove')")
    @Log(title = "个人赛事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(myRegistrationService.deleteMyRegistrationByIds(ids));
    }
}
