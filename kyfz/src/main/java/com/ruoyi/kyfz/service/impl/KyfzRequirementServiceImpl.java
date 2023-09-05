package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.domain.KyfzRequirement;
import com.ruoyi.kyfz.mapper.KyfzMatchMapper;
import com.ruoyi.kyfz.mapper.KyfzRequirementMapper;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

/**
 * 需求管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@Service
public class KyfzRequirementServiceImpl implements IKyfzRequirementService {
    @Autowired
    private KyfzRequirementMapper kyfzRequirementMapper;

    @Autowired
    private KyfzMatchMapper kyfzMatchMapper;

    /**
     * 查询需求管理
     * 
     * @param requirementId 需求管理主键
     * @return 需求管理
     */
    @Override
    public KyfzRequirement selectKyfzRequirementByRequirementId(Long requirementId) {
        return kyfzRequirementMapper.selectKyfzRequirementByRequirementId(requirementId);
    }

    /**
     * 查询需求管理列表
     * 
     * @param kyfzRequirement 需求管理
     * @return 需求管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<KyfzRequirement> selectKyfzRequirementList(KyfzRequirement kyfzRequirement) {
        return kyfzRequirementMapper.selectKyfzRequirementList(kyfzRequirement);
    }

    /**
     * 新增需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int insertKyfzRequirement(KyfzRequirement kyfzRequirement) {
        kyfzRequirement.setCreateTime(DateUtils.getNowDate());
        kyfzRequirement.setCreatBy(SecurityUtils.getUsername());
        kyfzRequirement.setUserId(SecurityUtils.getUserId());
        return kyfzRequirementMapper.insertKyfzRequirement(kyfzRequirement);
    }

    /**
     * 修改需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int updateKyfzRequirement(KyfzRequirement kyfzRequirement) {
        kyfzRequirement.setUpdateTime(DateUtils.getNowDate());
        return kyfzRequirementMapper.updateKyfzRequirement(kyfzRequirement);
    }

    /**
     * 批量删除需求管理
     * 
     * @param requirementIds 需要删除的需求管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRequirementByRequirementIds(Long[] requirementIds) {
        kyfzMatchMapper.deleteKyfzMatchByrequirementIds(requirementIds);
        return kyfzRequirementMapper.deleteKyfzRequirementByRequirementIds(requirementIds);
    }

    /**
     * 删除需求管理信息
     * 
     * @param requirementId 需求管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRequirementByRequirementId(Long requirementId) {
        kyfzMatchMapper.deleteKyfzMatchByrequirementId(requirementId);
        return kyfzRequirementMapper.deleteKyfzRequirementByRequirementId(requirementId);
    }

    /**
     * 查询企业列表
     * 
     * @param kyfzEnterprise 企业列表
     * @return 企业列表
     */
    @Override
    public List<KyfzEnterprise> selectKyfzEnterpriseList(KyfzEnterprise kyfzEnterprise) {
        return kyfzRequirementMapper.selectKyfzEnterpriseList(kyfzEnterprise);
    }

    /**
     * 修改需求匹配状态
     * 
     * @param Long 需求id
     * @return 结果
     */
    public int updateKyfzRequirementStatusById(Long requirementId) {
        return kyfzRequirementMapper.updateKyfzRequirementStatusById(requirementId);
    }

    /**
     * 查询需求管理列表
     * 
     * @param kyfzRequirement 需求管理
     * @return 需求管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<KyfzRequirement> selectKyfzRequirementList_staging(KyfzRequirement kyfzRequirement) {
        return kyfzRequirementMapper.selectKyfzRequirementList_staging(kyfzRequirement);
    }

    /**
     * 新增暂时的需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int insertKyfzRequirement_staging(KyfzRequirement kyfzRequirement) {
        kyfzRequirement.setCreateTime(DateUtils.getNowDate());
        kyfzRequirement.setCreatBy(SecurityUtils.getUsername());
        kyfzRequirement.setUserId(SecurityUtils.getUserId());
        return kyfzRequirementMapper.insertKyfzRequirement_staging(kyfzRequirement);
    }

    /**
     * 修改暂时的需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int updateKyfzRequirement_staging(KyfzRequirement kyfzRequirement) {
        kyfzRequirement.setUpdateTime(DateUtils.getNowDate());
        return kyfzRequirementMapper.updateKyfzRequirement_staging(kyfzRequirement);
    }

    /**
     * 批量暂时的删除需求管理
     * 
     * @param requirementIds 需要删除的需求管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRequirementByRequirementIds_staging(Long[] requirementIds) {
        return kyfzRequirementMapper.deleteKyfzRequirementByRequirementIds_staging(requirementIds);
    }

    /**
     * 查询暂时的需求管理
     * 
     * @param requirementId 需求管理主键
     * @return 需求管理
     */
    @Override
    public KyfzRequirement selectKyfzRequirementByRequirementId_staging(Long requirementId) {
        return kyfzRequirementMapper.selectKyfzRequirementByRequirementId_staging(requirementId);
    }

    /**
     * 根据企业名查询名下所有需求
     * 
     */
    @Override
    public List<KyfzRequirement> getEnterpriseRequirementInfo(String enterpriseName) {
        return kyfzRequirementMapper.getEnterpriseRequirementInfo(enterpriseName);
    }

    /**
     * 查找需求id
     * 
     */
    @Override
    public KyfzRequirement selectKyfzRequirementForRequirementId(KyfzRequirement kyfzRequirement) {
        return kyfzRequirementMapper.selectKyfzRequirementForRequirementId(kyfzRequirement);
    }
}
