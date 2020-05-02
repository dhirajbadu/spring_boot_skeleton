package com.realestate.re.service.common.enums;

public enum TokenAssociation {

    FORGET_PASSWORD("forgetPassword"), USER_ACTIVATION("userActivation");

    private final String value;

    TokenAssociation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static TokenAssociation getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (TokenAssociation v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }
}
