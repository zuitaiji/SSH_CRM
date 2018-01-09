package com.ithc.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.Sys_User;

public class Sys_UserDaoImpl extends HibernateDaoSupport implements Sys_UserDao {

	private Sys_User sys_users;
	/**
	 *  查询单个
	 */
	public Sys_User select(Sys_User sys_user) {
		List<Sys_User> list = (List<Sys_User>) this.getHibernateTemplate().find("from Sys_User where user_code = ? and user_password = ? and user_state = ?",sys_user.getUser_code(),sys_user.getUser_password(),"1");
		if(list.size() > 0){
			sys_users = list.get(0);
			return sys_users;
		}
		return null;
	}
	/**
	 *  注册校验登入名
	 */
	public Sys_User findByCode(Sys_User sys_user) {
		
		List<Sys_User> list = (List<Sys_User>) this.getHibernateTemplate().find("from Sys_User where user_code = ?", sys_user.getUser_code());
		if(list.size() > 0){
			sys_users = list.get(0);
			return sys_users;
		}
		return null;
	}
	/**
	 *  注册
	 */
	public void save(Sys_User sys_user) {
		
		this.getHibernateTemplate().save(sys_user);
		
	}
	/**
	 *  用id查询
	 */
	public Sys_User findById(Sys_User sys_user) {
		
		Sys_User user = this.getHibernateTemplate().get(Sys_User.class,sys_user.getUser_id());
		
		if(user != null){
			return user;
		}
		return null;
	}
	/**
	 *  修改密码
	 */
	public void update(Sys_User sys_user) {
		this.getHibernateTemplate().update(sys_user);
	}
	
	
	

}
