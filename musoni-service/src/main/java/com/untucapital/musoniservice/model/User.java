package com.untucapital.musoniservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "contact_detail_id"})
})
@RequiredArgsConstructor
@Data
public class User extends AbstractEntity {

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_token")
    private String resetPasswordToken;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @NotNull
    @JoinColumn(name = "contact_detail_id", nullable = false)
    private ContactDetail contactDetail;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "cms_user_id")
    private CmsUser cmsUser;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "po_user_id")
    private PoUser poUser;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotNull
    @Column(nullable = false)
    private boolean verified;

    private String branch;

    @Column(name = "credit_commit_group")
    private String creditCommitGroup;

    @Column(name = "dirt_of_birth")
    private String dirtOfBirth;

    @Column(name = "marital_status")
    private String maritalStatus;

    private String Gender;

    private String City;

    private String suburb;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "musoni_client_id")
    private String musoniClientId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
