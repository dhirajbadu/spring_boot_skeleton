package com.realestate.re.service.common.utls;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getServerUlr(HttpServletRequest request){

        if (request == null){
            return "";
        }

        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
