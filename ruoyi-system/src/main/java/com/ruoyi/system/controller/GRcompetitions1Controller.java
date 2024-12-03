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
import com.ruoyi.system.domain.GRcompetitions1;
import com.ruoyi.system.service.IGRcompetitions1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人赛事11Controller
 *
 * @author maomao
 * @date 2024-11-29
 */
@RestController
@RequestMapping("/system/GRcompetitions1")
public class GRcompetitions1Controller extends BaseController {
    @Autowired
    private IGRcompetitions1Service gRcompetitions1Service;

    /**
     * 查询个人赛事11列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:list')")
    @GetMapping("/list")
    public TableDataInfo list(GRcompetitions1 gRcompetitions1) {
        String studentId = getLoginUser().getUser().getUserName();
        if (!"admin".equals(studentId)) {
            gRcompetitions1.setStudentId(studentId); // 非admin用户限制条件
        }
        startPage();
        List<GRcompetitions1> list = gRcompetitions1Service.selectGRcompetitions1List(gRcompetitions1);
        return getDataTable(list);
    }

    /**
     * 导出个人赛事11列表
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:export')")
    @Log(title = "个人赛事11", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GRcompetitions1 gRcompetitions1) {
        String studentId = getLoginUser().getUser().getUserName();
        if (!"admin".equals(studentId)) {
            gRcompetitions1.setStudentId(studentId); // 非admin用户限制条件
        }
        List<GRcompetitions1> list = gRcompetitions1Service.selectGRcompetitions1List(gRcompetitions1);
        ExcelUtil<GRcompetitions1> util = new ExcelUtil<>(GRcompetitions1.class);
        util.exportExcel(response, list, "个人赛事11数据");
    }

    /**
     * 获取个人赛事11详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions1 data = gRcompetitions1Service.selectGRcompetitions1ById(id);
        if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
            return AjaxResult.error("无权限查看此数据");
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增个人赛事11
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:add')")
    @Log(title = "个人赛事11", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GRcompetitions1 gRcompetitions1) {
        String studentId = getLoginUser().getUser().getUserName();
        if (!"admin".equals(studentId)) {
            gRcompetitions1.setStudentId(studentId); // 设置学生ID为当前用户
        }
        return toAjax(gRcompetitions1Service.insertGRcompetitions1(gRcompetitions1));
    }

    /**
     * 修改个人赛事11
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:edit')")
    @Log(title = "个人赛事11", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GRcompetitions1 gRcompetitions1) {
        String studentId = getLoginUser().getUser().getUserName();
        GRcompetitions1 existingData = gRcompetitions1Service.selectGRcompetitions1ById(gRcompetitions1.getId());
        if (existingData == null || (!"admin".equals(studentId) && !studentId.equals(existingData.getStudentId()))) {
            return AjaxResult.error("无权限修改此数据");
        }
        return toAjax(gRcompetitions1Service.updateGRcompetitions1(gRcompetitions1));
    }

    /**
     * 删除个人赛事11
     */
    @PreAuthorize("@ss.hasPermi('system:GRcompetitions1:remove')")
    @Log(title = "个人赛事11", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        String studentId = getLoginUser().getUser().getUserName();
        for (Long id : ids) {
            GRcompetitions1 data = gRcompetitions1Service.selectGRcompetitions1ById(id);
            if (data == null || (!"admin".equals(studentId) && !studentId.equals(data.getStudentId()))) {
                return AjaxResult.error("无权限删除数据编号：" + id);
            }
        }
        return toAjax(gRcompetitions1Service.deleteGRcompetitions1ByIds(ids));
    }
}
