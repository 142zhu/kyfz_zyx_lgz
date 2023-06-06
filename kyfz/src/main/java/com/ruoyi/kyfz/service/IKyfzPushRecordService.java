package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzPushRecord;

/**
 * 推送记录Service接口
 * 
 * @author zhu
 * @date 2023-06-07
 */
public interface IKyfzPushRecordService {
    /**
     * 查询推送记录
     * 
     * @param pushId 推送记录主键
     * @return 推送记录
     */
    public KyfzPushRecord selectKyfzPushRecordByPushId(Long pushId);

    /**
     * 查询推送记录列表
     * 
     * @param kyfzPushRecord 推送记录
     * @return 推送记录集合
     */
    public List<KyfzPushRecord> selectKyfzPushRecordList(KyfzPushRecord kyfzPushRecord);

    /**
     * 新增推送记录
     * 
     * @param kyfzPushRecord 推送记录
     * @return 结果
     */
    public int insertKyfzPushRecord(KyfzPushRecord kyfzPushRecord);

    /**
     * 修改推送记录
     * 
     * @param kyfzPushRecord 推送记录
     * @return 结果
     */
    public int updateKyfzPushRecord(KyfzPushRecord kyfzPushRecord);

    /**
     * 批量删除推送记录
     * 
     * @param pushIds 需要删除的推送记录主键集合
     * @return 结果
     */
    public int deleteKyfzPushRecordByPushIds(Long[] pushIds);

    /**
     * 删除推送记录信息
     * 
     * @param pushId 推送记录主键
     * @return 结果
     */
    public int deleteKyfzPushRecordByPushId(Long pushId);
}
