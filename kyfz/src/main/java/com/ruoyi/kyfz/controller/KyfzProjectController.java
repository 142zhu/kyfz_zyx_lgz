package com.ruoyi.kyfz.controller;

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
import com.ruoyi.kyfz.domain.KyfzProject;
import com.ruoyi.kyfz.service.IKyfzProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目信息Controller
 * 
 * @author ruoyi
 * @date 2023-08-18
 */
@RestController
@RequestMapping("/kyfz/project")
public class KyfzProjectController extends BaseController
{
    @Autowired
    private IKyfzProjectService kyfzProjectService;

    /**
     * 查询项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzProject kyfzProject)
    {
        startPage();
        List<KyfzProject> list = kyfzProjectService.selectKyfzProjectList(kyfzProject);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzProject kyfzProject)
    {
        List<KyfzProject> list = kyfzProjectService.selectKyfzProjectList(kyfzProject);
        ExcelUtil<KyfzProject> util = new ExcelUtil<KyfzProject>(KyfzProject.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(kyfzProjectService.selectKyfzProjectByProjectId(projectId));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzProject kyfzProject)
    {
        return toAjax(kyfzProjectService.insertKyfzProject(kyfzProject));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzProject kyfzProject)
    {
        return toAjax(kyfzProjectService.updateKyfzProject(kyfzProject));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:project:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(kyfzProjectService.deleteKyfzProjectByProjectIds(projectIds));
    }
}
