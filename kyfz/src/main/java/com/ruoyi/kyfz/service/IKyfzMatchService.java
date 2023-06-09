package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.domain.KyfzPushRecord;

/**
 * 匹配列表Service接口
 * 
 * @author ruoyi
 * @date 2023-06-07
 */
public interface IKyfzMatchService {
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
     * 批量删除匹配列表
     * 
     * @param matchIds 需要删除的匹配列表主键集合
     * @return 结果
     */
    public int deleteKyfzMatchByMatchIds(Long[] matchIds);

    /**
     * 删除匹配列表信息
     * 
     * @param matchId 匹配列表主键
     * @return 结果
     */
    public int deleteKyfzMatchByMatchId(Long matchId);

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
    public String selectWorkName(Long workId);

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

}
