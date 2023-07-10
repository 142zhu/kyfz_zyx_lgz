package com.ruoyi.kyfz.mapper;

import java.util.List;
import com.ruoyi.kyfz.domain.KyfzWorkstation;

/**
 * 工作站Mapper接口
 * 
 * @author zyx
 * @date 2023-07-10
 */
public interface KyfzWorkstationMapper 
{
    /**
     * 查询工作站
     * 
     * @param taskId 工作站主键
     * @return 工作站
     */
    public KyfzWorkstation selectKyfzWorkstationByTaskId(Long taskId);

    /**
     * 查询工作站列表
     * 
     * @param kyfzWorkstation 工作站
     * @return 工作站集合
     */
    public List<KyfzWorkstation> selectKyfzWorkstationList(KyfzWorkstation kyfzWorkstation);

    /**
     * 新增工作站
     * 
     * @param kyfzWorkstation 工作站
     * @return 结果
     */
    public int insertKyfzWorkstation(KyfzWorkstation kyfzWorkstation);

    /**
     * 修改工作站
     * 
     * @param kyfzWorkstation 工作站
     * @return 结果
     */
    public int updateKyfzWorkstation(KyfzWorkstation kyfzWorkstation);

    /**
     * 删除工作站
     * 
     * @param taskId 工作站主键
     * @return 结果
     */
    public int deleteKyfzWorkstationByTaskId(Long taskId);

    /**
     * 批量删除工作站
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKyfzWorkstationByTaskIds(Long[] taskIds);
}
