package com.ithc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ithc.bean.Customer;
import com.ithc.utils.PageBean;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;

	public BaseDaoImpl() {
		// this表示的是子类 ,加载子类的时候父类也会被加载 ，所以这里的this代表子类
		Class c = this.getClass();
		// 获取父类 Type是一个接口 他的实现类就是class对象
		// type 是一个空接口，他有很多子接口，我们操作的是子接口
		Type type = c.getGenericSuperclass();
		// 把type接口转成子接口
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			// 获取Customer 因为泛型可以有多个所以返回一个数组
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Long id) {
		
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {

		return (List<T>) this.getHibernateTemplate().find("from"+clazz.getSimpleName());
	}

	@Override
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		// 需要给分页的javaBean属性赋值 , 一共要set设置四个属性
		PageBean<T> pageBean = new PageBean<>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		// 查询数据库数据的总数 select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> value = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (value != null && value.size() > 0) {
			Number number = value.get(0);
			int intValue = number.intValue();
			pageBean.setTotalCount(intValue);
		}
		// select count(*) 格式置空
		criteria.setProjection(null);
		// 查询 select *
		List<T> findByCriteria = (List<T>) this.getHibernateTemplate().findByCriteria(criteria,
				(pageCode - 1) * pageSize, pageSize);
		pageBean.setBeanList(findByCriteria);
		return pageBean;
	}

}
