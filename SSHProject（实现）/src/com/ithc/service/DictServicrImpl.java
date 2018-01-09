package com.ithc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Dict;
import com.ithc.dao.DictDao;

@Transactional
public class DictServicrImpl implements DictService{

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	/**
	 *  查询客户级别与信息来源
	 */
	public List<Dict> findByCode(String dict_type_code) {
		return dictDao.findByCode(dict_type_code);
	}
	
}
