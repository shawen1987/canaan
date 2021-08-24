package com.springboot.core.service;

import cn.hutool.json.JSONUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.core.mapper.auto.GeneratorMapper;
import com.springboot.core.model.custom.Tablepar;
import com.springboot.core.model.custom.TsysTables;
import com.springboot.core.model.custom.autocode.BeanColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 自动生成代码Service
* @Title: GeneratorService.java
* @Package com.springboot.core.service
* @author shawen 
* @date 2019年5月9日 上午12:24:47
* @version V1.0 
 */
@Service
public class GeneratorService {
	@Autowired
	private GeneratorMapper generatorMapper;
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TsysTables> list(Tablepar tablepar,String searchText){
		 	PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
		 	List<TsysTables> list=  generatorMapper.queryList(searchText);
		    PageInfo<TsysTables> pageInfo = new PageInfo<TsysTables>(list);
		    return pageInfo;
	 }
	 

	 /**
	  * 查询具体某表信息
	  * @param tableName
	  * @return
	  */
	 public List<TsysTables> queryList(String tableName){
		return generatorMapper.queryList(tableName);
	 }
	
	/**
	  * 查询表详情
	  * @param tableName
	  * @return
	  */
	public List<BeanColumn> queryColumns2(String tableName){
		System.out.println("queryColumns2>>>"+JSONUtil.toJsonPrettyStr(generatorMapper.queryColumns3(tableName)));
		return generatorMapper.queryColumns2(tableName);
	 }
	
}
