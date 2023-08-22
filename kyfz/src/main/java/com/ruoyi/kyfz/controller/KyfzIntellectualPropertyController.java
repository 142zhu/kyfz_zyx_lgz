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
import com.ruoyi.kyfz.domain.KyfzIntellectualProperty;
import com.ruoyi.kyfz.service.IKyfzIntellectualPropertyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 知识产权Controller
 * 
 * @author zyx
 * @date 2023-08-19
 */
@RestController
@RequestMapping("/kyfz/property")
public class KyfzIntellectualPropertyController extends BaseController
{
    @Autowired
    private IKyfzIntellectualPropertyService kyfzIntellectualPropertyService;

    /**
     * 查询知识产权列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzIntellectualProperty kyfzIntellectualProperty)
    {
        startPage();
        List<KyfzIntellectualProperty> list = kyfzIntellectualPropertyService.selectKyfzIntellectualPropertyList(kyfzIntellectualProperty);
        return getDataTable(list);
    }

    /**
     * 导出知识产权列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:export')")
    @Log(title = "知识产权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzIntellectualProperty kyfzIntellectualProperty)
    {
        List<KyfzIntellectualProperty> list = kyfzIntellectualPropertyService.selectKyfzIntellectualPropertyList(kyfzIntellectualProperty);
        ExcelUtil<KyfzIntellectualProperty> util = new ExcelUtil<KyfzIntellectualProperty>(KyfzIntellectualProperty.class);
        util.exportExcel(response, list, "知识产权数据");
    }

    /**
     * 获取知识产权详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:query')")
    @GetMapping(value = "/{intellectualPropertyId}")
    public AjaxResult getInfo(@PathVariable("intellectualPropertyId") Long intellectualPropertyId)
    {
        return success(kyfzIntellectualPropertyService.selectKyfzIntellectualPropertyByIntellectualPropertyId(intellectualPropertyId));
    }

    /**
     * 新增知识产权
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:add')")
    @Log(title = "知识产权", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzIntellectualProperty kyfzIntellectualProperty)
    {
        return toAjax(kyfzIntellectualPropertyService.insertKyfzIntellectualProperty(kyfzIntellectualProperty));
    }

    /**
     * 修改知识产权
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:edit')")
    @Log(title = "知识产权", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzIntellectualProperty kyfzIntellectualProperty)
    {
        return toAjax(kyfzIntellectualPropertyService.updateKyfzIntellectualProperty(kyfzIntellectualProperty));
    }

    /**
     * 删除知识产权
     */
    @PreAuthorize("@ss.hasPermi('kyfz:property:remove')")
    @Log(title = "知识产权", businessType = BusinessType.DELETE)
	@DeleteMapping("/{intellectualPropertyIds}")
    public AjaxResult remove(@PathVariable Long[] intellectualPropertyIds)
    {
        return toAjax(kyfzIntellectualPropertyService.deleteKyfzIntellectualPropertyByIntellectualPropertyIds(intellectualPropertyIds));
    }
}
