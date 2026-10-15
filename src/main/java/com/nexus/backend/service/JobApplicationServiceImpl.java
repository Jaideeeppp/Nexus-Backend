
package com.nexus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.request.UpdateJobApplicationRequest;
import com.nexus.backend.dto.response.JobApplicationResponse;
import com.nexus.backend.entity.JobApplication;
import com.nexus.backend.entity.User;
import com.nexus.backend.exception.ResourceNotFoundException;
import com.nexus.backend.repository.JobApplicationRepository;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Get the currently authenticated user from the JWT security context.
    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof User)) {
            throw new IllegalStateException(
                    "Authenticated user could not be identified.");
        }

        return (User) authentication.getPrincipal();
    }

    // Find a job application only if it belongs to the logged-in user.
    private JobApplication getOwnedApplication(Long id, Long userId) {
        return jobApplicationRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job application not found with ID: " + id));
    }

    @Override
    public JobApplicationResponse createApplication(
            CreateJobApplicationRequest request) {

        User currentUser = getCurrentUser();

        JobApplication application = new JobApplication();

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setJobUrl(request.getJobUrl());
        application.setAppliedDate(request.getAppliedDate());
        application.setStatus(request.getStatus());

        // Associate the new application with the logged-in user.
        application.setUser(currentUser);

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        return new JobApplicationResponse(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobApplicationResponse> getAllApplications() {
        User currentUser = getCurrentUser();

        return jobApplicationRepository.findAllByUserId(currentUser.getId())
                .stream()
                .map(JobApplicationResponse::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JobApplicationResponse getApplicationById(Long id) {
        User currentUser = getCurrentUser();

        JobApplication application =
                getOwnedApplication(id, currentUser.getId());

        return new JobApplicationResponse(application);
    }

    @Override
    public void deleteApplication(Long id) {
        User currentUser = getCurrentUser();

        JobApplication application =
                getOwnedApplication(id, currentUser.getId());

        jobApplicationRepository.delete(application);
    }

    @Override
    public JobApplicationResponse updateApplication(
            Long id, UpdateJobApplicationRequest request) {

        User currentUser = getCurrentUser();

        JobApplication application =
                getOwnedApplication(id, currentUser.getId());

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setJobUrl(request.getJobUrl());
        application.setAppliedDate(request.getAppliedDate());
        application.setStatus(request.getStatus());

        JobApplication updatedApplication =
                jobApplicationRepository.save(application);

        return new JobApplicationResponse(updatedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getApplications(
            Pageable pageable) {

        User currentUser = getCurrentUser();

        return jobApplicationRepository
                .findAllByUserId(currentUser.getId(), pageable)
                .map(JobApplicationResponse::new);
    }
}
