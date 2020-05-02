package com.realestate.re.service.web.controller.restcontroller;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.UIParserUtls;
import com.realestate.re.service.core.token.TokenDto;
import com.realestate.re.service.core.token.TokenFilter;
import com.realestate.re.service.core.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenRestController {

     @Autowired
     private TokenService tokenService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public Map<String , Object> ajaxList(HttpServletRequest req) {
        try {

            Map<String, Object> resultMap = new HashMap<>();

            TokenFilter filter = (TokenFilter) new TokenFilter().build(req);
            List<TokenDto> tokenDtoList = tokenService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , filter.getMax() , filter.getOffset(), filter.getDirection() , filter.getProperty());
            List<String[]> arrStr = new ArrayList<>();

            for (TokenDto tokenDto : tokenDtoList) {
                String[] arr = new String[7];

                arr[0] = String.valueOf(tokenDto.getId());
                arr[1] = tokenDto.getToken();
                arr[2] = tokenDto.isUsed() ? "true" : "false";
                arr[3] = tokenDto.getExpireDate().toString();
                arr[4] = tokenDto.getAssociation().getValue();
                arr[5] = tokenDto.getAssociationKey();
                arr[6] = tokenDto.isSendThroughEmail() ? "true" : "false";

                arrStr.add(arr);

            }

            resultMap.put("data", arrStr);
            resultMap.put("recordsTotal", tokenService.countByStatus(Status.getEnumList(Status.ACTIVE)));
            resultMap.put("recordsFiltered", tokenService.countByStatus(Status.getEnumList(Status.ACTIVE)));

            return resultMap;
        }catch (Exception e){
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }
}
