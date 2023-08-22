package com.ruoyi.kyfz.mapper;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzIntellectualProperty;

/**
 * 知识产权Mapper接口
 * 
 * @author zyx
 * @date 2023-08-19
 */
public interface KyfzIntellectualPropertyMapper {
    /**
     * 查询知识产权
     * 
     * @param intellectualPropertyId 知识产权主键
     * @return 知识产权
     */
    public KyfzIntellectualProperty selectKyfzIntellectualPropertyByIntellectualPropertyId(Long intellectualPropertyId);

    /**
     * 查询知识产权列表
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 知识产权集合
     */
    public List<KyfzIntellectualProperty> selectKyfzIntellectualPropertyList(
            KyfzIntellectualProperty kyfzIntellectualProperty);

    /**
     * 新增知识产权
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 结果
     */
    public int insertKyfzIntellectualProperty(KyfzIntellectualProperty kyfzIntellectualProperty);

    /**
     * 修改知识产权
     * 
     * @param kyfzIntellectualProperty 知识产权
     * @return 结果
     */
    public int updateKyfzIntellectualProperty(KyfzIntellectualProperty kyfzIntellectualProperty);

    /**
     * 删除知识产权
     * 
     * @param intellectualPropertyId 知识产权主键
     * @return 结果
     */
    public int deleteKyfzIntellectualPropertyByIntellectualPropertyId(Long intellectualPropertyId);

    /**
     * 批量删除知识产权
     * 
     * @param intellectualPropertyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzIntellectualPropertyByIntellectualPropertyIds(Long[] intellectualPropertyIds);

    /**
     * 知识产权id列表查询知识产权信息列表
     * 
     */
    public List<KyfzIntellectualProperty> selectKyfzPropertyList_PropertyIdlist(Long[] intellectualPropertyId);

    /**
     * 检索模块查成果
     * 
     */
    public List<KyfzIntellectualProperty> searchKyfzIntellectualProperty(
            KyfzIntellectualProperty kyfzIntellectualProperty);
}
