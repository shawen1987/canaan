package com.springboot.core.service;

import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.StrUtil;
import com.springboot.core.common.base.BaseService;
import com.springboot.core.common.support.ConvertUtil;
import com.springboot.core.mapper.auto.SysFaceMapper;
import com.springboot.core.model.auto.SysFace;
import com.springboot.core.model.auto.SysFaceExample;
import com.springboot.core.model.custom.Tablepar;
import com.springboot.core.util.SnowflakeIdWorker;
import com.springboot.core.util.StringUtils;

/**
 * 人脸特征 SysFaceService
 * @Title: SysFaceService.java 
 * @Package com.springboot.core.service 
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-02 15:32:50  
 **/
@Service
public class SysFaceService implements BaseService<SysFace, SysFaceExample>{
	@Autowired
	private SysFaceMapper sysFaceMapper;
	
      	   	      	      	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<SysFace> list(Tablepar tablepar,SysFace sysFace){
	        SysFaceExample testExample=new SysFaceExample();
			//搜索
			if(StrUtil.isNotEmpty(tablepar.getSearchText())) {//小窗体
	        	testExample.createCriteria().andLikeQuery2(tablepar.getSearchText());
	        }else {//大搜索
	        	testExample.createCriteria().andLikeQuery(sysFace);
	        }
			//表格排序
			//if(StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
	        //	testExample.setOrderByClause(StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) +" "+tablepar.getIsAsc());
	        //}else{
	        //	testExample.setOrderByClause("id ASC");
	        //}
	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<SysFace> list= sysFaceMapper.selectByExample(testExample);
	        PageInfo<SysFace> pageInfo = new PageInfo<SysFace>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			SysFaceExample example=new SysFaceExample();
			example.createCriteria().andIdIn(stringB);
			return sysFaceMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public SysFace selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return sysFaceMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(SysFace record) {
		return sysFaceMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(SysFace record) {
				
		record.setId(null);
		
				
		return sysFaceMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(SysFace record, SysFaceExample example) {
		
		return sysFaceMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(SysFace record, SysFaceExample example) {
		
		return sysFaceMapper.updateByExample(record, example);
	}

	@Override
	public List<SysFace> selectByExample(SysFaceExample example) {
		
		return sysFaceMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(SysFaceExample example) {
		
		return sysFaceMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(SysFaceExample example) {
		
		return sysFaceMapper.deleteByExample(example);
	}


}
