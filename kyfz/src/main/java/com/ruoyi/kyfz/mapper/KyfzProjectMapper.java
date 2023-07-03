package com.ruoyi.kyfz.mapper;

import java.util.List;

import com.ruoyi.kyfz.domain.KyfzProject;

public interface KyfzProjectMapper {
    /**
     * 查询项目列表
     * 
     * @param KyfzProjectImpl 项目
     * @return 项目集合
     */
    public List<KyfzProject> selectKyfzProjectList(Long[] projectIds);
}
