
package com.ithc.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.User;
import com.ithc.utils.State;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	/**
	 *  登入
	 */
	public User login(User user) {
		//测试密码是否加密
		/*System.out.println(user.getUser_code() + " : "+user.getUser_password() );*/
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ? and user_password = ? and user_state = ?",user.getUser_code(),user.getUser_password(),State.getState());
		if(list.size() < 1){
			return null;
		}
		return list.get(0);
	}
	/**
	 *  校验登入名
	 */
	public User checkCode(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?",user.getUser_code());
		if(list.size() < 1){
			return null;
		}
		return list.get(0);
	}
	//注册
	public void register(User user) {
		this.getHibernateTemplate().save(user);
	}
	/**
	 *  用id查询
	 */
	public User findById(Long user_id) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_id = ?",user_id);
		
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 修改密码
	 */
	public void updatePassword(User user) {
		this.getHibernateTemplate().update(user);
	}
	/**
	 *  查询所有
	 */
	public List<User> findAll() {
		
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

	
	
}
