package com.untucapital.cms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cms_vault")

public class CmsVault extends AbstractEntity {

    @Column(name = "account")
    private String account;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



}
