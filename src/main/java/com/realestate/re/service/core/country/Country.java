package com.realestate.re.service.core.country;

import com.realestate.re.service.common.abstracts.AbstractEntity;
import com.realestate.re.service.common.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "table_country")
public class Country extends AbstractEntity<Long>{

    @Column(length = 50 , unique = true , nullable = false)
    @NotEmpty
    private String name;

    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
