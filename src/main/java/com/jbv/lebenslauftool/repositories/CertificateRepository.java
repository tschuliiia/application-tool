package com.jbv.lebenslauftool.repositories;

import com.jbv.lebenslauftool.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByApplicantId(Long applicantId);

}
