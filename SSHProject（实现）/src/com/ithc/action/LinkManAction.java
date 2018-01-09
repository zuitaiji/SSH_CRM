package com.ithc.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.LinkMan;
import com.ithc.service.LinkManService;
import com.ithc.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan = new LinkMan();

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	public String initAddUI(){
		
		return "initAddUI";
	}
	
	/**
	 *  保存联系人
	 * @return
	 */
	public String save(){
		linkManService.save(linkMan);
		return initAddUI();
	}
	/**
	 *  分页查询
	 */
	private Integer pageCode = 1;//当前页
	private Integer pageSize = 2;//每页显示的数量
	
	public void setPageCode(Integer pageCode) {
		if (pageCode == null || pageCode <1) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}

	public String findByPage(){
		//创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		// 拼接查询条件
		//1.用名字查询
		try {
			String lkm_name = linkMan.getLkm_name();
			if(lkm_name!=null && !lkm_name.trim().isEmpty()){
				criteria.add(Restrictions.like("lkm_name","%"+lkm_name+"%"));
			}
			//2.所属客户查询
			Long id = linkMan.getCustomer().getCust_id();
			if(id!=null){
				criteria.add(Restrictions.eq("customer.cust_id",id));
			}
		} catch (Exception e) {
		}
		PageBean<LinkMan> page =  linkManService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ActionContext.getContext().getValueStack().set("page",page);
		return "findByPage";
	}
	
	/**
	 *  修改之前查询
	 *  用id查询 返回一个对象
	 */
	public String initUpdate(){
		Long lkm_id = linkMan.getLkm_id();
		if(lkm_id == null){
			return "login";
		}
		linkMan = linkManService.findById(lkm_id);
		return "initUpdate";
	}
	/**
	 *  修改
	 */
	
	public String update(){
		
		linkManService.update(linkMan);
		
		return "update";
	}
	
	
	/**
	 * 删除
	 */
	public String delete(){
		Long lkm_id = linkMan.getLkm_id();
		if(lkm_id == null){
			return "login";
		}
		linkMan = linkManService.findById(lkm_id);
		
		linkManService.delete(linkMan);
		
		return "delete";
	}
	
	
	
	
	
	
	
	
	
}
