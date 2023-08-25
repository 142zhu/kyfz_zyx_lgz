package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzIntellectualProperty;
import com.ruoyi.kyfz.mapper.KyfzIntellectualPropertyMapper;
import com.ruoyi.kyfz.service.IKyfzIntellectualPropertyService;

/**
 * 知识产权Service业务层处理
 * 
 * @author zyx
 * @date 2023-08-19
 */
@Service
public class KyfzIntellectualPropertyServiceImpl implements IKyfzIntellectualPropertyService {
    @Autowired
    private KyfzIntellectualPropertyMapper kyfzIntellectualPropertyMapper;

    /**
     * 查询知识产权
     * 
     * @param intellectualPropertyId 知识产权主键
     * @return 知识产权
     */
    @Override
    public KyfzIntellectualProperty selectKyfzIntellectualPropertyByIntellectualPropertyId(
            Long intellectualPropertyId) {
        return kyfzIntellectualPropertyMapper
                .selectKyfzIntellectualPropertyByIntellectualPropertyId(intellectualPropertyId);
    }

    /**
     * 查询知识产权列表
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 知识产权
     */
    @Override
    public List<KyfzIntellectualProperty> selectKyfzIntellectualPropertyList(
            KyfzIntellectualProperty kyfzIntellectualProperty) {
        List<KyfzIntellectualProperty> kyfzIntellectualProperties = kyfzIntellectualPropertyMapper
                .selectKyfzIntellectualPropertyList(kyfzIntellectualProperty);
        for (int i = 0; i < kyfzIntellectualProperties.size(); i++) {
            kyfzIntellectualProperties.get(i).setMemberInformation(
                    kyfzIntellectualProperties.get(i).getMemberInformation().replaceAll("\\（[^\\）]*\\）", ""));
        }
        return kyfzIntellectualProperties;
    }

    /**
     * 新增知识产权
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 结果
     */
    @Override
    public int insertKyfzIntellectualProperty(KyfzIntellectualProperty kyfzIntellectualProperty) {
        return kyfzIntellectualPropertyMapper.insertKyfzIntellectualProperty(kyfzIntellectualProperty);
    }

    /**
     * 修改知识产权
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 结果
     */
    @Override
    public int updateKyfzIntellectualProperty(KyfzIntellectualProperty kyfzIntellectualProperty) {
        return kyfzIntellectualPropertyMapper.updateKyfzIntellectualProperty(kyfzIntellectualProperty);
    }

    /**
     * 批量删除知识产权
     * 
     * @param intellectualPropertyIds 需要删除的知识产权主键
     * @return 结果
     */
    @Override
    public int deleteKyfzIntellectualPropertyByIntellectualPropertyIds(Long[] intellectualPropertyIds) {
        return kyfzIntellectualPropertyMapper
                .deleteKyfzIntellectualPropertyByIntellectualPropertyIds(intellectualPropertyIds);
    }

    /**
     * 删除知识产权信息
     * 
     * @param intellectualPropertyId 知识产权主键
     * @return 结果
     */
    @Override
    public int deleteKyfzIntellectualPropertyByIntellectualPropertyId(Long intellectualPropertyId) {
        return kyfzIntellectualPropertyMapper
                .deleteKyfzIntellectualPropertyByIntellectualPropertyId(intellectualPropertyId);
    }

    /**
     * 知识产权id列表查询知识产权信息列表
     * 
     */
    @Override
    public List<KyfzIntellectualProperty> selectKyfzPropertyList_PropertyIdlist(Long[] intellectualPropertyId) {
        return kyfzIntellectualPropertyMapper.selectKyfzPropertyList_PropertyIdlist(intellectualPropertyId);
    }

    /**
     * 检索模块查成果
     * 
     */
    @Override
    public List<KyfzIntellectualProperty> searchKyfzIntellectualProperty(
            KyfzIntellectualProperty KyfzIntellectualProperty) {
        List<KyfzIntellectualProperty> kyfzIntellectualProperties = kyfzIntellectualPropertyMapper
                .selectKyfzIntellectualPropertyList(KyfzIntellectualProperty);
        return kyfzIntellectualProperties;
    }
}
