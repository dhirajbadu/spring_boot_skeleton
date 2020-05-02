package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.role.RoleDto;
import com.realestate.re.service.core.role.RoleError;
import com.realestate.re.service.core.role.RoleService;
import com.realestate.re.service.core.role.RoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PreDestroy;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleValidation roleValidation;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('System')")
    public String index() {
        return "redirect:/role/list";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public String list() {
        return "/role/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('System')")
    public String add(ModelMap map) {

        map.put(StringConstants.PERMISSION_LIST , Permission.values());
        return "/role/add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('System')")
    public String save(@ModelAttribute("role") RoleDto roleDto, BindingResult bindingResult, ModelMap map, RedirectAttributes redirectAttributes) {

        try {

            synchronized (this.getClass()) {

                if (roleDto == null) {
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
                    return "redirect:/role/add";
                }

                RoleError roleError = roleValidation.onSave(roleDto, bindingResult);
                if (!roleError.isValid()) {
                    map.put(StringConstants.ROLE, roleDto);
                    map.put(StringConstants.ROLE_ERROR, roleError);
                    return "/role/add";
                }

                roleService.save(roleDto);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "saved successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/role/list";
    }

    @GetMapping("/edit/{roleId}")
    @PreAuthorize("hasAuthority('System')")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap map, RedirectAttributes redirectAttributes) {
        try {

            if (roleId == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/role/list";
            }

            if (roleId < 1){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "bad request");
                return "redirect:/role/list";
            }

            RoleDto roleDto = roleService.show(roleId , Status.ACTIVE);

            if (roleDto == null){
                redirectAttributes.addFlashAttribute(StringConstants.ERROR , "requested record not found");
                return "redirect:/role/list";
            }
            map.put(StringConstants.ROLE , roleDto);
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }
        return "/role/edit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('System')")
    public String update(@ModelAttribute("role") RoleDto roleDto, BindingResult bindingResult, ModelMap map, RedirectAttributes redirectAttributes) {

        try {
            synchronized (this.getClass()) {

                if (roleDto == null) {
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR, "please fill the form properly");
                    return "redirect:/role/list";
                }

                RoleError roleError = roleValidation.onUpdate(roleDto, bindingResult);
                if (!roleError.isValid()) {
                    if (roleError.isVersionUnmatched()){
                        redirectAttributes.addFlashAttribute(StringConstants.ERROR , roleError.getMessage());
                        return "redirect:/role/edit/"+roleDto.getId();
                    }

                    map.put(StringConstants.ROLE, roleDto);
                    map.put(StringConstants.ROLE_ERROR, roleError);
                    return "/role/add";
                }

                roleService.update(roleDto);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE, "saved successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/role/list";
    }

    @GetMapping("/delete/{roleId}")
    @PreAuthorize("hasAuthority('System')")
    public String delete(@PathVariable("roleId") Long roleId, @RequestParam("version")Integer version , RedirectAttributes redirectAttributes) {

        try {

            synchronized (this.getClass()){
                RoleError roleError = roleValidation.onDelete(roleId , version);
                if (!roleError.isValid()){
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR , roleError.getMessage());
                    return "redirect:/role/list";
                }

                roleService.delete(roleId);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE , "deleted successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/role/list";
    }
}
