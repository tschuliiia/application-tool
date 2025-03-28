package com.jbv.lebenslauftool.repository;


import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class ApplicantRepositoryIntegrationTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    void testUpdateExperiencePoints() {
        applicantRepository.updateExperiencePoints(1L, 5);

        var result = applicantRepository.findById(1L);
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(5, result.get()
                              .getExperiencePoints());
    }

    @Test
    void testGetLeadershipBoard() {
        applicantRepository.updateExperiencePoints(1L, 5);

        var result = applicantRepository.getLeadershipBoard();
        assertEquals(1L, result.getFirst()
                               .id());
        assertEquals(5, result.getFirst()
                              .experiencePoints());
    }
}
