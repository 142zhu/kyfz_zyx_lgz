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
import com.ruoyi.kyfz.domain.TTsjl;
import com.ruoyi.kyfz.service.ITTsjlService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推送记录Controller
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/kyfz/tsjl")
public class TTsjlController extends BaseController
{
    @Autowired
    private ITTsjlService tTsjlService;

    /**
     * 查询推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTsjl tTsjl)
    {
        startPage();
        List<TTsjl> list = tTsjlService.selectTTsjlList(tTsjl);
        return getDataTable(list);
    }

    /**
     * 导出推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:export')")
    @Log(title = "推送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTsjl tTsjl)
    {
        List<TTsjl> list = tTsjlService.selectTTsjlList(tTsjl);
        ExcelUtil<TTsjl> util = new ExcelUtil<TTsjl>(TTsjl.class);
        util.exportExcel(response, list, "推送记录数据");
    }

    /**
     * 获取推送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tTsjlService.selectTTsjlById(id));
    }

    /**
     * 新增推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:add')")
    @Log(title = "推送记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTsjl tTsjl)
    {
        return toAjax(tTsjlService.insertTTsjl(tTsjl));
    }

    /**
     * 修改推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:edit')")
    @Log(title = "推送记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTsjl tTsjl)
    {
        return toAjax(tTsjlService.updateTTsjl(tTsjl));
    }

    /**
     * 删除推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:tsjl:remove')")
    @Log(title = "推送记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tTsjlService.deleteTTsjlByIds(ids));
    }
}
