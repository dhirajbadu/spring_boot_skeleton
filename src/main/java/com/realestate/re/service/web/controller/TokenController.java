package com.realestate.re.service.web.controller;

import com.realestate.re.service.common.constants.StringConstants;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.core.token.TokenDto;
import com.realestate.re.service.core.token.TokenError;
import com.realestate.re.service.core.token.TokenService;
import com.realestate.re.service.core.token.TokenValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenValidation tokenValidation;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('System')")
    public String index() {
        return "redirect:/token/list";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('System')")
    public String list() {
        return "/token/list";
    }

    @GetMapping("/delete/{tokenId}")
    @PreAuthorize("hasAuthority('System')")
    public String delete(@PathVariable("tokenId") Long tokenId, @RequestParam("version")Integer version , RedirectAttributes redirectAttributes) throws Exception {

        try {

            synchronized (this.getClass()){
                TokenError tokenError = tokenValidation.onDelete(tokenId , version);
                if (!tokenError.isValid()){
                    redirectAttributes.addFlashAttribute(StringConstants.ERROR , tokenError.getMessage());
                    return "redirect:/token/list";
                }

                tokenService.delete(tokenId);
                redirectAttributes.addFlashAttribute(StringConstants.MESSAGE , "token deleted successfully");
            }
        } catch (Exception ex) {
            LoggerUtil.logException(this.getClass(), ex);
            throw ex;
        }

        return "redirect:/token/list";
    }
}
