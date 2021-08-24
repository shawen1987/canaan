package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.TSysDictData;
import com.springboot.core.model.auto.TSysDictDataExample;

@Mapper
public interface TSysDictDataMapper {
    long countByExample(TSysDictDataExample example);

    int deleteByExample(TSysDictDataExample example);
		
    int insert(TSysDictData record);

    int insertSelective(TSysDictData record);

    List<TSysDictData> selectByExample(TSysDictDataExample example);
		
    int updateByExampleSelective(@Param("record") TSysDictData record, @Param("example") TSysDictDataExample example);

    int updateByExample(@Param("record") TSysDictData record, @Param("example") TSysDictDataExample example);

    TSysDictData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSysDictData record);
}