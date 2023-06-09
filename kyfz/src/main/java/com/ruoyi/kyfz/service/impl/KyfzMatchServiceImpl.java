package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzMatchMapper;
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.domain.KyfzPushRecord;
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

    /**
     * 查询匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    @Override
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
    public KyfzMatch selectKyfzMatchDetailByMatchId(Long matchId) {
        return kyfzMatchMapper.selectKyfzMatchDetailByMatchId(matchId);
    }

    /***************************************************
     * 查询项目名称
     * 
     * @param projectId 项目表主键
     * @return 项目名称
     */
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
    public String selectWorkName(Long workId) {
        return kyfzMatchMapper.selectWorkName(workId);
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
}
