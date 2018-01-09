package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.LinkMan;
import com.ithc.util.PageBean;

public interface LinkManService {
	/**
	 * 分页查询
	 * @param criteria
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize);

}
