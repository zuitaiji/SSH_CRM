package com.ithc.testjson;

import com.alibaba.fastjson.annotation.JSONField;

public class Person {
	
	private Integer pid;
	private String pname;
	
	@JSONField(serialize=false)
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", role=" + role + "]";
	}
}
