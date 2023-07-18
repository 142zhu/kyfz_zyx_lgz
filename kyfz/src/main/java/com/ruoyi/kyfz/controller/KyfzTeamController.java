package com.ruoyi.kyfz.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kyfz.domain.KyfzTeam;
import com.ruoyi.kyfz.service.IKyfzTeamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专家团队Controller
 * 
 * @author zyx
 * @date 2023-07-18
 */
@RestController
@RequestMapping("/kyfz/team")
public class KyfzTeamController extends BaseController
{
    @Autowired
    private IKyfzTeamService kyfzTeamService;

    /**
     * 查询专家团队列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(KyfzTeam kyfzTeam)
    {
        startPage();
        List<KyfzTeam> list = kyfzTeamService.selectKyfzTeamList(kyfzTeam);
        return getDataTable(list);
    }

    /**
     * 导出专家团队列表
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:export')")
    @Log(title = "专家团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KyfzTeam kyfzTeam)
    {
        List<KyfzTeam> list = kyfzTeamService.selectKyfzTeamList(kyfzTeam);
        ExcelUtil<KyfzTeam> util = new ExcelUtil<KyfzTeam>(KyfzTeam.class);
        util.exportExcel(response, list, "专家团队数据");
    }

    /**
     * 获取专家团队详细信息
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(kyfzTeamService.selectKyfzTeamByTeamId(teamId));
    }

    /**
     * 新增专家团队
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:add')")
    @Log(title = "专家团队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KyfzTeam kyfzTeam)
    {
        return toAjax(kyfzTeamService.insertKyfzTeam(kyfzTeam));
    }

    /**
     * 修改专家团队
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:edit')")
    @Log(title = "专家团队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KyfzTeam kyfzTeam)
    {
        return toAjax(kyfzTeamService.updateKyfzTeam(kyfzTeam));
    }

    /**
     * 删除专家团队
     */
    @PreAuthorize("@ss.hasPermi('kyfz:team:remove')")
    @Log(title = "专家团队", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(kyfzTeamService.deleteKyfzTeamByTeamIds(teamIds));
    }
}
