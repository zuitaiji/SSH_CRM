package com.ithc.action;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.LinkMan;
import com.ithc.service.LinkManService;
import com.ithc.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		
		return linkMan;
	}
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	/**
	 * 从页面获取
	 *  1.第几页
	 *  2.每页显示显示多少条数据
	 * @return
	 */
	
	private Integer pageSize = 2;
	private Integer pageCode = 1;
	
	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}
	public void setPageCode(Integer pageCode) {
		if(pageCode == null || pageCode < 1){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	/**
	 *  分页查询
	 * @return
	 */
	public String findByPage(){
		// 创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		PageBean<LinkMan> page =  linkManService.findByPage(criteria,this.pageCode,this.pageSize);
		// 压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page",page);
		return "findByPage";
	}
	
	

}
