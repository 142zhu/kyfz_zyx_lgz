package com.ruoyi.kyfz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzIndustryClassification;
import com.ruoyi.kyfz.mapper.KyfzIndustryClassificationMapper;
import com.ruoyi.kyfz.service.IKyfzIndustryClassificationService;

/**
 * 行业分类Service业务层处理
 * 
 * @author zyx
 * @date 2023-07-16
 */
@Service
public class KyfzIndustryClassificationServiceImpl implements IKyfzIndustryClassificationService {
    @Autowired
    private KyfzIndustryClassificationMapper kyfzIndustryClassificationMapper;

    /**
     * 查询行业分类
     * 
     * @param categoryId 行业分类主键
     * @return 行业分类
     */
    @Override
    public KyfzIndustryClassification selectKyfzIndustryClassificationByCategoryId(Long categoryId) {
        return kyfzIndustryClassificationMapper.selectKyfzIndustryClassificationByCategoryId(categoryId);
    }

    /**
     * 查询行业分类列表
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 行业分类
     */
    @Override
    public List<KyfzIndustryClassification> selectKyfzIndustryClassificationList(
            KyfzIndustryClassification kyfzIndustryClassification) {
        List<KyfzIndustryClassification> lists = kyfzIndustryClassificationMapper
                .selectKyfzIndustryClassificationList(kyfzIndustryClassification);

        List<KyfzIndustryClassification> newList = new ArrayList<>(); // 新的Lists

        // 遍历原始列表
        for (KyfzIndustryClassification item : lists) {
            // 判断是否为一级节点
            if (item.getLevel().equals("1")) {
                // 创建一个新的一级节点
                KyfzIndustryClassification newParent = new KyfzIndustryClassification();
                newParent.setCategoryId(item.getCategoryId());
                newParent.setParentId(item.getParentId());
                newParent.setLevel(item.getLevel());
                newParent.setCategoryName(item.getCategoryName());
                // 遍历原始列表，查找与之相匹配的二级节点
                for (KyfzIndustryClassification child : lists) {
                    // 找到与一级节点相匹配的二级节点，将其添加为一级节点的孩子节点
                    if (child.getLevel().equals("2") && child.getParentId().equals(item.getCategoryId())) {
                        newParent.addChild(child);
                    }
                }
                newList.add(newParent);
            }
        }
        return newList;
    }

    /**
     * 新增行业分类
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 结果
     */
    @Override
    public int insertKyfzIndustryClassification(KyfzIndustryClassification kyfzIndustryClassification) {
        return kyfzIndustryClassificationMapper.insertKyfzIndustryClassification(kyfzIndustryClassification);
    }

    /**
     * 修改行业分类
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 结果
     */
    @Override
    public int updateKyfzIndustryClassification(KyfzIndustryClassification kyfzIndustryClassification) {
        return kyfzIndustryClassificationMapper.updateKyfzIndustryClassification(kyfzIndustryClassification);
    }

    /**
     * 批量删除行业分类
     * 
     * @param categoryIds 需要删除的行业分类主键
     * @return 结果
     */
    @Override
    public int deleteKyfzIndustryClassificationByCategoryIds(Long[] categoryIds) {
        return kyfzIndustryClassificationMapper.deleteKyfzIndustryClassificationByCategoryIds(categoryIds);
    }

    /**
     * 删除行业分类信息
     * 
     * @param categoryId 行业分类主键
     * @return 结果
     */
    @Override
    public int deleteKyfzIndustryClassificationByCategoryId(Long categoryId) {
        return kyfzIndustryClassificationMapper.deleteKyfzIndustryClassificationByCategoryId(categoryId);
    }

}
