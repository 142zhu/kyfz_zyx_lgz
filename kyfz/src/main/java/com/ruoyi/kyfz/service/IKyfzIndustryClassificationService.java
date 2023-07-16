package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzIndustryClassification;

/**
 * 行业分类Service接口
 * 
 * @author zyx
 * @date 2023-07-16
 */
public interface IKyfzIndustryClassificationService 
{
    /**
     * 查询行业分类
     * 
     * @param categoryId 行业分类主键
     * @return 行业分类
     */
    public KyfzIndustryClassification selectKyfzIndustryClassificationByCategoryId(Long categoryId);

    /**
     * 查询行业分类列表
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 行业分类集合
     */
    public List<KyfzIndustryClassification> selectKyfzIndustryClassificationList(KyfzIndustryClassification kyfzIndustryClassification);

    /**
     * 新增行业分类
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 结果
     */
    public int insertKyfzIndustryClassification(KyfzIndustryClassification kyfzIndustryClassification);

    /**
     * 修改行业分类
     * 
     * @param kyfzIndustryClassification 行业分类
     * @return 结果
     */
    public int updateKyfzIndustryClassification(KyfzIndustryClassification kyfzIndustryClassification);

    /**
     * 批量删除行业分类
     * 
     * @param categoryIds 需要删除的行业分类主键集合
     * @return 结果
     */
    public int deleteKyfzIndustryClassificationByCategoryIds(Long[] categoryIds);

    /**
     * 删除行业分类信息
     * 
     * @param categoryId 行业分类主键
     * @return 结果
     */
    public int deleteKyfzIndustryClassificationByCategoryId(Long categoryId);
}
