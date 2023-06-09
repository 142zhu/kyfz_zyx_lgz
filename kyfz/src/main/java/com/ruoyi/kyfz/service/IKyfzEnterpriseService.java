package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzEnterprise;

/**
 * 企业管理Service接口
 * 
 * @author ruoyi
 * @date 2023-06-09
 */
public interface IKyfzEnterpriseService 
{
    /**
     * 查询企业管理
     * 
     * @param enterpriseId 企业管理主键
     * @return 企业管理
     */
    public KyfzEnterprise selectKyfzEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询企业管理列表
     * 
     * @param kyfzEnterprise 企业管理
     * @return 企业管理集合
     */
    public List<KyfzEnterprise> selectKyfzEnterpriseList(KyfzEnterprise kyfzEnterprise);

    /**
     * 新增企业管理
     * 
     * @param kyfzEnterprise 企业管理
     * @return 结果
     */
    public int insertKyfzEnterprise(KyfzEnterprise kyfzEnterprise);

    /**
     * 修改企业管理
     * 
     * @param kyfzEnterprise 企业管理
     * @return 结果
     */
    public int updateKyfzEnterprise(KyfzEnterprise kyfzEnterprise);

    /**
     * 批量删除企业管理
     * 
     * @param enterpriseIds 需要删除的企业管理主键集合
     * @return 结果
     */
    public int deleteKyfzEnterpriseByEnterpriseIds(Long[] enterpriseIds);

    /**
     * 删除企业管理信息
     * 
     * @param enterpriseId 企业管理主键
     * @return 结果
     */
    public int deleteKyfzEnterpriseByEnterpriseId(Long enterpriseId);
}
