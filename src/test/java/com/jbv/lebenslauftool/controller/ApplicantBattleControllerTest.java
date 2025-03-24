package com.jbv.lebenslauftool.controller;

import com.jbv.lebenslauftool.errorhandling.BadRequestException;
import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import com.jbv.lebenslauftool.services.BattleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ApplicantBattleControllerTest {
    @Mock
    BattleService battleService;

    @Mock
    ApplicantRepository applicantRepository;

    @InjectMocks
    ApplicantBattleController applicantBattleController;

    private AutoCloseable closeable;


    @BeforeEach
    void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
        applicantBattleController = new ApplicantBattleController(battleService, applicantRepository);
    }

    @AfterEach
    void afterEach() throws Exception {
        closeable.close();
    }

    @Test
    void testFightBattleWith3Ids() {
        Exception exception = assertThrows(BadRequestException.class, () -> applicantBattleController.fightBattle(List.of(1L, 2L, 3L)));

        String expectedMessage = "Only 2 Applicant can battle against each other.";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testFightBattleWith2equalIds() {
        Exception exception = assertThrows(BadRequestException.class, () -> applicantBattleController.fightBattle(List.of(1L, 1L)));

        String expectedMessage = "An Applicant can't battle against him/herself.";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testFightBattle() {
        when(battleService.fightBattle(anyLong(), anyLong())).thenReturn(new Winner(1L, "Lena", 7));

        var result = applicantBattleController.fightBattle(List.of(1L, 2L));

        assertEquals("Lena", result.getName());
    }
}
