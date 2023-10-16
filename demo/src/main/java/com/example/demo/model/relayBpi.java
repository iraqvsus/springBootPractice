package com.example.demo.model;

public class relayBpi {

	private String currencyCode;

	private String currencyChineseName;

	private Double exchangeRate;

	public relayBpi(String code, String string, double rate_float) {
		this.currencyCode = code;
		this.currencyChineseName = string;
		this.exchangeRate = rate_float;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyChineseName() {
		return currencyChineseName;
	}

	public void setCurrencyChineseName(String currencyChineseName) {
		this.currencyChineseName = currencyChineseName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	
}
