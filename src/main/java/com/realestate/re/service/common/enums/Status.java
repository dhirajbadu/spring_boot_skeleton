package com.realestate.re.service.common.enums;

import java.util.Arrays;
import java.util.List;

public enum Status {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

	private final String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static List<Status> getEnumList(Status ... statuses){
		return Arrays.asList(statuses);
	}
	
	public static Status getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Status v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
