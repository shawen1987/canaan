package com.springboot.core.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.core.common.base.BaseController;
import com.springboot.core.common.domain.AjaxResult;
import com.springboot.core.common.domain.ResuTree;
import com.springboot.core.common.domain.ResultTable;
import com.springboot.core.model.custom.TsysTables;
import com.springboot.core.model.custom.TsysTablesVo;
import com.springboot.core.model.custom.autocode.AutoCodeConfig;
import com.springboot.core.model.custom.autocode.AutoConfigModel;
import com.springboot.core.model.custom.autocode.BeanColumn;
import com.springboot.core.model.custom.autocode.TableInfo;
import com.springboot.core.service.GeneratorService;
import com.springboot.core.util.AutoCode.AutoCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码自动生成
 * 
 */
@Api(value = "代码自动生成")
@Controller
@RequestMapping("/autoCodeController")
public class AutoCodeController extends BaseController {

	private String prefix = "admin/autoCode";
	@Autowired
	private GeneratorService generatorService;
	
	/**
	 * 代码自动生成全局配置
	 * 
	 * @param model
	 * @return
	 * @author shawen
	 * @Date 2019年8月13日 上午12:34:30
	 */
	@ApiOperation(value = " 代码自动生成全局配置", notes = "代码自动生成全局配置")
	@GetMapping("/global")
	@RequiresPermissions("system:autocode:global")
	public String global(ModelMap modelMap) {
		modelMap.put("author", AutoCodeConfig.getConfig().getProperty("author"));
		modelMap.put("email", AutoCodeConfig.getConfig().getProperty("author"));
		modelMap.put("parentPath", AutoCodeConfig.getConfig().getProperty("parentPath"));
		return prefix + "/global";
	}

	/**
	 * 树结构查询所有表
	 * 
	 * @return
	 * @author shawen
	 * @Date 2021年1月15日 下午2:21:19
	 */
	@GetMapping("/selectTables")
	@ResponseBody
	public ResuTree selectTables() {
		List<TsysTables> list = generatorService.queryList(null);
		List<TsysTablesVo> TreeList = new ArrayList<TsysTablesVo>();
		for (int i = 0; i < list.size(); i++) {
			TsysTablesVo tablesVo = new TsysTablesVo(i + 1, -1, list.get(i).getTableName(), list.get(i).getEngine(),
					list.get(i).getTableComment(), list.get(i).getTableModel(), list.get(i).getCreateTime(),
					list.get(i).getTableName() + " > " + list.get(i).getTableComment());
			TreeList.add(tablesVo);
		}

		TsysTablesVo tables = new TsysTablesVo();
		tables.setTableModel("all");
		tables.setTableAndName("所有表");
		tables.setParentId(0);
		tables.setId(-1);
		TreeList.add(tables);
		return dataTree(TreeList);
	}

	/**
	 * 根据表查询表字段详情
	 * 
	 * @param tableName
	 * @return
	 * @author shawen
	 * @Date 2019年8月15日 上午1:10:42
	 */
	@ApiOperation(value = "根据表查询表字段详情", notes = "根据表查询表字段详情")
	@GetMapping("/queryTableInfo")
	@ResponseBody
	public ResultTable queryTableInfo(String tableName) {
		List<BeanColumn> list = generatorService.queryColumns2(tableName);
		return pageTable(list, list.size());
	}

	/**
	 * 生成文件
	 * 
	 * @author shawen
	 * @Date 2021年1月15日 下午2:21:55
	 */
	@PostMapping("/createAuto")
	@ResponseBody
	public AjaxResult createAuto(AutoConfigModel autoConfigModel) {
		// 根据表名查询表字段集合
		List<BeanColumn> list = generatorService.queryColumns2(autoConfigModel.getTableName());
		// 初始化表信息
		TableInfo tableInfo = new TableInfo(autoConfigModel.getTableName(), list, autoConfigModel.getTableComment());

		AutoCodeUtil.autoCodeOneModel(tableInfo, autoConfigModel);
		return AjaxResult.success();
	}
}