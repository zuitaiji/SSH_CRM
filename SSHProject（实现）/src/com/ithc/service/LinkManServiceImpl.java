package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.LinkMan;
import com.ithc.dao.LinkManDao;
import com.ithc.utils.PageBean;

@Transactional
public class LinkManServiceImpl implements LinkManService{

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	/**
	 *  保存
	 */
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	/**
	 * 分页查询
	 */
	public PageBean<LinkMan> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return linkManDao.findByPage(pageCode, pageSize, criteria);
	}

	/**
	 *  用id查询
	 */
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	/**
	 * 修改
	 */
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	/**
	 *  删除
	 */
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
	
}
