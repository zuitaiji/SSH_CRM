package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Visit;
import com.ithc.utils.PageBean;

public interface VisitService {
	/**
	 *  保存
	 * @param visit
	 */
	void save(Visit visit);
	/**
	 *  分页查询
	 * @param pageCode
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
