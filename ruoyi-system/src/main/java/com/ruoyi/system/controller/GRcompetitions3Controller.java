package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.GRcompetitions2;
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
import com.ruoyi.system.domain.GRcompetitions3;
import com.ruoyi.system.service.IGRcompetitions3Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人赛事副本Controller
 * 
 * @author maomao
 * @date 2024-12-01
 */
@RestController
@RequestMapping("/system/GRcompetitions3")
public class GRcompetitions3Controller extends BaseController
{
    @Autowired
    private IGRcompetitions3Service gRcompetitions3Service;

    /**
     * 查询个人赛事副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:list')")
    @GetMapping("/list")
    public TableDataInfo list(GRcompetitions3 gRcompetitions3)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            gRcompetitions3.setStudentId(studentId);
        }
        startPage();
        List<GRcompetitions3> list = gRcompetitions3Service.selectGRcompetitions3List(gRcompetitions3);
        return getDataTable(list);
    }

    /**
     * 导出个人赛事副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:export')")
    @Log(title = "个人赛事副本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GRcompetitions3 gRcompetitions3)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if(!"admin".equals(studentId)) {
            gRcompetitions3.setStudentId(studentId);
        }
        List<GRcompetitions3> list = gRcompetitions3Service.selectGRcompetitions3List(gRcompetitions3);
        ExcelUtil<GRcompetitions3> util = new ExcelUtil<GRcompetitions3>(GRcompetitions3.class);
        util.exportExcel(response, list, "个人赛事副本数据");
    }

    /**
     * 获取个人赛事副本详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions3 data = gRcompetitions3Service.selectGRcompetitions3ById(id);
        if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
            return AjaxResult.error("无权限查看此数据");
        }
        return AjaxResult.success(gRcompetitions3Service.selectGRcompetitions3ById(id));
    }

    /**
     * 新增个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:add')")
    @Log(title = "个人赛事副本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GRcompetitions3 gRcompetitions3)
    {
        String studentId = getLoginUser().getUser().getUserName();
        if (!"admin".equals(studentId)) {
            gRcompetitions3.setStudentId(studentId); // 设置学生ID为当前用户
        }
        return toAjax(gRcompetitions3Service.insertGRcompetitions3(gRcompetitions3));
    }

    /**
     * 修改个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:edit')")
    @Log(title = "个人赛事副本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GRcompetitions3 gRcompetitions3)
    {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions3 existingData = gRcompetitions3Service.selectGRcompetitions3ById(gRcompetitions3.getId());
        if (existingData == null || (!"admin".equals(studentId) && !studentId.equals(existingData.getStudentId()))) {
            return AjaxResult.error("无权限修改此数据");
        }
        return toAjax(gRcompetitions3Service.updateGRcompetitions3(gRcompetitions3));
    }

    /**
     * 删除个人赛事副本
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions3:remove')")
    @Log(title = "个人赛事副本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        String studentId = getLoginUser().getUser().getUserName();
        for (Long id : ids) {
            GRcompetitions3 data = gRcompetitions3Service.selectGRcompetitions3ById(id);
            if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
                return AjaxResult.error("无权限删除数据编号：" + id);
            }
        }
        return toAjax(gRcompetitions3Service.deleteGRcompetitions3ByIds(ids));
    }
}
