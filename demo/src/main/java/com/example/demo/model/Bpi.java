package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bpi {

	@JsonProperty("USD")
	private Currency USD;
	
	@JsonProperty("GBP")
	private Currency GBP;
	
	@JsonProperty("EUR")
	private Currency EUR;

	public Currency getUSD() {
		return USD;
	}

	public void setUSD(Currency uSD) {
		USD = uSD;
	}

	public Currency getGBP() {
		return GBP;
	}

	public void setGBP(Currency gBP) {
		GBP = gBP;
	}

	public Currency getEUR() {
		return EUR;
	}

	public void setEUR(Currency eUR) {
		EUR = eUR;
	}

}
