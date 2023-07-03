package com.ruoyi.kyfz.service;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzProject;

public interface IKyfzProjectService {

    /**
     * 查询项目列表
     * 
     * @param KyfzProjectImpl 项目
     * @return 项目集合
     */
    public List<KyfzProject> selectKyfzProjectList(Long[] projectIds);
}
