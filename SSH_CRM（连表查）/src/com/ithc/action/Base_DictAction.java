package com.ithc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ithc.bean.Base_Dict;
import com.ithc.service.Base_DictService;
import com.ithc.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Base_DictAction extends ActionSupport implements ModelDriven<Base_Dict> {

	private Base_Dict base_Dict = new Base_Dict();
	
	public Base_Dict getModel() {
		
		return base_Dict;
	}

	private Base_DictService base_DictService;

	public void setBase_DictService(Base_DictService base_DictService) {
		this.base_DictService = base_DictService;
	}
	
	/**
	 *  用类别代码查询项目名称
	 *  006       vip
	 * @return
	 * @throws IOException 
	 */
	public String findByCode() throws IOException{
		List<Base_Dict> list = base_DictService.findByCode(base_Dict);
		String jsonString = JsonUtil.JsonString(list);
		// 响应数据需要声明json格式
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");;
		PrintWriter writer = response.getWriter();
		writer.print(jsonString);
		return null;
	}
	
}
