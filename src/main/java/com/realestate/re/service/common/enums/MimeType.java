package com.realestate.re.service.common.enums;

import java.util.Arrays;
import java.util.List;

public enum MimeType {

    JPE("jpe") , JPEG("jpeg") , GIF("gif") , PDF("pdf");

    private final String value;

    MimeType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static List<MimeType> getEnumList(MimeType ... mimeTypes){
        return Arrays.asList(mimeTypes);
    }

    public static MimeType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (MimeType v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }
}
