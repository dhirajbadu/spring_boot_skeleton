package com.realestate.re.service.core.user;

import com.realestate.re.service.common.abstracts.AbstractEntity;
import com.realestate.re.service.common.enums.UserType;
import com.realestate.re.service.core.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "table_user_login_info")
public class UserInfo extends AbstractEntity<Long>{

    @Column(unique = true , nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @Column(nullable = false)
    private Date expire;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roleSet;

    @Column(nullable = false)
    private UserType userType;

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
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

    public UserInfo(){}

    public UserInfo(long id, Date created, Date lastModified, int version, String email, String password, boolean enabled, Date expire, boolean accountNonExpired, boolean accountNonLocked, UserType userType) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.expire = expire;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userType = userType;
    }
}
