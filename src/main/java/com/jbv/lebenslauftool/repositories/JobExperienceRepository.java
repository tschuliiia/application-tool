package com.jbv.lebenslauftool.repositories;

import com.jbv.lebenslauftool.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobExperienceRepository extends JpaRepository<JobExperience, Long> {
    List<JobExperience> findByApplicantId(Long applicantId);

}
