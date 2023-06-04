package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.TTsjlMapper;
import com.ruoyi.kyfz.domain.TTsjl;
import com.ruoyi.kyfz.service.ITTsjlService;

/**
 * 推送记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class TTsjlServiceImpl implements ITTsjlService 
{
    @Autowired
    private TTsjlMapper tTsjlMapper;

    /**
     * 查询推送记录
     * 
     * @param id 推送记录主键
     * @return 推送记录
     */
    @Override
    public TTsjl selectTTsjlById(Long id)
    {
        return tTsjlMapper.selectTTsjlById(id);
    }

    /**
     * 查询推送记录列表
     * 
     * @param tTsjl 推送记录
     * @return 推送记录
     */
    @Override
    public List<TTsjl> selectTTsjlList(TTsjl tTsjl)
    {
        return tTsjlMapper.selectTTsjlList(tTsjl);
    }

    /**
     * 新增推送记录
     * 
     * @param tTsjl 推送记录
     * @return 结果
     */
    @Override
    public int insertTTsjl(TTsjl tTsjl)
    {
        tTsjl.setCreateTime(DateUtils.getNowDate());
        return tTsjlMapper.insertTTsjl(tTsjl);
    }

    /**
     * 修改推送记录
     * 
     * @param tTsjl 推送记录
     * @return 结果
     */
    @Override
    public int updateTTsjl(TTsjl tTsjl)
    {
        tTsjl.setUpdateTime(DateUtils.getNowDate());
        return tTsjlMapper.updateTTsjl(tTsjl);
    }

    /**
     * 批量删除推送记录
     * 
     * @param ids 需要删除的推送记录主键
     * @return 结果
     */
    @Override
    public int deleteTTsjlByIds(Long[] ids)
    {
        return tTsjlMapper.deleteTTsjlByIds(ids);
    }

    /**
     * 删除推送记录信息
     * 
     * @param id 推送记录主键
     * @return 结果
     */
    @Override
    public int deleteTTsjlById(Long id)
    {
        return tTsjlMapper.deleteTTsjlById(id);
    }
}
