package com.ithc.bean;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {
	/**
	 *  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  	`cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  	`cust_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
  	`cust_create_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  	`cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  	`cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  
  
   `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  
  `filePath` varchar(255) DEFAULT NULL COMMENT '上传资质'
	 */
	private Long cust_id;
	private String cust_name;
	private Long cust_user_id;
	private Long cust_create_id;
	private String cust_phone;
	private String cust_mobile;
	private String cust_linkman;
	
	private Dict source;
	private Dict level;
	private Dict industry;
	
	private Set<LinkMan> linkMan = new HashSet<LinkMan>();
	
	private String filePath;
	
	@JSONField(serialize=false)
	private Set<Visit> visit = new HashSet<Visit>();
	
	
	public Set<Visit> getVisit() {
		return visit;
	}
	public void setVisit(Set<Visit> visit) {
		this.visit = visit;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Set<LinkMan> getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(Set<LinkMan> linkMan) {
		this.linkMan = linkMan;
	}
	
	public Dict getSource() {
		return source;
	}
	public void setSource(Dict source) {
		this.source = source;
	}
	public Dict getLevel() {
		return level;
	}
	public void setLevel(Dict level) {
		this.level = level;
	}
	public Dict getIndustry() {
		return industry;
	}
	public void setIndustry(Dict industry) {
		this.industry = industry;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public Long getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public Long getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id=" + cust_user_id
				+ ", cust_create_id=" + cust_create_id + ", cust_phone=" + cust_phone + ", cust_mobile=" + cust_mobile
				+ ", cust_linkman=" + cust_linkman + ", source=" + source + ", level=" + level + ", industry="
				+ industry + "]";
	}
}
