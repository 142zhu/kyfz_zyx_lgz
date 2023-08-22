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
import com.ruoyi.kyfz.domain.KyfzThesis;
import com.ruoyi.kyfz.service.IKyfzThesisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 论文信息Controller
 * 
 * @author zyx
 * @date 2023-08-19
 */
@RestController
@RequestMapping("/kyfz/thesis")
public class KyfzThesisController extends BaseController
{
    @Autowired
    private IKyfzThesisService kyfzThesisService;

    /**
     * 查询论文信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzThesis kyfzThesis)
    {
        startPage();
        List<KyfzThesis> list = kyfzThesisService.selectKyfzThesisList(kyfzThesis);
        return getDataTable(list);
    }

    /**
     * 导出论文信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:export')")
    @Log(title = "论文信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzThesis kyfzThesis)
    {
        List<KyfzThesis> list = kyfzThesisService.selectKyfzThesisList(kyfzThesis);
        ExcelUtil<KyfzThesis> util = new ExcelUtil<KyfzThesis>(KyfzThesis.class);
        util.exportExcel(response, list, "论文信息数据");
    }

    /**
     * 获取论文信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:query')")
    @GetMapping(value = "/{thesisId}")
    public AjaxResult getInfo(@PathVariable("thesisId") Long thesisId)
    {
        return success(kyfzThesisService.selectKyfzThesisByThesisId(thesisId));
    }

    /**
     * 新增论文信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:add')")
    @Log(title = "论文信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzThesis kyfzThesis)
    {
        return toAjax(kyfzThesisService.insertKyfzThesis(kyfzThesis));
    }

    /**
     * 修改论文信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:edit')")
    @Log(title = "论文信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzThesis kyfzThesis)
    {
        return toAjax(kyfzThesisService.updateKyfzThesis(kyfzThesis));
    }

    /**
     * 删除论文信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:thesis:remove')")
    @Log(title = "论文信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{thesisIds}")
    public AjaxResult remove(@PathVariable Long[] thesisIds)
    {
        return toAjax(kyfzThesisService.deleteKyfzThesisByThesisIds(thesisIds));
    }
}
