package com.springboot.core.mapper.auto;

import com.springboot.core.model.auto.SysScanhistory;
import com.springboot.core.model.auto.SysScanhistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扫描记录 SysScanhistoryMapper
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-06 16:14:42
 */
@Mapper
public interface SysScanhistoryMapper {
      	   	      	      	      	      	      	      	      	      	      
    long countByExample(SysScanhistoryExample example);

    int deleteByExample(SysScanhistoryExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(SysScanhistory record);

    int insertSelective(SysScanhistory record);

    List<SysScanhistory> selectByExample(SysScanhistoryExample example);
		
    SysScanhistory selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") SysScanhistory record, @Param("example") SysScanhistoryExample example);

    int updateByExample(@Param("record") SysScanhistory record, @Param("example") SysScanhistoryExample example); 
		
    int updateByPrimaryKeySelective(SysScanhistory record);

    int updateByPrimaryKey(SysScanhistory record);
  	  	
}