package com.ruoyi.kyfz.service;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzProject;

/**
 * 项目信息Service接口
 * 
 * @author ruoyi
 * @date 2023-08-18
 */
public interface IKyfzProjectService {
    /**
     * 查询项目信息
     * 
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    public KyfzProject selectKyfzProjectByProjectId(Long projectId);

    /**
     * 查询项目信息列表
     * 
     * @param kyfzProject 项目信息
     * @return 项目信息集合
     */
    public List<KyfzProject> selectKyfzProjectList(KyfzProject kyfzProject);

    /**
     * 新增项目信息
     * 
     * @param kyfzProject 项目信息
     * @return 结果
     */
    public int insertKyfzProject(KyfzProject kyfzProject);

    /**
     * 修改项目信息
     * 
     * @param kyfzProject 项目信息
     * @return 结果
     */
    public int updateKyfzProject(KyfzProject kyfzProject);

    /**
     * 批量删除项目信息
     * 
     * @param projectIds 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deleteKyfzProjectByProjectIds(Long[] projectIds);

    /**
     * 删除项目信息信息
     * 
     * @param projectId 项目信息主键
     * @return 结果
     */
    public int deleteKyfzProjectByProjectId(Long projectId);

    /**
     * 项目id列表查询项目信息列表
     * 
     * @param projectId 项目信息
     * @return 项目信息
     */
    public List<KyfzProject> selectKyfzProjectList_Projectidlist(Long[] projectId);
}
