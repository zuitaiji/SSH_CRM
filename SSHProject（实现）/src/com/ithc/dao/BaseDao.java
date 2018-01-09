package com.ithc.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.utils.PageBean;

/**
 *  通用的增 、 删 、 改 、分页查询、查询所有、用id查询
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T> {

	
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	/**
	 *  用id查询
	 * @param id
	 * @return
	 */
	public T findById(Long id);
	/**
	 *  查询所有
	 */
	public List<T> findAll();
	/**
	 *  分页查询
	 */
	public PageBean<T> findByPage(Integer pageCode,Integer pageSize,DetachedCriteria criteria);
	
}
