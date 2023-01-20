package com.jbv.lebenslauftool.repositories;

import com.jbv.lebenslauftool.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
