package com.untucapital.cms.entity;


import com.untucapital.cms.enums.RoleType;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;


/**
 * @author Chirinda Nyasha Dell {22/11/2021}
 */

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60, name = "name")
    private RoleType name;

    @Column(name = "description")
    private String description;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
