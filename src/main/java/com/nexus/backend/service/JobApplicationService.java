
package com.nexus.backend.service;

import java.util.List;

import com.nexus.backend.dto.request.CreateJobApplicationRequest;
import com.nexus.backend.dto.response.JobApplicationResponse;

public interface JobApplicationService {

    JobApplicationResponse createApplication(
            CreateJobApplicationRequest request);

    List<JobApplicationResponse> getAllApplications();
}
