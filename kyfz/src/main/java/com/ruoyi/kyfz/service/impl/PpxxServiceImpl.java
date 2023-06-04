package com.ruoyi.kyfz.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kyfz.mapper.PpxxMapper;
import com.ruoyi.kyfz.domain.Ppxx;
import com.ruoyi.kyfz.service.IPpxxService;

/**
 * 匹配列表Service业务层处理
 * 
 * @author zhu
 * @date 2023-06-04
 */
@Service
public class PpxxServiceImpl implements IPpxxService 
{
    @Autowired
    private PpxxMapper ppxxMapper;

    /**
     * 查询匹配列表
     * 
     * @param id 匹配列表主键
     * @return 匹配列表
     */
    @Override
    public Ppxx selectPpxxById(Long id)
    {
        return ppxxMapper.selectPpxxById(id);
    }

    /**
     * 查询匹配列表列表
     * 
     * @param ppxx 匹配列表
     * @return 匹配列表
     */
    @Override
    public List<Ppxx> selectPpxxList(Ppxx ppxx)
    {
        return ppxxMapper.selectPpxxList(ppxx);
    }

    /**
     * 新增匹配列表
     * 
     * @param ppxx 匹配列表
     * @return 结果
     */
    @Override
    public int insertPpxx(Ppxx ppxx)
    {
        ppxx.setCreateTime(DateUtils.getNowDate());
        return ppxxMapper.insertPpxx(ppxx);
    }

    /**
     * 修改匹配列表
     * 
     * @param ppxx 匹配列表
     * @return 结果
     */
    @Override
    public int updatePpxx(Ppxx ppxx)
    {
        ppxx.setUpdateTime(DateUtils.getNowDate());
        return ppxxMapper.updatePpxx(ppxx);
    }

    /**
     * 批量删除匹配列表
     * 
     * @param ids 需要删除的匹配列表主键
     * @return 结果
     */
    @Override
    public int deletePpxxByIds(Long[] ids)
    {
        return ppxxMapper.deletePpxxByIds(ids);
    }

    /**
     * 删除匹配列表信息
     * 
     * @param id 匹配列表主键
     * @return 结果
     */
    @Override
    public int deletePpxxById(Long id)
    {
        return ppxxMapper.deletePpxxById(id);
    }
}
