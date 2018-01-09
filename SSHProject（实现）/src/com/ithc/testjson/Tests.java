package com.ithc.testjson;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Tests {
	
	@Test
	public void run1(){
		Person person = new Person();
		person.setPid(1);
		person.setPname("美美");
		
		String jsonString = JSON.toJSONString(person);
		System.out.println(jsonString);
	}
	
	@Test
	public void run2(){
		List<Person> list = new ArrayList<Person>();
		Person person = new Person();
		person.setPid(1);
		person.setPname("美美");
		Person person2 = new Person();
		person2.setPid(2);
		person2.setPname("冠希");
		list.add(person);
		list.add(person2);
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);
	}
	
	/**
	 *  测试循环引用:把同一个对象应用多次,但是会产生一个死循环的问题
	 */
	@Test
	public void run3(){
		List<Person> list = new ArrayList<Person>();
		Person person = new Person();
		person.setPid(1);
		person.setPname("美美");
		
		list.add(person);
		list.add(person);
		//解决循环引用的问题 添加 SerializerFeature.DisableCircularReferenceDetect
		String jsonString = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
	/**
	 *  解决死循环:
	 *  需要到某一方设置  : 如果你要查询角色，你需要到对方角色属性上面添加
	 *  @JSONField(serialize=false)
		private Role role;
	 */
	
	@Test
	public void run4(){
		Person person = new Person();
		person.setPid(1);
		person.setPname("美美");
		
		Role role = new Role();
		role.setRid(1);
		role.setRname("瞎子");
		
		person.setRole(role);
		role.setPerson(person);
		String jsonString = JSON.toJSONString(role,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
