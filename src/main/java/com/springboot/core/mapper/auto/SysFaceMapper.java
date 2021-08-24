package com.springboot.core.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.core.model.auto.SysFace;
import com.springboot.core.model.auto.SysFaceExample;

/**
 * 人脸特征 SysFaceMapper
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-02 15:32:50
 */
@Mapper
public interface SysFaceMapper {
      	   	      	      	      	      	      	      	      	      	      	      	      
    long countByExample(SysFaceExample example);

    int deleteByExample(SysFaceExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(SysFace record);

    int insertSelective(SysFace record);

    List<SysFace> selectByExample(SysFaceExample example);
		
    SysFace selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") SysFace record, @Param("example") SysFaceExample example);

    int updateByExample(@Param("record") SysFace record, @Param("example") SysFaceExample example); 
		
    int updateByPrimaryKeySelective(SysFace record);

    int updateByPrimaryKey(SysFace record);
  	  	
}