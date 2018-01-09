package com.ithc.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Customer;
import com.ithc.utils.PageBean;

public interface CustomerDao {
	/**
	 *  保存客户
	 * @param customer
	 */
	void save(Customer customer);
	/**
	 *  分页查询
	 * @param pageCode
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	/**
	 *  用id查询
	 * @param customer
	 * @return
	 */
	Customer findById(Customer customer);
	/**
	 *  修改
	 * @param customer
	 */
	void update(Customer customer);
	/**
	 *  删除
	 * @param customer
	 */
	void delete(Customer customer);
	/**
	 *  查询所有
	 * @return
	 */
	List<Customer> findAll();
	/**
	 *  客户级别统计
	 * @return
	 */
	List<Object[]> findByLevel();
	/**
	 *  客户信息来源统计
	 * @return
	 */
	List<Object[]> findBySource();

}
