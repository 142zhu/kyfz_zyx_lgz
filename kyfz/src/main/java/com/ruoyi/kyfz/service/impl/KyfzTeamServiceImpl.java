package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzTeam;
import com.ruoyi.kyfz.mapper.KyfzTeamMapper;
import com.ruoyi.kyfz.service.IKyfzTeamService;

/**
 * 专家团队Service业务层处理
 * 
 * @author zyx
 * @date 2023-07-18
 */
@Service
public class KyfzTeamServiceImpl implements IKyfzTeamService {
    @Autowired
    private KyfzTeamMapper kyfzTeamMapper;

    /**
     * 查询专家团队
     * 
     * @param teamId 专家团队主键
     * @return 专家团队
     */
    @Override
    public KyfzTeam selectKyfzTeamByTeamId(Long teamId) {
        return kyfzTeamMapper.selectKyfzTeamByTeamId(teamId);
    }

    /**
     * 查询专家团队列表
     * 
     * @param kyfzTeam 专家团队
     * @return 专家团队
     */
    @Override
    public List<KyfzTeam> selectKyfzTeamList(KyfzTeam kyfzTeam) {
        return kyfzTeamMapper.selectKyfzTeamList(kyfzTeam);
    }

    /**
     * 新增专家团队
     * 
     * @param kyfzTeam 专家团队
     * @return 结果
     */
    @Override
    public int insertKyfzTeam(KyfzTeam kyfzTeam) {
        return kyfzTeamMapper.insertKyfzTeam(kyfzTeam);
    }

    /**
     * 修改专家团队
     * 
     * @param kyfzTeam 专家团队
     * @return 结果
     */
    @Override
    public int updateKyfzTeam(KyfzTeam kyfzTeam) {
        return kyfzTeamMapper.updateKyfzTeam(kyfzTeam);
    }

    /**
     * 批量删除专家团队
     * 
     * @param teamIds 需要删除的专家团队主键
     * @return 结果
     */
    @Override
    public int deleteKyfzTeamByTeamIds(Long[] teamIds) {
        return kyfzTeamMapper.deleteKyfzTeamByTeamIds(teamIds);
    }

    /**
     * 删除专家团队信息
     * 
     * @param teamId 专家团队主键
     * @return 结果
     */
    @Override
    public int deleteKyfzTeamByTeamId(Long teamId) {
        return kyfzTeamMapper.deleteKyfzTeamByTeamId(teamId);
    }

    /**
     * 检索模块查专家团队列表
     * 
     * @param kyfzTeam 专家团队
     * @return 专家团队
     */
    @Override
    public List<KyfzTeam> searchTeams(KyfzTeam kyfzTeam) {
        return kyfzTeamMapper.searchTeams(kyfzTeam);
    }
}
