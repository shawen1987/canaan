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
import com.springboot.core.mapper.auto.SysDeviceDepartMapper;
import com.springboot.core.model.auto.SysDeviceDepart;
import com.springboot.core.model.auto.SysDeviceDepartExample;
import com.springboot.core.model.custom.Tablepar;
import com.springboot.core.util.SnowflakeIdWorker;
import com.springboot.core.util.StringUtils;

/**
 * 部门设备关系表 SysDeviceDepartService
 * @Title: SysDeviceDepartService.java 
 * @Package com.springboot.core.service 
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-02 18:29:26  
 **/
@Service
public class SysDeviceDepartService implements BaseService<SysDeviceDepart, SysDeviceDepartExample>{
	@Autowired
	private SysDeviceDepartMapper sysDeviceDepartMapper;
	
      	   	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<SysDeviceDepart> list(Tablepar tablepar,SysDeviceDepart sysDeviceDepart){
	        SysDeviceDepartExample testExample=new SysDeviceDepartExample();
			//搜索
			if(StrUtil.isNotEmpty(tablepar.getSearchText())) {//小窗体
	        	testExample.createCriteria().andLikeQuery2(tablepar.getSearchText());
	        }else {//大搜索
	        	testExample.createCriteria().andLikeQuery(sysDeviceDepart);
	        }
			//表格排序
			//if(StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
	        //	testExample.setOrderByClause(StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) +" "+tablepar.getIsAsc());
	        //}else{
	        //	testExample.setOrderByClause("id ASC");
	        //}
	        PageHelper.startPage(tablepar.getPage(), tablepar.getLimit());
	        List<SysDeviceDepart> list= sysDeviceDepartMapper.selectByExample(testExample);
	        PageInfo<SysDeviceDepart> pageInfo = new PageInfo<SysDeviceDepart>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			SysDeviceDepartExample example=new SysDeviceDepartExample();
			example.createCriteria().andIdIn(stringB);
			return sysDeviceDepartMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public SysDeviceDepart selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return sysDeviceDepartMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(SysDeviceDepart record) {
		return sysDeviceDepartMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(SysDeviceDepart record) {
				
		record.setId(null);
		
				
		return sysDeviceDepartMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(SysDeviceDepart record, SysDeviceDepartExample example) {
		
		return sysDeviceDepartMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(SysDeviceDepart record, SysDeviceDepartExample example) {
		
		return sysDeviceDepartMapper.updateByExample(record, example);
	}

	@Override
	public List<SysDeviceDepart> selectByExample(SysDeviceDepartExample example) {
		
		return sysDeviceDepartMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(SysDeviceDepartExample example) {
		
		return sysDeviceDepartMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(SysDeviceDepartExample example) {
		
		return sysDeviceDepartMapper.deleteByExample(example);
	}


}
