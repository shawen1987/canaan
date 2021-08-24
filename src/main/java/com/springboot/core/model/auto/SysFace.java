package com.springboot.core.model.auto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.date.DateUtil;
import java.util.Date;

public class SysFace implements Serializable {
    private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value = "主键")
	private Integer id;
	
	@ApiModelProperty(value = "部门主键")
	private Integer departmentId;
	
	@ApiModelProperty(value = "部门名称")
	private String departmentName;
	
	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "工号")
	private String number;
	
	@ApiModelProperty(value = "人脸特征")
	private String eigen;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "状态")
	private Integer status;
	
	@ApiModelProperty(value = "创建者")
	private String createUser;
	
	@ApiModelProperty(value = "更新者")
	private String updateUser;
	
	@ApiModelProperty(value = "人脸特征")
	private String base64;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id =  id;
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

	@JsonProperty("eigen")
	public String getEigen() {
		return eigen;
	}

	public void setEigen(String eigen) {
		this.eigen =  eigen;
	}
	@JsonProperty("createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime =  createTime;
	}
	@JsonProperty("updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime =  updateTime;
	}
	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status =  status;
	}
	@JsonProperty("createUser")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser =  createUser;
	}
	@JsonProperty("updateUser")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser =  updateUser;
	}

																						
	public SysFace(Integer id,Integer departmentId,String name,String number,String eigen,Date createTime,Date updateTime,Integer status,String createUser,String updateUser) {
				
		this.id = id;
				
		this.departmentId = departmentId;
				
		this.name = name;
				
		this.number = number;
				
		this.eigen = eigen;
				
		this.createTime = createTime;
				
		this.updateTime = updateTime;
				
		this.status = status;
				
		this.createUser = createUser;
				
		this.updateUser = updateUser;
				
	}

	public SysFace() {
	    super();
	}

	public String dateToStringConvert(Date date) {
		if(date!=null) {
			return DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
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
	 * @return the base64
	 */
	public String getBase64() {
		return base64;
	}

	/**
	 * @param base64 the base64 to set
	 */
	public void setBase64(String base64) {
		this.base64 = base64;
	}
}