package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzRelationship;
import com.ruoyi.kyfz.mapper.KyfzRelationshipMapper;
import com.ruoyi.kyfz.service.IKyfzRelationshipService;

/**
 * 专家关系Service业务层处理
 * 
 * @author zyx
 * @date 2023-07-18
 */
@Service
public class KyfzRelationshipServiceImpl implements IKyfzRelationshipService {
    @Autowired
    private KyfzRelationshipMapper kyfzRelationshipMapper;

    /**
     * 查询专家关系
     * 
     * @param relationshipId 专家关系主键
     * @return 专家关系
     */
    @Override
    public KyfzRelationship selectKyfzRelationshipByRelationshipId(Long relationshipId) {
        return kyfzRelationshipMapper.selectKyfzRelationshipByRelationshipId(relationshipId);
    }

    /**
     * 查询专家关系列表
     * 
     * @param kyfzRelationship 专家关系
     * @return 专家关系
     */
    @Override
    public List<KyfzRelationship> selectKyfzRelationshipList(KyfzRelationship kyfzRelationship) {
        return kyfzRelationshipMapper.selectKyfzRelationshipList(kyfzRelationship);
    }

    /**
     * 新增专家关系
     * 
     * @param kyfzRelationship 专家关系
     * @return 结果
     */
    @Override
    public int insertKyfzRelationship(KyfzRelationship kyfzRelationship) {
        return kyfzRelationshipMapper.insertKyfzRelationship(kyfzRelationship);
    }

    /**
     * 修改专家关系
     * 
     * @param kyfzRelationship 专家关系
     * @return 结果
     */
    @Override
    public int updateKyfzRelationship(KyfzRelationship kyfzRelationship) {
        return kyfzRelationshipMapper.updateKyfzRelationship(kyfzRelationship);
    }

    /**
     * 批量删除专家关系
     * 
     * @param relationshipIds 需要删除的专家关系主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRelationshipByRelationshipIds(Long[] relationshipIds) {
        return kyfzRelationshipMapper.deleteKyfzRelationshipByRelationshipIds(relationshipIds);
    }

    /**
     * 删除专家关系信息
     * 
     * @param relationshipId 专家关系主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRelationshipByRelationshipId(Long relationshipId) {
        return kyfzRelationshipMapper.deleteKyfzRelationshipByRelationshipId(relationshipId);
    }
}
