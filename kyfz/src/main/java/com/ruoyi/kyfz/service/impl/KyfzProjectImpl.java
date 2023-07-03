package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.kyfz.domain.KyfzProject;
import com.ruoyi.kyfz.mapper.KyfzProjectMapper;
import com.ruoyi.kyfz.service.IKyfzProjectService;

@Service
public class KyfzProjectImpl implements IKyfzProjectService {
    @Autowired
    private KyfzProjectMapper kyfzProjectMapper;

    @Override
    public List<KyfzProject> selectKyfzProjectList(Long[] projectIds) {
        return kyfzProjectMapper.selectKyfzProjectList(projectIds);
    }

}
