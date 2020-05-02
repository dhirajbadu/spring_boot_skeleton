package com.realestate.re.service.web.session;

import com.realestate.re.service.common.utls.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());


        final String failurCause = exception.getClass().toString().contains("class ") ? exception.getClass().toString().replaceAll("class " , "") : exception.getClass().toString();
        final String failurMessage = getMessage(failurCause);
        LoggerUtil.logInfo("login failed due to : " + failurMessage);
        request.setAttribute("loginError" , failurMessage);
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request , response);

        //response.sendRedirect("/login?loginError=" + failurMessage);
    }

    private String getMessage(String key) {
        Map<String, String> data = new HashMap<>();

        data.put("org.springframework.security.authentication.BadCredentialsException", "wrong username or password");
        data.put("org.springframework.security.authentication.CredentialsExpiredException", "credentials expired");
        data.put("org.springframework.security.authentication.LockedException", "account is locked");
        data.put("org.springframework.security.authentication.DisabledException", "account is not activated");
        data.put("org.springframework.security.authentication.AccountExpiredException", "account expired");

        return data.getOrDefault(key , "some thing went wrong please try again");
    }
}
