package com.ithc.dao;

import java.util.List;

import com.ithc.bean.Base_Dict;
import com.ithc.util.BaseDao;

public interface Base_DictDao extends BaseDao<Base_Dict> {

	List<Base_Dict> findByCode(Base_Dict base_Dict);

}
