package com.example.demo.model;

import java.util.List;

public class RelayDto {

	private String updateDate;
	
	private List<relayBpi> relayBpiList;

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String toDatetime) {
		this.updateDate = toDatetime;
	}

	public List<relayBpi> getRelayBpiList() {
		return relayBpiList;
	}

	public void setRelayBpiList(List<relayBpi> relayBpiList) {
		this.relayBpiList = relayBpiList;
	}


}
