package com.realestate.re.service.core.token;

import com.realestate.re.service.common.abstracts.GlobalValidation;
import com.realestate.re.service.common.service.GeneralMessage;
import com.realestate.re.service.common.service.RecaptchaService;
import com.realestate.re.service.common.utls.*;
import com.realestate.re.service.core.user.UserInfo;
import com.realestate.re.service.core.user.UserInfoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordValidation extends GlobalValidation{

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private RecaptchaService recaptchaService;

    @Autowired
    private TokenRepository tokenRepository;

    public GeneralMessage forgetPassword(String email, String recaptchaResponse, String remoteIp) {

        GeneralMessage gm = new GeneralMessage();

        try {

            if (recaptchaResponse == null) {
                gm.setValid(false);
                gm.setMessage("Please click on the check box of I'm not a robot");
                return gm;

            } else if (recaptchaResponse.isEmpty()) {
                gm.setValid(false);
                gm.setMessage("Please click on the check box of I'm not a robot");
                return gm;
            } else {

                String captchaVerifyMessage = recaptchaService.verifyRecaptcha(remoteIp, recaptchaResponse);

                if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
                    gm.setValid(false);
                    gm.setMessage("request invalid capcha");
                    return gm;
                }

            }

            UserInfo u = userRepository.findByEmail(ParseUtls.replaceAllSpace(ParseUtls.trimString(email)).toLowerCase());

            if (u == null){
                gm.setValid(false);
                gm.setMessage("user not found");
                return gm;
            }

            if (!(u.isEnabled() && u.isAccountNonLocked() && u.isAccountNonExpired())) {
                gm.setValid(false);
                gm.setMessage("Access denied , You are not an active user");
                return gm;
            }

        } catch (Exception e) {
            LoggerUtil.logWarn(this.getClass(), e);
            gm.setValid(false);
            gm.setMessage("Unknown Error, Contact Operator");
            return gm;
        }


        gm.setValid(true);
        gm.setMessage("");

        return gm;
    }

    public GeneralMessage resetPassword(String token , HttpServletRequest request){
        GeneralMessage gm = new GeneralMessage();

        try {
            gm.setMessage(checkString(token, 10 , 10 , "toen" , true));

            if (ParseUtls.isNotNull(gm.getMessage())){
                gm.setValid(false);
                return gm;
            }

            token = ParseUtls.replaceAllSpace(token);
            Token tokenEntity = tokenRepository.findByToken(token);

            if (tokenEntity == null){
                gm.setMessage("invalid link");
                gm.setValid(false);
                return gm;
            }

            if (tokenEntity.isUsed()){
                gm.setMessage("provided link is already used");
                gm.setValid(false);
                return gm;
            }

            //todo do security action
            if (!tokenEntity.isSendThroughEmail()){
                gm.setMessage("provided link is expired");
                gm.setValid(false);
                return gm;
            }

            if (!DateParseUtil.validateBeforeCurrentDate(tokenEntity.getExpireDate())){
                gm.setMessage("provided link is expired");
                gm.setValid(false);
                return gm;
            }

            gm.setValid(true);

            return gm;


        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            gm.setValid(false);
            gm.setMessage("Unknown Error, Contact Operator");
            return gm;
        }
    }

    public GeneralMessage resetPassword(String token , String password , String confirmPassowrd , String recaptchaResponse , HttpServletRequest request){
        GeneralMessage gm = new GeneralMessage();

        try {

            gm = resetPassword(token , request);

            if (!gm.isValid()){
                return gm;
            }

            gm.setMessage(checkString(password , 5, 20 , "password" , true));

            if (ParseUtls.isNotNull(gm.getMessage())){
                gm.setValid(false);
                return gm;
            }

            gm.setMessage(checkString(confirmPassowrd , 5, 20 , "confirmPassowrd" , true));

            if (ParseUtls.isNotNull(gm.getMessage())){
                gm.setValid(false);
                return gm;
            }

            if (ParseUtls.isNull(recaptchaResponse)){
                gm.setMessage("Please click on the check box of I'm not a robot");
                gm.setValid(false);
                return gm;
            }

            if (!password.equals(confirmPassowrd)){
                gm.setMessage("password doesnt watch with confirm password");
                gm.setValid(false);
                return gm;
            }

            String captchaVerifyMessage = recaptchaService.verifyRecaptcha(request.getRemoteAddr(), recaptchaResponse);

            if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
                gm.setValid(false);
                gm.setMessage("invalid captcha");
                return gm;
            }

            gm.setValid(true);

            return gm;

        }catch (Exception e){
            LoggerUtil.logException(this.getClass() , e);
            gm.setValid(false);
            gm.setMessage("Unknown Error, Contact Operator");
            return gm;
        }
    }
}
