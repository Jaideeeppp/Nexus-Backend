
package com.nexus.backend.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.nexus.backend.entity.ApplicationStatus;

public class UpdateJobApplicationRequest {

    @NotBlank
    @Size(max = 100)
    private String companyName;

    @NotBlank
    @Size(max = 100)
    private String jobTitle;

    @Size(max = 100)
    private String location;

    @Size(max = 2048)
    @Pattern(regexp = "^(https?://\\S+)?$")
    private String jobUrl;

    @NotNull
    private LocalDate appliedDate;

    @NotNull
    private ApplicationStatus status;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
