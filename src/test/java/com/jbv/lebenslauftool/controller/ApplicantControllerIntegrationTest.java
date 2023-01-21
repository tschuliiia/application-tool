package com.jbv.lebenslauftool.controller;

import com.jbv.lebenslauftool.LebenslaufToolApplication;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.testutils.TestEntityCreator;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LebenslaufToolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ApplicantControllerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveCertificatesForApplicant() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/applicants/1/certificates"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"name\":\"ISTQB\",\"description\":\"Certified Tester - Foundation Level\"}," + "{\"name"
                + "\":\"SAP Hybris\",\"description\":\"Base Commerce Training\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testRetrieveBasicApplicant() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/applicants/2"), HttpMethod.GET, entity,
                String.class);

        String expected =
                "{\"id\":2,\"firstName\":\"Maxi\",\"lastName\":\"Mustermann\",\"languages\":[\"ENGLISH\", " +
                        "\"GERMAN\"]," + "\"birthDate\":\"2022-02-22\",\"hobbies\":\"Sports, Cinema\"," +
                        "\"email\":\"max" + ".mustermann@gmail.com\"," + "\"jobExperiences\":[],\"educationList\":[]," +
                        "\"certificateList\":[]}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testAddApplicantWithDetails() {
        Applicant applicant = TestEntityCreator.buildApplicantWithDetails();
        HttpEntity<Applicant> entity = new HttpEntity<>(applicant, headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/applicants/"), HttpMethod.POST, entity,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody()
                           .contains("\"firstName\":\"Max\",\"lastName\":\"Mustermann\""));
        assertTrue(response.getBody()
                           .contains(
                                   "\"jobExperiences\":[{\"company\":\"TestCompany\"," + "\"position\":\"Developer\"," +
                                           "\"startDate\":\"2012-09\",\"endDate\":\"2015-06\",\"tasks\":\"Develop " + "Software\"}]"));
        assertTrue(response.getBody()
                           .contains(
                                   "\"educationList\":[{\"educationalInstitution\":\"FH Joanneum\"," +
                                           "\"specification\":\"Software Design\",\"startDate\":\"2012-09\"," +
                                           "\"endDate\":\"2015-06\"," + "\"educationLevel\":\"BACHELOR\"}]"));
        assertTrue(response.getBody()
                           .contains(
                                   "\"certificateList\":[{\"name\":\"TestCertificate\"," + "\"description\":\"for " +
                                           "Programming\"}]"));
    }

    @Test
    public void testAddBasicApplicant() {
        Applicant applicant = TestEntityCreator.buildBasicApplicant();
        HttpEntity<Applicant> entity = new HttpEntity<>(applicant, headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/applicants/"), HttpMethod.POST, entity,
                String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody()
                           .contains("\"firstName\":\"Maria\",\"lastName\":\"Musterfrau\""));
        assertTrue(response.getBody()
                           .contains("\"educationList\":null"));
        assertTrue(response.getBody()
                           .contains("\"jobExperiences\":null"));
    }


    private String createTestURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
