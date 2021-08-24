package com.springboot.core.model.auto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.date.DateUtil;
import java.util.Date;

public class SysDeviceDepart implements Serializable {
    private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "主键")
	private Integer id;
	
	@ApiModelProperty(value = "部门主键")
	private Integer deptId;
	
	@ApiModelProperty(value = "设备ID")
	private String deviceId;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id =  id;
	}
	@JsonProperty("deptId")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId =  deptId;
	}
	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId =  deviceId;
	}

						
	public SysDeviceDepart(Integer id,Integer deptId,String deviceId) {
				
		this.id = id;
				
		this.deptId = deptId;
				
		this.deviceId = deviceId;
				
	}

	public SysDeviceDepart() {
	    super();
	}

	

}