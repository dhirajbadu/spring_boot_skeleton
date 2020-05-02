package com.realestate.re.service.core.city;

import com.realestate.re.service.common.abstracts.AbstractDto;
import com.realestate.re.service.common.enums.Status;

public class CityDto extends AbstractDto {

	private String name;

	private Long countryId;

	private String countryName;

	private Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}