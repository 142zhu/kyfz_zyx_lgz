package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.kyfz.domain.KyfzProject;
import com.ruoyi.kyfz.mapper.KyfzProjectMapper;
import com.ruoyi.kyfz.service.IKyfzProjectService;

/**
 * 项目信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-18
 */
@Service
public class KyfzProjectServiceImpl implements IKyfzProjectService {
    @Autowired
    private KyfzProjectMapper kyfzProjectMapper;

    /**
     * 查询项目信息
     * 
     * @param projectId 项目信息主键
     * @return 项目信息
     */
    @Override
    public KyfzProject selectKyfzProjectByProjectId(Long projectId) {
        return kyfzProjectMapper.selectKyfzProjectByProjectId(projectId);
    }

    /**
     * 查询项目信息列表
     * 
     * @param kyfzProject 项目信息
     * @return 项目信息
     */
    @Override
    public List<KyfzProject> selectKyfzProjectList(KyfzProject kyfzProject) {
        return kyfzProjectMapper.selectKyfzProjectList(kyfzProject);
    }

    /**
     * 新增项目信息
     * 
     * @param kyfzProject 项目信息
     * @return 结果
     */
    @Override
    public int insertKyfzProject(KyfzProject kyfzProject) {
        kyfzProject.setCreateTime(DateUtils.getNowDate());
        return kyfzProjectMapper.insertKyfzProject(kyfzProject);
    }

    /**
     * 修改项目信息
     * 
     * @param kyfzProject 项目信息
     * @return 结果
     */
    @Override
    public int updateKyfzProject(KyfzProject kyfzProject) {
        kyfzProject.setUpdateTime(DateUtils.getNowDate());
        return kyfzProjectMapper.updateKyfzProject(kyfzProject);
    }

    /**
     * 批量删除项目信息
     * 
     * @param projectIds 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzProjectByProjectIds(Long[] projectIds) {
        return kyfzProjectMapper.deleteKyfzProjectByProjectIds(projectIds);
    }

    /**
     * 删除项目信息信息
     * 
     * @param projectId 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteKyfzProjectByProjectId(Long projectId) {
        return kyfzProjectMapper.deleteKyfzProjectByProjectId(projectId);
    }

    /**
     * 项目id列表查询项目信息列表
     * 
     * @param projectId 项目信息
     * @return 项目信息
     */
    @Override
    public List<KyfzProject> selectKyfzProjectList_Projectidlist(Long[] projectId) {
        return kyfzProjectMapper.selectKyfzProjectList_Projectidlist(projectId);
    }
}
