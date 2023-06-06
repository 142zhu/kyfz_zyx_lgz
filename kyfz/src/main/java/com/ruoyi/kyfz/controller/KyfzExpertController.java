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
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.service.IKyfzExpertService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专家管理Controller
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/kyfz/expert")
public class KyfzExpertController extends BaseController
{
    @Autowired
    private IKyfzExpertService kyfzExpertService;

    /**
     * 查询专家管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzExpert kyfzExpert)
    {
        startPage();
        List<KyfzExpert> list = kyfzExpertService.selectKyfzExpertList(kyfzExpert);
        return getDataTable(list);
    }

    /**
     * 导出专家管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:export')")
    @Log(title = "专家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzExpert kyfzExpert)
    {
        List<KyfzExpert> list = kyfzExpertService.selectKyfzExpertList(kyfzExpert);
        ExcelUtil<KyfzExpert> util = new ExcelUtil<KyfzExpert>(KyfzExpert.class);
        util.exportExcel(response, list, "专家管理数据");
    }

    /**
     * 获取专家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:query')")
    @GetMapping(value = "/{expertId}")
    public AjaxResult getInfo(@PathVariable("expertId") Long expertId)
    {
        return success(kyfzExpertService.selectKyfzExpertByExpertId(expertId));
    }

    /**
     * 新增专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:add')")
    @Log(title = "专家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzExpert kyfzExpert)
    {
        return toAjax(kyfzExpertService.insertKyfzExpert(kyfzExpert));
    }

    /**
     * 修改专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:edit')")
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzExpert kyfzExpert)
    {
        return toAjax(kyfzExpertService.updateKyfzExpert(kyfzExpert));
    }

    /**
     * 删除专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:remove')")
    @Log(title = "专家管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{expertIds}")
    public AjaxResult remove(@PathVariable Long[] expertIds)
    {
        return toAjax(kyfzExpertService.deleteKyfzExpertByExpertIds(expertIds));
    }
}
