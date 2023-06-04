package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.TTsjl;

/**
 * 推送记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface TTsjlMapper 
{
    /**
     * 查询推送记录
     * 
     * @param id 推送记录主键
     * @return 推送记录
     */
    public TTsjl selectTTsjlById(Long id);

    /**
     * 查询推送记录列表
     * 
     * @param tTsjl 推送记录
     * @return 推送记录集合
     */
    public List<TTsjl> selectTTsjlList(TTsjl tTsjl);

    /**
     * 新增推送记录
     * 
     * @param tTsjl 推送记录
     * @return 结果
     */
    public int insertTTsjl(TTsjl tTsjl);

    /**
     * 修改推送记录
     * 
     * @param tTsjl 推送记录
     * @return 结果
     */
    public int updateTTsjl(TTsjl tTsjl);

    /**
     * 删除推送记录
     * 
     * @param id 推送记录主键
     * @return 结果
     */
    public int deleteTTsjlById(Long id);

    /**
     * 批量删除推送记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTTsjlByIds(Long[] ids);
}
