package com.springboot.core.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.core.model.auto.TsysRole;

@Mapper
public interface RoleDao {
	/**
	 * 根据用户id查询角色
	 * @param userid
	 * @return
	 */
	public List<TsysRole> queryUserRole(String userid);
}
