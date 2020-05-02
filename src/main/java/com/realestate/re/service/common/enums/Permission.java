package com.realestate.re.service.common.enums;

public enum Permission {

	USER_CREATE("Role_User_Create") , USER_VIEW("Role_User_View") , USER_UPDATE("Role_User_Update") , USER_DELETE("Role_User_Delete") ,

	ROLE_CREATE("Role_Role_Create") , ROLE_VIEW("Role_Role_View") , ROLE_UPDATE("Role_Role_Update") , ROLE_DELETE("Role_Role_Delete"),

	AGENT_CREATE("Role_Agent_Create") , AGENT_VIEW("Role_Agent_View") , AGENT_UPDATE("Role_Agent_Update") , AGENT_DELETE("Role_Agent_Delete"),

	CITY_CREATE("Role_City_Create") , CITY_VIEW("Role_City_View") , CITY_UPDATE("Role_City_Update") , CITY_DELETE("Role_City_Delete");

	private final String value;

	Permission(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public static Permission getEnum(String value) {
		if (value == null)
			throw new IllegalArgumentException();
		for (Permission v : values())
			if (value.equalsIgnoreCase(v.getValue()))
				return v;
		throw new IllegalArgumentException();
	}
}
