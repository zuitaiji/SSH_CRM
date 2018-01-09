package com.ithc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Base_Dict;
import com.ithc.dao.Base_DictDao;

@Transactional
public class Base_DictServiceImpl implements Base_DictService {

	private Base_DictDao base_DictDao;

	public void setBase_DictDao(Base_DictDao base_DictDao) {
		this.base_DictDao = base_DictDao;
	}

	@Override
	public List<Base_Dict> findByCode(Base_Dict base_Dict) {
		
		return base_DictDao.findByCode(base_Dict);
	}
	
	
}
