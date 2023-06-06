package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzRequirement;

/**
 * 需求管理Service接口
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public interface IKyfzRequirementService 
{
    /**
     * 查询需求管理
     * 
     * @param requirementId 需求管理主键
     * @return 需求管理
     */
    public KyfzRequirement selectKyfzRequirementByRequirementId(Long requirementId);

    /**
     * 查询需求管理列表
     * 
     * @param kyfzRequirement 需求管理
     * @return 需求管理集合
     */
    public List<KyfzRequirement> selectKyfzRequirementList(KyfzRequirement kyfzRequirement);

    /**
     * 新增需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    public int insertKyfzRequirement(KyfzRequirement kyfzRequirement);

    /**
     * 修改需求管理
     * 
     * @param kyfzRequirement 需求管理
     * @return 结果
     */
    public int updateKyfzRequirement(KyfzRequirement kyfzRequirement);

    /**
     * 批量删除需求管理
     * 
     * @param requirementIds 需要删除的需求管理主键集合
     * @return 结果
     */
    public int deleteKyfzRequirementByRequirementIds(Long[] requirementIds);

    /**
     * 删除需求管理信息
     * 
     * @param requirementId 需求管理主键
     * @return 结果
     */
    public int deleteKyfzRequirementByRequirementId(Long requirementId);
}
