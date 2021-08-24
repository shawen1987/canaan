package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.SysPosition;
import com.springboot.core.model.auto.SysPositionExample;

@Mapper
public interface SysPositionMapper {
      	   	      	      	      	      
    long countByExample(SysPositionExample example);

    int deleteByExample(SysPositionExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysPosition record);

    int insertSelective(SysPosition record);

    List<SysPosition> selectByExample(SysPositionExample example);
		
    SysPosition selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysPosition record, @Param("example") SysPositionExample example);

    int updateByExample(@Param("record") SysPosition record, @Param("example") SysPositionExample example); 
		
    int updateByPrimaryKeySelective(SysPosition record);

    int updateByPrimaryKey(SysPosition record);
  	  	
}