package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.service.GeneralMessage;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.token.PasswordValidation;
import com.realestate.re.service.core.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth/password")
public class PasswordController {

    @Autowired
    private PasswordValidation passwordValidation;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/forget")
    public String resetPassword(){
        return "userinfo/forgetpassword";
    }

    @PostMapping("/forget")
    @PreAuthorize("permitAll()")
    public String forgetPassword(@RequestParam(value = "username") String username, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request , RedirectAttributes redirectAttributes) throws Exception {

        try {
            synchronized (this.getClass()) {
                GeneralMessage gm = passwordValidation.forgetPassword(username, recaptchaResponse, request.getRemoteAddr());
                if (!gm.isValid()) {
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR ,gm.getMessage());
                    return "redirect:/auth/password/forget";
                } else {
                    userInfoService.resetPasswordTokenizer(username , request);
                    return "redirect:/login?"+StringConstants.LOGIN_MESSAGE + "=Password reset link has been sent to your email. Please check the email";
                }
            }
        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }

    @GetMapping("/reset")
    @PreAuthorize("permitAll()")
    public String resetPassword(@RequestParam("token")String token , HttpServletRequest request , ModelMap map){

        try {
            synchronized (this.getClass()) {
                GeneralMessage error = passwordValidation.resetPassword(token, request);

                if (!error.isValid()) {
                    return "redirect:/login?" + StringConstants.LOGIN_ERROR + "=" + error.getMessage();
                }
                map.put(StringConstants.TOKEN, token);
            }
        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            throw e;
        }
        return "userinfo/resetpassword";
    }

    @PostMapping("/reset")
    @PreAuthorize("permitAll()")
    public String resetPassword(@RequestParam("token")String token , @RequestParam("password")String password , @RequestParam("confirmPassword")String confirmPassword , @RequestParam(name = "g-recaptcha-response") String recaptchaResponse , HttpServletRequest request , ModelMap map){

        try {

            synchronized (this.getClass()) {
                GeneralMessage error = passwordValidation.resetPassword(token, password , confirmPassword , recaptchaResponse , request);

                if (!error.isValid()) {
                    map.put(StringConstants.TOKEN , token);
                    map.put(StringConstants.ERROR , error.getMessage());
                    return "userinfo/resetpassword";
                }

                userInfoService.resetPassword(token , password);
            }

        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            throw e;
        }
        return "redirect:/login?"+StringConstants.LOGIN_MESSAGE + "=Password has been updated please login with new password";
    }
}
