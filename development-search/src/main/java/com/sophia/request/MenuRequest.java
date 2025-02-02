package com.sophia.request;

import org.hibernate.validator.constraints.NotBlank;

public class MenuRequest extends CrudRequest{

	@NotBlank
	private String name;
	private String remark;
	private String pid = "0"; //默认为0
	private String ico;
	private String link;
	private Long idx;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long index) {
		this.idx = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String icon) {
		this.ico = icon;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String url) {
		this.link = url;
	}
}
