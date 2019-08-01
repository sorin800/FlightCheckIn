package com.tsv.flightcheckin.integration.dto;

public class MainTemp {
	private String message;

	private String cnt;

	private String cod;

	private Lista[] list;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Lista[] getList() {
		return list;
	}

	public void setList(Lista[] list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ClassPojo [message = " + message + ", cnt = " + cnt + ", cod = " + cod + ", list = " + list
				+ ", city = " + "]";
	}
}
