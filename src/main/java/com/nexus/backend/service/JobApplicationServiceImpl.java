
package com.nexus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.response.JobApplicationResponse;
import com.nexus.backend.entity.JobApplication;
import com.nexus.backend.entity.User;
import com.nexus.backend.repository.JobApplicationRepository;
import com.nexus.backend.service.JobApplicationService;
import com.nexus.backend.dto.request.UpdateJobApplicationRequest;
import com.nexus.backend.exception.ResourceNotFoundException;

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

        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        application.setUser(currentUser);

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
    


	@Override
	@Transactional(readOnly = true)
	public JobApplicationResponse getApplicationById(Long id) {
	
	    JobApplication application = jobApplicationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Job application not found with ID: " + id
	            ));
	
	    return new JobApplicationResponse(application);
	}

	

	@Override
	public void deleteApplication(Long id) {
	
	    JobApplication application = jobApplicationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Job application not found with ID: " + id
	            ));
	
	    jobApplicationRepository.delete(application);
	}

	


	@Override
	public JobApplicationResponse updateApplication(
	        Long id, UpdateJobApplicationRequest request) {
	
	    JobApplication application = jobApplicationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Job application not found with ID: " + id
	            ));
	
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
	public Page<JobApplicationResponse> getApplications(Pageable pageable) {
		// TODO Auto-generated method stub
		return jobApplicationRepository.findAll(pageable)
	            .map(JobApplicationResponse::new);
	}
	
	



}
