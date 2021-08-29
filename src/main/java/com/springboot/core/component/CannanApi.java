package com.springboot.core.component;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * 嘉楠API调用的共通类
 * @author sha.wen
 *
 */
@Component
public class CannanApi {

	@Autowired
    RestTemplate restTemplate;
    
	@Value("${canaan.server}")
	private String CANAAN_SERVER;
	
	// GET请求
	public final static int REQUEST_TYPE_GET = 1;
	
	// POST请求
	public final static int REQUEST_TYPE_POST = 2;
	
	// 返回结果的JSON-KEY
	public final static String RESPONSE_KEY = "resp";
	
	// 请求在线设备API
	public final static String API_ONLINE = "api/online";
	
	// 查看设备详情API
	public final static String API_FIRMWARE = "api/firmware";
	
	// 更改设备名称API
	public final static String API_MODIFYDEVICE = "api/8";
	
	// 上传特征API
	public final static String API_UPLOADFACE = "api/5";
	
	// 同步识别记录API
	public final static String API_GETRECORDS = "api/record";
	
	// 本地特征值提取API
	public final static String API_EXTRACTLOCAL = "api/extractLocal";
	
	// 上传base64图片
	public final static String API_SINGLEBASE64 = "upload/singleBase64";
	
	// 删除设备特征API
	public final static String API_DELETEFACE = "api/6";
	
	// 更新设备时间API
	public final static String API_UPDATETIME = "api/9";
	
	// 查询设备信息API
	public final static String API_DEVICEINFO = "api/7";
	
	/**
	 * 向嘉楠服务端发起请求
	 * @param api api接口名称
	 * @param paramsMap 请求参数
	 * @param requestType 请求类型
	 * @return
	 */
	public JSONObject sendRequest(String api, Map<String, Object> paramsMap, int requestType) {
		String responseEntity = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		if (REQUEST_TYPE_GET == requestType) {
			responseEntity = restTemplate.getForObject(CANAAN_SERVER + api, String.class, paramsMap);
		} else {
			HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(new JSONObject(paramsMap), headers);
			responseEntity = restTemplate.postForObject(CANAAN_SERVER + api, formEntity, String.class);
		}
		
		if (StringUtils.isNotEmpty(responseEntity)) {
			return JSONObject.parseObject(responseEntity);
		}
		
		return null;
	}
}
