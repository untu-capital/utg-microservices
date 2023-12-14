package com.untucapital.cms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authorisation")
public class Authorisation extends AbstractEntity {
    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "auth_level")
    private String authLevel;

    @Column(name = "user_id")
    private String userId;


    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
