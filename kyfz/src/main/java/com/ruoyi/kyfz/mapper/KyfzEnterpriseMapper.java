package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzEnterprise;

/**
 * 推送记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public interface KyfzEnterpriseMapper 
{
    /**
     * 查询推送记录
     * 
     * @param enterpriseId 推送记录主键
     * @return 推送记录
     */
    public KyfzEnterprise selectKyfzEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 查询推送记录列表
     * 
     * @param kyfzEnterprise 推送记录
     * @return 推送记录集合
     */
    public List<KyfzEnterprise> selectKyfzEnterpriseList(KyfzEnterprise kyfzEnterprise);

    /**
     * 新增推送记录
     * 
     * @param kyfzEnterprise 推送记录
     * @return 结果
     */
    public int insertKyfzEnterprise(KyfzEnterprise kyfzEnterprise);

    /**
     * 修改推送记录
     * 
     * @param kyfzEnterprise 推送记录
     * @return 结果
     */
    public int updateKyfzEnterprise(KyfzEnterprise kyfzEnterprise);

    /**
     * 删除推送记录
     * 
     * @param enterpriseId 推送记录主键
     * @return 结果
     */
    public int deleteKyfzEnterpriseByEnterpriseId(Long enterpriseId);

    /**
     * 批量删除推送记录
     * 
     * @param enterpriseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzEnterpriseByEnterpriseIds(Long[] enterpriseIds);
}
