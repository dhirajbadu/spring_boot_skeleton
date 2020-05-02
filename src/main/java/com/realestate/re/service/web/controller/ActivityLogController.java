package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.utls.FileHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("activity/log")
public class ActivityLogController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('System')")
    public String index(){
        return "redirect:/activity/list";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public String list(ModelMap map){

        map.put(StringConstants.LOG_FILE_LIST , FileHandler.getLogFileNameList());
        return "activitylog/filelist";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAuthority('System')")
    public String show(ModelMap map , @RequestParam(value = "date")String date){

        if (date == null){
            return "redirect:/activity/list";
        }
        map.put(StringConstants.LOG_LIST , FileHandler.getListOfActivityLog(date));
        return "activitylog/list";
    }
}
