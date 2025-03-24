package com.jbv.lebenslauftool.webMvcTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jbv.lebenslauftool.LebenslaufToolApplication;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.testutils.TestEntityCreator;

import org.hamcrest.core.IsNull;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class ApplicantControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testRetrieveBasicApplicant() throws Exception {
        String expected =
                "{\"id\":2,\"firstName\":\"Maxi\",\"lastName\":\"Mustermann\",\"languages\":[\"GERMAN\"," +
                        "\"ENGLISH\"]," + "\"birthDate\":\"2022-02-22\",\"hobbies\":\"Sports, Cinema\"," +
                        "\"email\":\"max" + ".mustermann@gmail.com\",\"experiencePoints\":0,\"jobExperiences\":[],\"educationList\":[]," +
                        "\"certificateList\":[]}";

        mvc.perform(get("/applicants/2")
                   .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content()
                   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("firstName", is("Maxi")))
           .andExpect(content().json(expected));
    }

    @Test
    void testRetrieveCertificatesForApplicant() throws Exception {
        String expected = "[{\"name\":\"ISTQB\",\"description\":\"Certified Tester - Foundation Level\"}," + "{\"name"
                + "\":\"SAP Hybris\",\"description\":\"Base Commerce Training\"}]";

        mvc.perform(get("/applicants/1/certificates")
                   .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content()
                   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
           .andExpect(content().json(expected));
    }


    @Test
    void testAddBasicApplicant() throws Exception {
        Applicant applicant = TestEntityCreator.buildBasicApplicant();

        String entity = objectMapper.writeValueAsString(applicant);

        mvc.perform(post("/applicants/")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(entity))
           .andExpect(status().isCreated())
           .andExpect(content()
                   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("firstName", is("Maria")))
           .andExpect(jsonPath("lastName", is("Musterfrau")))
           .andExpect(jsonPath("educationList").value(IsNull.nullValue()))
           .andExpect(jsonPath("jobExperiences").value(IsNull.nullValue()));
    }

    @Test
    void testAddApplicantWithDetails() throws Exception {
        Applicant applicant = TestEntityCreator.buildApplicantWithDetails();

        objectMapper.registerModule(new JavaTimeModule());
        String entity = objectMapper.writeValueAsString(applicant);

        String response = mvc.perform(post("/applicants/")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content(entity))
                             .andExpect(status().isCreated())
                             .andExpect(content()
                                     .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                             .andExpect(jsonPath("firstName", is("Max")))
                             .andExpect(jsonPath("lastName", is("Mustermann")))
                             .andReturn().getResponse().getContentAsString();

        Applicant actualApplicant = objectMapper.readValue(response, Applicant.class);
        assertEquals(applicant.getJobExperiences(), actualApplicant.getJobExperiences());
        assertEquals(applicant.getEducationList(), actualApplicant.getEducationList());
        assertEquals(applicant.getCertificateList(), actualApplicant.getCertificateList());
    }
}
