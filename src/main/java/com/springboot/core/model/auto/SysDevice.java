package com.springboot.core.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 设备表 SysDevice
 * 
 * @author shawen_自动生成
 * @email 303209232@qq.com
 * @date 2020-04-17 13:12:58
 */
@ApiModel(value = "SysDevice", description = "设备表")
public class SysDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 设备名称 **/
	@ApiModelProperty(value = "设备名称")
	private String deviceName;

	/** 设备ID**/
	@ApiModelProperty(value = "设备ID")
	private String deviceID;

	/** 信号时长**/
	@ApiModelProperty(value = "信号时长")
	private int openTime;
	
	/** 所属部门名称**/
	@ApiModelProperty(value = "所属部门名称")
	private String departmentName;
	
	/** 所属部门ID**/
	@ApiModelProperty(value = "所属部门ID")
	private String depId;
	
	/** 特征数**/
	@ApiModelProperty(value = "特征数")
	private String featureCount;
	
	/** 识别记录数**/
	@ApiModelProperty(value = "识别记录数")
	private String recordCount;
	
	/** 活体检测状态 1：打开 0：关闭**/
	@ApiModelProperty(value = "活体检测状态")
	private int activeStatus;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the openTime
	 */
	public int getOpenTime() {
		return openTime;
	}

	/**
	 * @param openTime the openTime to set
	 */
	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	/**
	 * @return the featureCount
	 */
	public String getFeatureCount() {
		return featureCount;
	}

	/**
	 * @param featureCount the featureCount to set
	 */
	public void setFeatureCount(String featureCount) {
		this.featureCount = featureCount;
	}

	/**
	 * @return the recordCount
	 */
	public String getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the depId
	 */
	public String getDepId() {
		return depId;
	}

	/**
	 * @param depId the depId to set
	 */
	public void setDepId(String depId) {
		this.depId = depId;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
}