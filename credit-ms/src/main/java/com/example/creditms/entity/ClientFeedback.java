package com.example.creditms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "client_feedback")
public class ClientFeedback extends AbstractEntity {

    @NotNull
    @JoinColumn(name = "user_id")
    private String userId;

    @Column(name = "feedback_category")
    private String feedbackCategory;

    @Column(name = "feedback_description")
    private String feedbackDescription;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedbackCategory() {
        return feedbackCategory;
    }

    public void setFeedbackCategory(String feedbackCategory) {
        this.feedbackCategory = feedbackCategory;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }




}
