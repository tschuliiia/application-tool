package com.jbv.lebenslauftool.testutils;

import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Certificate;
import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.model.EducationLevel;
import com.jbv.lebenslauftool.model.JobExperience;
import com.jbv.lebenslauftool.model.Language;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

public class TestEntityCreator {

    public static Applicant buildApplicantWithDetails() {
        return Applicant.builder()
                        .firstName("Max")
                        .lastName("Mustermann")
                        .birthDate(LocalDate.of(2022, 1, 1))
                        .email("mustermann@test.com")
                        .hobbies("skiing")
                        .languages(Set.of(Language.ENGLISH))
                        .educationList(List.of(buildEducation()))
                        .jobExperiences(List.of(buildJobExperience()))
                        .certificateList(List.of(buildCertificate()))
                        .experiencePoints(0)
                        .build();
    }

    public static Applicant buildBasicApplicant() {
        return Applicant.builder()
                        .firstName("Maria")
                        .lastName("Musterfrau")
                        .languages(Set.of(Language.ENGLISH))
                        .experiencePoints(0)
                        .build();
    }

    public static Education buildEducation() {
        return Education.builder()
                        .educationLevel(EducationLevel.BACHELOR)
                        .educationalInstitution("FH Joanneum")
                        .specification("Software Design")
                        .startDate(YearMonth.of(2012, 9))
                        .endDate(YearMonth.of(2015, 6))
                        .build();
    }

    private static JobExperience buildJobExperience() {
        return JobExperience.builder()
                            .company("TestCompany")
                            .position("Developer")
                            .tasks("Develop Software")
                            .startDate(YearMonth.of(2012, 9))
                            .endDate(YearMonth.of(2015, 6))
                            .build();
    }

    private static Certificate buildCertificate() {
        return Certificate.builder()
                          .name("TestCertificate")
                          .description("for Programming")
                          .build();
    }
}
