package com.ithc.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.Customer;
import com.ithc.utils.PageBean;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	/**
	 * 分页查询
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		// 需要给分页的javaBean属性赋值 , 一共要set设置四个属性
		PageBean<Customer> pageBean = new PageBean<>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		// 查询数据库数据的总数 select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> value = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (value != null && value.size() > 0) {
			Number number = value.get(0);
			int intValue = number.intValue();
			pageBean.setTotalCount(intValue);
		}
		// select count(*) 格式置空
		criteria.setProjection(null);
		// 查询 select *
		List<Customer> findByCriteria = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,
				(pageCode - 1) * pageSize, pageSize);
		pageBean.setBeanList(findByCriteria);
		return pageBean;
	}

	/**
	 * 用id查询
	 */
	public Customer findById(Customer customer) {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer where cust_id = ?",
				customer.getCust_id());
		return list.get(0);
	}

	/**
	 * 修改
	 */
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	/**
	 * 删除
	 */
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	/**
	 * 查询所有
	 */
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	/**
	 * 客户级别统计
	 */
	public List<Object[]> findByLevel() {
		/**
		 * SELECT b.`dict_item_name`,COUNT(*) FROM cst_customer c INNER JOIN
		 * base_dict b ON c.cust_level = b.dict_id GROUP BY c.`cust_level`
		 */
		/*
		 * String hql = "select " + "c.level.dict_item_name,count(*) " +
		 * "from Customer c " + "inner join " + "c.level " + "group by " +
		 * "c.level";
		 */
		String hql = "select c.level.dict_item_name,count(*) from Customer c inner join c.level group by c.level";

		return (List<Object[]>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * 客户信息来源统计
	 */
	public List<Object[]> findBySource() {
		String hql = "select c.source.dict_item_name,count(*) from Customer c inner join c.source group by c.source";

		return (List<Object[]>) this.getHibernateTemplate().find(hql);
	}

}
