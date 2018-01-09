package com.ithc.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TestJson {
	@Test	
	public void run1(){
		
		User user = new User();
		user.setUage(18);
		user.setUname("小苍");
		
		String string = JSON.toJSONString(user);
		System.out.println(string);
	}
	@Test
	public void run2(){
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setUage(18);
		user.setUname("小苍");
		
		User user2 = new User();
		user2.setUage(35);
		user2.setUname("小野");
		
		list.add(user);
		list.add(user2);
		
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	/**
	 *  循环引用
	 */
	@Test
	public void run3(){
		User user = new User();
		user.setUage(18);
		user.setUname("小苍");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	/**
	 *  解决循环引用 : SerializerFeature.DisableCircularReferenceDetect
	 */
	@Test
	public void run4(){
		User user = new User();
		user.setUage(18);
		user.setUname("小苍");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
	
	/**
	 *  多对一
	 */
	@Test
	public void run5(){
		User user = new User();
		user.setUage(18);
		user.setUname("小苍");
		
		Role role = new Role();
		role.setRname("红狗");
		role.setUser(user);
		
		Role role2 = new Role();
		role2.setRname("下水道三帅");
		role2.setUser(user);
		
		user.getRoles().add(role);
		user.getRoles().add(role2);
		
		String jsonString = JSON.toJSONString(user,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
		
	}
	
	
	
	
	
	
	
	
	
	

}
