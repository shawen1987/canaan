package com.springboot.core.mapper.auto;

import com.springboot.core.model.auto.SysDeviceDepart;
import com.springboot.core.model.auto.SysDeviceDepartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门设备关系表 SysDeviceDepartMapper
 * @author sha.wen_自动生成
 * @email ${email}
 * @date 2021-08-02 18:29:26
 */
@Mapper
public interface SysDeviceDepartMapper {
      	   	      	      	      
    long countByExample(SysDeviceDepartExample example);

    int deleteByExample(SysDeviceDepartExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(SysDeviceDepart record);

    int insertSelective(SysDeviceDepart record);

    List<SysDeviceDepart> selectByExample(SysDeviceDepartExample example);
		
    SysDeviceDepart selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") SysDeviceDepart record, @Param("example") SysDeviceDepartExample example);

    int updateByExample(@Param("record") SysDeviceDepart record, @Param("example") SysDeviceDepartExample example); 
		
    int updateByPrimaryKeySelective(SysDeviceDepart record);

    int updateByPrimaryKey(SysDeviceDepart record);
  	  	
}