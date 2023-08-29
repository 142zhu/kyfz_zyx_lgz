package com.ruoyi.kyfz.mapper;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.domain.KyfzPushRecord;

/**
 * 匹配列表Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
public interface KyfzMatchMapper {
    /**
     * 查询匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    public KyfzMatch selectKyfzMatchByMatchId(Long matchId);

    /**
     * 查询匹配列表列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 匹配列表集合
     */
    public List<KyfzMatch> selectKyfzMatchList(KyfzMatch kyfzMatch);

    /**
     * 新增匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    public int insertKyfzMatch(KyfzMatch kyfzMatch);

    /**
     * 修改匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    public int updateKyfzMatch(KyfzMatch kyfzMatch);

    /**
     * 删除匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 结果
     */
    public int deleteKyfzMatchByMatchId(Long matchId);

    /**
     * 批量删除匹配列表
     * 
     * @param matchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzMatchByMatchIds(Long[] matchIds);

    /***************************************************
     * 查询匹配详细列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    public KyfzMatch selectKyfzMatchDetailByMatchId(Long matchId);

    /***************************************************
     * 查询项目名称
     * 
     * @param projectId 项目表主键
     * @return 项目名称
     */
    public String selectProjectName(Long projectId);

    /***************************************************
     * 查询论文名称
     * 
     * @param thesisId 论文表主键
     * @return 论文名称
     */
    public String selectThesisName(Long thesisId);

    /***************************************************
     * 查询著作名称
     * 
     * @param Id 著作表主键
     * @return 著作名称
     */
    public String selectAwardName(Long workId);

    /***************************************************
     * 查询证书名称
     * 
     * @param Id 证书表主键
     * @return 证书名称
     */
    public String selectCertificateName(Long certificateId);

    /***************************************************
     * 添加推送记录
     * 
     * @param pushRecords 推送记录list（多条或1条）
     * @return 结果
     */
    public int batchInsert(List<KyfzPushRecord> pushRecords);

    /**
     * 修改推送记录（评分）
     * 
     * @param kyfzMatch 推送记录列表
     * @return 结果
     */
    public int updatePushRecord2(Long matchId);

    /**
     * 修改推送记录（评分）
     * 
     * @param kyfzMatch 推送记录列表
     * @return 结果
     */
    public int updatePushRecord(KyfzMatch kyfzMatch);

    /**
     * 根据需求批量删除匹配表里的匹配数据
     * 
     * @param requirementIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzMatchByrequirementIds(Long[] requirementIds);

    /**
     * 根据需求删除匹配表里的匹配数据
     * 
     * @param requirementId 匹配列表主键
     * @return 结果
     */
    public int deleteKyfzMatchByrequirementId(Long requirementId);

    /**
     * 批量更新匹配列表的推送状态
     * 
     * @param matchIds 数据主键集合
     * @return 结果
     */
    public int updateKyfzMatchByMatchIds_pushState(Long[] matchIds);

    /**
     * 匹配api返回的json数据写入数据库
     * 
     */
    public int insert_json_KyfzMatch(List<KyfzMatch> kyfzMatchList);
}
