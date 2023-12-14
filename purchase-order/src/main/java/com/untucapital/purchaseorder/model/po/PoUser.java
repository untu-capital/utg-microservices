package com.untucapital.purchaseorder.model.po;

import com.untucapital.purchaseorder.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

;

@Entity
@Table(name = "po_users")
public class PoUser extends AbstractEntity {

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
