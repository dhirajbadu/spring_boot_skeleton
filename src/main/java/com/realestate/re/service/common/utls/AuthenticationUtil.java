package com.realestate.re.service.common.utls;

import com.realestate.re.service.core.user.UserInfo;
import com.realestate.re.service.core.user.UserInfoDto;
import com.realestate.re.service.core.user.UserInfoRepository;
import com.realestate.re.service.web.session.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

    @Autowired
    private UserInfoRepository userRepository;

    public final Object getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();

        return principal;
    }

    public static final Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    public final UserInfoDto getCurrentUserDto() {

        Object principal = getCurrentPrincipal();

        if (principal instanceof UserDetailsWrapper) {
            UserInfoDto currentUser = ((UserDetailsWrapper) principal).getUser();
            return currentUser;
        }

        return null;
    }

    public final UserInfo getCurrentUserEntity() {

        Object principal = getCurrentPrincipal();

        if (principal instanceof UserDetailsWrapper) {
            UserInfoDto userDTO = ((UserDetailsWrapper) principal).getUser();
            UserInfo currentUser = userRepository.findById(userDTO.getUserId()).get();

            return currentUser;
        }

        return null;
    }

    public final UserInfo getCurrentUserFullEntity() {

        Object principal = getCurrentPrincipal();

        if (principal instanceof UserDetailsWrapper) {
            UserInfoDto userDTO = ((UserDetailsWrapper) principal).getUser();
            UserInfo currentUser = userRepository.findByIdJoinRole(userDTO.getUserId());

            return currentUser;
        }

        return null;
    }

}
