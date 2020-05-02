package com.realestate.re.service.common.utls;

import com.realestate.re.service.common.enums.Permission;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;

public class UIParserUtls {

    public static String getPermisions(List<Permission> permissionList){
        StringBuilder builder = new StringBuilder();

        int counter = 0;
        for (Permission permission : permissionList){
            if (counter == 3){
                builder.append(" <span class=\"badge bg-green\">" + permission.getValue() + "</span>");
                builder.append("<br>");
                counter = 0;
                continue;
            }else {
                builder.append(" <span class=\"badge bg-green\">" + permission.getValue() + "</span>&nbsp;");
            }
            counter++;
        }

        return builder.toString();
    }

    public static String simpleAnchorTagGenerator(String url  , String text){
        StringBuilder builder = new StringBuilder();

        builder.append("&nbsp;<a href='" + url + "'>" + text + "</a>&nbsp;" );

        return builder.toString();
    }
}
