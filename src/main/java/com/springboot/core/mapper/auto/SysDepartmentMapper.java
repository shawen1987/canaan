package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.SysDepartment;
import com.springboot.core.model.auto.SysDepartmentExample;

@Mapper
public interface SysDepartmentMapper {
      	   	      	      	      	      	      	      	      	      
    long countByExample(SysDepartmentExample example);

    int deleteByExample(SysDepartmentExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    List<SysDepartment> selectByExample(SysDepartmentExample example);
		
    SysDepartment selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example);

    int updateByExample(@Param("record") SysDepartment record, @Param("example") SysDepartmentExample example); 
		
    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);
  	  	
}