package com.ruoyi.kyfz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.kyfz.domain.KyfzAward;
import com.ruoyi.kyfz.domain.KyfzCertificate;
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.domain.KyfzIntellectualProperty;
import com.ruoyi.kyfz.domain.KyfzProject;
import com.ruoyi.kyfz.domain.KyfzTeam;
import com.ruoyi.kyfz.domain.KyfzThesis;
import com.ruoyi.kyfz.domain.KyfzWork;
import com.ruoyi.kyfz.service.IKyfzAwardService;
import com.ruoyi.kyfz.service.IKyfzExpertService;
import com.ruoyi.kyfz.service.IKyfzIntellectualPropertyService;
import com.ruoyi.kyfz.service.IKyfzMatchService;
import com.ruoyi.kyfz.service.IKyfzProjectService;
import com.ruoyi.kyfz.service.IKyfzThesisService;

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

    @Autowired
    private IKyfzProjectService iKyfzProjectService;

    @Autowired
    private IKyfzThesisService iKyfzThesisService;

    @Autowired
    private IKyfzAwardService kyfzAwardService;

    @Autowired
    private IKyfzIntellectualPropertyService kyfzIntellectualPropertyService;

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
    // @PreAuthorize("@ss.hasPermi('kyfz:expert:detail')")
    @GetMapping(value = "/detail/{expertId}")
    public AjaxResult getDetailInfo(@PathVariable("expertId") Long expertId) {
        KyfzExpert expert = kyfzExpertService.selectKyfzExpertByExpertId(expertId);
        // 获取所有的id号码
        String thesisIds = expert.getThesisId();
        String projectIds = expert.getProjectId();
        String awardsIds = expert.getAwardsId();
        String intellectualPropertyIds = expert.getIntellectualPropertyId();
        if (projectIds != null && !projectIds.isEmpty()) {
            ArrayList<KyfzProject> projectArray = new ArrayList<KyfzProject>();
            Long projectId[] = extractIds(projectIds);
            projectArray = (ArrayList<KyfzProject>) iKyfzProjectService.selectKyfzProjectList_Projectidlist(projectId);
            expert.setProjectArray(projectArray);
        }

        if (thesisIds != null && !thesisIds.isEmpty()) {
            ArrayList<KyfzThesis> thesisArray = new ArrayList<KyfzThesis>();
            Long thesisId[] = extractIds(thesisIds);
            thesisArray = (ArrayList<KyfzThesis>) iKyfzThesisService.selectKyfzThesisList_Thesisidlist(thesisId);
            expert.setThesisArray(thesisArray);
        }

        if (awardsIds != null && !awardsIds.isEmpty()) {
            ArrayList<KyfzAward> awardArray = new ArrayList<KyfzAward>();
            Long awardId[] = extractIds(awardsIds);
            awardArray = (ArrayList<KyfzAward>) kyfzAwardService.selectKyfzAwardsList_AwardsIdlist(awardId);
            expert.setAwardArray(awardArray);
        }

        if (intellectualPropertyIds != null && !intellectualPropertyIds.isEmpty()) {
            ArrayList<KyfzIntellectualProperty> intellectualPropertArray = new ArrayList<KyfzIntellectualProperty>();
            Long intellectualPropertyId[] = extractIds(intellectualPropertyIds);
            intellectualPropertArray = (ArrayList<KyfzIntellectualProperty>) kyfzIntellectualPropertyService
                    .selectKyfzPropertyList_PropertyIdlist(intellectualPropertyId);
            expert.setIntellectualPropertArray(intellectualPropertArray);
        }
        return success(expert);
    }

    /**
     * 获取专家详细信息
     */
    @PutMapping("/detailByAccount")
    public AjaxResult getDetailByAccountInfo(@RequestBody KyfzExpert kyfzExpert) {
        // 1.索引专家的所有信息
        // 2.索引匹配表的人工标准信息
        String markProject = kyfzExpert.getMarkProject();
        String markThesis = kyfzExpert.getMarkThesis();
        String markWork = kyfzExpert.getMarkWork();
        String markCertificate = kyfzExpert.getMarkCertificate();
        KyfzExpert expert = kyfzExpertService.selectKyfzExpertByExpertAccount(kyfzExpert.getExpertAccount());

        // 专家对应的所有项目、论文、著作、证书id
        String thesisIds = expert.getThesisId();
        String projectIds = expert.getProjectId();
        String awardsIds = expert.getWorkId();
        String intellectualPropertyIds = expert.getCertificateId();
        // 人工标注的id

        if (projectIds != null && !projectIds.isEmpty()) {
            List<KyfzProject> projectList = new ArrayList<KyfzProject>();
            Long projectId[] = extractIds(projectIds);
            for (int i = 0; i < projectId.length; i++) {
                String projectName = kyfzMatchService.selectProjectName(projectId[i]);
                KyfzProject project = new KyfzProject();
                project.setProjectId(projectId[i]);
                project.setProjectName(projectName);
                projectList.add(project);
            }
            expert.setProjectList(projectList);
        }

        if (thesisIds != null && !thesisIds.isEmpty()) {
            System.out.println(thesisIds);
            List<KyfzThesis> thesisList = new ArrayList<KyfzThesis>();
            Long thesisId[] = extractIds(thesisIds);
            for (int i = 0; i < thesisId.length; i++) {
                String thesisName = kyfzMatchService.selectThesisName(thesisId[i]);
                KyfzThesis thesis = new KyfzThesis();
                thesis.setThesisId(thesisId[i]);
                thesis.setThesisName(thesisName);
                thesisList.add(thesis);
            }
            expert.setThesisList(thesisList);
        }

        if (awardsIds != null && !awardsIds.isEmpty()) {
            List<KyfzWork> workList = new ArrayList<KyfzWork>();

            Long workId[] = extractIds(awardsIds);
            for (int i = 0; i < workId.length; i++) {
                String workName = kyfzMatchService.selectAwardName(workId[i]);
                KyfzWork work = new KyfzWork();
                work.setWorkId(workId[i]);
                work.setWorkName(workName);
                workList.add(work);
            }
            expert.setWorkList(workList);
        }

        if (intellectualPropertyIds != null && !intellectualPropertyIds.isEmpty()) {
            List<KyfzCertificate> certificateList = new ArrayList<KyfzCertificate>();
            Long certificateId[] = extractIds(intellectualPropertyIds);
            for (int i = 0; i < certificateId.length; i++) {
                String certificateName = kyfzMatchService.selectCertificateName(certificateId[i]);
                KyfzCertificate certificate = new KyfzCertificate();
                certificate.setCertificateId(certificateId[i]);
                certificate.setCertificateName(certificateName);
                certificateList.add(certificate);
            }
            expert.setCertificateList(certificateList);
        }
        /*********************** */
        if (markProject != null && !markProject.isEmpty()) {
            Long markProjectId[] = extractIds(markProject);
            expert.setMarkProjectId(markProjectId);
        }

        if (markThesis != null && !markThesis.isEmpty()) {
            Long markThesisId[] = extractIds(markThesis);
            expert.setMarkThesisId(markThesisId);
        }

        if (markWork != null && !markWork.isEmpty()) {
            Long markWorkId[] = extractIds(markWork);
            expert.setMarkWorkId(markWorkId);
        }

        if (markCertificate != null && !markCertificate.isEmpty()) {
            Long markProjmarkCertificateId[] = extractIds(markCertificate);
            expert.setMarkCertificateId(markProjmarkCertificateId);
        }
        return success(expert);
    }

    /**
     * 修改专家管理
     */
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateMarkProject")
    public AjaxResult updateMarkProject(@RequestBody KyfzExpert kyfzExpert) {
        kyfzExpert.setMarkProject(kyfzExpert.getProjectId());
        if (kyfzExpert.isDeleteBool()) {
            return toAjax(kyfzExpertService.deleteMarkProject(kyfzExpert));
        } else {
            return toAjax(kyfzExpertService.updateMarkProject(kyfzExpert));
        }

    }

    /**
     * 修改专家管理
     */
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateMarkThesis")
    public AjaxResult updateMarkThesis(@RequestBody KyfzExpert kyfzExpert) {
        kyfzExpert.setMarkThesis(kyfzExpert.getThesisId());
        if (kyfzExpert.isDeleteBool()) {
            return toAjax(kyfzExpertService.deleteMarkThesis(kyfzExpert));
        } else {
            return toAjax(kyfzExpertService.updateMarkThesis(kyfzExpert));
        }
    }

    /**
     * 修改专家管理
     */
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateMarkWork")
    public AjaxResult updateMarkWork(@RequestBody KyfzExpert kyfzExpert) {
        kyfzExpert.setMarkWork(kyfzExpert.getWorkId());
        if (kyfzExpert.isDeleteBool()) {
            return toAjax(kyfzExpertService.deleteMarkWork(kyfzExpert));
        } else {
            return toAjax(kyfzExpertService.updateMarkWork(kyfzExpert));
        }
    }

    /**
     * 修改专家管理
     */
    @Log(title = "专家管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateMarkCertificate")
    public AjaxResult updateMarkCertificate(@RequestBody KyfzExpert kyfzExpert) {
        kyfzExpert.setMarkCertificate(kyfzExpert.getCertificateId());
        if (kyfzExpert.isDeleteBool()) {
            return toAjax(kyfzExpertService.deleteMarkCertificate(kyfzExpert));
        } else {
            return toAjax(kyfzExpertService.updateMarkCertificate(kyfzExpert));
        }
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

    /**
     * 获取专家关系图数据
     */
    @GetMapping(value = "/getEchartData/{expertId}")
    public AjaxResult getEchartExpertData(@PathVariable("expertId") Long expertId) {
        return success(kyfzExpertService.getEchartExpertData(expertId));
    }

    /**
     * 获取专家关系图数据
     */
    @PostMapping(value = "/getEchartData_2")
    public AjaxResult getEchartExpertData2(@RequestBody KyfzTeam kyfzTeam) {
        return success(kyfzExpertService.getEchartExpertData2(kyfzTeam.getTeamId()));
    }

}
