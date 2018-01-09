package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.LinkMan;
import com.ithc.dao.LinkManDao;
import com.ithc.util.PageBean;

public class LinkManServiceImpl implements LinkManService{

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	/**
	 * 分页查询
	 */
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		return linkManDao.findByPage(criteria, pageCode, pageSize);
	}
	
}
