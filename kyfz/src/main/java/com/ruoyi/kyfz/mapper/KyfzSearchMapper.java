package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzSearch;

/**
 * 检索Mapper接口
 * 
 * @author zyx
 * @date 2023-07-05
 */
public interface KyfzSearchMapper 
{
    /**
     * 查询检索
     * 
     * @param searchId 检索主键
     * @return 检索
     */
    public KyfzSearch selectKyfzSearchBySearchId(Long searchId);

    /**
     * 查询检索列表
     * 
     * @param kyfzSearch 检索
     * @return 检索集合
     */
    public List<KyfzSearch> selectKyfzSearchList(KyfzSearch kyfzSearch);

    /**
     * 新增检索
     * 
     * @param kyfzSearch 检索
     * @return 结果
     */
    public int insertKyfzSearch(KyfzSearch kyfzSearch);

    /**
     * 修改检索
     * 
     * @param kyfzSearch 检索
     * @return 结果
     */
    public int updateKyfzSearch(KyfzSearch kyfzSearch);

    /**
     * 删除检索
     * 
     * @param searchId 检索主键
     * @return 结果
     */
    public int deleteKyfzSearchBySearchId(Long searchId);

    /**
     * 批量删除检索
     * 
     * @param searchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzSearchBySearchIds(Long[] searchIds);
}
