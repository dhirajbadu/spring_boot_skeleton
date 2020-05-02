package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.UserType;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.ParseUtls;
import com.realestate.re.service.core.role.RoleService;
import com.realestate.re.service.core.user.UserInfoDto;
import com.realestate.re.service.core.user.UserInfoError;
import com.realestate.re.service.core.user.UserInfoService;
import com.realestate.re.service.core.user.UserInfoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userinfoService;

    @Autowired
    private UserInfoValidation userinfoValidation;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('System')")
    public String index() {
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public String list() {
        return "/userinfo/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('System')")
    public String add(ModelMap map) {

        map.put(StringConstants.USER_TYPE_LIST , UserType.values());
        map.put(StringConstants.ROLE_LIST , roleService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , 300 , 0 , "asc" , "id"));
        return "/userinfo/add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('System')")
    public String save(@ModelAttribute("userinfo") UserInfoDto userinfoDto, BindingResult bindingResult, ModelMap map, RedirectAttributes redirectAttributes) {

        try {

            synchronized (this.getClass()) {

                if (userinfoDto == null) {
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
                    return "redirect:/userinfo/add";
                }

                UserInfoError userinfoError = userinfoValidation.onSave(userinfoDto, bindingResult);
                if (!userinfoError.isValid()) {
                    map.put(StringConstants.USERINFO, userinfoDto);
                    map.put(StringConstants.USERINFO_ERROR, userinfoError);
                    map.put(StringConstants.USER_TYPE_LIST , UserType.values());
                    map.put(StringConstants.ROLE_LIST , roleService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , 300 , 0 , "asc" , "id"));

                    return "/userinfo/add";
                }

                userinfoService.save(userinfoDto);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "userinfo saved successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/user/list";
    }

    @GetMapping("/show/{userinfoId}")
    @PreAuthorize("hasAuthority('System')")
    public String show(@PathVariable("userinfoId") Long userinfoId, ModelMap map, RedirectAttributes redirectAttributes) {
        try {

            if (userinfoId == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            if (userinfoId < 1){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            UserInfoDto userinfoDto = userinfoService.show(userinfoId);

            if (userinfoDto == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "requested record not found");
                return "redirect:/user/list";
            }
            map.put(StringConstants.USERINFO , userinfoDto);
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }
        return "/userinfo/show";
    }

    @GetMapping("/action/{userinfoId}")
    @PreAuthorize("hasAuthority('System')")
    public String action(@PathVariable("userinfoId") Long userinfoId, @RequestParam("query")String query , ModelMap map, RedirectAttributes redirectAttributes) throws Exception {
        try {

            if (userinfoId == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            if (userinfoId < 1){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            if (ParseUtls.isNull(query)){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            UserInfoDto userinfoDto = userinfoService.show(userinfoId);

            if (userinfoDto == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "requested record not found");
                return "redirect:/user/list";
            }
            userinfoService.action(userinfoId , query);
            redirectAttributes.addFlashAttribute(StringConstants.MESSAGE , "user action successfully completed");
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }
        return "redirect:/user/show/"+userinfoId;
    }

    @GetMapping("/edit/{userinfoId}")
    @PreAuthorize("hasAuthority('System')")
    public String edit(@PathVariable("userinfoId") Long userinfoId, ModelMap map, RedirectAttributes redirectAttributes) {
        try {

            if (userinfoId == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            if (userinfoId < 1){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/user/list";
            }

            UserInfoDto userinfoDto = userinfoService.show(userinfoId);

            if (userinfoDto == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "requested record not found");
                return "redirect:/user/list";
            }
            map.put(StringConstants.USERINFO , userinfoDto);
            map.put(StringConstants.USER_TYPE_LIST , UserType.values());
            map.put(StringConstants.ROLE_LIST , roleService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , 300 , 0 , "asc" , "id"));

        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }
        return "/userinfo/edit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('System')")
    public String update(@ModelAttribute("userinfo") UserInfoDto userinfoDto, BindingResult bindingResult, ModelMap map, RedirectAttributes redirectAttributes) {

        try {
            synchronized (this.getClass()) {

                if (userinfoDto == null) {
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
                    return "redirect:/user/list";
                }

                UserInfoError userinfoError = userinfoValidation.onUpdate(userinfoDto, bindingResult);
                if (!userinfoError.isValid()) {
                    if (userinfoError.isVersionUnmatched()){
                        redirectAttributes.addFlashAttribute(StringConstants.ERROR , userinfoError.getMessage());
                        return "redirect:/userinfo/edit/"+userinfoDto.getId();
                    }

                    map.put(StringConstants.USERINFO, userinfoDto);
                    map.put(StringConstants.USERINFO_ERROR, userinfoError);
                    map.put(StringConstants.USER_TYPE_LIST , UserType.values());
                    map.put(StringConstants.ROLE_LIST , roleService.listOffsetBased(Status.getEnumList(Status.ACTIVE) , 300 , 0 , "asc" , "id"));

                    return "/userinfo/edit";
                }

                userinfoService.update(userinfoDto);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "userinfo updated successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/user/list";
    }

    @GetMapping("/delete/{userinfoId}")
    @PreAuthorize("hasAuthority('System')")
    public String delete(@PathVariable("userinfoId") Long userinfoId, @RequestParam("version")Integer version , RedirectAttributes redirectAttributes) {

        try {

            synchronized (this.getClass()){
                UserInfoError userinfoError = userinfoValidation.onDelete(userinfoId , version);
                if (!userinfoError.isValid()){
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR , userinfoError.getMessage());
                    return "redirect:/user/list";
                }

                userinfoService.delete(userinfoId);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE , "userinfo deleted successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/user/list";
    }
}
