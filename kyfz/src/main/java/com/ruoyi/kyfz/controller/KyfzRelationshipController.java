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
import com.ruoyi.kyfz.domain.KyfzRelationship;
import com.ruoyi.kyfz.service.IKyfzRelationshipService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专家关系Controller
 * 
 * @author zyx
 * @date 2023-07-18
 */
@RestController
@RequestMapping("/kyfz/relationship")
public class KyfzRelationshipController extends BaseController
{
    @Autowired
    private IKyfzRelationshipService kyfzRelationshipService;

    /**
     * 查询专家关系列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzRelationship kyfzRelationship)
    {
        startPage();
        List<KyfzRelationship> list = kyfzRelationshipService.selectKyfzRelationshipList(kyfzRelationship);
        return getDataTable(list);
    }

    /**
     * 导出专家关系列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:export')")
    @Log(title = "专家关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzRelationship kyfzRelationship)
    {
        List<KyfzRelationship> list = kyfzRelationshipService.selectKyfzRelationshipList(kyfzRelationship);
        ExcelUtil<KyfzRelationship> util = new ExcelUtil<KyfzRelationship>(KyfzRelationship.class);
        util.exportExcel(response, list, "专家关系数据");
    }

    /**
     * 获取专家关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:query')")
    @GetMapping(value = "/{relationshipId}")
    public AjaxResult getInfo(@PathVariable("relationshipId") Long relationshipId)
    {
        return success(kyfzRelationshipService.selectKyfzRelationshipByRelationshipId(relationshipId));
    }

    /**
     * 新增专家关系
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:add')")
    @Log(title = "专家关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzRelationship kyfzRelationship)
    {
        return toAjax(kyfzRelationshipService.insertKyfzRelationship(kyfzRelationship));
    }

    /**
     * 修改专家关系
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:edit')")
    @Log(title = "专家关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzRelationship kyfzRelationship)
    {
        return toAjax(kyfzRelationshipService.updateKyfzRelationship(kyfzRelationship));
    }

    /**
     * 删除专家关系
     */
    @PreAuthorize("@ss.hasPermi('kyfz:relationship:remove')")
    @Log(title = "专家关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationshipIds}")
    public AjaxResult remove(@PathVariable Long[] relationshipIds)
    {
        return toAjax(kyfzRelationshipService.deleteKyfzRelationshipByRelationshipIds(relationshipIds));
    }
}
