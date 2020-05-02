package com.realestate.re.service.web.session;


import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.utls.LoggerUtil;
import com.realestate.re.service.common.utls.ParseUtls;
import com.realestate.re.service.core.role.Role;
import com.realestate.re.service.core.user.UserInfoDto;
import com.realestate.re.service.core.user.UserInfo;
import com.realestate.re.service.core.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            if (ParseUtls.isNull(username)){
                throw new UsernameNotFoundException("user doesnt exists");
            }

            UserInfo u = userRepository.findByEmailLeftJoinRole(ParseUtls.replaceAllSpace(ParseUtls.trimString(username)).toLowerCase());

            System.out.println("login");

            //todo
            /*HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            final StringBuilder msg = new StringBuilder();

            msg.append(curRequest.getRemoteAddr());

            final String forwardedFor = curRequest.getHeader("X-Forwarded-For");

            if (forwardedFor != null) {
                msg.append(", forwardedFor = ").append(forwardedFor);
            }*/

            if (u == null) {

                throw new UsernameNotFoundException("user doesnt exists");
            }

            UserInfoDto dto = getUserDTO(u);

            return new UserDetailsWrapper(dto, getAuthorities(u));

        } catch (UsernameNotFoundException ex) {
            LoggerUtil.logInfo("unregistered user trying to login : \t username : " + username);
            throw new UsernameNotFoundException("user doesnt exists");
        } catch (Exception e) {
            LoggerUtil.logException(this.getClass(), e);
            throw new UsernameNotFoundException("user doesnt exists");
        }

    }

    /*private List<GrantedAuthority> getAuthorities(UserInfo user) {

        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        auth.add(new SimpleGrantedAuthority(user.getUserType().getValue()));
        auth.add(new SimpleGrantedAuthority("AUTHENTICATED"));

        return auth;
    }*/

    private List<GrantedAuthority> getAuthorities(UserInfo user){

        Set<String> authorities = new HashSet<>();

        for (Role role : user.getRoleSet()){

            for (Permission permission : role.getPermissionSet()){

                try {

                    authorities.add(permission.getValue());

                }catch (Exception e){
                    continue;
                }
            }
        }

        List<GrantedAuthority> auth = createAuthorityList(authorities);

        if (auth == null){

            new ArrayList<GrantedAuthority>();

            auth.add(new SimpleGrantedAuthority(user.getUserType().getValue()));
            auth.add(new SimpleGrantedAuthority("AUTHENTICATED"));

        }else {

            auth.add(new SimpleGrantedAuthority(user.getUserType().getValue()));
            auth.add(new SimpleGrantedAuthority("AUTHENTICATED"));
        }

        return auth;
    }

    private List<GrantedAuthority> createAuthorityList(Set<String>roles) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size());

        for (String role : roles) {

            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    private UserInfoDto getUserDTO(UserInfo user) {

        UserInfoDto dto = new UserInfoDto();

        dto.setUserId(user.getId());
        dto.setEnabled(user.isEnabled());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUserType(user.getUserType());
        dto.setAccountNonExpired(user.isAccountNonExpired());
        dto.setAccountNonLocked(user.isAccountNonLocked());
        dto.setExpire(user.getExpire());

        return dto;
    }


}
