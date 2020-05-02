package com.realestate.re.service.web.controller.restcontroller;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.UIParserUtls;
import com.realestate.re.service.core.user.UserInfoDto;
import com.realestate.re.service.core.user.UserInfoFilter;
import com.realestate.re.service.core.user.UserInfoService;
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
@RequestMapping("/api/user")
public class UserInfoRestController {

     @Autowired
     private UserInfoService userinfoService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public Map<String , Object> ajaxList(HttpServletRequest req) {
        try {

            Map<String, Object> resultMap = new HashMap<>();

            UserInfoFilter filter = (UserInfoFilter) new UserInfoFilter().build(req);
            List<UserInfoDto> userinfoDtoList = userinfoService.listOffsetBased(filter.getMax() , filter.getOffset(), filter.getDirection() , filter.getProperty());
            List<String[]> arrStr = new ArrayList<>();

            for (UserInfoDto userinfoDto : userinfoDtoList) {
                String[] arr = new String[6];

                arr[0] = String.valueOf(userinfoDto.getUserId());
                arr[1] = userinfoDto.getEmail();
                arr[2] = userinfoDto.isEnabled() ? "true" : "false";
                arr[3] = userinfoDto.isAccountNonLocked() ? "true" : "false";
                arr[4] = userinfoDto.isAccountNonExpired() ? "true" : "false";
                arr[5] = userinfoDto.getExpire().toString();

                arrStr.add(arr);

            }

            resultMap.put("data", arrStr);
            resultMap.put("recordsTotal", userinfoService.count());
            resultMap.put("recordsFiltered", userinfoService.count());

            return resultMap;
        }catch (Exception e){
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }
}
