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
import com.ruoyi.kyfz.domain.TQyxq;
import com.ruoyi.kyfz.service.ITQyxqService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业需求信息Controller
 * 
 * @author zhu
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/kyfz/qyxq")
public class TQyxqController extends BaseController
{
    @Autowired
    private ITQyxqService tQyxqService;

    /**
     * 查询企业需求信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:list')")
    @GetMapping("/list")
    public TableDataInfo list(TQyxq tQyxq)
    {
        startPage();
        List<TQyxq> list = tQyxqService.selectTQyxqList(tQyxq);
        return getDataTable(list);
    }

    /**
     * 导出企业需求信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:export')")
    @Log(title = "企业需求信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TQyxq tQyxq)
    {
        List<TQyxq> list = tQyxqService.selectTQyxqList(tQyxq);
        ExcelUtil<TQyxq> util = new ExcelUtil<TQyxq>(TQyxq.class);
        util.exportExcel(response, list, "企业需求信息数据");
    }

    /**
     * 获取企业需求信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tQyxqService.selectTQyxqById(id));
    }

    /**
     * 新增企业需求信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:add')")
    @Log(title = "企业需求信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TQyxq tQyxq)
    {
        return toAjax(tQyxqService.insertTQyxq(tQyxq));
    }

    /**
     * 修改企业需求信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:edit')")
    @Log(title = "企业需求信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TQyxq tQyxq)
    {
        return toAjax(tQyxqService.updateTQyxq(tQyxq));
    }

    /**
     * 删除企业需求信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:qyxq:remove')")
    @Log(title = "企业需求信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tQyxqService.deleteTQyxqByIds(ids));
    }
}
