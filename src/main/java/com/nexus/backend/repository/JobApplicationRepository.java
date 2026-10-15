
package com.nexus.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.backend.entity.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findAllByUserId(Long userId);

    Optional<JobApplication> findByIdAndUserId(
            Long id, Long userId);

    Page<JobApplication> findAllByUserId(
            Long userId, Pageable pageable);
}
