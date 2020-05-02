package com.realestate.re.service.core.country;

import com.realestate.re.service.common.abstracts.AbstractError;

public class CountryError extends AbstractError {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}