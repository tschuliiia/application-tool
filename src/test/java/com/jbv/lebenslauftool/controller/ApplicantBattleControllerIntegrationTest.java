package com.jbv.lebenslauftool.controller;

import com.jbv.lebenslauftool.LebenslaufToolApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LebenslaufToolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ApplicantBattleControllerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testLeadershipBoard() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/battles/leadershipBoard"),
                HttpMethod.GET, entity, String.class);

        String expected = "\"id\":3,\"firstName\":\"Micky\",\"lastName\":\"Mouse\"";

        assertTrue(response.getBody().contains(expected));
    }

    @Test
    public void testAddBasicApplicant() {
        HttpEntity<List<Long>> entity = new HttpEntity<>(List.of(2L, 4L), headers);

        ResponseEntity<String> response = restTemplate.exchange(createTestURL("/battles/"), HttpMethod.POST, entity,
                String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(response.getBody(), "{\"id\":4,\"name\":\"Minnie\",\"experiencePoints\":1}");
    }

    private String createTestURL(String uri) {
        return "http://localhost:" + port + uri;
    }

}
