package com.realestate.re.service.common.service;

import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.RecaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dhiraj on 2/2/18.
 */
@Service
public class RecaptchaService {

    @Value("${google.recaptcha.secret}")

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private static final String LOCALHOST_SITE_SECRET = "6LeG-kMUAAAAACApsSOmYiulgmDIMHn6aMqaKeUM";

    private static final String LOCALHOST_Secret_Key = "6LeG-kMUAAAAADnsNpYzwxxiwKgIEBN56kMUR-RE";

    public String verifyRecaptcha(String ip, String recaptchaResponse){

        Map<String, String> body = new HashMap<>();

        body.put("secret", LOCALHOST_Secret_Key);

        body.put("response", recaptchaResponse);

        body.put("remoteip", ip);

        LoggerUtil.logDebug(this.getClass() ,"Request body for recaptcha: " + body);

        ResponseEntity<Map> recaptchaResponseEntity = new RestTemplate()
                .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL+ "?secret={secret}&response={response}&remoteip={remoteip}", body, Map.class, body);

        LoggerUtil.logDebug(this.getClass() ,"Response from recaptcha: " + recaptchaResponseEntity);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

        boolean recaptchaSucess = (Boolean)responseBody.get("success");

        if ( !recaptchaSucess) {

            List<String> errorCodes = (List)responseBody.get("error-codes");

            String errorMessage = errorCodes.stream().map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s)).collect(Collectors.joining(", "));

            return errorMessage;

        }else {

            return StringUtils.EMPTY;

        }

    }
}
