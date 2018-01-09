package com.ithc.testjson;

public class Role {
	
	private Integer rid;
	private String rname;
	
	private Person  person;
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", person=" + person + "]";
	}
}
