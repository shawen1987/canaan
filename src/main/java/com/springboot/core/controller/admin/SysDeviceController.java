package com.springboot.core.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ListUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.springboot.core.common.base.BaseController;
import com.springboot.core.common.domain.AjaxResult;
import com.springboot.core.common.domain.ResultTable;
import com.springboot.core.component.CannanApi;
import com.springboot.core.model.auto.SysDepartment;
import com.springboot.core.model.auto.SysDevice;
import com.springboot.core.model.auto.SysDeviceDepart;
import com.springboot.core.model.auto.SysDeviceDepartExample;
import com.springboot.core.model.auto.SysFace;
import com.springboot.core.model.auto.SysFaceExample;
import com.springboot.core.model.auto.SysFaceExample.Criteria;
import com.springboot.core.model.auto.SysScanhistory;
import com.springboot.core.model.custom.Tablepar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "设备表")
@Controller
@RequestMapping("/SysDeviceController")
public class SysDeviceController extends BaseController{
	
	private final String prefix = "admin/device";
	
	@Autowired
	private CannanApi cannanApi;
	
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:sysDevice:view")
    public String view(ModelMap model)
    {
		return prefix + "/list";
    }
	
	/**
	 * 根据ID查找设备
	 * @param deviceId
	 * @return
	 */
	private SysDevice getDeviceById(String deviceId) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("did", deviceId);
		JSONObject deviceDetailEntity = cannanApi.sendRequest(CannanApi.API_FIRMWARE, paramsMap, CannanApi.REQUEST_TYPE_POST);
		SysDevice device = new SysDevice();
		device.setDeviceID(String.valueOf(deviceId));
		device.setDeviceName(deviceDetailEntity.getString("name"));
		device.setOpenTime(deviceDetailEntity.getIntValue("openTime") / 100);
		device.setFeatureCount(deviceDetailEntity.getString("featureCount"));
		device.setRecordCount(deviceDetailEntity.getString("recordCount"));
		device.setActiveStatus(deviceDetailEntity.getIntValue("aliveCheck"));
		
		SysDeviceDepartExample example = new SysDeviceDepartExample();
		example.createCriteria().andDeviceIdEqualTo(String.valueOf(deviceId));
		List<SysDeviceDepart> deviceDepartList = sysDeviceDepartService.selectByExample(example);
		if (!ListUtils.isEmpty(deviceDepartList)) {
			int deptId = deviceDepartList.get(0).getDeptId();
			SysDepartment sysDepartment = sysDepartmentService.selectByPrimaryKey(String.valueOf(deptId));
			device.setDepartmentName(sysDepartment.getDeptName());
			device.setDepId(String.valueOf(deptId));
		}
		
