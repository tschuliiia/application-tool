INSERT INTO Applicant(first_name, last_name, email, birth_date, hobbies)
VALUES ('Julia', 'Viehberger', 'j.viehberger@gmail.com', '1990-08-20',
        'Ski touring, Skiing, Mountain biking, reading, creative stuff like drawing, sewing');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (1, 'GERMAN');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (1, 'ENGLISH');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (1, 'SPANISH');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (1, 'FH Joanneum', 'Soziale Arbeit', 'BACHELOR', '2009-09-01', '2012-07-01');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (1, 'FH Joanneum', 'Software Design', 'BACHELOR', '2012-09-01', '2015-07-01');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (1, 'TU Graz', 'Software Entwicklung Wirtschaft', 'MASTER', '2015-09-01', '2018-12-01');

INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'XiTrust GmbH', 'Senior Java Developer & Technical Team Lead', '2023-06-01', '2025-04-30',
        'Technical Leadership & technical PO of a renewal of a legacy system, Analyzing old system and defining architecture for new development(Tech stack: Java 17/21, Spring Boot 3, Ms SQL & H2, JUnit 4/5, Mockito, Keycloak), Keycloak Integration, Rollout of SonarQube for development department, Rollout of Conventional Commits & Unified Code Formatter for whole development department, Guidance in Scrum Processes');

INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'Parkside GmbH', 'Java Developer', '2019-03-17', '2023-05-30',
        'Developing Rest APIs; NoSQL databases(EspressoDB, Firestore) and relational databases such as PostgresSQL, MySQL, H2; Testing with TestNG, Mockito and PowerMock, JUnit; Micro Services with Apache Kafka; Firebase with Python Backend; Build Tools: most projects with Gradle; Continuous Integration & Delivery with Jenkins and Spinnaker; Basic knowledge of Docker & Kubernetes; Experience in agile Software Development with Scrum and SAFE');
INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'Samsung', 'Software Trainer for Kids', '2016-07-01', '2018-09-01',
        'Programming workshops for students in the age of 7 to 17 with the visual programming app PocketCode');
INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'TU Graz', 'Software Trainer & Instructor for the Remote Mentoring Project', '2018-10-01', '2018-11-01',
        'Coordination of Remote Mentoring Sessions in the classroom here high school students had to design and program their own games with PocketCode and advicing students in case they needed help');
INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'Bits for kids', 'Software Trainer for students', '2018-09-01', '2019-03-01',
        'Programming workshops for students in the age of 7 to 17 with Pocket Code, PocketPaint, Scratch, Microbits and Unity basics');
INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (1, 'Netconomy', 'Software Quality Manager', '2012-08-01', '2016-06-01',
'Quality and defect management, Risk analysis, Conducting trainings and workshops for customers - Test case generation & Automated GUI testing, Coordination of Testing Agents, CMS Editing');

INSERT INTO certificate (applicant_id, name, description)
VALUES (1, 'ISTQB', 'Certified Tester - Foundation Level');
INSERT INTO certificate (applicant_id, name, description)
VALUES (1, 'SAP Hybris', 'Base Commerce Training');

INSERT INTO Applicant(first_name, last_name, email, birth_date, hobbies)
VALUES ('Max', 'Mustermann', 'max.mustermann@gmail.com', '2022-02-22',
        'Sports, Cinema');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (2, 'GERMAN');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (2, 'ENGLISH');

INSERT INTO Applicant(first_name, last_name, email, birth_date, hobbies)
VALUES ('Micky', 'Mouse', 'micky.mouse@gmail.com', '1980-02-22',
        'Sports, reading');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (3, 'GERMAN');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (3, 'ENGLISH');

INSERT INTO certificate (applicant_id, name, description)
VALUES (3, 'Certificate 1', 'Certified Person');
INSERT INTO certificate (applicant_id, name, description)
VALUES (3, 'Certificate 2', 'Certified Person');

INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (3, 'Company', 'Employee', '2010-09-01', '2019-10-01',
        'Working for the Company');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (3, 'University', 'Master degree', 'MASTER', '2015-09-01', '2020-01-01');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (3, 'University', 'Bachelor degree', 'MASTER', '2012-09-01', '2012-09-01');

INSERT INTO Applicant(first_name, last_name, email, birth_date, hobbies)
VALUES ('Minnie', 'Mouse', 'minnie.mouse@gmail.com', '1982-02-22',
        'Sports, reading');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (4, 'GERMAN');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (4, 'ENGLISH');

INSERT INTO certificate (applicant_id, name, description)
VALUES (4, 'Certificate 1', 'Certified Person');

INSERT INTO job_experience (applicant_id, company, position, start_date, end_date, tasks)
VALUES (4, 'Company', 'Employee', '2008-09-01', '2010-10-01',
        'Working for the Company');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (4, 'University', 'Master degree', 'PHD', '2020-01-01', '2023-01-01');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (4, 'University', 'Master degree', 'MASTER', '2015-09-01', '2020-01-01');
INSERT INTO education (applicant_id, educational_institution, specification, education_level, start_date, end_date)
VALUES (4, 'University', 'Bachelor degree', 'MASTER', '2012-09-01', '2012-09-01');