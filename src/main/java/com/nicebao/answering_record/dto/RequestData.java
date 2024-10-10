package com.nicebao.answering_record.dto;

/**
 * @name: RequestData
 * @author: IhaveBB
 * @date: 2024-10-10 21:36
 **/
public class RequestData {
	private String names;
	private String data;
	private String strategyType;

	// Getters 和 Setters
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
}

