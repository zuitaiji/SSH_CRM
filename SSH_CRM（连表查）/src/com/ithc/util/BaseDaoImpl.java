package com.ithc.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.Customer;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	
	private Class clazz;

	public BaseDaoImpl() {
		//获取运行时的类,this代表子类
		Class c = this.getClass();		
		// 获取父接口 Type 这是一个空接口，我们需要用到空接口下面的一个子接口ParameterizedType
		Type type = c.getGenericSuperclass();
		
		if(type instanceof ParameterizedType){
			ParameterizedType pt = (ParameterizedType) type;
			Type[] types = pt.getActualTypeArguments();
			clazz = (Class) types[0];
		}
	}
	/**
	 *  保存
	 */
	public void save(T t) {
		
		this.getHibernateTemplate().save(t);
	}

	/**
	 *  删除
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 *  修改
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 *  查询
	 */
	public T selectOne(T t) {
		
		return null;
	}

	/**
	 *  查询所有
	 */
	public List<T> selectAll() {
		
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	/**
	 * 用id查询
	 */
	public T findById(Long id) {
		
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	
	/**
	 *  分页查询
	 */
	public PageBean<T> findByPage(DetachedCriteria criteria,Integer pageCode,Integer pageSize){
		// 设置四个数据
		PageBean<T> pageBean = new PageBean<>();
		// 1.数据库的查询位置
		pageBean.setPageCode(pageCode);
		// 2. 每页显示的数量
		pageBean.setPageSize(pageSize);
		// 总数量  select count(*)
		criteria.setProjection(Projections.rowCount());
		
		List<Number> values = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(values !=null && values.size()>0 ){
			Number num =  values.get(0);
			int totalCount = num.intValue();
			//3.总记录数
			pageBean.setTotalCount(totalCount);
			
		}
		/**	criteria ： 代表离线的对象
		 * 	firstResultfirstResult  : 数据库查询的位置	
		 * 	maxResults  : 每页显示多少条数据	
		 *  findByCriteria(criteria, firstResult, maxResults);
		 */
		//置空sql格式
		criteria.setProjection(null);
		// select *
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
		pageBean.setBeanList(list);
		
		return pageBean;
	}
	@Override
	public List<T> selectAll(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		
		
		return null;
	}
	
	
	
	
	
	
}
