package com.ithc.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("existUser");
		
		if(user == null){
			return "login";
		}
		return arg0.invoke();
	}

}
