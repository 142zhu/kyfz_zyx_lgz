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
import com.ruoyi.kyfz.domain.KyfzPushRecord;
import com.ruoyi.kyfz.service.IKyfzPushRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推送记录Controller
 * 
 * @author zhu
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/kyfz/record")
public class KyfzPushRecordController extends BaseController {
    @Autowired
    private IKyfzPushRecordService kyfzPushRecordService;

    /**
     * 查询推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzPushRecord kyfzPushRecord) {
        startPage();
        List<KyfzPushRecord> list = kyfzPushRecordService.selectKyfzPushRecordList(kyfzPushRecord);
        return getDataTable(list);
    }

    /**
     * 导出推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:export')")
    @Log(title = "推送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzPushRecord kyfzPushRecord) {
        List<KyfzPushRecord> list = kyfzPushRecordService.selectKyfzPushRecordList(kyfzPushRecord);
        ExcelUtil<KyfzPushRecord> util = new ExcelUtil<KyfzPushRecord>(KyfzPushRecord.class);
        util.exportExcel(response, list, "推送记录数据");
    }

    /**
     * 获取推送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:query')")
    @GetMapping(value = "/{pushId}")
    public AjaxResult getInfo(@PathVariable("pushId") Long pushId) {
        return success(kyfzPushRecordService.selectKyfzPushRecordByPushId(pushId));
    }

    /**
     * 新增推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:add')")
    @Log(title = "推送记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzPushRecord kyfzPushRecord) {
        return toAjax(kyfzPushRecordService.insertKyfzPushRecord(kyfzPushRecord));
    }

    /**
     * 修改推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:edit')")
    @Log(title = "推送记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzPushRecord kyfzPushRecord) {
        return toAjax(kyfzPushRecordService.updateKyfzPushRecord(kyfzPushRecord));
    }

    /**
     * 删除推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:record:remove')")
    @Log(title = "推送记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pushIds}")
    public AjaxResult remove(@PathVariable Long[] pushIds) {
        return toAjax(kyfzPushRecordService.deleteKyfzPushRecordByPushIds(pushIds));
    }
}
