package com.springboot.core.controller.gen;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.core.common.base.BaseController;
import com.springboot.core.common.domain.AjaxResult;
import com.springboot.core.common.domain.ResultTable;
import com.springboot.core.component.CannanApi;
import com.springboot.core.model.auto.SysDepartment;
import com.springboot.core.model.auto.SysFace;
import com.springboot.core.model.custom.Tablepar;
import com.springboot.core.shiro.util.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 人脸特征Controller
 * @ClassName: SysFaceController
 * @author sha.wen
 * @date 2021-08-02 15:32:50
 */
@Api(value = "人脸特征")
@Controller
@RequestMapping("/SysFaceController")
public class SysFaceController extends BaseController{
	
	private String prefix = "gen/sysFace";
	
	/**
	 * 本地站点信息存储文件名称
	 */
	@Value("${canaan.faceimg-path}")
	private String FACEIMG_PATH;
	
	@Autowired
	private CannanApi cannanApi;
	
	/**
	 * 人脸特征页面展示
	 * @param model
	 * @return String
	 * @author sha.wen
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:sysFace:view")
    public String view(ModelMap model)
    {
        return prefix + "/list";
    }
	
	/**
	 * list集合
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "人脸特征", action = "111")
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/list")
	@RequiresPermissions("gen:sysFace:list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar,SysFace sysFace){
		PageInfo<SysFace> page=sysFaceService.list(tablepar,sysFace) ; 
		List<SysFace> sysFaceList = page.getList();
		for (SysFace face : sysFaceList) {
			SysDepartment department = sysDepartmentService.selectByPrimaryKey(String.valueOf(face.getDepartmentId()));
			face.setDepartmentName(department.getDeptName());
		}
		return pageTable(page.getList(),page.getTotal());
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
    /**
     * 新增保存
     * @param 
     * @return
     */
	//@Log(title = "人脸特征新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("gen:sysFace:add")
	@ResponseBody
	public AjaxResult add(SysFace sysFace, String depId){
		
		sysFace.setDepartmentId(Integer.parseInt(depId));
		sysFace.setCreateUser(ShiroUtils.getUser().getUsername());
		sysFace.setCreateTime(new Date());
		sysFace.setUpdateUser(ShiroUtils.getUser().getUsername());
		sysFace.setUpdateTime(new Date());
		sysFace.setStatus(1);
		int b=sysFaceService.insertSelective(sysFace);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 人脸特征删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "人脸特征删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@RequiresPermissions("gen:sysFace:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysFaceService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap map)
    {
        map.put("SysFace", sysFaceService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "人脸特征修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("gen:sysFace:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFace sysFace, String depId)
    {
		sysFace.setDepartmentId(Integer.parseInt(depId));
        return toAjax(sysFaceService.updateByPrimaryKeySelective(sysFace));
    }

	/**
     * 上传特征图片
     */
	@ApiOperation(value = "上传特征图片", notes = "上传特征图片")
    @RequiresPermissions("gen:sysFace:edit")
    @PostMapping("/uploadFaceImage")
    @ResponseBody
    public AjaxResult uploadFaceImg(@RequestParam(value = "file") MultipartFile file)
    {
		try {
			File imgFileDirFile = new File(FACEIMG_PATH);
			if (!imgFileDirFile.exists()) {
				imgFileDirFile.mkdirs();
			}
			
            File imgFile = new File(FACEIMG_PATH + "/" + file.getOriginalFilename());
            file.transferTo(imgFile);
            
    		Map<String, Object> paramsMap = new HashMap<String, Object>();
        	paramsMap.put("path", FACEIMG_PATH + "/" + file.getOriginalFilename());
        	paramsMap.put("type", "1");
        	JSONObject response = cannanApi.sendRequest(CannanApi.API_EXTRACTLOCAL, paramsMap, CannanApi.REQUEST_TYPE_POST);
        	if (StringUtils.isEmpty(response.getString("feature"))) {
        		return retobject(-1, "生成base64格式的图片字符串失败，请选择宽高为300px*400px的图片。");
        	}
	        return retobject(0, response.getString("feature"));
		} catch (IOException e) {
			return retobject(-1, "上传图片失败，请联系管理员。");
		}
    }
	
    @PutMapping("/updateStatus")
	@ResponseBody
    public AjaxResult updateStatus(@RequestBody SysFace sysFace){
		int i= sysFaceService.updateByPrimaryKeySelective(sysFace);
		 return toAjax(i);
	}
}
