package com.ithc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Customer;
import com.ithc.util.PageBean;

public interface CustomerService {
	/**
	 *  分页查询
	 * @return
	 */
	PageBean<Customer> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize);
	/**
	 *  添加
	 * @param customer
	 */
	void save(Customer customer);
	/**
	 *  用id查询
	 * @param cust_id
	 * @return
	 */
	Customer findById(Long cust_id);
	/**
	 * 修改
	 * @param customer
	 */
	void update(Customer customer);
	/**
	 *  删除
	 * @param customer
	 */
	void delete(Customer customer);

}
