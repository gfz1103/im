package com.buit.cis.dctwork.dto;

import com.buit.mq.dto.NotifyMessageDto;

public class CisHzyzMessDto extends NotifyMessageDto{

	private Integer zyh;
	
	private String zyhm;
	
	private Integer jlxh;
	
	public CisHzyzMessDto() {
		
	}
	
	public CisHzyzMessDto(Integer zyh, String zyhm, Integer jlxh) {
		this.zyh = zyh;
		this.zyhm = zyhm;
		this.jlxh = jlxh;
	}

	public Integer getZyh() {
		return zyh;
	}

	public void setZyh(Integer zyh) {
		this.zyh = zyh;
	}

	public String getZyhm() {
		return zyhm;
	}

	public void setZyhm(String zyhm) {
		this.zyhm = zyhm;
	}

	public Integer getJlxh() {
		return jlxh;
	}

	public void setJlxh(Integer jlxh) {
		this.jlxh = jlxh;
	}
	
	
}
