package com.ruoyi.kyfz.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.domain.KyfzRequirement;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

/**
 * 需求管理Controller
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/kyfz/requirement")
public class KyfzRequirementController extends BaseController {
    @Autowired
    private IKyfzRequirementService kyfzRequirementService;

    /**
     * 查询需求管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzRequirement kyfzRequirement) {
        startPage();
        List<KyfzRequirement> list = kyfzRequirementService.selectKyfzRequirementList(kyfzRequirement);
        return getDataTable(list);
    }

    /**
     * 导出需求管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:export')")
    @Log(title = "需求管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzRequirement kyfzRequirement) {
        List<KyfzRequirement> list = kyfzRequirementService.selectKyfzRequirementList(kyfzRequirement);
        ExcelUtil<KyfzRequirement> util = new ExcelUtil<KyfzRequirement>(KyfzRequirement.class);
        util.exportExcel(response, list, "需求管理数据");
    }

    /**
     * 获取需求管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:query')")
    @GetMapping(value = "/{requirementId}")
    public AjaxResult getInfo(@PathVariable("requirementId") Long requirementId) {
        return success(kyfzRequirementService.selectKyfzRequirementByRequirementId(requirementId));
    }

    /**
     * 新增需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:add')")
    @Log(title = "需求管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.insertKyfzRequirement(kyfzRequirement));
    }

    /**
     * 修改需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:edit')")
    @Log(title = "需求管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.updateKyfzRequirement(kyfzRequirement));
    }

    /**
     * 删除需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:remove')")
    @Log(title = "需求管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{requirementIds}")
    public AjaxResult remove(@PathVariable Long[] requirementIds) {
        return toAjax(kyfzRequirementService.deleteKyfzRequirementByRequirementIds(requirementIds));
    }

    /**
     * 查询需求管理中企业列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:listenterprise')")
    @GetMapping("/listenterprise")
    public TableDataInfo listenterprise(KyfzEnterprise kyfzEnterprise) {
        // startPage();
        List<KyfzEnterprise> list = kyfzRequirementService.selectKyfzEnterpriseList(kyfzEnterprise);
        return getDataTable(list);
    }

}
