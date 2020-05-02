package com.realestate.re.service.core.role;

import com.realestate.re.service.common.abstracts.AbstractEntity;
import com.realestate.re.service.common.enums.Permission;
import com.realestate.re.service.common.enums.Status;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by dhiraj on 2/9/18.
 */
@Entity
@Table(name = "table_role_info")
public class Role extends AbstractEntity<Long> {

    private String title;

    private Status status;

    @ElementCollection(fetch= FetchType.EAGER)
    private Set<Permission> permissionSet;

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

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
}
