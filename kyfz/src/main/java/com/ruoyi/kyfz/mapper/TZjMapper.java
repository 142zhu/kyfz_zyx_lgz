package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.TZj;

/**
 * 专家信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface TZjMapper 
{
    /**
     * 查询专家信息
     * 
     * @param id 专家信息主键
     * @return 专家信息
     */
    public TZj selectTZjById(Long id);

    /**
     * 查询专家信息列表
     * 
     * @param tZj 专家信息
     * @return 专家信息集合
     */
    public List<TZj> selectTZjList(TZj tZj);

    /**
     * 新增专家信息
     * 
     * @param tZj 专家信息
     * @return 结果
     */
    public int insertTZj(TZj tZj);

    /**
     * 修改专家信息
     * 
     * @param tZj 专家信息
     * @return 结果
     */
    public int updateTZj(TZj tZj);

    /**
     * 删除专家信息
     * 
     * @param id 专家信息主键
     * @return 结果
     */
    public int deleteTZjById(Long id);

    /**
     * 批量删除专家信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTZjByIds(Long[] ids);
}