		return device;
	}
	
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/list")
	@RequiresPermissions("gen:sysDevice:list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar, String deviceID, String deviceName) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		JSONObject responseEntity = cannanApi.sendRequest(CannanApi.API_ONLINE, paramsMap, CannanApi.REQUEST_TYPE_GET);

		List<Long> totalDeviceIdList = new ArrayList<Long>();
		List<SysDevice> deviceList = new ArrayList<SysDevice>();
		
		int startIndex = (tablepar.getPage() - 1) * tablepar.getLimit() + 1;
		int endIndex = tablepar.getPage() * tablepar.getLimit();
		
		if (responseEntity != null) {
			Long[] diviceIdArray = responseEntity.getObject("resp", new TypeReference<Long[]>(){});
			if (diviceIdArray != null) {
				int index = 0;
				for (Long deviceId : diviceIdArray) {
					totalDeviceIdList.add(deviceId);
					index++;
					if (index >= startIndex && index <= endIndex) {
						SysDevice device = getDeviceById(String.valueOf(deviceId));
						boolean filterFlag = false;
						if (StringUtils.isNotEmpty(deviceID) && !device.getDeviceID().contains(deviceID)) {
							filterFlag = true;
						}
						if (StringUtils.isNotEmpty(deviceName) && !device.getDeviceName().contains(deviceName)) {
							filterFlag = true;
						}
						
						if (!filterFlag) {
				    		deviceList.add(device);
						}
					}
				}
			}
		}
		
		return pageTable(deviceList, totalDeviceIdList.size());
	}
	
	/**
	 * 修改设备跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String deviceId, ModelMap mmap)
    {
		SysDevice device = getDeviceById(deviceId);
        mmap.put("sysDevice", device);
        return prefix + "/edit";
    }
	
	/**
     * 修改保存设备
     */
    @ApiOperation(value = "修改保存设备", notes = "修改保存设备")
    @RequiresPermissions("gen:sysDevice:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysDevice sysDevice)
    {
    	// 先删除设备所属的旧部门
		SysDeviceDepartExample example = new SysDeviceDepartExample();
		example.createCriteria().andDeviceIdEqualTo(sysDevice.getDeviceID());
		sysDeviceDepartService.deleteByExample(example);
		
		SysDeviceDepart deviceDepart = new SysDeviceDepart();
		deviceDepart.setDeptId(Integer.parseInt(sysDevice.getDepId()));
		deviceDepart.setDeviceId(sysDevice.getDeviceID());
		sysDeviceDepartService.insertSelective(deviceDepart);
		
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("did", sysDevice.getDeviceID());
    	paramsMap.put("p1", "2");
    	paramsMap.put("p2", sysDevice.getDeviceName());
    	// 更改设备名称
    	cannanApi.sendRequest(CannanApi.API_MODIFYDEVICE, paramsMap, CannanApi.REQUEST_TYPE_POST);    	
    	
    	paramsMap.put("p1", "0");
    	paramsMap.put("p2", sysDevice.getOpenTime());
    	// 更改识别模式
    	cannanApi.sendRequest(CannanApi.API_MODIFYDEVICE, paramsMap, CannanApi.REQUEST_TYPE_POST);

        return toAjax(1);
    }
    
	/**
     * 同步特征到设备
     */
    @ApiOperation(value = "同步特征到设备", notes = "同步特征到设备")
    @RequiresPermissions("gen:sysDevice:edit")
    @RequestMapping("/uploadFace")
    @ResponseBody
    public AjaxResult uploadFace(String deviceID)
    {
    	SysDeviceDepartExample example = new SysDeviceDepartExample();
    	example.createCriteria().andDeviceIdEqualTo(deviceID);
    	List<SysDeviceDepart> deviceDepartList = sysDeviceDepartService.selectByExample(example);
    	for (SysDeviceDepart deviceDepart : deviceDepartList) { 
    		SysFaceExample faceCondition = new SysFaceExample();
    		Criteria criteria= faceCondition.createCriteria();
    		criteria.andDepartmentIdEqualTo(deviceDepart.getDeptId());
    		criteria.andStatusEqualTo(1);
    		List<SysFace> faceList = sysFaceService.selectByExample(faceCondition);
        	for (SysFace face : faceList) {
	    		Map<String, Object> paramsMap = new HashMap<String, Object>();
	        	paramsMap.put("did", deviceID);
	        	paramsMap.put("p1", face.getId());
	        	paramsMap.put("p2", "1");
	        	paramsMap.put("feature", face.getBase64());
	        	paramsMap.put("uname", face.getName());
	        	cannanApi.sendRequest(CannanApi.API_UPLOADFACE, paramsMap, CannanApi.REQUEST_TYPE_POST);
        	}
    	}

        return success("同步特征数据成功");
    }
    
	/**
     * 下载识别记录
     */
    @ApiOperation(value = "下载识别记录", notes = "下载识别记录")
    @RequiresPermissions("gen:sysDevice:edit")
    @RequestMapping("/downloadRecord")
    @ResponseBody
    public AjaxResult downloadRecord(String deviceID)
    {
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("did", deviceID);
    	SysDevice device = getDeviceById(deviceID);
    	Map<String, SysFace> faceMap = new HashMap<String, SysFace>();
    	Map<Integer, SysDepartment> departmentMap = new HashMap<Integer, SysDepartment>();

    	
    	JSONObject response = cannanApi.sendRequest(CannanApi.API_GETRECORDS, paramsMap, CannanApi.REQUEST_TYPE_POST);
    	int recordLength = response.getJSONObject("resp").getIntValue("length");
    	if (recordLength > 0) {
	    	JSONArray records = response.getJSONObject("resp").getJSONArray("data");
	    	for (int i = 0; i < records.size(); i++) {
	    		JSONObject record = records.getJSONObject(i);
	    		// 特征ID
	    		String uid = record.getString("uid");
	    		// 识别时间
	    		long scanTime = record.getLongValue("createTime");

	    		if (!faceMap.containsKey(uid)) {
	    			SysFace selectFace = sysFaceService.selectByPrimaryKey(uid);
	    			faceMap.put(uid, selectFace);
	    		}
	    		SysFace face = faceMap.get(uid);
	    		
	    		SysScanhistory scanhistory = new SysScanhistory();
	    		scanhistory.setName(face.getName());
	    		scanhistory.setDepartmentId(face.getDepartmentId());
	    		if (!departmentMap.containsKey(face.getDepartmentId())) {
	    			SysDepartment department = sysDepartmentService.selectByPrimaryKey(String.valueOf(face.getDepartmentId()));
	    			departmentMap.put(face.getDepartmentId(), department);
	    		}
	    		SysDepartment department = departmentMap.get(face.getDepartmentId());
	    		scanhistory.setDepartmentName(department.getDeptName());
	    		scanhistory.setDeviceId(deviceID);
	    		scanhistory.setDeviceName(device.getDeviceName());
	    		scanhistory.setFaceId(Integer.parseInt(uid));
	    		scanhistory.setNumber(face.getNumber());
	    		scanhistory.setScanTime(new Date(scanTime));
	    		sysScanhistoryService.insertSelective(scanhistory);
	    	}
    	}
    	
        return toAjax(1);
    }
    
	/**
	 * 设定设备所属部门
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "设定设备所属部门", notes = "设定设备所属部门")
	@GetMapping("/assign")
    public String assign()
    {
        return prefix + "/assign";
    }
	
	/**
     * 设定设备所属部门
     */
    @ApiOperation(value = "设定设备所属部门", notes = "设定设备所属部门")
    @RequiresPermissions("gen:sysDevice:edit")
    @PostMapping("/assign")
    @ResponseBody
    public AjaxResult assignSave(int depId, String deviceIds)
    {
    	String[] deviceIdArray = deviceIds.split(",");
    	for (String deviceId : deviceIdArray) {
    		// 先删除设备所属的旧部门
    		SysDeviceDepartExample example = new SysDeviceDepartExample();
    		example.createCriteria().andDeviceIdEqualTo(deviceId);
    		sysDeviceDepartService.deleteByExample(example);
    		
    		SysDeviceDepart deviceDepart = new SysDeviceDepart();
    		deviceDepart.setDeptId(depId);
    		deviceDepart.setDeviceId(deviceId);
    		sysDeviceDepartService.insertSelective(deviceDepart);
    	}
        return toAjax(1);
    }
    
    @PutMapping("/updateActiveStatus")
	@ResponseBody
    public AjaxResult updateActiveStatus(@RequestBody SysDevice sysDevice){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("did", sysDevice.getDeviceID());
		paramsMap.put("p1", "1");
		paramsMap.put("p2", sysDevice.getActiveStatus());
		cannanApi.sendRequest(CannanApi.API_MODIFYDEVICE, paramsMap, CannanApi.REQUEST_TYPE_POST);
		return toAjax(1);
	}
}
