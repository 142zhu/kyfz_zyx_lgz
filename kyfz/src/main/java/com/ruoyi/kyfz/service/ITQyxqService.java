package com.ruoyi.kyfz.service;

import java.util.List;
import com.ruoyi.kyfz.domain.TQyxq;

/**
 * 企业需求信息Service接口
 * 
 * @author zhu
 * @date 2023-06-04
 */
public interface ITQyxqService 
{
    /**
     * 查询企业需求信息
     * 
     * @param id 企业需求信息主键
     * @return 企业需求信息
     */
    public TQyxq selectTQyxqById(Long id);

    /**
     * 查询企业需求信息列表
     * 
     * @param tQyxq 企业需求信息
     * @return 企业需求信息集合
     */
    public List<TQyxq> selectTQyxqList(TQyxq tQyxq);

    /**
     * 新增企业需求信息
     * 
     * @param tQyxq 企业需求信息
     * @return 结果
     */
    public int insertTQyxq(TQyxq tQyxq);

    /**
     * 修改企业需求信息
     * 
     * @param tQyxq 企业需求信息
     * @return 结果
     */
    public int updateTQyxq(TQyxq tQyxq);

    /**
     * 批量删除企业需求信息
     * 
     * @param ids 需要删除的企业需求信息主键集合
     * @return 结果
     */
    public int deleteTQyxqByIds(Long[] ids);

    /**
     * 删除企业需求信息信息
     * 
     * @param id 企业需求信息主键
     * @return 结果
     */
    public int deleteTQyxqById(Long id);
}
