package com.ruoyi.kyfz.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.KyfzExpertMapper;
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.service.IKyfzExpertService;

/**
 * 专家管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
@Service
public class KyfzExpertServiceImpl implements IKyfzExpertService {
    @Autowired
    private KyfzExpertMapper kyfzExpertMapper;

    /**
     * 查询专家管理
     * 
     * @param expertId 专家管理主键
     * @return 专家管理
     */
    @Override
    public KyfzExpert selectKyfzExpertByExpertId(Long expertId) {
        return kyfzExpertMapper.selectKyfzExpertByExpertId(expertId);
    }

    /**
     * 查询专家管理列表
     * 
     * @param kyfzExpert 专家管理
     * @return 专家管理
     */
    @Override
    public List<KyfzExpert> selectKyfzExpertList(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.selectKyfzExpertList(kyfzExpert);
    }

    /**
     * 新增专家管理
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    @Override
    public int insertKyfzExpert(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.insertKyfzExpert(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    @Override
    public int updateKyfzExpert(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.updateKyfzExpert(kyfzExpert);
    }

    /**
     * 批量删除专家管理
     * 
     * @param expertIds 需要删除的专家管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzExpertByExpertIds(Long[] expertIds) {
        return kyfzExpertMapper.deleteKyfzExpertByExpertIds(expertIds);
    }

    /**
     * 删除专家管理信息
     * 
     * @param expertId 专家管理主键
     * @return 结果
     */
    @Override
    public int deleteKyfzExpertByExpertId(Long expertId) {
        return kyfzExpertMapper.deleteKyfzExpertByExpertId(expertId);
    }

    /**
     * 查询专家管理
     * 
     * @param expertId 专家管理主键
     * @return 专家管理
     */
    public KyfzExpert selectKyfzExpertByExpertAccount(String expertAccount) {
        return kyfzExpertMapper.selectKyfzExpertByExpertAccount(expertAccount);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkProject(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.updateMarkProject(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkWork(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.updateMarkWork(kyfzExpert);
    }

    /**
     * updateMarkWork updateMarkThesis updateMarkCertificate
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkThesis(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.updateMarkThesis(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkCertificate(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.updateMarkCertificate(kyfzExpert);
    }

    /************** */
    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkProject(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.deleteMarkProject(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkWork(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.deleteMarkWork(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkThesis(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.deleteMarkThesis(kyfzExpert);
    }

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkCertificate(KyfzExpert kyfzExpert) {
        return kyfzExpertMapper.deleteMarkCertificate(kyfzExpert);
    }

    /**
     * 查询匹配
     * 
     * @param expertId 匹配管理主键
     * @return 匹配管理
     */
    public KyfzExpert selectKyfzMatchMark(Long matchId) {
        return kyfzExpertMapper.selectKyfzMatchMark(matchId);
    }
}
