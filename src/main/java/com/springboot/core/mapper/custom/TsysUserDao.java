package com.springboot.core.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.core.model.auto.TsysUser;

/**
 * @ClassName: TsysUserDao
 * @author shawen
 * @date 2018年8月25日
 *
 */
@Mapper
public interface TsysUserDao {
	/**
	 * 根据用户名字查询用户
	 * @param username
	 * @return
	 */
	public TsysUser queryUserName(String username);
	
	/**
	 * 查询用户详情
	 * String name 如果没用 注解@Param("") 它到mapper里面为_parameter
	 * @return
	 * @author shawen
	 * @Date 2020年12月6日 下午9:02:20
	 */
	public List<TsysUser> queryUserInfo(String username);
}
