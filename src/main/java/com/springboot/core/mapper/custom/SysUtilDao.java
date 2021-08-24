package com.springboot.core.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.core.model.custom.SQLAdapter;


@Mapper
public interface SysUtilDao {
	
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 * @author shawen
	 * @Date 2019年8月31日 下午6:15:08
	 */
	public int executeSQL(SQLAdapter sql);
	
	
	/**
	 * 查询sql
	 * @param sql
	 * @return
	 * @author shawen
	 * @Date 2020年4月10日 下午11:43:16
	 */
	public List<Map<Object,Object>> SelectExecuteSQL(SQLAdapter sql); 
}
