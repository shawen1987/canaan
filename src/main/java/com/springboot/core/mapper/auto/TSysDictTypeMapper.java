package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.TSysDictType;
import com.springboot.core.model.auto.TSysDictTypeExample;

@Mapper
public interface TSysDictTypeMapper {
    long countByExample(TSysDictTypeExample example);

    int deleteByExample(TSysDictTypeExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(TSysDictType record);

    int insertSelective(TSysDictType record);

    List<TSysDictType> selectByExample(TSysDictTypeExample example);
		
    TSysDictType selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") TSysDictType record, @Param("example") TSysDictTypeExample example);

    int updateByExample(@Param("record") TSysDictType record, @Param("example") TSysDictTypeExample example); 
		
    int updateByPrimaryKeySelective(TSysDictType record);

    int updateByPrimaryKey(TSysDictType record);
}