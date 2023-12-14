package com.untucapital.musoniservice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Entity
@Table(name = "contact_details")
public class ContactDetail extends AbstractEntity {

    @NotNull
    @Column(name = "mobile_number", length = 20, nullable = false)
    private Long mobileNumber;

    @NotNull
    @Email
    @Column(name = "email_address", length = 50, nullable = false)
    private String emailAddress;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    public @NotNull Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
