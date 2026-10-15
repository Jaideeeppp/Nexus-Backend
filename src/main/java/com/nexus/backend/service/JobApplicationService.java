
package com.nexus.backend.service;

import java.util.List;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.response.JobApplicationResponse;
import com.nexus.backend.dto.request.UpdateJobApplicationRequest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface JobApplicationService {

    JobApplicationResponse createApplication(
            CreateJobApplicationRequest request);

    List<JobApplicationResponse> getAllApplications();
    
    Page<JobApplicationResponse> getApplications(Pageable pageable);
    

    JobApplicationResponse getApplicationById(Long id);

    void deleteApplication(Long id);
    
    JobApplicationResponse updateApplication(
    	    Long id,
    	    UpdateJobApplicationRequest request
    	);

}
