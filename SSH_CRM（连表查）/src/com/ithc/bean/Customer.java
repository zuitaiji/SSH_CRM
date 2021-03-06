package com.ithc.bean;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	/**
	 * 
Create Table

CREATE TABLE `cst_customer` (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
  `cust_create_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
  
  
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
  `filePath` varchar(255) DEFAULT NULL COMMENT '上传资质',
  PRIMARY KEY (`cust_id`),
  KEY `FKeh5g36duab8g1h051pdjfwcgd` (`cust_source`),
  KEY `FK2xhr3arwp3tkuae1da4lqv352` (`cust_industry`),
  KEY `FKrty52nvbjg1echf0se39eng49` (`cust_level`),
	  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  CONSTRAINT `FK2xhr3arwp3tkuae1da4lqv352` FOREIGN KEY (`cust_industry`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FKeh5g36duab8g1h051pdjfwcgd` FOREIGN KEY (`cust_source`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FKrty52nvbjg1echf0se39eng49` FOREIGN KEY (`cust_level`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `PK_cst_customer_001` FOREIGN KEY (`cust_source`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `PK_cst_customer_002` FOREIGN KEY (`cust_industry`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `PK_cst_customer_003` FOREIGN KEY (`cust_level`) REFERENCES `base_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8
	 
	
	`cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
	  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
	  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
	  
	  */
	private Long cust_id;
	private String cust_name;
	private Long cust_user_id;
	private Long cust_create_id;
	private String cust_phone;
	private String cust_mobile;
	private String filePath;
	private String cust_linkman;
	/**
	 *  拜访记录
	 * @return
	 */
	private Set<Visit> visits = new HashSet<Visit>();
	
	/**
	 *  联系人
	 * @return
	 */
	private Set<LinkMan> linkMans = new HashSet<LinkMan>();
	
	
	
	
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	private Base_Dict source; 
	private Base_Dict industry;
	private Base_Dict level;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Base_Dict getSource() {
		return source;
	}
	public void setSource(Base_Dict source) {
		this.source = source;
	}
	public Base_Dict getIndustry() {
		return industry;
	}
	public void setIndustry(Base_Dict industry) {
		this.industry = industry;
	}
	public Base_Dict getLevel() {
		return level;
	}
	public void setLevel(Base_Dict level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id=" + cust_user_id
				+ ", cust_create_id=" + cust_create_id + ", cust_phone=" + cust_phone + ", cust_mobile=" + cust_mobile
				+ ", filePath=" + filePath + ", cust_linkman=" + cust_linkman + ", visits=" + visits + ", linkMans="
				+ linkMans + ", source=" + source + ", industry=" + industry + ", level=" + level + "]";
	}
	
}
