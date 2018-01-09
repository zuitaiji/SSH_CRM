package com.ithc.json;

import com.alibaba.fastjson.annotation.JSONField;

public class Role {
	
	private String rname;
	// 解决栈溢出
	@JSONField(serialize=false)
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	@Override
	public String toString() {
		return "Role [rname=" + rname + ", user=" + user + "]";
	}
}
