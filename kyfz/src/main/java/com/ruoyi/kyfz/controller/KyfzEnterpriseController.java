package com.ruoyi.kyfz.controller;

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
import com.ruoyi.kyfz.service.IKyfzEnterpriseService;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

/**
 * 企业管理Controller
 * 
 * @author ruoyi
 * @date 2023-06-09
 */
@RestController
@RequestMapping("/kyfz/enterprise")
public class KyfzEnterpriseController extends BaseController {
    @Autowired
    private IKyfzEnterpriseService kyfzEnterpriseService;

    @Autowired
    private IKyfzRequirementService kyfzRequirementService;

    /**
     * 查询企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzEnterprise kyfzEnterprise) {
        startPage();
        List<KyfzEnterprise> list = kyfzEnterpriseService.selectKyfzEnterpriseList(kyfzEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:export')")
    @Log(title = "企业管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzEnterprise kyfzEnterprise) {
        List<KyfzEnterprise> list = kyfzEnterpriseService.selectKyfzEnterpriseList(kyfzEnterprise);
        ExcelUtil<KyfzEnterprise> util = new ExcelUtil<KyfzEnterprise>(KyfzEnterprise.class);
        util.exportExcel(response, list, "企业管理数据");
    }

    /**
     * 获取企业管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") Long enterpriseId) {
        return success(kyfzEnterpriseService.selectKyfzEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增企业管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:add')")
    @Log(title = "企业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzEnterprise kyfzEnterprise) {
        return toAjax(kyfzEnterpriseService.insertKyfzEnterprise(kyfzEnterprise));
    }

    /**
     * 修改企业管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:edit')")
    @Log(title = "企业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzEnterprise kyfzEnterprise) {
        return toAjax(kyfzEnterpriseService.updateKyfzEnterprise(kyfzEnterprise));
    }

    /**
     * 删除企业管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:enterprise:remove')")
    @Log(title = "企业管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIds) {
        return toAjax(kyfzEnterpriseService.deleteKyfzEnterpriseByEnterpriseIds(enterpriseIds));
    }

    /**
     * 根据企业名查询名下所有需求
     */
    @GetMapping(value = "/getEnterpriseRequirement/{enterpriseName}")
    public AjaxResult getEnterpriseRequirementInfo(@PathVariable("enterpriseName") String enterpriseName) {
        List<KyfzRequirement> list = kyfzRequirementService.getEnterpriseRequirementInfo(enterpriseName);
        return success(list);
    }

    /**
     * 根据企业名查询企业信息
     */
    @GetMapping(value = "/getEnterprisefromName/{enterpriseName}")
    public AjaxResult getEnterprisefromName(@PathVariable("enterpriseName") String enterpriseName) {
        KyfzEnterprise kyfzEnterprise = kyfzEnterpriseService.getEnterprisefromName(enterpriseName);
        return success(kyfzEnterpriseService.getEnterprisefromName(enterpriseName));
    }

}
