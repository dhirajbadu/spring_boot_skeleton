package com.realestate.re.service.core.city;

import com.realestate.re.service.common.abstracts.AbstractEntity;
import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.core.country.Country;

import javax.persistence.*;

@Entity
@Table(name = "table_city")
public class City extends AbstractEntity<Long>{

    @Column(length = 50 , nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
