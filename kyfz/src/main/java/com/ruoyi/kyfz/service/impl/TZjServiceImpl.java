package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.TZjMapper;
import com.ruoyi.kyfz.domain.TZj;
import com.ruoyi.kyfz.service.ITZjService;

/**
 * 专家信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class TZjServiceImpl implements ITZjService 
{
    @Autowired
    private TZjMapper tZjMapper;

    /**
     * 查询专家信息
     * 
     * @param id 专家信息主键
     * @return 专家信息
     */
    @Override
    public TZj selectTZjById(Long id)
    {
        return tZjMapper.selectTZjById(id);
    }

    /**
     * 查询专家信息列表
     * 
     * @param tZj 专家信息
     * @return 专家信息
     */
    @Override
    public List<TZj> selectTZjList(TZj tZj)
    {
        return tZjMapper.selectTZjList(tZj);
    }

    /**
     * 新增专家信息
     * 
     * @param tZj 专家信息
     * @return 结果
     */
    @Override
    public int insertTZj(TZj tZj)
    {
        tZj.setCreateTime(DateUtils.getNowDate());
        return tZjMapper.insertTZj(tZj);
    }

    /**
     * 修改专家信息
     * 
     * @param tZj 专家信息
     * @return 结果
     */
    @Override
    public int updateTZj(TZj tZj)
    {
        tZj.setUpdateTime(DateUtils.getNowDate());
        return tZjMapper.updateTZj(tZj);
    }

    /**
     * 批量删除专家信息
     * 
     * @param ids 需要删除的专家信息主键
     * @return 结果
     */
    @Override
    public int deleteTZjByIds(Long[] ids)
    {
        return tZjMapper.deleteTZjByIds(ids);
    }

    /**
     * 删除专家信息信息
     * 
     * @param id 专家信息主键
     * @return 结果
     */
    @Override
    public int deleteTZjById(Long id)
    {
        return tZjMapper.deleteTZjById(id);
    }
}
