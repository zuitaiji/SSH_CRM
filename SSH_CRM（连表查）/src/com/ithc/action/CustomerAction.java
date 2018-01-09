package com.ithc.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Base_Dict;
import com.ithc.bean.Customer;
import com.ithc.service.CustomerService;
import com.ithc.util.PageBean;
import com.ithc.util.UUIDName;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	public Customer getModel() {
		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 分页查询 ： 离线条件查询 1.数据库的查询位置 2.多少页
	 * 
	 * 每页显示多少条数据 :pageSize（给个默认值2） 第几页 ：pageCode
	 * 
	 */
	private Integer pageSize = 2;
	private Integer pageCode = 1;

	// 总页面数
	private Integer count;

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setPageSize(Integer pageSize) {

		this.pageSize = pageSize;
	}

	public void setPageCode(Integer pageCode) {
		// 变换每页现实的数量，页数从1开始
		if (pageCode == null || pageCode < 1 || pageCode > count) {
			pageCode = 1;
		}

		this.pageCode = pageCode;
	}

	public String findByPage() {
		// 创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		try {
			// 拼接条件查询
			if (this.customer.getCust_name() != null && !this.customer.getCust_name().trim().equals("")) {
				criteria.add(Restrictions.like("cust_name", "%" + this.customer.getCust_name() + "%"));
			}
			// 拼接信息来源查询
			Base_Dict source = this.customer.getSource();
			if (!source.getDict_id().equals("")) {
				String id = source.getDict_id();
				criteria.add(Restrictions.eq("source.dict_id", id));
			}
			// 拼接客户级别查询
			Base_Dict level = this.customer.getLevel();
			if (!level.getDict_id().equals("")) {
				String id = level.getDict_id();
				criteria.add(Restrictions.eq("level.dict_id", id));
			}
		} catch (Exception e) {
		}
		PageBean<Customer> page = customerService.findByPage(criteria, this.pageCode, this.pageSize);
		// 获取值栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);

		return "findByPage";
	}

	/**
	 * 跳转添加页面
	 */

	public String initAddUI() {

		return "initAddUI";
	}

	/**
	 * 添加
	 */

	/**
	 * 文件上传： 文件 文件名 文件类型
	 * 
	 * @return
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
	 *  添加
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if (upload != null) {
			// 生成唯一的名字
			String name = UUIDName.getName(uploadFileName);
			String path = "F:/9.25/apache-tomcat-7.0.70/webapps/upload/";
			File file = new File(path + name);
			FileUtils.copyFile(upload, file);
			this.customer.setFilePath(path + name);
		}

		customerService.save(customer);

		return "initAddUI";
	}

	/**
	 * 修改之前先查询
	 */

	public String initUpdate() {
		Long cust_id = customer.getCust_id();

		customer = customerService.findById(cust_id);

		System.out.println(customer);

		return "initUpdate";
	}

	/**
	 * 修改
	 * @throws Exception 
	 */
	public String update() throws Exception {
		// 修改的时候从新上传了文件需要把原来的文件删除
		if (upload != null) {
			String filePath = customer.getFilePath();
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			// 生成唯一的名字
			String name = UUIDName.getName(uploadFileName);
			String path = "F:/9.25/apache-tomcat-7.0.70/webapps/upload/";
			File file2 = new File(path + name);
			FileUtils.copyFile(upload, file2);
			this.customer.setFilePath(path + name);
		}
		customerService.update(customer);
		return "update";
	}
	
	/**
	 *  删除:先查询再删除
	 *  	也要删除资质
	 */
	
	public String delete(){
		Long cust_id = customer.getCust_id();
		//查
		customer = customerService.findById(cust_id);
		
		String filePath = customer.getFilePath();
		if(!filePath.trim().equals("")){
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		//删
		customerService.delete(customer);
		
		return "delete";
	}
}
