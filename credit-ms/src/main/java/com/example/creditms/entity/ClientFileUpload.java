package com.example.creditms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class ClientFileUpload extends AbstractEntity{


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_description")
    private String fileDescription;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
