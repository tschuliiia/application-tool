package com.jbv.lebenslauftool.controller;

import com.jbv.lebenslauftool.errorhandling.ApplicantNotFoundException;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Certificate;
import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.model.JobExperience;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import com.jbv.lebenslauftool.repositories.CertificateRepository;
import com.jbv.lebenslauftool.repositories.EducationRepository;
import com.jbv.lebenslauftool.repositories.JobExperienceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantRepository applicantRepository;

    private final EducationRepository educationRepository;

    private final JobExperienceRepository jobExperienceRepository;

    private final CertificateRepository certificateRepository;

    ApplicantController(ApplicantRepository applicantRepository, EducationRepository educationRepository,
                        JobExperienceRepository jobExperienceRepository, CertificateRepository certificateRepository) {
        this.applicantRepository = applicantRepository;
        this.educationRepository = educationRepository;
        this.jobExperienceRepository = jobExperienceRepository;
        this.certificateRepository = certificateRepository;
    }


    @GetMapping("/")
    List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    Applicant createApplicant(@RequestBody Applicant newApplicant) {
        return applicantRepository.save(newApplicant);
    }


    @GetMapping("/{id}")
    Applicant getApplicant(@PathVariable Long id) {

        return applicantRepository.findById(id).orElseThrow(() -> new ApplicantNotFoundException(id));
    }

    @GetMapping("/{id}/education")
    List<Education> getEducationForApplicant(@PathVariable Long id) {

        return educationRepository.findByApplicantId(id);
    }

    @GetMapping("/{id}/jobExperience")
    List<JobExperience> getJobExperiencesForApplicant(@PathVariable Long id) {
        return jobExperienceRepository.findByApplicantId(id);
    }

    @GetMapping("/{id}/certificates")
    List<Certificate> getCertificatesForApplicant(@PathVariable Long id) {
        return certificateRepository.findByApplicantId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Applicant replaceApplicant(@RequestBody Applicant applicant, @PathVariable Long id) {
        return applicantRepository.findById(id).map(updatedApplicant -> {
            updatedApplicant.setFirstName(applicant.getFirstName());
            updatedApplicant.setLastName(applicant.getLastName());
            updatedApplicant.setBirthDate(applicant.getBirthDate());
            updatedApplicant.setEmail(applicant.getEmail());
            updatedApplicant.setHobbies(applicant.getHobbies());
            return applicantRepository.save(updatedApplicant);
        }).orElseThrow(() -> new ApplicantNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteApplicant(@PathVariable Long id) {
        applicantRepository.deleteById(id);
    }
}
