package com.ruoyi.kyfz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.kyfz.domain.KyfzRelationship;

/**
 * 专家关系Mapper接口
 * 
 * @author zyx
 * @date 2023-07-18
 */
public interface KyfzRelationshipMapper {
    /**
     * 查询专家关系
     * 
     * @param relationshipId 专家关系主键
     * @return 专家关系
     */
    public KyfzRelationship selectKyfzRelationshipByRelationshipId(Long relationshipId);

    /**
     * 查询专家关系列表
     * 
     * @param kyfzRelationship 专家关系
     * @return 专家关系集合
     */
    public List<KyfzRelationship> selectKyfzRelationshipList(KyfzRelationship kyfzRelationship);

    /**
     * 新增专家关系
     * 
     * @param kyfzRelationship 专家关系
     * @return 结果
     */
    public int insertKyfzRelationship(KyfzRelationship kyfzRelationship);

    /**
     * 修改专家关系
     * 
     * @param kyfzRelationship 专家关系
     * @return 结果
     */
    public int updateKyfzRelationship(KyfzRelationship kyfzRelationship);

    /**
     * 删除专家关系
     * 
     * @param relationshipId 专家关系主键
     * @return 结果
     */
    public int deleteKyfzRelationshipByRelationshipId(Long relationshipId);

    /**
     * 批量删除专家关系
     * 
     * @param relationshipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzRelationshipByRelationshipIds(Long[] relationshipIds);

    /**
     * 查询两个专家之间的关系
     * 
     * @param relationshipId 专家关系主键
     * @return 专家关系
     */
    public KyfzRelationship selectKyfzRelationship_twoId(@Param("expertAId") String expert_AId,
            @Param("expertBId") String expert_BId);
}
