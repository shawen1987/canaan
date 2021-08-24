package com.springboot.core.controller.gen;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.springboot.core.common.base.BaseController;
import com.springboot.core.common.domain.AjaxResult;
import com.springboot.core.common.domain.ResultTable;
import com.springboot.core.model.auto.SysScanhistory;
import com.springboot.core.model.auto.SysScanhistoryExample;
import com.springboot.core.model.auto.SysScanhistoryExample.Criteria;
import com.springboot.core.model.custom.Tablepar;
import com.springboot.core.service.SysScanhistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 扫描记录Controller
 * @ClassName: SysScanhistoryController
 * @author sha.wen
 * @date 2021-08-06 16:14:42
 */
@Api(value = "扫描记录")
@Controller
@RequestMapping("/SysScanhistoryController")
public class SysScanhistoryController extends BaseController{
	
	private String prefix = "gen/sysScanhistory";
	
	@Autowired
	private SysScanhistoryService sysScanhistoryService;
	
	
	/**
	 * 扫描记录页面展示
	 * @param model
	 * @return String
	 * @author sha.wen
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:sysScanhistory:view")
    public String view(ModelMap model)
    {
        return prefix + "/list";
    }
	
	/**
	 * list集合
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/list")
	@RequiresPermissions("gen:sysScanhistory:list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar,SysScanhistory sysScanhistory){
		PageInfo<SysScanhistory> page = sysScanhistoryService.list(tablepar, sysScanhistory) ; 
		return pageTable(page.getList(),page.getTotal());
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
    /**
     * 新增保存
     * @param 
     * @return
     */
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("gen:sysScanhistory:add")
	@ResponseBody
	public AjaxResult add(SysScanhistory sysScanhistory){
		int b=sysScanhistoryService.insertSelective(sysScanhistory);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 扫描记录删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@RequiresPermissions("gen:sysScanhistory:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysScanhistoryService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap map)
    {
        map.put("SysScanhistory", sysScanhistoryService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("gen:sysScanhistory:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysScanhistory sysScanhistory)
    {
        return toAjax(sysScanhistoryService.updateByPrimaryKeySelective(sysScanhistory));
    }
}
