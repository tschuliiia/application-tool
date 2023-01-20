package com.jbv.lebenslauftool.repositories;

import com.jbv.lebenslauftool.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByApplicantId(Long applicantId);

}
