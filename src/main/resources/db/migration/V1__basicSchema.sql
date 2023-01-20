CREATE TABLE applicant
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(32) NOT NULL,
    last_name  VARCHAR(32) NOT NULL,
    birth_date DATE,
    hobbies    VARCHAR(256),
    email      VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE applicant_languages
(
    applicant_id bigint NOT NULL,
    language     VARCHAR(64)
);

CREATE TABLE certificate
(
    id           bigint      NOT NULL AUTO_INCREMENT,
    applicant_id bigint      NOT NULL,
    name         VARCHAR(64) NOT NULL,
    description  VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE education
(
    id                      bigint      NOT NULL AUTO_INCREMENT,
    applicant_id            bigint      NOT NULL,
    educational_institution VARCHAR(64) NOT NULL,
    specification           VARCHAR(128),
    education_level         VARCHAR(32),
    start_date              DATE,
    end_date                DATE,
    PRIMARY KEY (id)
);

CREATE TABLE job_experience
(
    id           bigint NOT NULL AUTO_INCREMENT,
    applicant_id bigint NOT NULL,
    company      VARCHAR(128) NOT NULL,
    position     VARCHAR(64) NOT NULL,
    tasks        VARCHAR(512),
    start_date   DATE,
    end_date     DATE,
    PRIMARY KEY (id)
);

ALTER TABLE Applicant_Languages
    ADD FOREIGN KEY (applicant_id) REFERENCES Applicant (id);
ALTER TABLE education
    ADD FOREIGN KEY (applicant_id) REFERENCES Applicant (id);
ALTER TABLE job_experience
    ADD FOREIGN KEY (applicant_id) REFERENCES Applicant (id);
ALTER TABLE certificate
    ADD FOREIGN KEY (applicant_id) REFERENCES Applicant (id);