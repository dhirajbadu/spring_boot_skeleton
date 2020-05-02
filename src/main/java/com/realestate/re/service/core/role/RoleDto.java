package com.realestate.re.service.core.role;

import com.realestate.re.service.common.abstracts.AbstractDto;
import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.enums.Status;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;
import java.util.Set;

public class RoleDto extends AbstractDto{

    private String title;

    private Status status;

    private List<Permission> permissionList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}