package com.example.creditms.entity.fcb;

import com.example.creditms.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_inactive_searches")
public class Inactive extends AbstractEntity {

    @Column(name = "counterparty")
    private String counterparty;

    @Column(name = "branch")
    private String branch;

    @Column(name = "amount")
    private String amount;

    @Column(name = "event_type")
    @JsonProperty(value = "event_type")
    private String eventType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "comment")
    private String comment;

    @Column(name = "court_ref")
    private String courtRef;

    @Column(name = "event_date")
    private String eventDate;

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCourtRef() {
        return courtRef;
    }

    public void setCourtRef(String courtRef) {
        this.courtRef = courtRef;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
