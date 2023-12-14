package com.example.creditms.entity.fcb;

import com.example.creditms.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fcb_additional_info")
public class AdditionalInfo extends AbstractEntity {

    @JsonProperty(value = "INFO")
    private String INFO;

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

}
