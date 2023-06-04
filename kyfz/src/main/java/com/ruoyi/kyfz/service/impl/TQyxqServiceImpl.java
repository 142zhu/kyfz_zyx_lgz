package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.TQyxqMapper;
import com.ruoyi.kyfz.domain.TQyxq;
import com.ruoyi.kyfz.service.ITQyxqService;

/**
 * 企业需求信息Service业务层处理
 * 
 * @author zhu
 * @date 2023-06-04
 */
@Service
public class TQyxqServiceImpl implements ITQyxqService 
{
    @Autowired
    private TQyxqMapper tQyxqMapper;

    /**
     * 查询企业需求信息
     * 
     * @param id 企业需求信息主键
     * @return 企业需求信息
     */
    @Override
    public TQyxq selectTQyxqById(Long id)
    {
        return tQyxqMapper.selectTQyxqById(id);
    }

    /**
     * 查询企业需求信息列表
     * 
     * @param tQyxq 企业需求信息
     * @return 企业需求信息
     */
    @Override
    public List<TQyxq> selectTQyxqList(TQyxq tQyxq)
    {
        return tQyxqMapper.selectTQyxqList(tQyxq);
    }

    /**
     * 新增企业需求信息
     * 
     * @param tQyxq 企业需求信息
     * @return 结果
     */
    @Override
    public int insertTQyxq(TQyxq tQyxq)
    {
        tQyxq.setCreateTime(DateUtils.getNowDate());
        return tQyxqMapper.insertTQyxq(tQyxq);
    }

    /**
     * 修改企业需求信息
     * 
     * @param tQyxq 企业需求信息
     * @return 结果
     */
    @Override
    public int updateTQyxq(TQyxq tQyxq)
    {
        tQyxq.setUpdateTime(DateUtils.getNowDate());
        return tQyxqMapper.updateTQyxq(tQyxq);
    }

    /**
     * 批量删除企业需求信息
     * 
     * @param ids 需要删除的企业需求信息主键
     * @return 结果
     */
    @Override
    public int deleteTQyxqByIds(Long[] ids)
    {
        return tQyxqMapper.deleteTQyxqByIds(ids);
    }

    /**
     * 删除企业需求信息信息
     * 
     * @param id 企业需求信息主键
     * @return 结果
     */
    @Override
    public int deleteTQyxqById(Long id)
    {
        return tQyxqMapper.deleteTQyxqById(id);
    }
}
