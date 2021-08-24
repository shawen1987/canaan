package com.springboot.core.model.auto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.date.DateUtil;
import java.util.Date;

public class SysScanhistory implements Serializable {
    private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "主键")
	private Integer id;
	
	@ApiModelProperty(value = "人脸特征主键")
	private Integer faceId;
	
	@ApiModelProperty(value = "部门主键")
	private Integer departmentId;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "工号")
	private String number;
	
	@ApiModelProperty(value = "设备ID")
	private String deviceId;
	
	@ApiModelProperty(value = "设备名称")
	private String deviceName;
	
	@ApiModelProperty(value = "部门名称")
	private String departmentName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "扫描时间")
	private Date scanTime;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id =  id;
	}
	@JsonProperty("faceId")
	public Integer getFaceId() {
		return faceId;
	}

	public void setFaceId(Integer faceId) {
		this.faceId =  faceId;
	}
	@JsonProperty("departmentId")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId =  departmentId;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name =  name;
	}
	@JsonProperty("number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number =  number;
	}
	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId =  deviceId;
	}
	@JsonProperty("deviceName")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName =  deviceName;
	}
	@JsonProperty("departmentName")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName =  departmentName;
	}
	@JsonProperty("scanTime")
	public Date getScanTime() {
		return scanTime;
	}

	public void setScanTime(Date scanTime) {
		this.scanTime =  scanTime;
	}

	public SysScanhistory() {
	    super();
	}

	public String dateToStringConvert(Date date) {
		if(date!=null) {
			return DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	

}