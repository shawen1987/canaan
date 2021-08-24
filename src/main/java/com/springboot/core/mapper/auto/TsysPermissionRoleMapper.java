package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.TsysPermissionRole;
import com.springboot.core.model.auto.TsysPermissionRoleExample;

@Mapper
public interface TsysPermissionRoleMapper {
    int countByExample(TsysPermissionRoleExample example);

    int deleteByExample(TsysPermissionRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysPermissionRole record);

    int insertSelective(TsysPermissionRole record);

    List<TsysPermissionRole> selectByExample(TsysPermissionRoleExample example);

    TsysPermissionRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysPermissionRole record, @Param("example") TsysPermissionRoleExample example);

    int updateByExample(@Param("record") TsysPermissionRole record, @Param("example") TsysPermissionRoleExample example);

    int updateByPrimaryKeySelective(TsysPermissionRole record);

    int updateByPrimaryKey(TsysPermissionRole record);
}