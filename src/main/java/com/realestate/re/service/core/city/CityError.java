package com.realestate.re.service.core.city;

import com.realestate.re.service.common.abstracts.AbstractError;

public class CityError extends AbstractError {

	private String name;

	private String country;

	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}