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
import com.ruoyi.kyfz.domain.KyfzWorkstation;
import com.ruoyi.kyfz.service.IKyfzWorkstationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工作站Controller
 * 
 * @author zyx
 * @date 2023-07-10
 */
@RestController
@RequestMapping("/kyfz/workstation")
public class KyfzWorkstationController extends BaseController
{
    @Autowired
    private IKyfzWorkstationService kyfzWorkstationService;

    /**
     * 查询工作站列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzWorkstation kyfzWorkstation)
    {
        startPage();
        List<KyfzWorkstation> list = kyfzWorkstationService.selectKyfzWorkstationList(kyfzWorkstation);
        return getDataTable(list);
    }

    /**
     * 导出工作站列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:export')")
    @Log(title = "工作站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzWorkstation kyfzWorkstation)
    {
        List<KyfzWorkstation> list = kyfzWorkstationService.selectKyfzWorkstationList(kyfzWorkstation);
        ExcelUtil<KyfzWorkstation> util = new ExcelUtil<KyfzWorkstation>(KyfzWorkstation.class);
        util.exportExcel(response, list, "工作站数据");
    }

    /**
     * 获取工作站详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(kyfzWorkstationService.selectKyfzWorkstationByTaskId(taskId));
    }

    /**
     * 新增工作站
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:add')")
    @Log(title = "工作站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzWorkstation kyfzWorkstation)
    {
        return toAjax(kyfzWorkstationService.insertKyfzWorkstation(kyfzWorkstation));
    }

    /**
     * 修改工作站
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:edit')")
    @Log(title = "工作站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzWorkstation kyfzWorkstation)
    {
        return toAjax(kyfzWorkstationService.updateKyfzWorkstation(kyfzWorkstation));
    }

    /**
     * 删除工作站
     */
    @PreAuthorize("@ss.hasPermi('kyfz:workstation:remove')")
    @Log(title = "工作站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(kyfzWorkstationService.deleteKyfzWorkstationByTaskIds(taskIds));
    }
}
