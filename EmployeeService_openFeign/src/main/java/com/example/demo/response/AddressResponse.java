package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressResponse {

	private int id;
	private String lane1;
	private String lane2;
	private long zip;
	private String state;
	
	private int employeeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLane1() {
		return lane1;
	}

	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}

	public String getLane2() {
		return lane2;
	}

	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "AddressResponse [id=" + id + ", lane1=" + lane1 + ", lane2=" + lane2 + ", zip=" + zip + ", state="
				+ state + ", employeeId=" + employeeId + "]";
	}

}
