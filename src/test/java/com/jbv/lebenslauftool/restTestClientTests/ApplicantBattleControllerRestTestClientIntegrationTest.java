package com.jbv.lebenslauftool.restTestClientTests;

import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.model.helper.LeaderShipBoardEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ApplicantBattleControllerRestTestClientIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void testLeadershipBoard() {
        restTestClient.get()
                      .uri("/battles/leadershipBoard")
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody(new ParameterizedTypeReference<List<LeaderShipBoardEntry>>() {
                      })
                      .value(result -> {
                          Assertions.assertNotNull(result);
                          assertThat(result.contains(new LeaderShipBoardEntry(3L, "Micky", "Mouse", 0)));
                      });
    }

    @Test
    void testFightBattle() {
        List<Long> entity = List.of(2L, 4L);

        restTestClient.post()
                      .uri("/battles/")
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(entity)
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody(Winner.class)
                      .isEqualTo(new Winner(4L, "Minnie", 1));
    }
}
