package com.untucapital.musoniservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "musoni_clients")
@Data
public class MusoniClient extends AbstractEntity{

    @NotNull
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    private String status;

    @Column(name = "activation_date", nullable = false)
    private String activationDate;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable=false)
    private String lastname;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "office_name", nullable = false)
    private String officeName;

    @Column(name = "submitted_by_username", nullable = false)
    private String submittedByUsername;

    @Column(name = "savings_account_id", nullable = false)
    private String savingsAccountId;

}
