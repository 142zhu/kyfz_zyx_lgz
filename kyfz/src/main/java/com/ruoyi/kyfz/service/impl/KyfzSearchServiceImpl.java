package com.ruoyi.kyfz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzSearchMapper;
import com.ruoyi.kyfz.domain.KyfzSearch;
import com.ruoyi.kyfz.service.IKyfzSearchService;

/**
 * 检索Service业务层处理
 * 
 * @author zyx
 * @date 2023-07-05
 */
@Service
public class KyfzSearchServiceImpl implements IKyfzSearchService 
{
    @Autowired
    private KyfzSearchMapper kyfzSearchMapper;

    /**
     * 查询检索
     * 
     * @param searchId 检索主键
     * @return 检索
     */
    @Override
    public KyfzSearch selectKyfzSearchBySearchId(Long searchId)
    {
        return kyfzSearchMapper.selectKyfzSearchBySearchId(searchId);
    }

    /**
     * 查询检索列表
     * 
     * @param kyfzSearch 检索
     * @return 检索
     */
    @Override
    public List<KyfzSearch> selectKyfzSearchList(KyfzSearch kyfzSearch)
    {
        return kyfzSearchMapper.selectKyfzSearchList(kyfzSearch);
    }

    /**
     * 新增检索
     * 
     * @param kyfzSearch 检索
     * @return 结果
     */
    @Override
    public int insertKyfzSearch(KyfzSearch kyfzSearch)
    {
        return kyfzSearchMapper.insertKyfzSearch(kyfzSearch);
    }

    /**
     * 修改检索
     * 
     * @param kyfzSearch 检索
     * @return 结果
     */
    @Override
    public int updateKyfzSearch(KyfzSearch kyfzSearch)
    {
        return kyfzSearchMapper.updateKyfzSearch(kyfzSearch);
    }

    /**
     * 批量删除检索
     * 
     * @param searchIds 需要删除的检索主键
     * @return 结果
     */
    @Override
    public int deleteKyfzSearchBySearchIds(Long[] searchIds)
    {
        return kyfzSearchMapper.deleteKyfzSearchBySearchIds(searchIds);
    }

    /**
     * 删除检索信息
     * 
     * @param searchId 检索主键
     * @return 结果
     */
    @Override
    public int deleteKyfzSearchBySearchId(Long searchId)
    {
        return kyfzSearchMapper.deleteKyfzSearchBySearchId(searchId);
    }
}
