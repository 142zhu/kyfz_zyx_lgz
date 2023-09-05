package com.ruoyi.kyfz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.domain.KyfzPushRecord;
import com.ruoyi.kyfz.domain.jsonMarch;
import com.ruoyi.kyfz.mapper.KyfzMatchMapper;
import com.ruoyi.kyfz.service.IKyfzExpertService;
import com.ruoyi.kyfz.service.IKyfzMatchService;

/**
 * 匹配列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
@Service
public class KyfzMatchServiceImpl implements IKyfzMatchService {
    @Autowired
    private KyfzMatchMapper kyfzMatchMapper;

    @Autowired
    private IKyfzExpertService kyfzExpertService;

    /**
     * 查询匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public KyfzMatch selectKyfzMatchByMatchId(Long matchId) {
        return kyfzMatchMapper.selectKyfzMatchByMatchId(matchId);
    }

    /**
     * 查询匹配列表列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 匹配列表
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<KyfzMatch> selectKyfzMatchList(KyfzMatch kyfzMatch) {
        return kyfzMatchMapper.selectKyfzMatchList(kyfzMatch);
    }

    /**
     * 新增匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    @Override
    public int insertKyfzMatch(KyfzMatch kyfzMatch) {
        kyfzMatch.setCreateTime(DateUtils.getNowDate());
        kyfzMatch.setCreateBy(SecurityUtils.getUsername());
        kyfzMatch.setUserId(SecurityUtils.getUserId());
        return kyfzMatchMapper.insertKyfzMatch(kyfzMatch);
    }

    /**
     * 修改匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    @Override
    public int updateKyfzMatch(KyfzMatch kyfzMatch) {
        kyfzMatch.setUpdateTime(DateUtils.getNowDate());
        kyfzMatch.setUpdateBy(SecurityUtils.getUsername());
        return kyfzMatchMapper.updateKyfzMatch(kyfzMatch);
    }

    /**
     * 批量删除匹配列表
     * 
     * @param matchIds 需要删除的匹配列表主键
     * @return 结果
     */
    @Override
    public int deleteKyfzMatchByMatchIds(Long[] matchIds) {
        return kyfzMatchMapper.deleteKyfzMatchByMatchIds(matchIds);
    }

    /**
     * 删除匹配列表信息
     * 
     * @param matchId 匹配列表主键
     * @return 结果
     */
    @Override
    public int deleteKyfzMatchByMatchId(Long matchId) {
        return kyfzMatchMapper.deleteKyfzMatchByMatchId(matchId);
    }

    /**************************************
     * 查询匹配详细列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public KyfzMatch selectKyfzMatchDetailByMatchId(Long matchId) {
        return kyfzMatchMapper.selectKyfzMatchDetailByMatchId(matchId);
    }

    /***************************************************
     * 查询项目名称
     * 
     * @param projectId 项目表主键
     * @return 项目名称
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public String selectProjectName(Long projectId) {
        return kyfzMatchMapper.selectProjectName(projectId);
    }

    /***************************************************
     * 查询论文名称
     * 
     * @param thesisId 论文表主键
     * @return 论文名称
     */
    public String selectThesisName(Long thesisId) {
        return kyfzMatchMapper.selectThesisName(thesisId);
    }

    /***************************************************
     * 查询著作名称
     * 
     * @param Id 著作表主键
     * @return 著作名称
     */
    public String selectAwardName(Long workId) {
        return kyfzMatchMapper.selectAwardName(workId);
    }

    /***************************************************
     * 查询证书名称
     * 
     * @param Id 证书表主键
     * @return 证书名称
     */
    public String selectCertificateName(Long certificateId) {
        return kyfzMatchMapper.selectCertificateName(certificateId);
    }

    /***************************************************
     * 添加推送记录
     * 
     * @param pushRecords 推送记录list（多条或1条）
     * @return 结果
     */
    public int batchInsert(List<KyfzPushRecord> pushRecords) {
        return kyfzMatchMapper.batchInsert(pushRecords);
    }

    /**
     * 修改推送记录（评分）
     * 
     * @param kyfzMatch 推送记录列表
     * @return 结果
     */
    public int updatePushRecord2(Long matchId) {
        return kyfzMatchMapper.updatePushRecord2(matchId);
    }

    /**
     * 修改推送记录（评分）
     * 
     * @param kyfzMatch 推送记录列表
     * @return 结果
     */
    public int updatePushRecord(KyfzMatch kyfzMatch) {
        return kyfzMatchMapper.updatePushRecord(kyfzMatch);
    }

    public int updateKyfzMatchByMatchIds_pushState(Long[] matchIds) {
        return kyfzMatchMapper.updateKyfzMatchByMatchIds_pushState(matchIds);
    }

    /**
     * 匹配api返回的json数据写入数据库
     * 
     */
    public List<KyfzMatch> insert_json_KyfzMatch(String jsonData, String requirementId) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<KyfzMatch> kyfzMatchList = new ArrayList<>();
        try {
            List<List<jsonMarch>> json_match_lists = objectMapper.readValue(jsonData,
                    new TypeReference<List<List<jsonMarch>>>() {
                    });
            List<jsonMarch> json_match = json_match_lists.get(0);
            for (int i = 0; i < json_match.size(); i++) {
                KyfzMatch kyfzMatch = new KyfzMatch();
                // 写入数据信息
                // kyfzMatch.setAwardId(json_match.get(i).getAward_id().stream().collect(Collectors.joining("、")));
                kyfzMatch.setIntellectualPropertyId(
                        json_match.get(i).getIntellectual_property_id().stream().collect(Collectors.joining("、")));
                kyfzMatch
                        .setProjectId(json_match.get(i).getProject_id().stream().collect(Collectors.joining("、")));
                kyfzMatch
                        .setThesisId(json_match.get(i).getThesis_id().stream().collect(Collectors.joining("、")));
                kyfzMatch
                        .setOtherResultId(
                                json_match.get(i).getOther_result_id().stream().collect(Collectors.joining("、")));
                kyfzMatch.setExpertAccount(json_match.get(i).getExpert_account());
                KyfzExpert kyfzExpert = kyfzExpertService.selectKyfzExpertByExpertAccount(kyfzMatch.getExpertAccount());
                kyfzMatch.setMatchScore(json_match.get(i).getMatch_score());
                kyfzMatch.setExpertName(kyfzExpert.getExpertName());
                kyfzMatch.setResearchDirection(kyfzExpert.getResearchDirection());
                // 写入用户信息
                kyfzMatch.setCreateTime(DateUtils.getNowDate());
                kyfzMatch.setCreateBy(SecurityUtils.getUsername());
                kyfzMatch.setUserId(SecurityUtils.getUserId());
                kyfzMatch.setRequirementId(requirementId);
                kyfzMatchList.add(i, kyfzMatch);
            }
            kyfzMatchMapper.insert_json_KyfzMatch(kyfzMatchList);
            return kyfzMatchList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ai搜索匹配数据并返回专家账号列表
     * 
     */
    public List<String> search_jsonExpert_account(String requirementId) {
        // 获取api数据
        // String url = "http://47.113.145.216:6666/infer";
        String url = "http://172.18.166.90:6666/infer";
        String jsonData;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("OtherHeadersxxx", "xxxx"); // Other headers
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("requirement_id", Integer.valueOf(requirementId));
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<String> responseEntity;
        List<String> AccountList = new ArrayList<>();
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            jsonData = responseEntity.getBody();
            if (jsonData.equals("-1\n")) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<List<jsonMarch>> json_match_lists = objectMapper.readValue(jsonData,
                    new TypeReference<List<List<jsonMarch>>>() {
                    });
            List<jsonMarch> json_match = json_match_lists.get(0);
            for (int i = 0; i < json_match.size(); i++) {
                AccountList.add(i, json_match.get(i).getExpert_account());
            }
            return AccountList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AccountList;
    }
}
