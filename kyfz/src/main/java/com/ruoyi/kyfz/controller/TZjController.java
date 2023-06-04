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
import com.ruoyi.kyfz.domain.TZj;
import com.ruoyi.kyfz.service.ITZjService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专家信息Controller
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/kyfz/zjxx")
public class TZjController extends BaseController
{
    @Autowired
    private ITZjService tZjService;

    /**
     * 查询专家信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:list')")
    @GetMapping("/list")
    public TableDataInfo list(TZj tZj)
    {
        startPage();
        List<TZj> list = tZjService.selectTZjList(tZj);
        return getDataTable(list);
    }

    /**
     * 导出专家信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:export')")
    @Log(title = "专家信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TZj tZj)
    {
        List<TZj> list = tZjService.selectTZjList(tZj);
        ExcelUtil<TZj> util = new ExcelUtil<TZj>(TZj.class);
        util.exportExcel(response, list, "专家信息数据");
    }

    /**
     * 获取专家信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tZjService.selectTZjById(id));
    }

    /**
     * 新增专家信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:add')")
    @Log(title = "专家信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TZj tZj)
    {
        return toAjax(tZjService.insertTZj(tZj));
    }

    /**
     * 修改专家信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:edit')")
    @Log(title = "专家信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TZj tZj)
    {
        return toAjax(tZjService.updateTZj(tZj));
    }

    /**
     * 删除专家信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:zjxx:remove')")
    @Log(title = "专家信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tZjService.deleteTZjByIds(ids));
    }
}
