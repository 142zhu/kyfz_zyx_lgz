package com.ruoyi.kyfz.mapper;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzIndustryClassification;

/**
 * 行业分类Mapper接口
 * 
 * @author zyx
 * @date 2023-07-16
 */
public interface KyfzIndustryClassificationMapper {
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
    public List<KyfzIndustryClassification> selectKyfzIndustryClassificationList(
            KyfzIndustryClassification kyfzIndustryClassification);

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
     * 删除行业分类
     * 
     * @param categoryId 行业分类主键
     * @return 结果
     */
    public int deleteKyfzIndustryClassificationByCategoryId(Long categoryId);

    /**
     * 批量删除行业分类
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzIndustryClassificationByCategoryIds(Long[] categoryIds);

    /**
     * 查询行业分类
     * 
     * @param categoryIds 行业分类主键(字符串)
     * @return 行业分类
     */
    public String selectKyfzIndustryClassificationByCategoryIds(String categoryIds);
}
