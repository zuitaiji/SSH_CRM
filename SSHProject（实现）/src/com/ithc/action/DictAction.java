package com.ithc.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.ithc.bean.Dict;
import com.ithc.service.DictService;
import com.ithc.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict> {

	private Dict dict = new Dict();

	@Override
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictServicr;

	public void setDictServicr(DictService dictServicr) {
		this.dictServicr = dictServicr;
	}
	/**
	 *  ajax加载 客户级别与信息来源
	 * @return
	 */
	public String findByCode(){
		//查询ajax  post请求提交过来的数据
		String dict_type_code = dict.getDict_type_code();
		//然后用数据去查询
		List<Dict> list = dictServicr.findByCode(dict_type_code);
		
		//把集合数据转为json格式
		String jsonString = FastJsonUtil.toJSONString(list);
		//把数据写出去
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	
	
}
