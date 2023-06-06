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
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.service.IKyfzEnterpriseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推送记录Controller
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/kyfz/push_record")
public class KyfzEnterpriseController extends BaseController
{
    @Autowired
    private IKyfzEnterpriseService kyfzEnterpriseService;

    /**
     * 查询推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzEnterprise kyfzEnterprise)
    {
        startPage();
        List<KyfzEnterprise> list = kyfzEnterpriseService.selectKyfzEnterpriseList(kyfzEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:export')")
    @Log(title = "推送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzEnterprise kyfzEnterprise)
    {
        List<KyfzEnterprise> list = kyfzEnterpriseService.selectKyfzEnterpriseList(kyfzEnterprise);
        ExcelUtil<KyfzEnterprise> util = new ExcelUtil<KyfzEnterprise>(KyfzEnterprise.class);
        util.exportExcel(response, list, "推送记录数据");
    }

    /**
     * 获取推送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:query')")
    @GetMapping(value = "/{enterpriseId}")
    public AjaxResult getInfo(@PathVariable("enterpriseId") Long enterpriseId)
    {
        return success(kyfzEnterpriseService.selectKyfzEnterpriseByEnterpriseId(enterpriseId));
    }

    /**
     * 新增推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:add')")
    @Log(title = "推送记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzEnterprise kyfzEnterprise)
    {
        return toAjax(kyfzEnterpriseService.insertKyfzEnterprise(kyfzEnterprise));
    }

    /**
     * 修改推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:edit')")
    @Log(title = "推送记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzEnterprise kyfzEnterprise)
    {
        return toAjax(kyfzEnterpriseService.updateKyfzEnterprise(kyfzEnterprise));
    }

    /**
     * 删除推送记录
     */
    @PreAuthorize("@ss.hasPermi('kyfz:push_record:remove')")
    @Log(title = "推送记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIds}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIds)
    {
        return toAjax(kyfzEnterpriseService.deleteKyfzEnterpriseByEnterpriseIds(enterpriseIds));
    }
}
