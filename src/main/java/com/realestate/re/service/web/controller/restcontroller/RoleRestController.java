package com.realestate.re.service.web.controller.restcontroller;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.UIParserUtls;
import com.realestate.re.service.core.role.RoleDto;
import com.realestate.re.service.core.role.RoleFilter;
import com.realestate.re.service.core.role.RoleService;
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
@RequestMapping("/api/role")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public Map<String , Object> ajaxList(HttpServletRequest req) {
        try {

            Map<String, Object> resultMap = new HashMap<>();

            RoleFilter filter = (RoleFilter) new RoleFilter().build(req);
            List<RoleDto> roleDtoList = roleService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , filter.getMax() , filter.getOffset(), filter.getDirection() , filter.getProperty());
            List<String[]> arrStr = new ArrayList<>();

            for (RoleDto roleDto : roleDtoList) {
                String[] arr = new String[3];

                arr[0] = String.valueOf(roleDto.getId());
                arr[1] = roleDto.getTitle();
                arr[2] = UIParserUtls.getPermisions(roleDto.getPermissionList());

                arrStr.add(arr);

            }

            resultMap.put("data", arrStr);
            resultMap.put("recordsTotal", roleService.countByStatus(Status.getEnumList(Status.ACTIVE)));
            resultMap.put("recordsFiltered", roleService.countByStatus(Status.getEnumList(Status.ACTIVE)));

            return resultMap;
        }catch (Exception e){
            LoggerUtil.logException(this.getClass(), e);
            throw e;
        }
    }
}
