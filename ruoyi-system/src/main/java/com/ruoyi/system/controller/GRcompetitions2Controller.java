package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.GRcompetitions1;
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
import com.ruoyi.system.domain.GRcompetitions2;
import com.ruoyi.system.service.IGRcompetitions2Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人赛事副本Controller
 * 
 * @author maomao
 * @date 2024-11-29
 */
@RestController
@RequestMapping("/system/GRcompetitions2")
public class GRcompetitions2Controller extends BaseController
{
    @Autowired
    private IGRcompetitions2Service gRcompetitions2Service;

    /**
     * 查询个人赛事副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:list')")
    @GetMapping("/list")
    public TableDataInfo list(GRcompetitions2 gRcompetitions2)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            gRcompetitions2.setStudentId(studentId);
        }
        startPage();
        List<GRcompetitions2> list = gRcompetitions2Service.selectGRcompetitions2List(gRcompetitions2);
        return getDataTable(list);
    }

    /**
     * 导出个人赛事副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:export')")
    @Log(title = "个人赛事副本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GRcompetitions2 gRcompetitions2)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            gRcompetitions2.setStudentId(studentId);
        }
        List<GRcompetitions2> list = gRcompetitions2Service.selectGRcompetitions2List(gRcompetitions2);
        ExcelUtil<GRcompetitions2> util = new ExcelUtil<GRcompetitions2>(GRcompetitions2.class);
        util.exportExcel(response, list, "个人赛事副本数据");
    }

    /**
     * 获取个人赛事副本详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions2 data = gRcompetitions2Service.selectGRcompetitions2ById(id);
        if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
            return AjaxResult.error("无权限查看此数据");
        }
        return AjaxResult.success(gRcompetitions2Service.selectGRcompetitions2ById(id));
    }

    /**
     * 新增个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:add')")
    @Log(title = "个人赛事副本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GRcompetitions2 gRcompetitions2)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if (!"admin".equals(studentId)) {
            gRcompetitions2.setStudentId(studentId); // 设置学生ID为当前用户
        }
        return toAjax(gRcompetitions2Service.insertGRcompetitions2(gRcompetitions2));
    }

    /**
     * 修改个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:edit')")
    @Log(title = "个人赛事副本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GRcompetitions2 gRcompetitions2)
    {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions2 existingData = gRcompetitions2Service.selectGRcompetitions2ById(gRcompetitions2.getId());
        if (existingData == null || (!"admin".equals(studentId) && !studentId.equals(existingData.getStudentId()))) {
            return AjaxResult.error("无权限修改此数据");
        }
        return toAjax(gRcompetitions2Service.updateGRcompetitions2(gRcompetitions2));
    }

    /**
     * 删除个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions2:remove')")
    @Log(title = "个人赛事副本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        String studentId = getLoginUser().getUser().getUserName();
        for (Long id : ids) {
            GRcompetitions2 data = gRcompetitions2Service.selectGRcompetitions2ById(id);
            if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
                return AjaxResult.error("无权限删除数据编号：" + id);
            }
        }
        return toAjax(gRcompetitions2Service.deleteGRcompetitions2ByIds(ids));
    }
}
