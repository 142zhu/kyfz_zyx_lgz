package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kyfz.domain.KyfzEnterprise;
import com.ruoyi.kyfz.mapper.KyfzEnterpriseMapper;
import com.ruoyi.kyfz.mapper.KyfzIndustryClassificationMapper;
import com.ruoyi.kyfz.service.IKyfzEnterpriseService;

/**
 * 企业管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-09
 */
@Service
public class KyfzEnterpriseServiceImpl implements IKyfzEnterpriseService {
    @Autowired
    private KyfzEnterpriseMapper kyfzEnterpriseMapper;

    @Autowired
    private KyfzIndustryClassificationMapper kyfzIndustryClassificationMapper;

    /**
     * 查询企业管理
     * 
     * @param enterpriseId 企业管理主键
     * @return 企业管理
     */
    @Override
    public KyfzEnterprise selectKyfzEnterpriseByEnterpriseId(Long enterpriseId) {
        return kyfzEnterpriseMapper.selectKyfzEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询企业管理列表
     * 
     * @param kyfzEnterprise 企业管理
     * @return 企业管理
     */
    @Override
    public List<KyfzEnterprise> selectKyfzEnterpriseList(KyfzEnterprise kyfzEnterprise) {
        List<KyfzEnterprise> kyfzEnterpriseList = kyfzEnterpriseMapper.selectKyfzEnterpriseList(kyfzEnterprise);
        for (KyfzEnterprise e : kyfzEnterpriseList) {
            e.setCategoryNames(
                    kyfzIndustryClassificationMapper.selectKyfzIndustryClassificationByCategoryIds(e.getCategoryId()));
        }
        return kyfzEnterpriseList;
    }

    /**
     * 新增企业管理
     * 
     * @param kyfzEnterprise 企业管理
     * @return 结果
     */
    @Override
    public int insertKyfzEnterprise(KyfzEnterprise kyfzEnterprise) {
        kyfzEnterprise.setCreateTime(DateUtils.getNowDate());
        return kyfzEnterpriseMapper.insertKyfzEnterprise(kyfzEnterprise);
    }

    /**
     * 修改企业管理
     * 
     * @param kyfzEnterprise 企业管理
     * @return 结果
     */
    @Override
    public int updateKyfzEnterprise(KyfzEnterprise kyfzEnterprise) {
        kyfzEnterprise.setUpdateTime(DateUtils.getNowDate());
        return kyfzEnterpriseMapper.updateKyfzEnterprise(kyfzEnterprise);
    }

    /**
     * 批量删除企业管理
     * 
     * @param enterpriseIds 需要删除的企业管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzEnterpriseByEnterpriseIds(Long[] enterpriseIds) {
        return kyfzEnterpriseMapper.deleteKyfzEnterpriseByEnterpriseIds(enterpriseIds);
    }

    /**
     * 删除企业管理信息
     * 
     * @param enterpriseId 企业管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzEnterpriseByEnterpriseId(Long enterpriseId) {
        return kyfzEnterpriseMapper.deleteKyfzEnterpriseByEnterpriseId(enterpriseId);
    }

    /**
     * 检索模块查询企业管理列表
     * 
     * @param kyfzEnterprise 企业管理
     * @return 企业管理
     */
    @Override
    public List<KyfzEnterprise> searchEnterprises(KyfzEnterprise kyfzEnterprise) {
        List<KyfzEnterprise> kyfzEnterpriseList = kyfzEnterpriseMapper.searchEnterprises(kyfzEnterprise);
        for (KyfzEnterprise e : kyfzEnterpriseList) {
            e.setCategoryNames(
                    kyfzIndustryClassificationMapper.selectKyfzIndustryClassificationByCategoryIds(e.getCategoryId()));
        }
        return kyfzEnterpriseList;
    }
}
