package com.ithc.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	public static void main(String[] args) throws Exception {
		//加密
		String encode = encode("abc");
		System.out.println("加密 = "+encode);
		//解密
		String decode = decode(encode);
		System.out.println("解密 = "+decode);
	}
	
	//加密
	public static String encode(String value) throws Exception{
		
		return new BASE64Encoder().encode(value.getBytes("utf-8"));
	}
	
	//解密
	public static String decode(String value) throws Exception{
		byte[] by = new BASE64Decoder().decodeBuffer(value);
		return new String(by,"utf-8");
	}

}
