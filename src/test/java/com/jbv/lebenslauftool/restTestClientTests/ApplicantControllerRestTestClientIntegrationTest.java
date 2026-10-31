package com.jbv.lebenslauftool.restTestClientTests;

import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Certificate;
import com.jbv.lebenslauftool.testutils.TestEntityCreator;
import org.assertj.core.api.InstanceOfAssertFactories;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ApplicantControllerRestTestClientIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;


    @Test
    void testRetrieveBasicApplicant_withJSONPathCheck() {
        restTestClient.get()
                      .uri("/applicants/{id}", 2L)
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody()
                      .jsonPath("$.firstName")
                      .isEqualTo("Maxi")
                      .jsonPath("$.lastName")
                      .isEqualTo("Mustermann")
                      .jsonPath("$.birthDate")
                      .isEqualTo("2022-02-22")
                      .jsonPath("$.hobbies")
                      .isEqualTo("Sports, Cinema")
                      .jsonPath("$.email")
                      .isEqualTo("max.mustermann@gmail.com")
                      .jsonPath("$.languages")
                      .value(result -> {
                          assertThat(result)
                                  .asInstanceOf(InstanceOfAssertFactories.LIST)
                                  .containsExactlyInAnyOrder("ENGLISH", "GERMAN");
                      })
                      .jsonPath("$.jobExperiences")
                      .isEqualTo(List.of())
                      .jsonPath("$.educationList")
                      .isEqualTo(List.of())
                      .jsonPath("$.certificateList")
                      .isEqualTo(List.of());
    }

    @Test
    void testRetrieveBasicApplicant_checkApplicantObject() {
        restTestClient.get()
                      .uri("/applicants/{id}", 2L)
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody(Applicant.class)
                      .isEqualTo(TestEntityCreator.buildApplicantMaxMustermann());
    }

    @Test
    void testRetrieveCertificatesForApplicant() {
        var certIstqb = Certificate.builder()
                                   .name("ISTQB")
                                   .description("Certified Tester - Foundation Level")
                                   .build();
        var certHybris = Certificate.builder()
                                    .name("SAP Hybris")
                                    .description("Base Commerce Training")
                                    .build();

        restTestClient.get()
                      .uri("/applicants/{id}/certificates", 1L)
                      .exchange()
                      .expectStatus()
                      .isOk()
                      .expectBody(new ParameterizedTypeReference<List<Certificate>>() {})
                      .isEqualTo(List.of(certIstqb, certHybris));
    }

    @Test
    void testAddBasicApplicant() {
        Applicant applicant = TestEntityCreator.buildBasicApplicant();

        restTestClient.post()
                      .uri("/applicants/")
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(applicant)
                      .exchange()
                      .expectStatus()
                      .isCreated()
                      .expectBody()
                      .jsonPath("$.firstName")
                      .isEqualTo("Maria")
                      .jsonPath("$.lastName")
                      .isEqualTo("Musterfrau");
    }


    @Test
    void testAddApplicantWithDetails() {
        Applicant applicant = TestEntityCreator.buildApplicantWithDetails();

        restTestClient.post()
                      .uri("/applicants/")
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(applicant)
                      .exchange()
                      .expectStatus()
                      .isCreated()
                      .expectBody(Applicant.class)
                      .value(result -> {
                          Assertions.assertNotNull(result);
                          assertThat(result.getFirstName()).isEqualTo(applicant.getFirstName());
                          assertThat(result.getLastName()).isEqualTo(applicant.getLastName());
                          assertThat(result.getLastName()).isEqualTo(applicant.getLastName());
                          assertThat(result.getJobExperiences()).isEqualTo(applicant.getJobExperiences());
                          assertThat(result.getCertificateList()).isEqualTo(applicant.getCertificateList());
                          assertThat(result.getEducationList()).isEqualTo(applicant.getEducationList());
                      });
    }
}
