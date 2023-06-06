package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzEnterpriseMapper;
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.service.IKyfzEnterpriseService;

/**
 * 推送记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@Service
public class KyfzEnterpriseServiceImpl implements IKyfzEnterpriseService 
{
    @Autowired
    private KyfzEnterpriseMapper kyfzEnterpriseMapper;

    /**
     * 查询推送记录
     * 
     * @param enterpriseId 推送记录主键
     * @return 推送记录
     */
    @Override
    public KyfzEnterprise selectKyfzEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return kyfzEnterpriseMapper.selectKyfzEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询推送记录列表
     * 
     * @param kyfzEnterprise 推送记录
     * @return 推送记录
     */
    @Override
    public List<KyfzEnterprise> selectKyfzEnterpriseList(KyfzEnterprise kyfzEnterprise)
    {
        return kyfzEnterpriseMapper.selectKyfzEnterpriseList(kyfzEnterprise);
    }

    /**
     * 新增推送记录
     * 
     * @param kyfzEnterprise 推送记录
     * @return 结果
     */
    @Override
    public int insertKyfzEnterprise(KyfzEnterprise kyfzEnterprise)
    {
        kyfzEnterprise.setCreateTime(DateUtils.getNowDate());
        return kyfzEnterpriseMapper.insertKyfzEnterprise(kyfzEnterprise);
    }

    /**
     * 修改推送记录
     * 
     * @param kyfzEnterprise 推送记录
     * @return 结果
     */
    @Override
    public int updateKyfzEnterprise(KyfzEnterprise kyfzEnterprise)
    {
        kyfzEnterprise.setUpdateTime(DateUtils.getNowDate());
        return kyfzEnterpriseMapper.updateKyfzEnterprise(kyfzEnterprise);
    }

    /**
     * 批量删除推送记录
     * 
     * @param enterpriseIds 需要删除的推送记录主键
     * @return 结果
     */
    @Override
    public int deleteKyfzEnterpriseByEnterpriseIds(Long[] enterpriseIds)
    {
        return kyfzEnterpriseMapper.deleteKyfzEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除推送记录信息
     * 
     * @param enterpriseId 推送记录主键
     * @return 结果
     */
    @Override
    public int deleteKyfzEnterpriseByEnterpriseId(Long enterpriseId)
    {
        return kyfzEnterpriseMapper.deleteKyfzEnterpriseByEnterpriseId(enterpriseId);
    }
}
