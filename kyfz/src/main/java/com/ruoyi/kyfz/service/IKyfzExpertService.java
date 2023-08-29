package com.ruoyi.kyfz.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.kyfz.domain.KyfzExpert;

/**
 * 专家管理Service接口
 * 
 * @author ruoyi
 * @date 2023-06-06
 */
public interface IKyfzExpertService {
    /**
     * 查询专家管理
     * 
     * @param expertId 专家管理主键
     * @return 专家管理
     */
    public KyfzExpert selectKyfzExpertByExpertId(Long expertId);

    /**
     * 查询专家管理列表
     * 
     * @param kyfzExpert 专家管理
     * @return 专家管理集合
     */
    public List<KyfzExpert> selectKyfzExpertList(KyfzExpert kyfzExpert);

    /**
     * 新增专家管理
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    public int insertKyfzExpert(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    public int updateKyfzExpert(KyfzExpert kyfzExpert);

    /**
     * 批量删除专家管理
     * 
     * @param expertIds 需要删除的专家管理主键集合
     * @return 结果
     */
    public int deleteKyfzExpertByExpertIds(Long[] expertIds);

    /**
     * 删除专家管理信息
     * 
     * @param expertId 专家管理主键
     * @return 结果
     */
    public int deleteKyfzExpertByExpertId(Long expertId);

    /**
     * 查询专家管理
     * 
     * @param expertId 专家管理主键
     * @return 专家管理
     */
    public KyfzExpert selectKyfzExpertByExpertAccount(String expertAccount);

    /**
     * 批量查询专家管理
     * 
     * @param expertId 专家管理主键
     * @return 专家管理
     */
    public List<KyfzExpert> selectKyfzExpertByExpertAccounts(List<String> expertAccount);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkProject(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkWork(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkThesis(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int updateMarkCertificate(KyfzExpert kyfzExpert);

    /************** */
    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkProject(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkWork(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkThesis(KyfzExpert kyfzExpert);

    /**
     * 修改专家管理
     * 
     * @param kyfzExpert 专家管理(人工标记)
     * @return 结果
     */
    public int deleteMarkCertificate(KyfzExpert kyfzExpert);

    /**
     * 查询匹配
     * 
     * @param expertId 匹配管理主键
     * @return 匹配管理
     */
    public KyfzExpert selectKyfzMatchMark(Long matchId);

    /**
     * 获取专家关系图数据
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    public JSONObject getEchartExpertData(Long expertId);

    /**
     * 获取专家关系图数据
     * 
     * @param kyfzExpert 专家管理
     * @return 结果
     */
    public JSONObject getEchartExpertData2(Long teamId);

    /**
     * 检索搜索专家
     * 
     * @param kyfzExpert 专家管理
     * @return 专家管理集合
     */
    public List<KyfzExpert> searchExperts(KyfzExpert kyfzExpert);

}
