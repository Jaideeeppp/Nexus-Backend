
package com.nexus.backend.dto.response;

import java.time.LocalDate;

import com.nexus.backend.entity.ApplicationStatus;
import com.nexus.backend.entity.JobApplication;

public class JobApplicationResponse {

    private Long id;
    private String companyName;
    private String jobTitle;
    private String location;
    private String jobUrl;
    private LocalDate appliedDate;
    private ApplicationStatus status;

    public JobApplicationResponse(JobApplication application) {
        this.id = application.getId();
        this.companyName = application.getCompanyName();
        this.jobTitle = application.getJobTitle();
        this.location = application.getLocation();
        this.jobUrl = application.getJobUrl();
        this.appliedDate = application.getAppliedDate();
        this.status = application.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
