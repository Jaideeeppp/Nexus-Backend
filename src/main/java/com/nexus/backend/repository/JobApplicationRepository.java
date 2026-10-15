
package com.nexus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.backend.entity.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}
