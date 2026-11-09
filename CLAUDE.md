# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

A Spring Boot REST API showcase about job applications (CV data). You can create, read, update and delete applicants. Two applicants can also "battle": the winner is chosen from their skills plus a random value, and there is a leaderboard. Applicant id 1 is the author's real CV.

Stack: Spring Boot 4.1, Java 21, Gradle, Spring Data JPA, H2 in-memory, Flyway, Lombok, Spring Security (HTTP Basic). The README's "Spring 3.0 / Java 17" setup notes are outdated.

## Commands

```bash
./gradlew bootRun                     # run the app (H2 console at /h2-console, user/test)
./gradlew build                       # compile + test
./gradlew test                        # all tests
./gradlew test --tests 'com.jbv.lebenslauftool.services.BattleServiceTest'              # one class
./gradlew test --tests 'com.jbv.lebenslauftool.services.BattleServiceTest.someMethod'   # one method
```

There is no linter configured. A Postman collection for every endpoint is in `postmanTests/`.

## Architecture

Package root: `com.jbv.lebenslauftool`. It follows the usual controller → service/repository layering. The parts that take reading several files to understand:

- **Database schema comes only from Flyway** (`src/main/resources/db/migration/V*__*.sql`). `ddl-auto=none`, so entity changes need a new `V{n}__*.sql` migration. Never edit existing migrations, because their checksums are stored. Seed data loads after Flyway from `data.sql` (app) and `src/test/resources/test-data.sql` (tests). Keep both in sync when you change the schema; the tests assert on specific seed rows (for example applicant 2 = "Maxi Mustermann").
- **Applicant aggregate**: `Applicant` owns `JobExperience`, `Education` and `Certificate` through `@OneToMany(mappedBy = "applicantId")`. The child entities hold a plain `Long applicantId` column with its getter suppressed, not a back-reference to `Applicant`. Enum collections (`languages`, `drivingLicenses`) are `@ElementCollection` tables named `applicant_<x>`.
- **Updates in `ApplicantController.replaceApplicant` copy fields one by one.** A new `Applicant` field is not updated by PUT until you add it there.
- **JSON field order matters**: `spring.jackson.mapper.sort-properties-alphabetically=false` keeps entity declaration order, and some tests compare JSON strings. Where you put a new field in the entity affects the API output.
- `YearMonth` dates on child entities are persisted through `model/helper/YearMonthConverter`.
- **Battle**: `BattleService.calculateSkillLevel` adds up education-level points, the number of certificates, years of job experience and `experiencePoints`, plus a random 0–5. The winner gets +1 `experiencePoints` via the custom `@Modifying` query in `ApplicantRepository`. The leaderboard is a JPQL constructor projection into the `LeaderShipBoardEntry` record.
- **Security**: `SecurityConfig` requires HTTP Basic with the in-memory user `user`/`1234` for `/applicants/**` and `/battles/**`. Under the `test` profile, `ApplicationNoSecurity` turns security off completely.

## Tests

- Integration tests use `@ActiveProfiles("test")` (`application-test.properties` → separate H2 DB plus `test-data.sql`, security disabled). There are two styles: `controller/*IntegrationTest` uses a real server with `TestRestTemplate`, and `webMvcTests/*` uses `MockMvc`. Both run against the real seeded DB, not mocks.
- `repository/*` are `@DataJpaTest`. `services/BattleServiceTest` and `controller/ApplicantBattleControllerTest` are Mockito unit tests.
- `testutils/TestEntityCreator` builds the test fixtures (`buildApplicantWithDetails`, `buildBasicApplicant`).