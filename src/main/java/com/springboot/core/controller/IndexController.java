package com.springboot.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.core.common.base.BaseController;

/**
 * 如果有前台这儿写前台访问方法
 * @ClassName: IndexController
 * @author shawen
 * @date 2019-10-21 00:15
 */
@Api(tags = "首页模块")
@Controller
public class IndexController extends BaseController{
	
	/**
	 * 前台访问 域名:端口 例如:localhost:80的get请求
	 * @param map
	 * @return
	 * @author shawen
	 * @Date 2019年11月20日 下午10:55:13
	 */
	@ApiOperation(value="前台",notes="前台")
	@GetMapping("/")
	public String index(ModelMap map) {
		
		//直接访问后台用
		return "redirect:/admin/login";
	}
}
