package com.ithc.util;

import java.util.UUID;

public class UUIDName {
	
	public static String getName(String name){
		
		// 截取后缀
		int lastIndexOf = name.lastIndexOf(".");
		String substring = name.substring(lastIndexOf);
		String replace = UUID.randomUUID().toString().replace("-", "");
		return replace+substring;
	}

}
