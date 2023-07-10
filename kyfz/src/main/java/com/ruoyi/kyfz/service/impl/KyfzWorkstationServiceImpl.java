package com.ruoyi.kyfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.kyfz.domain.KyfzWorkstation;
import com.ruoyi.kyfz.mapper.KyfzWorkstationMapper;
import com.ruoyi.kyfz.service.IKyfzWorkstationService;

/**
 * 工作站Service业务层处理
 * 
 * @author zyx
 * @date 2023-07-10
 */
@Service
public class KyfzWorkstationServiceImpl implements IKyfzWorkstationService 
{
    @Autowired
    private KyfzWorkstationMapper kyfzWorkstationMapper;

    /**
     * 查询工作站
     * 
     * @param taskId 工作站主键
     * @return 工作站
     */
    @Override
    public KyfzWorkstation selectKyfzWorkstationByTaskId(Long taskId)
    {
        return kyfzWorkstationMapper.selectKyfzWorkstationByTaskId(taskId);
    }

    /**
     * 查询工作站列表
     * 
     * @param kyfzWorkstation 工作站
     * @return 工作站
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<KyfzWorkstation> selectKyfzWorkstationList(KyfzWorkstation kyfzWorkstation)
    {
        return kyfzWorkstationMapper.selectKyfzWorkstationList(kyfzWorkstation);
    }

    /**
     * 新增工作站
     * 
     * @param kyfzWorkstation 工作站
     * @return 结果
     */
    @Override
    public int insertKyfzWorkstation(KyfzWorkstation kyfzWorkstation)
    {
        kyfzWorkstation.setUserId(SecurityUtils.getUserId());
        kyfzWorkstation.setUpdateTime(DateUtils.getNowDate());
        return kyfzWorkstationMapper.insertKyfzWorkstation(kyfzWorkstation);
    }

    /**
     * 修改工作站
     * 
     * @param kyfzWorkstation 工作站
     * @return 结果
     */
    @Override
    public int updateKyfzWorkstation(KyfzWorkstation kyfzWorkstation)
    {
        kyfzWorkstation.setUpdateTime(DateUtils.getNowDate());
        return kyfzWorkstationMapper.updateKyfzWorkstation(kyfzWorkstation);
    }

    /**
     * 批量删除工作站
     * 
     * @param taskIds 需要删除的工作站主键
     * @return 结果
     */
    @Override
    public int deleteKyfzWorkstationByTaskIds(Long[] taskIds)
    {
        return kyfzWorkstationMapper.deleteKyfzWorkstationByTaskIds(taskIds);
    }

    /**
     * 删除工作站信息
     * 
     * @param taskId 工作站主键
     * @return 结果
     */
    @Override
    public int deleteKyfzWorkstationByTaskId(Long taskId)
    {
        return kyfzWorkstationMapper.deleteKyfzWorkstationByTaskId(taskId);
    }
}
