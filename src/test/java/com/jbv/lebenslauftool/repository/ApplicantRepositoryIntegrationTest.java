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
public class ApplicantRepositoryIntegrationTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void testUpdateExperiencePoints() {
        applicantRepository.updateExperiencePoints(1L, 5);

        var result = applicantRepository.findById(1L);
        assertEquals(5, result.get().getExperiencePoints());
    }

    @Test
    public void testGetLeadershipBoard() {
        applicantRepository.updateExperiencePoints(1L, 5);

        var result = applicantRepository.getLeadershipBoard();
        assertEquals(1L, result.get(0).getId());
        assertEquals(5, result.get(0).getExperiencePoints());
    }
}
