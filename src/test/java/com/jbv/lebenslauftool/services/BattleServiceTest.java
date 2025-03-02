package com.jbv.lebenslauftool.services;

import com.jbv.lebenslauftool.errorhandling.ApplicantNotFoundException;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import com.jbv.lebenslauftool.testutils.TestEntityCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

 class BattleServiceTest {

    @Mock
    ApplicantRepository applicantRepository;

    @InjectMocks
    BattleService battleService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup(){
        closeable = MockitoAnnotations.openMocks(this);
        battleService = new BattleService(applicantRepository);
    }

     @AfterEach
     public void afterEach() throws Exception {
         closeable.close();
     }

    @Test
    void testFightBattle(){
        Applicant applicant1 = TestEntityCreator.buildApplicantWithDetails();
        applicant1.setId(1L);
        Education education = TestEntityCreator.buildEducation();
        education.setId(1L);
        education.setApplicantId(2L);
        Applicant applicant2 = TestEntityCreator.buildApplicantWithDetails();
        applicant2.setId(2L);
        applicant2.setEducationList(List.of(education, education, education));
        when(applicantRepository.findById(1L)).thenReturn(Optional.of(applicant1));
        when(applicantRepository.findById(2L)).thenReturn(Optional.of(applicant2));

        var winner = new Winner(2L, applicant2.getFirstName(), 1);
        var result = battleService.fightBattle(1L, 2L);

        assertEquals(winner.getId(), result.getId());
        assertEquals(winner.getName(), result.getName());
        assertEquals(winner.getExperiencePoints(), result.getExperiencePoints());
    }

    @Test
    void testFightBattleWithApplicantNotFound() {
        Exception exception = assertThrows(ApplicantNotFoundException.class, () -> battleService.fightBattle(1L, 2L));

        String expectedMessage = "Could not find applicant 1";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCalculateSkillLevel(){
        Applicant applicant = TestEntityCreator.buildApplicantWithDetails();
        Applicant basicApplicant = TestEntityCreator.buildBasicApplicant();

        var skillLevel = battleService.calculateSkillLevel(applicant);
        var skillLevelBasicApplicant = battleService.calculateSkillLevel(basicApplicant);

        assertEquals(6, skillLevel);
        assertEquals(0, skillLevelBasicApplicant);
    }
}
