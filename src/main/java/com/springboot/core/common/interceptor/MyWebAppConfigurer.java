package com.springboot.core.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer  extends  WebMvcConfigurationSupport  {

	/** 这里配置视图解析器 **/
	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry){
		super.configureViewResolvers(registry);
	}
	
	/** 配置内容裁决的一些选项 **/
	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer){
		super.configureContentNegotiation(configurer);
	}
	
	/** 视图跳转控制器 **/
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		
		super.addViewControllers(registry);
	}
	
	
	/** 静态资源处理 **/
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		//配置虚拟路径为项目得static下面
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");      
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);

    }
	/** 默认静态资源处理器 **/

	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		super.configureDefaultServletHandling(configurer);
	}




}
