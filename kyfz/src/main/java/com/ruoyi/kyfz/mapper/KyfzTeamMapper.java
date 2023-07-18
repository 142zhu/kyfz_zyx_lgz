package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzTeam;

/**
 * 专家团队Mapper接口
 * 
 * @author zyx
 * @date 2023-07-18
 */
public interface KyfzTeamMapper 
{
    /**
     * 查询专家团队
     * 
     * @param teamId 专家团队主键
     * @return 专家团队
     */
    public KyfzTeam selectKyfzTeamByTeamId(Long teamId);

    /**
     * 查询专家团队列表
     * 
     * @param kyfzTeam 专家团队
     * @return 专家团队集合
     */
    public List<KyfzTeam> selectKyfzTeamList(KyfzTeam kyfzTeam);

    /**
     * 新增专家团队
     * 
     * @param kyfzTeam 专家团队
     * @return 结果
     */
    public int insertKyfzTeam(KyfzTeam kyfzTeam);

    /**
     * 修改专家团队
     * 
     * @param kyfzTeam 专家团队
     * @return 结果
     */
    public int updateKyfzTeam(KyfzTeam kyfzTeam);

    /**
     * 删除专家团队
     * 
     * @param teamId 专家团队主键
     * @return 结果
     */
    public int deleteKyfzTeamByTeamId(Long teamId);

    /**
     * 批量删除专家团队
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzTeamByTeamIds(Long[] teamIds);
}
