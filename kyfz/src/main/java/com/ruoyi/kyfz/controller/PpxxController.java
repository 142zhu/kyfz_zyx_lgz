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
import com.ruoyi.kyfz.domain.Ppxx;
import com.ruoyi.kyfz.service.IPpxxService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 匹配列表Controller
 * 
 * @author zhu
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/kyfz/Ppxx")
public class PpxxController extends BaseController
{
    @Autowired
    private IPpxxService ppxxService;

    /**
     * 查询匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:list')")
    @GetMapping("/list")
    public TableDataInfo list(Ppxx ppxx)
    {
        startPage();
        List<Ppxx> list = ppxxService.selectPpxxList(ppxx);
        return getDataTable(list);
    }

    /**
     * 导出匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:export')")
    @Log(title = "匹配列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Ppxx ppxx)
    {
        List<Ppxx> list = ppxxService.selectPpxxList(ppxx);
        ExcelUtil<Ppxx> util = new ExcelUtil<Ppxx>(Ppxx.class);
        util.exportExcel(response, list, "匹配列表数据");
    }

    /**
     * 获取匹配列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ppxxService.selectPpxxById(id));
    }

    /**
     * 新增匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:add')")
    @Log(title = "匹配列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Ppxx ppxx)
    {
        return toAjax(ppxxService.insertPpxx(ppxx));
    }

    /**
     * 修改匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:edit')")
    @Log(title = "匹配列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Ppxx ppxx)
    {
        return toAjax(ppxxService.updatePpxx(ppxx));
    }

    /**
     * 删除匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:Ppxx:remove')")
    @Log(title = "匹配列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ppxxService.deletePpxxByIds(ids));
    }
}
