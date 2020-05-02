package com.realestate.re.service.core.token;

import com.realestate.re.service.common.abstracts.AbstractDto;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.TokenAssociation;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class TokenDto extends AbstractDto{

    private String token;

    private boolean used;

    private Date expireDate;

    private TokenAssociation association;

    private String associationKey;

    private boolean sendThroughEmail;

    private Status status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public TokenAssociation getAssociation() {
        return association;
    }

    public void setAssociation(TokenAssociation association) {
        this.association = association;
    }

    public String getAssociationKey() {
        return associationKey;
    }

    public void setAssociationKey(String associationKey) {
        this.associationKey = associationKey;
    }

    public boolean isSendThroughEmail() {
        return sendThroughEmail;
    }

    public void setSendThroughEmail(boolean sendThroughEmail) {
        this.sendThroughEmail = sendThroughEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}