package com.ithc.service;

import java.util.List;

import com.ithc.bean.Base_Dict;

public interface Base_DictService {
	/**
	 *  
	 * @param base_Dict
	 * @return
	 */
	List<Base_Dict> findByCode(Base_Dict base_Dict);

}
