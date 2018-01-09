package com.ithc.service;

import java.util.List;

import com.ithc.bean.User;

public interface UserService {
	/**
	 * 登入 
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 *  校验登入名
	 * @param user
	 * @return
	 */
	User checkCode(User user);
	/**
	 *  注册
	 * @param user
	 */
	void register(User user);
	/**
	 *  用id查询
	 * @param user_id
	 * @return
	 */
	User findById(Long user_id);
	/**
	 *  修改密码
	 * @param user
	 */
	void updatePassword(User user);
	/**
	 *  查询所有
	 * @return
	 */
	List<User> findAll();

}
