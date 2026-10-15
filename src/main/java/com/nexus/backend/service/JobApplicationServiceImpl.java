
package com.nexus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.response.JobApplicationResponse;
import com.nexus.backend.entity.JobApplication;
import com.nexus.backend.repository.JobApplicationRepository;
import com.nexus.backend.service.JobApplicationService;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public JobApplicationResponse createApplication(
            CreateJobApplicationRequest request) {

        JobApplication application = new JobApplication();
        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setJobUrl(request.getJobUrl());
        application.setAppliedDate(request.getAppliedDate());
        application.setStatus(
                request.getStatus() == null
                        ? com.nexus.backend.entity.ApplicationStatus.WISHLIST
                        : request.getStatus());

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        return new JobApplicationResponse(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobApplicationResponse> getAllApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(JobApplicationResponse::new)
                .toList();
    }
}
