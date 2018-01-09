package com.ithc.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Visit;
import com.ithc.service.VisitService;
import com.ithc.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private Visit visit = new Visit();

	@Override
	public Visit getModel() {
		return visit;
	}
	
	private VisitService visitService;

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	public String initAddUI(){
		
		return "initAddUI";
	}
	
	/**
	 *  保存拜访记录
	 */
	
	public String save(){
		
		visitService.save(visit);
		
		return initAddUI();
	}
	/**
	 * 分页查询客户拜访列表
	 */
	private Integer pageCode = 1;
	private Integer pageSize = 2;
	
	public void setPageCode(Integer pageCode) {
		if(pageCode==null || pageCode<1){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	//开始时间
	private String beginDate;
	//结束时间
	private String endDate;
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String findByPage(){
		//创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		
		/**
		 *  拼接筛选查询
		 */
		try {
			
			// 1.名字
			String visit_interviewee = visit.getVisit_interviewee();
			if(visit_interviewee !=null && !visit_interviewee.trim().isEmpty()){
				criteria.add(Restrictions.like("visit_interviewee", "%"+visit_interviewee+"%"));
			}
			//2.开始时间
			System.out.println(beginDate +" : "+endDate);
			if(beginDate != null && !beginDate.trim().isEmpty()){
				criteria.add(Restrictions.ge("visit_time", beginDate));
			}
			//3.结束时间
			if(endDate!=null && !endDate.trim().isEmpty()){
				criteria.add(Restrictions.le("visit_nexttime", endDate));
			}
			
		} catch (Exception e) {
		}
		
		
		
		PageBean<Visit> page =  visitService.findByPage(pageCode,pageSize,criteria);
		//压栈.
		ActionContext.getContext().getValueStack().set("page",page);
		
		return "findByPage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
