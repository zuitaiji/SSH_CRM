package com.ithc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

	/**
	 * 禁止循环引用
	 * @param object
	 * @return
	 */
	public static String JsonString(Object object){
		
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	}
	
}
