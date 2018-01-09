package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.LinkMan;
import com.ithc.utils.PageBean;

public interface LinkManService {
	/**
	 *  保存
	 */
	void save(LinkMan linkMan);
	/**
	 *  分页查询
	 * @param pageCode
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	PageBean<LinkMan> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	/**
	 *  用id查询
	 * @param lkm_id
	 * @return
	 */
	LinkMan findById(Long lkm_id);
	/**
	 *  修改
	 * @param linkMan
	 */
	void update(LinkMan linkMan);
	/**
	 *  删除
	 * @param linkMan
	 */
	void delete(LinkMan linkMan);

}
