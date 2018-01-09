package com.ithc.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Customer;
import com.ithc.bean.Dict;
import com.ithc.service.CustomerService;
import com.ithc.utils.FastJsonUtil;
import com.ithc.utils.PageBean;
import com.ithc.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	 *  点击跳转
	 */
	public String initAddUI(){
		return "initAddUI";
	}
	
	/**
	 *  添加客户
	 *  
	 *  文件上传需要三个属性 : 需求，把文件名需要添加到数据库，把文件添加到tomcat服务器中（D:\apache-tomcat-7.0.70\webapps）
	 *  upload
	 *  uploadFileName  需要设置成唯一
	 *  uploadContentType
	 */
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 *  添加客户
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(upload != null){
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//给对象的属性赋值(保存数据时，数据库里就会有文件名加后缀)
			customer.setFilePath("D:/apache-tomcat-7.0.70/webapps/"+uuidName);
			File file = new File("D:/apache-tomcat-7.0.70/webapps/"+uuidName);
			FileUtils.copyFile(upload, file);
		}
		//保存对象存到数据库
		customerService.save(customer);
		
		return initAddUI();
	}
	/**
	 *  查询客户，需要用到分页，用离线条件查询
	 */
	// 属性驱动
		// 当前页
		private Integer pageCode = 1;

		public void setPageCode(Integer pageCode) {
			if (pageCode == null || pageCode <1) {
				pageCode = 1;
			}
			this.pageCode = pageCode;
		}

		// 每页显示的条数
		private Integer pageSize = 2;

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
	public String findByPage(){
		//创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		System.out.println(0);
		try{
			//1.用名字查询
			String name = customer.getCust_name();
			if(!name.trim().isEmpty() && name != null){
				criteria.add(Restrictions.like("cust_name", "%"+name+"%"));
			}
			
			//2.用客户级别查询
			Dict level = customer.getLevel();
			if(level != null && !level.getDict_id().trim().isEmpty()){
				criteria.add(Restrictions.eq("level.dict_id",level.getDict_id()));
			}
			//2.用客户来源
			Dict source = customer.getSource();
			if(source != null && !source.getDict_id().trim().isEmpty()){
				criteria.add(Restrictions.eq("source.dict_id",source.getDict_id()));
			}
			
			
		}catch(Exception e){
			
		}
		//查
		PageBean<Customer>  page = customerService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ActionContext.getContext().getValueStack().set("page",page);
		return "findByPage";
	}
	
	/**
	 *  修改，
	 *  先用id查询
	 */
	public String initUpdate(){
		
		customer = customerService.findById(customer);
		
		return "initUpdate";
	}
	
	/**
	 *  修改
	 * @throws Exception 
	 */
	
	public String update() throws Exception{
//		customer = customerService.findById(customer);
		
		if(upload != null){
			String filePath = customer.getFilePath();
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//给对象的属性赋值(保存数据时，数据库里就会有文件名加后缀)
			customer.setFilePath("D:/apache-tomcat-7.0.70/webapps/"+uuidName);
			File file2 = new File("D:/apache-tomcat-7.0.70/webapps/"+uuidName);
			FileUtils.copyFile(upload, file2);
		}
		
		customerService.update(customer);
		
		//修改成功在重新查询
		return "update";
	}
	
	/**
	 *  删除
	 */
	public String delete(){
		Long cid = customer.getCust_id();
		if(cid == null){
			return "login";
		}
		Customer c = customerService.findById(customer);
		if(c == null){
			return "login";
		}
		//删除联系人删除对应的文件
		String filePath = c.getFilePath();
		if(!filePath.trim().isEmpty()){
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(c);
		return findByPage();
	}
	
	/**
	 *  查询所有
	 */
	public String findAll(){
		
		List<Customer> list = customerService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		//把数据写出去
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	/**
	 *  客户级别统计
	 */
	public String findByLevel(){
		
		List<Object[]> list = customerService.findByLevel();
		//压栈
		ActionContext.getContext().getValueStack().set("list",list);
		
		return "findByLevel";
	}
	
	/**
	 *  信息来源统计
	 */
	public String findBySource(){
		
		List<Object[]> list = customerService.findBySource();
		//压栈
		ActionContext.getContext().getValueStack().set("list",list);
		
		return "findBySource";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
