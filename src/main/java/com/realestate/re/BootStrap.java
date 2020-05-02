package com.realestate.re;

import com.realestate.re.service.common.constants.ParameterConstants;
import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.UserType;
import com.realestate.re.service.common.utls.DateParseUtil;
import com.realestate.re.service.core.role.Role;
import com.realestate.re.service.core.role.RoleRepository;
import com.realestate.re.service.core.user.UserInfo;
import com.realestate.re.service.core.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class BootStrap {

    @Autowired
    private UserInfoRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void loader(){
        createUser();
    }
    
    private void createUser(){
        Role role = createRole("Role_System");

        if (role != null) {

            Set<Role> roleSet = new HashSet<>();

            roleSet.add(role);

            createUser("dhirajbadu50@gmail.com", "1234567", roleSet);
        }

        createAgentRole(ParameterConstants.AGENT_ROLE_TITLE);
    }

    private void createUser(String userName,  String password , Set<Role> roleSet) {
        try {


            if (userRepository.findByEmail(userName) == null) {

                UserInfo user = new UserInfo();

                user.setEmail(userName);
                user.setPassword(passwordEncoder.encode(password));
                user.setRoleSet(roleSet);
                user.setUserType(UserType.SYSTEM);
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setEnabled(true);
                user.setExpire(DateParseUtil.addYears(DateParseUtil.getCurrentDateTime(), 1));

                userRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void  createAgentRole(String title){
        Role role = roleRepository.findByTitle(title);
        if (role == null){
            Set<Permission> permissionSet = new HashSet<>();

            permissionSet.add(Permission.AGENT_UPDATE);
            permissionSet.add(Permission.AGENT_VIEW);
            permissionSet.add(Permission.CITY_CREATE);
            permissionSet.add(Permission.CITY_UPDATE);
            permissionSet.add(Permission.CITY_VIEW);

            role = new Role();

            role.setStatus(Status.ACTIVE);
            role.setTitle(title);
            role.setPermissionSet(permissionSet);

            roleRepository.save(role);
        }
    }

    private Role createRole(String title){

        Role role = roleRepository.findByTitle(title);
        if (role == null){


            Set<Permission> permissionSet = new HashSet<>();

            permissionSet.addAll(Arrays.asList(Permission.values()));

            role = new Role();

            role.setStatus(Status.ACTIVE);
            role.setTitle(title);
            role.setPermissionSet(permissionSet);

            return roleRepository.save(role);

        }else if (role.getPermissionSet().size() != Arrays.asList(Permission.values()).size()){
            Set<Permission> permissionSet = new HashSet<>();

            permissionSet.addAll(Arrays.asList(Permission.values()));

            role.setPermissionSet(permissionSet);

            return roleRepository.save(role);
        }

        return null;
    }
    
    
}
