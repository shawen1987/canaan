package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.SysNoticeUser;
import com.springboot.core.model.auto.SysNoticeUserExample;

@Mapper
public interface SysNoticeUserMapper {
      	      	   	      	   	      	   	      
    long countByExample(SysNoticeUserExample example);

    int deleteByExample(SysNoticeUserExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysNoticeUser record);

    int insertSelective(SysNoticeUser record);

    List<SysNoticeUser> selectByExample(SysNoticeUserExample example);
		
    SysNoticeUser selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysNoticeUser record, @Param("example") SysNoticeUserExample example);

    int updateByExample(@Param("record") SysNoticeUser record, @Param("example") SysNoticeUserExample example); 
		
    int updateByPrimaryKeySelective(SysNoticeUser record);

    int updateByPrimaryKey(SysNoticeUser record);
  	  	
}