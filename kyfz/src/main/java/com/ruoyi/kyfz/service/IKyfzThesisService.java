package com.ruoyi.kyfz.service;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzThesis;

/**
 * 论文信息Service接口
 * 
 * @author zyx
 * @date 2023-08-19
 */
public interface IKyfzThesisService {
    /**
     * 查询论文信息
     * 
     * @param thesisId 论文信息主键
     * @return 论文信息
     */
    public KyfzThesis selectKyfzThesisByThesisId(Long thesisId);

    /**
     * 查询论文信息列表
     * 
     * @param kyfzThesis 论文信息
     * @return 论文信息集合
     */
    public List<KyfzThesis> selectKyfzThesisList(KyfzThesis kyfzThesis);

    /**
     * 新增论文信息
     * 
     * @param kyfzThesis 论文信息
     * @return 结果
     */
    public int insertKyfzThesis(KyfzThesis kyfzThesis);

    /**
     * 修改论文信息
     * 
     * @param kyfzThesis 论文信息
     * @return 结果
     */
    public int updateKyfzThesis(KyfzThesis kyfzThesis);

    /**
     * 批量删除论文信息
     * 
     * @param thesisIds 需要删除的论文信息主键集合
     * @return 结果
     */
    public int deleteKyfzThesisByThesisIds(Long[] thesisIds);

    /**
     * 删除论文信息信息
     * 
     * @param thesisId 论文信息主键
     * @return 结果
     */
    public int deleteKyfzThesisByThesisId(Long thesisId);

    /**
     * 论文id列表查询论文信息列表
     * 
     */
    public List<KyfzThesis> selectKyfzThesisList_Thesisidlist(Long[] thesisId);
}
