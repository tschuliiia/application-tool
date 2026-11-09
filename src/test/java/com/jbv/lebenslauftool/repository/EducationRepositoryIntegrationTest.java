package com.jbv.lebenslauftool.repository;

import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.repositories.EducationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class EducationRepositoryIntegrationTest {

    @Autowired
    private EducationRepository educationRepository;


    @Test
    void whenFindByApplicantId_thenReturnEducation() {
        List<Education> result = educationRepository.findByApplicantId(1L);
        assertThat(result).hasSize(3);
    }

    @Test
    void whenInvalidApplicantId_thenReturnNull() {
        List<Education> fromDb = educationRepository.findByApplicantId(10L);
        assertThat(fromDb).isEmpty();
    }
}
