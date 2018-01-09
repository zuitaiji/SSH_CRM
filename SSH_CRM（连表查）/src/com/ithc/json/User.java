package com.ithc.json;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	
	private String uname;
	private Integer uage;
	
	private Set<Role> roles = new HashSet<Role>();
	
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getUage() {
		return uage;
	}
	public void setUage(Integer uage) {
		this.uage = uage;
	}
	@Override
	public String toString() {
		return "User [uname=" + uname + ", uage=" + uage + ", roles=" + roles + "]";
	}
}
