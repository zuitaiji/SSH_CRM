package com.ithc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.Sys_User;
import com.ithc.service.Sys_UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("all")
public class Sys_UserAction extends ActionSupport implements ModelDriven<Sys_User>{
	private Sys_User sys_user = new Sys_User();
	@Override
	public Sys_User getModel() {
		return sys_user;
	}
	
	// 注入service
	private Sys_UserService sys_UserService;
	public void setSys_UserService(Sys_UserService sys_UserService) {
		this.sys_UserService = sys_UserService;
	}
	
	//验证码属性
	private String viCode;
	public void setViCode(String viCode) {
		this.viCode = viCode;
	}

	HttpSession session;
	/**
	 *  登入
	 * @return
	 */
	public String login(){
		session = ServletActionContext.getRequest().getSession();
		//获取后台生成的验证码
		String vcode = (String) session.getAttribute("validateCode");
		if(vcode.equalsIgnoreCase(viCode)){
			Sys_User sys_user = sys_UserService.select(this.sys_user);
			if(sys_user != null){
			
				// 保存当前用户（页面只需要查询真实名字）
				session.setAttribute("existUser", sys_user);
				
				return "index";
			}else{
				session.setAttribute("admin","账号或者密码错误");
				return "login";
			}
		}
		session.setAttribute("msg","验证码错误");
		return "login";
	}
	
	/**
	 *  校验登入名
	 * @throws Exception 
	 */
	public String checkCode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		Sys_User sys_user= sys_UserService.findByCode(this.sys_user);
		if(sys_user == null){
			writer.print(1);
		}
		return null;
	}
	
	/**
	 *  注册
	 *   1.需要手动添加状态 user_state
	 *   2.需要把密码加密
	 */
	public String register(){
		
		sys_UserService.save(this.sys_user);
		
		return "login";
	}
	
	/**
	 *  修改密码 : 
	 */
	
	public String updatePassword(){
		
		
		return "updatePassword";
	}
	
	
	
	/**
	 *  修改提交
	 */
	public String password(){
		session = ServletActionContext.getRequest().getSession();
		// 获取session里面登入时存入的对象
		Sys_User user  = (Sys_User) session.getAttribute("existUser");
		//修改密码时 ， 提交过来的数据只有一个密码属性，需要自己添加值
		this.sys_user.setUser_id(user.getUser_id());
		this.sys_user.setUser_code(user.getUser_code());
		this.sys_user.setUser_name(user.getUser_name());
		this.sys_user.setUser_state(user.getUser_state());
		sys_UserService.update(this.sys_user);
		return "login";
	}
	/**
	 *  安全退出
	 */
	public String exit(){
		// 销毁
		session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("existUser");
		return "login";
	}
}
