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
import com.ruoyi.kyfz.domain.KyfzIndustryClassification;
import com.ruoyi.kyfz.service.IKyfzIndustryClassificationService;

/**
 * 行业分类Controller
 * 
 * @author zyx
 * @date 2023-07-16
 */
@RestController
@RequestMapping("/kyfz/classification")
public class KyfzIndustryClassificationController extends BaseController {
    @Autowired
    private IKyfzIndustryClassificationService kyfzIndustryClassificationService;

    /**
     * 查询行业分类列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzIndustryClassification kyfzIndustryClassification) {
        List<KyfzIndustryClassification> list = kyfzIndustryClassificationService
                .selectKyfzIndustryClassificationList(kyfzIndustryClassification);
        return getDataTable(list);
    }

    /**
     * 导出行业分类列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:export')")
    @Log(title = "行业分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzIndustryClassification kyfzIndustryClassification) {
        List<KyfzIndustryClassification> list = kyfzIndustryClassificationService
                .selectKyfzIndustryClassificationList(kyfzIndustryClassification);
        ExcelUtil<KyfzIndustryClassification> util = new ExcelUtil<KyfzIndustryClassification>(
                KyfzIndustryClassification.class);
        util.exportExcel(response, list, "行业分类数据");
    }

    /**
     * 获取行业分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return success(kyfzIndustryClassificationService.selectKyfzIndustryClassificationByCategoryId(categoryId));
    }

    /**
     * 新增行业分类
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:add')")
    @Log(title = "行业分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzIndustryClassification kyfzIndustryClassification) {
        return toAjax(kyfzIndustryClassificationService.insertKyfzIndustryClassification(kyfzIndustryClassification));
    }

    /**
     * 修改行业分类
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:edit')")
    @Log(title = "行业分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzIndustryClassification kyfzIndustryClassification) {
        return toAjax(kyfzIndustryClassificationService.updateKyfzIndustryClassification(kyfzIndustryClassification));
    }

    /**
     * 删除行业分类
     */
    @PreAuthorize("@ss.hasPermi('kyfz:classification:remove')")
    @Log(title = "行业分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(kyfzIndustryClassificationService.deleteKyfzIndustryClassificationByCategoryIds(categoryIds));
    }
}
