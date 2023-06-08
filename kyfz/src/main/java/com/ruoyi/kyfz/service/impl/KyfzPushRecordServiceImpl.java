package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzPushRecordMapper;
import com.ruoyi.kyfz.domain.KyfzPushRecord;
import com.ruoyi.kyfz.service.IKyfzPushRecordService;

/**
 * 推送记录Service业务层处理
 * 
 * @author zhu
 * @date 2023-06-07
 */
@Service
public class KyfzPushRecordServiceImpl implements IKyfzPushRecordService {
    @Autowired
    private KyfzPushRecordMapper kyfzPushRecordMapper;

    /**
     * 查询推送记录
     * 
     * @param pushId 推送记录主键
     * @return 推送记录
     */
    @Override
    public KyfzPushRecord selectKyfzPushRecordByPushId(Long pushId) {
        return kyfzPushRecordMapper.selectKyfzPushRecordByPushId(pushId);
    }

    /**
     * 查询推送记录列表
     * 
     * @param kyfzPushRecord 推送记录
     * @return 推送记录
     */
    @Override
    public List<KyfzPushRecord> selectKyfzPushRecordList(KyfzPushRecord kyfzPushRecord) {
        return kyfzPushRecordMapper.selectKyfzPushRecordList(kyfzPushRecord);
    }

    /**
     * 新增推送记录
     * 
     * @param kyfzPushRecord 推送记录
     * @return 结果
     */
    @Override
    public int insertKyfzPushRecord(KyfzPushRecord kyfzPushRecord) {
        kyfzPushRecord.setCreateTime(DateUtils.getNowDate());
        return kyfzPushRecordMapper.insertKyfzPushRecord(kyfzPushRecord);
    }

    /**
     * 修改推送记录
     * 
     * @param kyfzPushRecord 推送记录
     * @return 结果
     */
    @Override
    public int updateKyfzPushRecord(KyfzPushRecord kyfzPushRecord) {
        kyfzPushRecord.setUpdateTime(DateUtils.getNowDate());
        return kyfzPushRecordMapper.updateKyfzPushRecord(kyfzPushRecord);
    }

    /**
     * 批量删除推送记录
     * 
     * @param pushIds 需要删除的推送记录主键
     * @return 结果
     */
    @Override
    public int deleteKyfzPushRecordByPushIds(Long[] pushIds) {
        return kyfzPushRecordMapper.deleteKyfzPushRecordByPushIds(pushIds);
    }

    /**
     * 删除推送记录信息
     * 
     * @param pushId 推送记录主键
     * @return 结果
     */
    @Override
    public int deleteKyfzPushRecordByPushId(Long pushId) {
        return kyfzPushRecordMapper.deleteKyfzPushRecordByPushId(pushId);
    }

    /***************************************************
     * 添加推送记录
     * 
     * @param pushRecords 推送记录list（多条或1条）
     * @return 结果
     */
    public int batchInsert(List<KyfzPushRecord> pushRecords) {
        return kyfzPushRecordMapper.batchInsert(pushRecords);
    }
}
