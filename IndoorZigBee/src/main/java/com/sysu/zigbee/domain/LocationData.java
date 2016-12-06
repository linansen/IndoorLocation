package com.sysu.zigbee.domain;

public class LocationData {

	private double[] location;
	private double synyTime;

	public LocationData() {
		super();
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public double getSynyTime() {
		return synyTime;
	}

	public void setSynyTime(double synyTime) {
		this.synyTime = synyTime;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public LocationData(double[] location, double synyTime) {
		super();
		this.location = location;
		this.synyTime = synyTime;
	}

}
