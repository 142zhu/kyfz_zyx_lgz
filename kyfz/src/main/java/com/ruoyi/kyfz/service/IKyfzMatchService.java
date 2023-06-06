package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzMatch;

/**
 * 匹配列表Service接口
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public interface IKyfzMatchService 
{
    /**
     * 查询匹配列表
     * 
     * @param matchId 匹配列表主键
     * @return 匹配列表
     */
    public KyfzMatch selectKyfzMatchByMatchId(Long matchId);

    /**
     * 查询匹配列表列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 匹配列表集合
     */
    public List<KyfzMatch> selectKyfzMatchList(KyfzMatch kyfzMatch);

    /**
     * 新增匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    public int insertKyfzMatch(KyfzMatch kyfzMatch);

    /**
     * 修改匹配列表
     * 
     * @param kyfzMatch 匹配列表
     * @return 结果
     */
    public int updateKyfzMatch(KyfzMatch kyfzMatch);

    /**
     * 批量删除匹配列表
     * 
     * @param matchIds 需要删除的匹配列表主键集合
     * @return 结果
     */
    public int deleteKyfzMatchByMatchIds(Long[] matchIds);

    /**
     * 删除匹配列表信息
     * 
     * @param matchId 匹配列表主键
     * @return 结果
     */
    public int deleteKyfzMatchByMatchId(Long matchId);
}
