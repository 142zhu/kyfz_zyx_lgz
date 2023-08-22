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
import com.ruoyi.kyfz.domain.KyfzAward;
import com.ruoyi.kyfz.service.IKyfzAwardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖项信息Controller
 * 
 * @author zyx
 * @date 2023-08-19
 */
@RestController
@RequestMapping("/kyfz/award")
public class KyfzAwardController extends BaseController
{
    @Autowired
    private IKyfzAwardService kyfzAwardService;

    /**
     * 查询奖项信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzAward kyfzAward)
    {
        startPage();
        List<KyfzAward> list = kyfzAwardService.selectKyfzAwardList(kyfzAward);
        return getDataTable(list);
    }

    /**
     * 导出奖项信息列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:export')")
    @Log(title = "奖项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzAward kyfzAward)
    {
        List<KyfzAward> list = kyfzAwardService.selectKyfzAwardList(kyfzAward);
        ExcelUtil<KyfzAward> util = new ExcelUtil<KyfzAward>(KyfzAward.class);
        util.exportExcel(response, list, "奖项信息数据");
    }

    /**
     * 获取奖项信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:query')")
    @GetMapping(value = "/{awardId}")
    public AjaxResult getInfo(@PathVariable("awardId") Long awardId)
    {
        return success(kyfzAwardService.selectKyfzAwardByAwardId(awardId));
    }

    /**
     * 新增奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:add')")
    @Log(title = "奖项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzAward kyfzAward)
    {
        return toAjax(kyfzAwardService.insertKyfzAward(kyfzAward));
    }

    /**
     * 修改奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:edit')")
    @Log(title = "奖项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzAward kyfzAward)
    {
        return toAjax(kyfzAwardService.updateKyfzAward(kyfzAward));
    }

    /**
     * 删除奖项信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:award:remove')")
    @Log(title = "奖项信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{awardIds}")
    public AjaxResult remove(@PathVariable Long[] awardIds)
    {
        return toAjax(kyfzAwardService.deleteKyfzAwardByAwardIds(awardIds));
    }
}
