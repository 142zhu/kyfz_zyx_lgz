package com.ruoyi.kyfz.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.domain.KyfzRequirement;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

/**
 * 需求管理Controller
 *
 * @author ruoyi
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/kyfz/requirement")
public class KyfzRequirementController extends BaseController {
    @Autowired
    private IKyfzRequirementService kyfzRequirementService;

    /**
     * 查询需求管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzRequirement kyfzRequirement) {
        startPage();
        List<KyfzRequirement> list = kyfzRequirementService.selectKyfzRequirementList(kyfzRequirement);
        return getDataTable(list);
    }

    /**
     * 导出需求管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:export')")
    @Log(title = "需求管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzRequirement kyfzRequirement) {
        List<KyfzRequirement> list = kyfzRequirementService.selectKyfzRequirementList(kyfzRequirement);
        ExcelUtil<KyfzRequirement> util = new ExcelUtil<KyfzRequirement>(KyfzRequirement.class);
        util.exportExcel(response, list, "需求管理数据");
    }

    /**
     * 获取需求管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:query')")
    @GetMapping(value = "/{requirementId}")
    public AjaxResult getInfo(@PathVariable("requirementId") Long requirementId) {
        return success(kyfzRequirementService.selectKyfzRequirementByRequirementId(requirementId));
    }

    /**
     * 新增需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:add')")
    @Log(title = "需求管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.insertKyfzRequirement(kyfzRequirement));
    }

    /**
     * 修改需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:edit')")
    @Log(title = "需求管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.updateKyfzRequirement(kyfzRequirement));
    }

    /**
     * 删除需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:remove')")
    @Log(title = "需求管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{requirementIds}")
    public AjaxResult remove(@PathVariable Long[] requirementIds) {
        return toAjax(kyfzRequirementService.deleteKyfzRequirementByRequirementIds(requirementIds));
    }

    /**
     * 查询需求管理中企业列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:listenterprise')")
    @GetMapping("/listenterprise")
    public TableDataInfo listenterprise(KyfzEnterprise kyfzEnterprise) {
        // startPage();
        List<KyfzEnterprise> list = kyfzRequirementService.selectKyfzEnterpriseList(kyfzEnterprise);
        return getDataTable(list);
    }

    @PostMapping("/handleMatch")
    public AjaxResult handleMatch(String requirementId) {
        // return toAjax(kyfzMatchService.updatePushRecord(kyfzMatch));
        // String url = "http://127.0.0.1:8083";
        String url = "http://172.18.166.90:6666/infer";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("OtherHeadersxxx", "xxxx"); // Other headers
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("requirement_id", requirementId);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
        String body = responseEntity.getBody();
        // TODO:更新需求匹配状态
        Long requirementIds = Long.parseLong(requirementId);
        int s = kyfzRequirementService.updateKyfzRequirementStatusById(requirementIds);

        System.out.println("responseBody:" + body);
        return "-1".equals(body) ? AjaxResult.error() : AjaxResult.success(s);
    }

    /**
     * 查询暂时的需求管理列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:list')")
    @GetMapping("/list_staging")
    public TableDataInfo list_staging(KyfzRequirement kyfzRequirement) {
        startPage();
        List<KyfzRequirement> list = kyfzRequirementService.selectKyfzRequirementList_staging(kyfzRequirement);
        return getDataTable(list);
    }

    /**
     * 新增暂时的需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:add')")
    @Log(title = "需求管理", businessType = BusinessType.INSERT)
    @PostMapping("/add_staging")
    public AjaxResult add_staging(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.insertKyfzRequirement_staging(kyfzRequirement));
    }

    /**
     * 修改暂时的需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:edit')")
    @Log(title = "需求管理", businessType = BusinessType.UPDATE)
    @PutMapping("/eidt_staging")
    public AjaxResult eidt_staging(@RequestBody KyfzRequirement kyfzRequirement) {
        return toAjax(kyfzRequirementService.updateKyfzRequirement_staging(kyfzRequirement));
    }

    /**
     * 删除暂时的需求管理
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:remove')")
    @Log(title = "需求管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/del_staging/{requirementIds}")
    public AjaxResult remove_staging(@PathVariable Long[] requirementIds) {
        return toAjax(kyfzRequirementService.deleteKyfzRequirementByRequirementIds_staging(requirementIds));
    }


    /**
     * 获取暂时的需求管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:requirement:query')")
    @GetMapping(value = "/get_staging/{requirementId}")
    public AjaxResult getInfo_staging(@PathVariable("requirementId") Long requirementId) {
        return success(kyfzRequirementService.selectKyfzRequirementByRequirementId_staging(requirementId));
    }
}
