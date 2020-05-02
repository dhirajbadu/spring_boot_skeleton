package com.realestate.re.config;

import com.realestate.re.service.common.utls.AuthenticationUtil;
import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.common.utls.FileHandler;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.user.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private String log = "";

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String username = "unknown";

        UserInfoDto currentUser = authenticationUtil.getCurrentUserDto();

        if (currentUser != null) {
            username = currentUser.getEmail();
        }

        if (request != null) {
            if (!(request.getRequestURI().contains("resources") && request.getRequestURI().contains("error"))) {
                log = "";
                log = "enter" + "," + request.getRequestURI() + "," + request.getMethod() + "," + request.getRemoteAddr() + "," + username + "," + DateParseUtil.getCurrentDateTime() + System.lineSeparator();

                try {

                    new Thread() {
                        public void run() {
                            new FileHandler().writeActivityLog(log);
                        }
                    }.start();
                    LoggerUtil.interceptorLog("enter", request.getRequestURI() + " : " + request.getMethod(), request.getRemoteAddr(), username, DateParseUtil.getCurrentDateTime());

                } catch (Exception e) {
                    LoggerUtil.logException(this.getClass(), e);
                }
            }
        }

/*
        HttpServletRequest requestCache = new ContentCachingRequestWrapper(request);
        requestCache.getParameterMap();

        List<String> output = null;
        try {
           output = ParseUtls.output(requestCache.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null){
            System.out.println("..................................");
            for (String str : output){
                System.out.println(str);
            }
            System.out.println("..................................");
        }*/

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //System.out.println("RequestInterceptor afterCompletion");
    }
}
