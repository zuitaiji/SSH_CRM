package com.ithc.dao;

import com.ithc.bean.Sys_User;

public interface Sys_UserDao {
	/**
	 *  查询单个
	 * @param sys_user
	 * @return
	 */
	Sys_User select(Sys_User sys_user);
	/**
	 *  注册校验登入名
	 * @param sys_user
	 * @return
	 */
	Sys_User findByCode(Sys_User sys_user);
	/**
	 *  注册
	 * @param sys_user
	 */
	void save(Sys_User sys_user);
	/**
	 * 用id查询
	 * @param sys_user
	 * @return
	 */
	Sys_User findById(Sys_User sys_user);
	/**
	 *  修改密码
	 */
	void update(Sys_User sys_user);

}
