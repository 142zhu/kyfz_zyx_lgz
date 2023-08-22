package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzAward;
import com.ruoyi.kyfz.mapper.KyfzAwardMapper;
import com.ruoyi.kyfz.service.IKyfzAwardService;

/**
 * 奖项信息Service业务层处理
 * 
 * @author zyx
 * @date 2023-08-19
 */
@Service
public class KyfzAwardServiceImpl implements IKyfzAwardService {
    @Autowired
    private KyfzAwardMapper kyfzAwardMapper;

    /**
     * 查询奖项信息
     * 
     * @param awardId 奖项信息主键
     * @return 奖项信息
     */
    @Override
    public KyfzAward selectKyfzAwardByAwardId(Long awardId) {
        return kyfzAwardMapper.selectKyfzAwardByAwardId(awardId);
    }

    /**
     * 查询奖项信息列表
     * 
     * @param kyfzAward 奖项信息
     * @return 奖项信息
     */
    @Override
    public List<KyfzAward> selectKyfzAwardList(KyfzAward kyfzAward) {
        return kyfzAwardMapper.selectKyfzAwardList(kyfzAward);
    }

    /**
     * 新增奖项信息
     * 
     * @param kyfzAward 奖项信息
     * @return 结果
     */
    @Override
    public int insertKyfzAward(KyfzAward kyfzAward) {
        return kyfzAwardMapper.insertKyfzAward(kyfzAward);
    }

    /**
     * 修改奖项信息
     * 
     * @param kyfzAward 奖项信息
     * @return 结果
     */
    @Override
    public int updateKyfzAward(KyfzAward kyfzAward) {
        return kyfzAwardMapper.updateKyfzAward(kyfzAward);
    }

    /**
     * 批量删除奖项信息
     * 
     * @param awardIds 需要删除的奖项信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzAwardByAwardIds(Long[] awardIds) {
        return kyfzAwardMapper.deleteKyfzAwardByAwardIds(awardIds);
    }

    /**
     * 删除奖项信息信息
     * 
     * @param awardId 奖项信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzAwardByAwardId(Long awardId) {
        return kyfzAwardMapper.deleteKyfzAwardByAwardId(awardId);
    }

    /**
     * 奖项id列表查询奖项信息列表
     * 
     */
    @Override
    public List<KyfzAward> selectKyfzAwardsList_AwardsIdlist(Long[] awardId) {
        return kyfzAwardMapper.selectKyfzAwardsList_AwardsIdlist(awardId);
    }
}
