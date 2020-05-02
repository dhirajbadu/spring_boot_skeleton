package com.realestate.re.service.core.country;

import com.realestate.re.service.common.abstracts.AbstractDto;
import com.realestate.re.service.common.enums.Status;

public class CountryDto extends AbstractDto {

	private String name;

	private Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}