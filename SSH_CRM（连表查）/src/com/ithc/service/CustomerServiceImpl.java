package com.ithc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Customer;
import com.ithc.dao.CustomerDao;
import com.ithc.util.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/**
	 *  分页查询
	 *  
	 */
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		
		return customerDao.findByPage(criteria, pageCode, pageSize);
	}
	/**
	 *  添加
	 */
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	/**
	 *  用id查询
	 */
	public Customer findById(Long cust_id) {
		
		return customerDao.findById(cust_id);
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
}
