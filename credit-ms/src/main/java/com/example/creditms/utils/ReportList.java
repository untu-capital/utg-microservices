package com.example.creditms.utils;

import com.example.creditms.entity.fcb.Report;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

class ReportList {

    @JsonProperty(value = "Report")
    public List<Report> report;

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }
}
