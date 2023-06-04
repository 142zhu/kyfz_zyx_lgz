package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.Ppxx;

/**
 * 匹配列表Service接口
 * 
 * @author zhu
 * @date 2023-06-04
 */
public interface IPpxxService 
{
    /**
     * 查询匹配列表
     * 
     * @param id 匹配列表主键
     * @return 匹配列表
     */
    public Ppxx selectPpxxById(Long id);

    /**
     * 查询匹配列表列表
     * 
     * @param ppxx 匹配列表
     * @return 匹配列表集合
     */
    public List<Ppxx> selectPpxxList(Ppxx ppxx);

    /**
     * 新增匹配列表
     * 
     * @param ppxx 匹配列表
     * @return 结果
     */
    public int insertPpxx(Ppxx ppxx);

    /**
     * 修改匹配列表
     * 
     * @param ppxx 匹配列表
     * @return 结果
     */
    public int updatePpxx(Ppxx ppxx);

    /**
     * 批量删除匹配列表
     * 
     * @param ids 需要删除的匹配列表主键集合
     * @return 结果
     */
    public int deletePpxxByIds(Long[] ids);

    /**
     * 删除匹配列表信息
     * 
     * @param id 匹配列表主键
     * @return 结果
     */
    public int deletePpxxById(Long id);
}
