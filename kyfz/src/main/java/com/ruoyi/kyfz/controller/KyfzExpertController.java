package com.ruoyi.kyfz.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.service.IKyfzExpertService;
import com.ruoyi.kyfz.service.IKyfzMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 专家管理Controller
 *
 * @author ruoyi
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/kyfz/expert")
public class KyfzExpertController extends BaseController {
    @Autowired
    private IKyfzExpertService kyfzExpertService;

    @Autowired
    private IKyfzMatchService kyfzMatchService;

    /**
     * 查询专家管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzExpert kyfzExpert) {
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
    public void export(HttpServletResponse response, KyfzExpert kyfzExpert) {
        List<KyfzExpert> list = kyfzExpertService.selectKyfzExpertList(kyfzExpert);
        ExcelUtil<KyfzExpert> util = new ExcelUtil<KyfzExpert>(KyfzExpert.class);
        util.exportExcel(response, list, "专家管理数据");
    }

    /**
     * 获取专家管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:query')")
    @GetMapping(value = "/{expertId}")
    public AjaxResult getInfo(@PathVariable("expertId") Long expertId) {
        return success(kyfzExpertService.selectKyfzExpertByExpertId(expertId));
    }

    /**
     * 新增专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:add')")
    @Log(title = "专家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzExpert kyfzExpert) {
        return toAjax(kyfzExpertService.insertKyfzExpert(kyfzExpert));
    }

    /**
     * 修改专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:edit')")
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzExpert kyfzExpert) {
        return toAjax(kyfzExpertService.updateKyfzExpert(kyfzExpert));
    }

    /**
     * 删除专家管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:expert:remove')")
    @Log(title = "专家管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{expertIds}")
    public AjaxResult remove(@PathVariable Long[] expertIds) {
        return toAjax(kyfzExpertService.deleteKyfzExpertByExpertIds(expertIds));
    }

    /**
     * 获取专家详细信息
     */
//    @PreAuthorize("@ss.hasPermi('kyfz:expert:detail')")
    @GetMapping(value = "/detail/{expertId}")
    public AjaxResult getDetailInfo(@PathVariable("expertId") Long expertId) {
        KyfzExpert expert = kyfzExpertService.selectKyfzExpertByExpertId(expertId);
        // 获取所有的id号码
        String thesisIds = expert.getThesisId();
        String projectIds = expert.getProjectId();
        String workIds = expert.getWorkId();
        String certificateIds = expert.getCertificateId();

        if (projectIds != null && !projectIds.isEmpty()) {
            ArrayList projectArray = new ArrayList();
            Long projectId[] = extractIds(projectIds);
            for (int i = 0; i < projectId.length; i++) {
                String projectName = kyfzMatchService.selectProjectName(projectId[i]);
                projectArray.add(projectName);
            }
            expert.setProjectArray(projectArray);
        }

        if (thesisIds != null && !thesisIds.isEmpty()) {
            ArrayList thesisArray = new ArrayList();
            Long thesisId[] = extractIds(thesisIds);
            for (int i = 0; i < thesisId.length; i++) {
                String thesisName = kyfzMatchService.selectThesisName(thesisId[i]);
                thesisArray.add(thesisName);
            }
            expert.setThesisArray(thesisArray);
        }

        if (workIds != null && !workIds.isEmpty()) {
            ArrayList workArray = new ArrayList();
            Long workId[] = extractIds(workIds);
            for (int i = 0; i < workId.length; i++) {
                String workName = kyfzMatchService.selectWorkName(workId[i]);
                workArray.add(workName);
            }
            expert.setWorkArray(workArray);
        }

        if (certificateIds != null && !certificateIds.isEmpty()) {
            ArrayList certificateArray = new ArrayList();
            Long certificateId[] = extractIds(certificateIds);
            for (int i = 0; i < certificateId.length; i++) {
                String certificateName = kyfzMatchService.selectCertificateName(certificateId[i]);
                certificateArray.add(certificateName);
            }
            expert.setWorkArray(certificateArray);
        }

        return success(expert);
    }

    /************ 工具方法获取String projectId中的所有方法然后用Long 数组存起来 */
    public Long[] extractIds(String projectId) {
        String[] projectIds = projectId.split("[,，、]");
        Long[] Id = new Long[projectIds.length];

        for (int i = 0; i < projectIds.length; i++) {
            Id[i] = Long.parseLong(projectIds[i].trim());
        }
        return Id;
    }

}
