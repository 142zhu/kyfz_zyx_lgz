package com.ruoyi.kyfz.mapper;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzAward;

/**
 * 奖项信息Mapper接口
 * 
 * @author zyx
 * @date 2023-08-19
 */
public interface KyfzAwardMapper {
    /**
     * 查询奖项信息
     * 
     * @param awardId 奖项信息主键
     * @return 奖项信息
     */
    public KyfzAward selectKyfzAwardByAwardId(Long awardId);

    /**
     * 查询奖项信息列表
     * 
     * @param kyfzAward 奖项信息
     * @return 奖项信息集合
     */
    public List<KyfzAward> selectKyfzAwardList(KyfzAward kyfzAward);

    /**
     * 新增奖项信息
     * 
     * @param kyfzAward 奖项信息
     * @return 结果
     */
    public int insertKyfzAward(KyfzAward kyfzAward);

    /**
     * 修改奖项信息
     * 
     * @param kyfzAward 奖项信息
     * @return 结果
     */
    public int updateKyfzAward(KyfzAward kyfzAward);

    /**
     * 删除奖项信息
     * 
     * @param awardId 奖项信息主键
     * @return 结果
     */
    public int deleteKyfzAwardByAwardId(Long awardId);

    /**
     * 批量删除奖项信息
     * 
     * @param awardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzAwardByAwardIds(Long[] awardIds);

    /**
     * 奖项id列表查询奖项信息列表
     * 
     */
    public List<KyfzAward> selectKyfzAwardsList_AwardsIdlist(Long[] awardId);
}
