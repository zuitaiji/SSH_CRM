package com.ithc.service;

import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Sys_User;
import com.ithc.dao.Sys_UserDao;
import com.ithc.util.Base64;
import com.ithc.util.State;

@Transactional
public class Sys_UserServiceImpl implements Sys_UserService {
	// 注入dao
	private Sys_UserDao sys_UserDao;

	public void setSys_UserDao(Sys_UserDao sys_UserDao) {
		this.sys_UserDao = sys_UserDao;
	}

	/**
	 * 查询单个
	 */
	public Sys_User select(Sys_User sys_user) {
		// 登入时也需要把密码加密
		String user_password = sys_user.getUser_password();

		try {
			String encode = Base64.encode(user_password);
			sys_user.setUser_password(encode);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sys_UserDao.select(sys_user);
	}

	/**
	 * 注册校验登入名
	 */
	public Sys_User findByCode(Sys_User sys_user) {

		return sys_UserDao.findByCode(sys_user);
	}

	/**
	 * 注册
	 */
	public void save(Sys_User sys_user) {
		// 添加状态
		sys_user.setUser_state(State.getState());
		// 密码加密：base64
		// 先查询密码
		String user_password = sys_user.getUser_password();
		try {
			String encode = Base64.encode(user_password);

			// 再把加密后的数据重新赋值给对象
			sys_user.setUser_password(encode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sys_UserDao.save(sys_user);

	}

	/**
	 * 用id查询
	 */
	public Sys_User findById(Sys_User sys_user) {
		return sys_UserDao.findById(sys_user);
	}

	/**
	 * 修改密码
	 */
	public void update(Sys_User sys_user) {
		// 密码加密：base64
		// 先查询密码
		String user_password = sys_user.getUser_password();
		try {
			String encode = Base64.encode(user_password);

			// 再把加密后的数据重新赋值给对象
			sys_user.setUser_password(encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sys_UserDao.update(sys_user);
	}

}
