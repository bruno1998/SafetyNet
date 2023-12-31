package com.database.model;

public class Firestation {
	
	private String address;
	private String station;
	
	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Firestation firestation) {
		return this.getAddress().equals(firestation.getAddress());
	}
}
