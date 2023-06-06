package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzMatchMapper;
import com.ruoyi.kyfz.domain.KyfzMatch;
import com.ruoyi.kyfz.service.IKyfzMatchService;

/**
 * 匹配列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@Service
public class KyfzMatchServiceImpl implements IKyfzMatchService 
{
    @Autowired
    private KyfzMatchMapper kyfzMatchMapper;

    /**
     * 查询匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    @Override
    public KyfzMatch selectKyfzMatchByMatchId(Long matchId)
    {
        return kyfzMatchMapper.selectKyfzMatchByMatchId(matchId);
    }

    /**
     * 查询匹配列表列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 匹配列表
     */
    @Override
    public List<KyfzMatch> selectKyfzMatchList(KyfzMatch kyfzMatch)
    {
        return kyfzMatchMapper.selectKyfzMatchList(kyfzMatch);
    }

    /**
     * 新增匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    @Override
    public int insertKyfzMatch(KyfzMatch kyfzMatch)
    {
        kyfzMatch.setCreateTime(DateUtils.getNowDate());
        return kyfzMatchMapper.insertKyfzMatch(kyfzMatch);
    }

    /**
     * 修改匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    @Override
    public int updateKyfzMatch(KyfzMatch kyfzMatch)
    {
        kyfzMatch.setUpdateTime(DateUtils.getNowDate());
        return kyfzMatchMapper.updateKyfzMatch(kyfzMatch);
    }

    /**
     * 批量删除匹配列表
     * 
     * @param matchIds 需要删除的匹配列表主键
     * @return 结果
     */
    @Override
    public int deleteKyfzMatchByMatchIds(Long[] matchIds)
    {
        return kyfzMatchMapper.deleteKyfzMatchByMatchIds(matchIds);
    }

    /**
     * 删除匹配列表信息
     * 
     * @param matchId 匹配列表主键
     * @return 结果
     */
    @Override
    public int deleteKyfzMatchByMatchId(Long matchId)
    {
        return kyfzMatchMapper.deleteKyfzMatchByMatchId(matchId);
    }
}
