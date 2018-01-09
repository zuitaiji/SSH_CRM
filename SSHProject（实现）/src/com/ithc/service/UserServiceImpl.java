package com.ithc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.User;
import com.ithc.dao.UserDao;
import com.ithc.utils.MDUtils;
import com.ithc.utils.State;

@Transactional
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 登入
	 */
	public User login(User user) {
		//因为密码需要加密
		//1.先把密码查出来
		String user_password = user.getUser_password();
		//把查出来的密码进行加密之后与数据库的密码比较，因为数据库的密码加过密
		String md5 = MDUtils.md5(user_password);
		user.setUser_password(md5);
		
		return userDao.login(user);
	}
	/**
	 *  校验登入名
	 */
	public User checkCode(User user) {
		return userDao.checkCode(user);
	}
	/**
	 *  注册
	 */
	public void register(User user) {
		//添加状态 
		user.setUser_state(State.getState());
		//密码加密
		String user_password = user.getUser_password();
		String md5 = MDUtils.md5(user_password);
		//设置加密后的密码
		user.setUser_password(md5);
		userDao.register(user);
	}
	/**
	 *  用id查询
	 */
	public User findById(Long user_id) {
		
		return userDao.findById(user_id);
	}
	/**
	 *  修改密码
	 */
	public void updatePassword(User user) {
		userDao.updatePassword(user);
	}
	/**
	 * 查询所有
	 */
	public List<User> findAll() {
		
		return userDao.findAll();
	}
	
	
	
}
