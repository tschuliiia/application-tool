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
VALUES (1, 'Parkside GmbH', 'Software Quality Manager', '2012-08-01', '2016-06-01',
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

INSERT INTO certificate (applicant_id, name, description)
VALUES (1, 'ISTQB', 'Certified Tester - Foundation Level');
INSERT INTO certificate (applicant_id, name, description)
VALUES (1, 'SAP Hybris', 'Base Commerce Training');

INSERT INTO Applicant(first_name, last_name, email, birth_date, hobbies)
VALUES ('Maxi', 'Mustermann', 'max.mustermann@gmail.com', '2022-02-22',
        'Sports, Cinema');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (2, 'GERMAN');
INSERT INTO Applicant_languages(applicant_id, language)
VALUES (2, 'ENGLISH');