package com.ruoyi.kyfz.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.domain.KyfzPushRecord;
import com.ruoyi.kyfz.service.IKyfzMatchService;
import com.ruoyi.kyfz.service.IKyfzPushRecordService;

/**
 * 匹配列表Controller
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/kyfz/match")
public class KyfzMatchController extends BaseController {
    @Autowired
    private IKyfzMatchService kyfzMatchService;

    @Autowired
    private IKyfzPushRecordService kyfzPushRecordService;

    /**
     * 查询匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzMatch kyfzMatch) {
        startPage();
        List<KyfzMatch> list = kyfzMatchService.selectKyfzMatchList(kyfzMatch);
        return getDataTable(list);
    }

    /**
     * 导出匹配列表列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:export')")
    @Log(title = "匹配列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzMatch kyfzMatch) {
        List<KyfzMatch> list = kyfzMatchService.selectKyfzMatchList(kyfzMatch);
        ExcelUtil<KyfzMatch> util = new ExcelUtil<KyfzMatch>(KyfzMatch.class);
        util.exportExcel(response, list, "匹配列表数据");
    }

    /**
     * 获取匹配列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:query')")
    @GetMapping(value = "/2/{matchId}")
    public AjaxResult getInfo(@PathVariable("matchId") Long matchId) {
        return success(kyfzMatchService.selectKyfzMatchByMatchId(matchId));
    }

    /**
     * 获取匹配列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:detail')")
    @GetMapping(value = "/{matchIds}")
    public AjaxResult getDetailInfo(@PathVariable("matchIds") Long matchIds) {
        // 先索引出所有信息
        Long matchId = matchIds;

        // KyfzMatch match_0 = kyfzMatchService.selectKyfzMatchByMatchId(matchIds);
        KyfzMatch match1 = kyfzMatchService.selectKyfzMatchDetailByMatchId(matchId);
        // 从索引出的匹配表中获取匹配出来的项目id（多个id用逗号分隔开了）
        String projectIds = match1.getProjectId();

        // 从索引出的匹配表中获取匹配出来的论文id（多个id用逗号分隔开了）
        String thesiseIds = match1.getThesisId();

        // 从索引出的匹配表中获取匹配出来的著作id（多个id用逗号分隔开了）
        String awardIds = match1.getAwardId();

        // 把索引到的所有id用分隔函数分开，存在数组中
        if (projectIds != null && !projectIds.isEmpty()) {
            // 按照得到的项目id，索引出每一个项目的名称，然后项目名称都放进projectNames中
            // String projectNames = "";
            ArrayList strArray = new ArrayList();
            Long projectId[] = extractIds(projectIds);
            for (int i = 0; i < projectId.length; i++) {
                String projectName = kyfzMatchService.selectProjectName(projectId[i]);
                strArray.add(projectName);
                // if (i == projectId.length - 1) {
                // projectNames += projectName;
                // } else {
                // projectNames += projectName + ",";
                // }
            }
            match1.setStrArray(strArray);
        }

        if (thesiseIds != null && !thesiseIds.isEmpty()) {
            // 按照得到的项目id，索引出每一个项目的名称，然后项目名称都放进projectNames中
            String thesisNames = "";
            ArrayList strArray1 = new ArrayList();

            Long thesisId[] = extractIds(thesiseIds);
            for (int i = 0; i < thesisId.length; i++) {
                String thesisName = kyfzMatchService.selectThesisName(thesisId[i]);
                strArray1.add(thesisName);
            }
            match1.setStrArray1(strArray1);
        }

        if (awardIds != null && !awardIds.isEmpty()) {
            // 按照得到的项目id，索引出每一个项目的名称，然后项目名称都放进projectNames中
            String workNames = "";
            ArrayList strArray2 = new ArrayList();

            Long awardId[] = extractIds(awardIds);
            for (int i = 0; i < awardId.length; i++) {
                String workName = kyfzMatchService.selectAwardName(awardId[i]);
                strArray2.add(workName);

            }
            match1.setStrArray2(strArray2);
        }
        // 把projectNames变量传进工具类match1中

        return success(match1);
    }

    /**
     * 推送匹配列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:match:push')")
    @Log(title = "匹配列表", businessType = BusinessType.INSERT)
    @PutMapping("/{matchIds}")
    public AjaxResult push(@PathVariable Long[] matchIds) {
        // TODO: 实现推送逻辑
        // 记录推送后的反馈状态状态（）
        // 获取推送时间
        LocalDateTime pushTime = LocalDateTime.now();
        Date pushTimeDate = Date.from(pushTime.atZone(ZoneId.systemDefault()).toInstant());

        // 构造 KyfzPushRecord 对象列表
        List<KyfzPushRecord> pushRecords = new ArrayList<>();
        for (Long matchId : matchIds) {
            KyfzPushRecord pushRecord = new KyfzPushRecord();
            pushRecord.setMatchId(matchId);
            pushRecord.setPushTime(pushTimeDate);
            pushRecords.add(pushRecord); // 添加到列表中
        }
        // 批量插入推送记录
        // kyfzMatchService.batchInsert(pushRecords);
        kyfzMatchService.updateKyfzMatchByMatchIds_pushState(matchIds);
        return toAjax(kyfzMatchService.batchInsert(pushRecords));
    }

    @Log(title = "匹配列表", businessType = BusinessType.UPDATE)
    @PutMapping("/3/")
    public AjaxResult updateScore(@RequestBody KyfzMatch kyfzMatch) {
        return toAjax(kyfzMatchService.updatePushRecord(kyfzMatch));
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
