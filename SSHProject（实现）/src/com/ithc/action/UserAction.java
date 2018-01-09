package com.ithc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.Customer;
import com.ithc.bean.User;
import com.ithc.service.UserService;
import com.ithc.utils.FastJsonUtil;
import com.ithc.utils.MDUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	// model == user
	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 属性驱动验证码
	 */
	private String viCode;

	public void setViCode(String viCode) {
		this.viCode = viCode;
	}

	/**
	 * 登入模块 思路:用登入的名字与密码去数据库查询，如果有就跳转到index.jsp
	 * 
	 * @return
	 */
	public String login() {
		// 登入名
		String user_code = user.getUser_code();
		// 登入密码
		String user_password = user.getUser_password();
		// 判断登入的用户名与密码是否为空
		HttpSession session = ServletActionContext.getRequest().getSession();
		String vcode = (String) session.getAttribute("validateCode");
		// 判断验证码, 不区分大小写
		if (user_code.trim().isEmpty() || user_code == null || user_password.trim().isEmpty() || user_password == null
				|| !vcode.equalsIgnoreCase(viCode)) {

			return "login";
		}
		User u = userService.login(user);
		// 如果用户不存在就回到登入页面
		if (u == null) {
			return "login";
		}

		// 登入之后要显示真实的名字，把对象存session里面然后去页面取值
		session.setAttribute("existUser", u);

		return "index";
	}

	/**
	 * 校验登入名
	 * 
	 * @throws Exception
	 */
	public String checkCode() throws Exception {
		// 查询注册的登入名
		String user_code = user.getUser_code();
		// 进行判断
		if (user_code == null || user_code.trim().isEmpty()) {
			return "register";
		}
		User u = userService.checkCode(user);
		System.out.println(u);
		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw = response.getWriter();
		if (u == null) {
			// 如果等于空说明不存在，此注册名可以使用
			pw.println(1);
		}
		return NONE;
	}

	/**
	 * 注册
	 */
	public String register() {
		String user_code = user.getUser_code();
		String user_name = user.getUser_name();
		String user_password = user.getUser_password();
		// 判断注册的信息是否为空
		if (user_code.trim().isEmpty() || user_code == null || user_name.trim().isEmpty() || user_name == null
				|| user_password.trim().isEmpty() || user_password == null) {
			return "register";
		}
		// 如果不为空取数据库添加值
		userService.register(user);

		return "login";
	}

	/**
	 * 安全退出登入 需要销毁存入session的对象
	 */
	public String exit() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("existUser");
		return "login";
	}

	/**
	 * 修改密码：修改之前先查询
	 */
	public String updatePassword() {
		Long user_id = user.getUser_id();
		// 用id查询用户
		user = userService.findById(user_id);
		return "updatePassword";
	}

	/**
	 * ajax判断输入的密码属否正确
	 * 
	 * @throws Exception
	 */
	public String checkPassword() throws Exception {
		User u = userService.login(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw = response.getWriter();
		if (u != null) {
			pw.println(1);
		} else {
			pw.println(0);
		}
		return NONE;
	}

	// 把输入的新密码属性驱动
	private String u_password;

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	/**
	 * 修改密码之前先查询一个对象，把查询出来的对象在放到update方法里面
	 * 
	 * @return
	 */
	public String password() {
		User u = userService.login(user);
		String md5 = MDUtils.md5(u_password);
		u.setUser_password(md5);
		userService.updatePassword(u);

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("existUser");
		return "login";
	}

	/**
	 * 查询所有
	 */
	public String findAll() {

		List<User> list = userService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		// 把数据写出去
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}

}
