package com.ruoyi.kyfz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.kyfz.domain.KyfzExpert;
import com.ruoyi.kyfz.domain.KyfzRelationship;
import com.ruoyi.kyfz.domain.KyfzTeam;
import com.ruoyi.kyfz.mapper.KyfzExpertMapper;
import com.ruoyi.kyfz.mapper.KyfzIndustryClassificationMapper;
import com.ruoyi.kyfz.mapper.KyfzRelationshipMapper;
import com.ruoyi.kyfz.mapper.KyfzTeamMapper;
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

    @Autowired
    private KyfzTeamMapper KyfzTeamMapper;

    @Autowired
    private KyfzRelationshipMapper KyfzRelationshipMapper;

    @Autowired
    private KyfzIndustryClassificationMapper kyfzIndustryClassificationMapper;

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
        List<KyfzExpert> ExpertList = kyfzExpertMapper.selectKyfzExpertList(kyfzExpert);
        for (KyfzExpert e : ExpertList) {
            e.setCategoryNames(
                    kyfzIndustryClassificationMapper.selectKyfzIndustryClassificationByCategoryIds(e.getCategoryId()));
        }
        return ExpertList;
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

    /**
     * echart 数据
     * 
     * @return 结果
     */
    @Override
    public JSONObject getEchartExpertData(Long expertId) {
        KyfzExpert kyfzexpert = kyfzExpertMapper.selectKyfzExpertByExpertId(expertId);
        KyfzTeam Kyfzteam = KyfzTeamMapper.selectKyfzTeamByTeamId(Long.parseLong(kyfzexpert.getExpertTeams()));
        // 团队表中专家权重分值
        String[] scores = Kyfzteam.getMemberScores().split("[、,\\-;]");
        double[] weight = new double[scores.length];

        for (int i = 0; i < scores.length; i++) {
            weight[i] = Double.parseDouble(scores[i]);
        }

        // 专家账号集合
        String[] expertAccounts = Kyfzteam.getTeamAccount().split("[、,\\-;]");
        String[] expertName = Kyfzteam.getTeamMembers().split("[、,\\-;]");
        // 专家之间关系集合
        List<KyfzRelationship> KyfzRelationships = new ArrayList<KyfzRelationship>();
        KyfzRelationship rs;
        for (int i = 0; i < expertAccounts.length - 1; i++) {
            for (int j = i + 1; j < expertAccounts.length; j++) {
                // i 为专家A,j 为专家B
                rs = KyfzRelationshipMapper.selectKyfzRelationship_twoId(expertAccounts[i],
                        expertAccounts[j]);
                if (rs != null) {
                    KyfzRelationships.add(rs);
                }
            }
        }

        // 构造json数据
        // 构造"nodes" 和 构造"categories"
        JSONArray nodesArray_categories = new JSONArray();
        JSONObject[] node_categories = new JSONObject[expertAccounts.length];
        JSONArray nodesArray = new JSONArray();
        JSONObject[] node = new JSONObject[expertAccounts.length];
        double centerX = 50.0; // 中心点X坐标
        double centerY = 50.0; // 中心点Y坐标
        double minRadius = 5.0; // 最小半径
        double maxRadius = 30.0; // 最大半径

        Random random = new Random();
        double radius;
        double angle;
        double randomX;
        double randomY;

        for (int i = 0; i < expertAccounts.length; i++) {
            node[i] = new JSONObject(); // 初始化每个索引位置的JSONObject对象
            node_categories[i] = new JSONObject();// 初始化每个索引位置的JSONObject对象
            node[i].put("id", expertAccounts[i]);
            node[i].put("name", expertName[i]);
            node[i].put("symbolSize", 30.0 + weight[i] * 250);
            radius = random.nextDouble() * (maxRadius - minRadius) + minRadius;
            angle = random.nextDouble() * 2 * Math.PI;
            randomX = centerX + radius * Math.cos(angle);
            randomY = centerY + radius * Math.sin(angle);
            node[i].put("x", randomX);
            node[i].put("y", randomY);
            node[i].put("value", "权重:" + weight[i]);
            node[i].put("category", i);
            nodesArray.add(node[i]);
            node_categories[i].put("name", expertName[i]);
            nodesArray_categories.add(node_categories[i]);
        }

        // 构造"links"
        JSONArray nodesArray_links = new JSONArray();
        JSONObject[] node_links = new JSONObject[KyfzRelationships.size()];
        for (int i = 0; i < KyfzRelationships.size(); i++) {
            node_links[i] = new JSONObject(); // 初始化每个索引位置的JSONObject对象
            node_links[i].put("source", "" + KyfzRelationships.get(i).getExpertAId());
            node_links[i].put("target", "" + KyfzRelationships.get(i).getExpertBId());
            nodesArray_links.add(node_links[i]);
        }

        // 创建最终的 JSON 对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categories", nodesArray_categories);
        jsonObject.put("links", nodesArray_links);
        jsonObject.put("nodes", nodesArray);

        return jsonObject;
    }

    /**
     * 检索搜索专家
     * 
     * @param kyfzExpert 专家管理
     * @return 专家管理
     */
    @Override
    public List<KyfzExpert> searchExperts(KyfzExpert kyfzExpert) {
        List<KyfzExpert> ExpertList = kyfzExpertMapper.searchExperts(kyfzExpert);
        for (KyfzExpert e : ExpertList) {
            e.setCategoryNames(
                    kyfzIndustryClassificationMapper.selectKyfzIndustryClassificationByCategoryIds(e.getCategoryId()));
        }
        return ExpertList;
    }

    /**
     * echart 数据
     * 
     * @return 结果
     */
    @Override
    public JSONObject getEchartExpertData2(Long teamId) {
        KyfzTeam Kyfzteam = KyfzTeamMapper.selectKyfzTeamByTeamId(teamId);
        // 团队表中专家权重分值
        String[] scores = Kyfzteam.getMemberScores().split("[、,\\-;]");
        double[] weight = new double[scores.length];

        for (int i = 0; i < scores.length; i++) {
            weight[i] = Double.parseDouble(scores[i]);
        }

        // 专家账号集合
        String[] expertAccounts = Kyfzteam.getTeamAccount().split("[、,\\-;]");
        String[] expertName = Kyfzteam.getTeamMembers().split("[、,\\-;]");
        // 专家之间关系集合
        List<KyfzRelationship> KyfzRelationships = new ArrayList<KyfzRelationship>();
        KyfzRelationship rs;
        for (int i = 0; i < expertAccounts.length - 1; i++) {
            for (int j = i + 1; j < expertAccounts.length; j++) {
                // i 为专家A,j 为专家B
                rs = KyfzRelationshipMapper.selectKyfzRelationship_twoId(expertAccounts[i],
                        expertAccounts[j]);
                if (rs != null) {
                    KyfzRelationships.add(rs);
                }
            }
        }

        // 构造json数据
        // 构造"nodes" 和 构造"categories"
        JSONArray nodesArray_categories = new JSONArray();
        JSONObject[] node_categories = new JSONObject[expertAccounts.length];
        JSONArray nodesArray = new JSONArray();
        JSONObject[] node = new JSONObject[expertAccounts.length];
        double centerX = 50.0; // 中心点X坐标
        double centerY = 50.0; // 中心点Y坐标
        double minRadius = 5.0; // 最小半径
        double maxRadius = 30.0; // 最大半径

        Random random = new Random();
        double radius;
        double angle;
        double randomX;
        double randomY;

        for (int i = 0; i < expertAccounts.length; i++) {
            node[i] = new JSONObject(); // 初始化每个索引位置的JSONObject对象
            node_categories[i] = new JSONObject();// 初始化每个索引位置的JSONObject对象
            node[i].put("id", expertAccounts[i]);
            node[i].put("name", expertName[i]);
            node[i].put("symbolSize", 30.0 + weight[i] * 250);
            radius = random.nextDouble() * (maxRadius - minRadius) + minRadius;
            angle = random.nextDouble() * 2 * Math.PI;
            randomX = centerX + radius * Math.cos(angle);
            randomY = centerY + radius * Math.sin(angle);
            node[i].put("x", randomX);
            node[i].put("y", randomY);
            node[i].put("value", "权重:" + weight[i]);
            node[i].put("category", i);
            nodesArray.add(node[i]);
            node_categories[i].put("name", expertName[i]);
            nodesArray_categories.add(node_categories[i]);
        }

        // 构造"links"
        JSONArray nodesArray_links = new JSONArray();
        JSONObject[] node_links = new JSONObject[KyfzRelationships.size()];
        for (int i = 0; i < KyfzRelationships.size(); i++) {
            node_links[i] = new JSONObject(); // 初始化每个索引位置的JSONObject对象
            node_links[i].put("source", "" + KyfzRelationships.get(i).getExpertAId());
            node_links[i].put("target", "" + KyfzRelationships.get(i).getExpertBId());
            nodesArray_links.add(node_links[i]);
        }

        // 创建最终的 JSON 对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categories", nodesArray_categories);
        jsonObject.put("links", nodesArray_links);
        jsonObject.put("nodes", nodesArray);

        return jsonObject;
    }
}
