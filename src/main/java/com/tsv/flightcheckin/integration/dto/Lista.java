package com.tsv.flightcheckin.integration.dto;

public class Lista {

	private String dt;

	private String dt_txt;

	private Main main;

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@Override
	public String toString() {
		return "ClassPojo [clouds = " + ", dt = " + dt + ", wind = " + ", sys = " + ", weather = " + ", dt_txt = "
				+ dt_txt + ", main = " + main + "]";
	}
}
