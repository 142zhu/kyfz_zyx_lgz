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
import com.ruoyi.kyfz.domain.KyfzSearch;
import com.ruoyi.kyfz.service.IKyfzSearchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检索Controller
 * 
 * @author zyx
 * @date 2023-07-05
 */
@RestController
@RequestMapping("/kyfz/search")
public class KyfzSearchController extends BaseController
{
    @Autowired
    private IKyfzSearchService kyfzSearchService;

    /**
     * 查询检索列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzSearch kyfzSearch)
    {
        startPage();
        List<KyfzSearch> list = kyfzSearchService.selectKyfzSearchList(kyfzSearch);
        return getDataTable(list);
    }

    /**
     * 导出检索列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:export')")
    @Log(title = "检索", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzSearch kyfzSearch)
    {
        List<KyfzSearch> list = kyfzSearchService.selectKyfzSearchList(kyfzSearch);
        ExcelUtil<KyfzSearch> util = new ExcelUtil<KyfzSearch>(KyfzSearch.class);
        util.exportExcel(response, list, "检索数据");
    }

    /**
     * 获取检索详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:query')")
    @GetMapping(value = "/{searchId}")
    public AjaxResult getInfo(@PathVariable("searchId") Long searchId)
    {
        return success(kyfzSearchService.selectKyfzSearchBySearchId(searchId));
    }

    /**
     * 新增检索
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:add')")
    @Log(title = "检索", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzSearch kyfzSearch)
    {
        return toAjax(kyfzSearchService.insertKyfzSearch(kyfzSearch));
    }

    /**
     * 修改检索
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:edit')")
    @Log(title = "检索", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzSearch kyfzSearch)
    {
        return toAjax(kyfzSearchService.updateKyfzSearch(kyfzSearch));
    }

    /**
     * 删除检索
     */
    @PreAuthorize("@ss.hasPermi('kyfz:search:remove')")
    @Log(title = "检索", businessType = BusinessType.DELETE)
	@DeleteMapping("/{searchIds}")
    public AjaxResult remove(@PathVariable Long[] searchIds)
    {
        return toAjax(kyfzSearchService.deleteKyfzSearchBySearchIds(searchIds));
    }
}
