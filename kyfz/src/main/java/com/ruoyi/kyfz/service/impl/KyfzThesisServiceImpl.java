package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kyfz.domain.KyfzThesis;
import com.ruoyi.kyfz.mapper.KyfzThesisMapper;
import com.ruoyi.kyfz.service.IKyfzThesisService;

/**
 * 论文信息Service业务层处理
 * 
 * @author zyx
 * @date 2023-08-19
 */
@Service
public class KyfzThesisServiceImpl implements IKyfzThesisService {
    @Autowired
    private KyfzThesisMapper kyfzThesisMapper;

    /**
     * 查询论文信息
     * 
     * @param thesisId 论文信息主键
     * @return 论文信息
     */
    @Override
    public KyfzThesis selectKyfzThesisByThesisId(Long thesisId) {
        return kyfzThesisMapper.selectKyfzThesisByThesisId(thesisId);
    }

    /**
     * 查询论文信息列表
     * 
     * @param kyfzThesis 论文信息
     * @return 论文信息
     */
    @Override
    public List<KyfzThesis> selectKyfzThesisList(KyfzThesis kyfzThesis) {
        return kyfzThesisMapper.selectKyfzThesisList(kyfzThesis);
    }

    /**
     * 新增论文信息
     * 
     * @param kyfzThesis 论文信息
     * @return 结果
     */
    @Override
    public int insertKyfzThesis(KyfzThesis kyfzThesis) {
        kyfzThesis.setCreateTime(DateUtils.getNowDate());
        return kyfzThesisMapper.insertKyfzThesis(kyfzThesis);
    }

    /**
     * 修改论文信息
     * 
     * @param kyfzThesis 论文信息
     * @return 结果
     */
    @Override
    public int updateKyfzThesis(KyfzThesis kyfzThesis) {
        kyfzThesis.setUpdateTime(DateUtils.getNowDate());
        return kyfzThesisMapper.updateKyfzThesis(kyfzThesis);
    }

    /**
     * 批量删除论文信息
     * 
     * @param thesisIds 需要删除的论文信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzThesisByThesisIds(Long[] thesisIds) {
        return kyfzThesisMapper.deleteKyfzThesisByThesisIds(thesisIds);
    }

    /**
     * 删除论文信息信息
     * 
     * @param thesisId 论文信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzThesisByThesisId(Long thesisId) {
        return kyfzThesisMapper.deleteKyfzThesisByThesisId(thesisId);
    }

    /**
     * 论文id列表查询论文信息列表
     * 
     */
    @Override
    public List<KyfzThesis> selectKyfzThesisList_Thesisidlist(Long[] thesisId) {
        return kyfzThesisMapper.selectKyfzThesisList_Thesisidlist(thesisId);
    }
}
