package com.jbv.lebenslauftool.webMvcTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.jbv.lebenslauftool.LebenslaufToolApplication;
import com.jbv.lebenslauftool.model.helper.LeaderShipBoardEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = LebenslaufToolApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(
        locations = "classpath:application-test.properties")
class ApplicantBattleControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testLeadershipBoard() throws Exception {
        String result = mvc.perform(get("/battles/leadershipBoard")
                                   .contentType(MediaType.APPLICATION_JSON))
                           .andExpect(status().isOk())
                           .andExpect(content()
                                   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                           .andReturn().getResponse().getContentAsString();

        CollectionType collectionType = objectMapper.getTypeFactory()
                                                    .constructCollectionType(List.class, LeaderShipBoardEntry.class);
        List<LeaderShipBoardEntry> leaderShipBoard = objectMapper.readValue(result, collectionType);

        LeaderShipBoardEntry entry = new LeaderShipBoardEntry(3L, "Micky", "Mouse", 0);
        Assertions.assertTrue(leaderShipBoard.contains(entry));
    }

    @Test
    void testAddBasicApplicant() throws Exception {
        String entity = objectMapper.writeValueAsString(List.of(2L, 4L));

        mvc.perform(post("/battles/")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(entity))
           .andExpect(status().isOk())
           .andExpect(jsonPath("name", is("Minnie")))
           .andExpect(jsonPath("id", is(4)))
           .andExpect(jsonPath("experiencePoints", is(1)));
    }
}
