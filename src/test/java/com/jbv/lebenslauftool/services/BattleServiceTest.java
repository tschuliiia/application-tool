package com.jbv.lebenslauftool.services;

import com.jbv.lebenslauftool.errorhandling.ApplicantNotFoundException;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import com.jbv.lebenslauftool.testutils.TestEntityCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class BattleServiceTest {

    @Mock
    ApplicantRepository applicantRepository;

    @InjectMocks
    BattleService battleService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        battleService = new BattleService(applicantRepository);
    }


    @Test
    public void testFightBattle(){
        Applicant applicant1 = TestEntityCreator.buildApplicantWithDetails();
        applicant1.setId(1L);
        Education education = TestEntityCreator.buildEducation();
        education.setId(1L);
        education.setApplicantId(2L);
        Applicant applicant2 = TestEntityCreator.buildApplicantWithDetails();
        applicant2.setId(2L);
        applicant2.setEducationList(List.of(education, education, education));
        when(applicantRepository.findById(eq(1L))).thenReturn(Optional.of(applicant1));
        when(applicantRepository.findById(eq(2L))).thenReturn(Optional.of(applicant2));

        var winner = new Winner(2L, applicant2.getFirstName(), 1);
        var result = battleService.fightBattle(1L, 2L);

        assertEquals(winner.getId(), result.getId());
        assertEquals(winner.getName(), result.getName());
        assertEquals(winner.getExperiencePoints(), result.getExperiencePoints());
    }

    @Test
    public void testFightBattleWithApplicantNotFound() {
        Exception exception = assertThrows(ApplicantNotFoundException.class, () -> {
            battleService.fightBattle(1L, 2L);
        });

        String expectedMessage = "Could not find applicant 1";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testCalculateSkillLevel(){
        Applicant applicant = TestEntityCreator.buildApplicantWithDetails();
        Applicant basicApplicant = TestEntityCreator.buildBasicApplicant();

        var skillLevel = battleService.calculateSkillLevel(applicant);
        var skillLevelBasicApplicant = battleService.calculateSkillLevel(basicApplicant);

        assertEquals(6, skillLevel);
        assertEquals(0, skillLevelBasicApplicant);
    }
}
