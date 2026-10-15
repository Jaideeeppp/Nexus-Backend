
package com.nexus.backend.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.nexus.backend.entity.ApplicationStatus;

public class CreateJobApplicationRequest {

    @NotBlank(message = "Company name is required.")
    @Size(max = 100, message = "Company name cannot exceed 100 characters.")
    private String companyName;

    @NotBlank(message = "Job title is required.")
    @Size(max = 100, message = "Job title cannot exceed 100 characters.")
    private String jobTitle;

    @Size(max = 100, message = "Location cannot exceed 100 characters.")
    private String location;

    @Size(max = 2048, message = "Job URL cannot exceed 2048 characters.")
    @Pattern(
        regexp = "^(https?://\\S+)?$",
        message = "Job URL must start with http:// or https://."
    )
    private String jobUrl;

    @NotNull(message = "Application date is required.")
    private LocalDate appliedDate;

    private ApplicationStatus status = ApplicationStatus.WISHLIST;

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
