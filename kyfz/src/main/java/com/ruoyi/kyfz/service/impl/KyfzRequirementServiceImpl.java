package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzRequirementMapper;
import com.ruoyi.kyfz.domain.KyfzRequirement;
import com.ruoyi.kyfz.service.IKyfzRequirementService;

/**
 * 需求管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@Service
public class KyfzRequirementServiceImpl implements IKyfzRequirementService 
{
    @Autowired
    private KyfzRequirementMapper kyfzRequirementMapper;

    /**
     * 查询需求管理
     * 
     * @param requirementId 需求管理主键
     * @return 需求管理
     */
    @Override
    public KyfzRequirement selectKyfzRequirementByRequirementId(Long requirementId)
    {
        return kyfzRequirementMapper.selectKyfzRequirementByRequirementId(requirementId);
    }

    /**
     * 查询需求管理列表
     * 
     * @param kyfzRequirement 需求管理
     * @return 需求管理
     */
    @Override
    public List<KyfzRequirement> selectKyfzRequirementList(KyfzRequirement kyfzRequirement)
    {
        return kyfzRequirementMapper.selectKyfzRequirementList(kyfzRequirement);
    }

    /**
     * 新增需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int insertKyfzRequirement(KyfzRequirement kyfzRequirement)
    {
        kyfzRequirement.setCreateTime(DateUtils.getNowDate());
        return kyfzRequirementMapper.insertKyfzRequirement(kyfzRequirement);
    }

    /**
     * 修改需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    @Override
    public int updateKyfzRequirement(KyfzRequirement kyfzRequirement)
    {
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
    public int deleteKyfzRequirementByRequirementIds(Long[] requirementIds)
    {
        return kyfzRequirementMapper.deleteKyfzRequirementByRequirementIds(requirementIds);
    }

    /**
     * 删除需求管理信息
     * 
     * @param requirementId 需求管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzRequirementByRequirementId(Long requirementId)
    {
        return kyfzRequirementMapper.deleteKyfzRequirementByRequirementId(requirementId);
    }
}
