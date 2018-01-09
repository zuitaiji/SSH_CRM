package com.ithc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Customer;
import com.ithc.dao.CustomerDao;
import com.ithc.utils.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/**
	 *  保存客户
	 */
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	/**
	 *  分页查询
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return customerDao.findByPage(pageCode,pageSize,criteria);
	}
	/**
	 *  用id查询
	 */
	public Customer findById(Customer customer) {
		return customerDao.findById(customer);
	}
	/**
	 *  修改
	 */
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	/**
	 *  删除
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	/**
	 *  查询所有
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	/**
	 *  客户级别统计
	 */
	public List<Object[]> findByLevel() {
		return customerDao.findByLevel();
	}
	/**
	 * 客户信息来源统计
	 */
	public List<Object[]> findBySource() {
		return customerDao.findBySource();
	}
	
	
}
