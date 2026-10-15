
package com.nexus.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.request.UpdateJobApplicationRequest;
import com.nexus.backend.dto.response.ApiResponse;
import com.nexus.backend.dto.response.JobApplicationResponse;
import com.nexus.backend.service.JobApplicationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<ApiResponse<JobApplicationResponse>>
            createApplication(
                    @Valid @RequestBody CreateJobApplicationRequest request) {

        JobApplicationResponse application =
                jobApplicationService.createApplication(request);

        ApiResponse<JobApplicationResponse> response =
                new ApiResponse<>(
                        201,
                        "Job application created successfully.",
                        application
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobApplicationResponse>>>
            getAllApplications() {

        List<JobApplicationResponse> applications =
                jobApplicationService.getAllApplications();

        ApiResponse<List<JobApplicationResponse>> response =
                new ApiResponse<>(
                        200,
                        "Job applications retrieved successfully.",
                        applications
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobApplicationResponse>>
            getApplicationById(@PathVariable Long id) {

        JobApplicationResponse application =
                jobApplicationService.getApplicationById(id);

        ApiResponse<JobApplicationResponse> response =
                new ApiResponse<>(
                        200,
                        "Job application retrieved successfully.",
                        application
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobApplicationResponse>>
            updateApplication(
                    @PathVariable Long id,
                    @Valid @RequestBody UpdateJobApplicationRequest request) {

        JobApplicationResponse application =
                jobApplicationService.updateApplication(id, request);

        ApiResponse<JobApplicationResponse> response =
                new ApiResponse<>(
                        200,
                        "Job application updated successfully.",
                        application
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>
            deleteApplication(@PathVariable Long id) {

        jobApplicationService.deleteApplication(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        200,
                        "Job application deleted successfully.",
                        null
                );

        return ResponseEntity.ok(response);
    }
    

	@GetMapping("/page")
	public ResponseEntity<ApiResponse<Page<JobApplicationResponse>>>
	        getApplications(
	                @PageableDefault(
	                        size = 10,
	                        sort = "appliedDate",
	                        direction = Sort.Direction.DESC
	                ) Pageable pageable) {
	
	    Page<JobApplicationResponse> applications =
	            jobApplicationService.getApplications(pageable);
	
	    ApiResponse<Page<JobApplicationResponse>> response =
	            new ApiResponse<>(
	                    200,
	                    "Job applications retrieved successfully.",
	                    applications
	            );
	
	    return ResponseEntity.ok(response);
	}

}
