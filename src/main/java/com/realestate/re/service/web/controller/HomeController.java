package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.utls.AuthenticationUtil;
import com.realestate.re.service.common.utls.FileHandler;
import com.realestate.re.service.common.utls.LoggerUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){

        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String loginGet(@RequestParam(value = "loginError" , required = false) String loginError , @RequestParam(value = "loginMessage" , required = false) String message , ModelMap map , HttpServletRequest req){

        if (loginError == null){
            loginError = (String) req.getAttribute("loginError");
        }

        if (message == null){
            message = (String) req.getAttribute("loginMessage");
        }
        map.put(StringConstants.LOGIN_ERROR , loginError);
        map.put(StringConstants.LOGIN_MESSAGE, message);
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap map){
        return "/dashboard/dashboard";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam(value = "loginError" , required = false) String loginError , @RequestParam(value = "loginMessage" , required = false) String message , ModelMap map , HttpServletRequest req){

        if (loginError == null){
            loginError = (String) req.getAttribute("loginError");
        }

        map.put(StringConstants.LOGIN_ERROR , loginError);
        if (message == null){
            message = (String) req.getAttribute("loginMessage");
        }
        map.put(StringConstants.LOGIN_ERROR , loginError);
        map.put(StringConstants.LOGIN_MESSAGE, message);
        return "login";
    }

    //todo remove this test function
    @PostMapping("/upload")
    public String uploadTest(@RequestParam("file")MultipartFile file){

        try {
            String fileName = FileHandler.uploadClientDoc(file , "/upload");
            LoggerUtil.logInfo(fileName);
        } catch (IOException e) {
            LoggerUtil.logException(this.getClass() , e);
        }
        return "redirect:/";
    }
}
