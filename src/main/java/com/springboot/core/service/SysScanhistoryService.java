package com.springboot.core.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.core.common.base.BaseService;
import com.springboot.core.common.support.ConvertUtil;
import com.springboot.core.mapper.auto.SysScanhistoryMapper;
import com.springboot.core.model.auto.SysScanhistory;
import com.springboot.core.model.auto.SysScanhistoryExample;
import com.springboot.core.model.custom.Tablepar;

import cn.hutool.core.util.StrUtil;

/**
 * 扫描记录 SysScanhistoryService
 * @Title: SysScanhistoryService.java 
 * @Package com.springboot.core.service 
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-06 16:14:42  
 **/
@Service
public class SysScanhistoryService implements BaseService<SysScanhistory, SysScanhistoryExample>{
	@Autowired
	private SysScanhistoryMapper sysScanhistoryMapper;
	
      	   	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<SysScanhistory> list(Tablepar tablepar,SysScanhistory sysScanhistory){
	        SysScanhistoryExample testExample=new SysScanhistoryExample();
			//搜索
			if(StrUtil.isNotEmpty(tablepar.getSearchText())) {//小窗体
	        	testExample.createCriteria().andLikeQuery2(tablepar.getSearchText());
	        }else {//大搜索
	        	testExample.createCriteria().andLikeQuery(sysScanhistory);
	        }

	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<SysScanhistory> list= sysScanhistoryMapper.selectByExample(testExample);
	        PageInfo<SysScanhistory> pageInfo = new PageInfo<SysScanhistory>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			SysScanhistoryExample example=new SysScanhistoryExample();
			example.createCriteria().andIdIn(stringB);
			return sysScanhistoryMapper.deleteByExample(example);	
	}
	
	
	@Override
	public SysScanhistory selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return sysScanhistoryMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(SysScanhistory record) {
		return sysScanhistoryMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(SysScanhistory record) {
				
		record.setId(null);
		
				
		return sysScanhistoryMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(SysScanhistory record, SysScanhistoryExample example) {
		
		return sysScanhistoryMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(SysScanhistory record, SysScanhistoryExample example) {
		
		return sysScanhistoryMapper.updateByExample(record, example);
	}

	@Override
	public List<SysScanhistory> selectByExample(SysScanhistoryExample example) {
		
		return sysScanhistoryMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(SysScanhistoryExample example) {
		
		return sysScanhistoryMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(SysScanhistoryExample example) {
		
		return sysScanhistoryMapper.deleteByExample(example);
	}
}
