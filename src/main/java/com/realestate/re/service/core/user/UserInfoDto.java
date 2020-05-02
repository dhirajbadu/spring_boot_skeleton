package com.realestate.re.service.core.user;

import com.realestate.re.service.common.abstracts.AbstractDto;
import com.realestate.re.service.common.enums.UserType;
import com.realestate.re.service.core.role.Role;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserInfoDto extends AbstractDto {

    private Long userId;

    private String email;

    private String password;

    private String confirmPassword;

    private boolean enabled;

    private Date expire;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private UserType userType;

    private Set<Long> roleIdSet;

    private Set<Role> roleSet;

    public Set<Long> getRoleIdSet() {
        return roleIdSet;
    }

    public void setRoleIdSet(Set<Long> roleIdSet) {
        this.roleIdSet = roleIdSet;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
