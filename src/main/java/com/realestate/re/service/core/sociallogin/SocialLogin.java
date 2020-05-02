package com.realestate.re.service.core.sociallogin;

import com.realestate.re.service.common.abstracts.AbstractEntity;
import com.realestate.re.service.common.enums.Status;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tabe_social_login_info")
public class SocialLogin extends AbstractEntity<Long>{

    private String authKey;

    private String name;

    private String email;

    private boolean emailVerified;

    private String locale;

    private String picture;

    private String client;

    private Status status;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
