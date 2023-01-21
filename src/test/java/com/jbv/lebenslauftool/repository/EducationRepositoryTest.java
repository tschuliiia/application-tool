package com.jbv.lebenslauftool.repository;

import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.repositories.EducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;


    @Test
    public void whenFindByName_thenReturnEducation() {
        List<Education> result = educationRepository.findByApplicantId(1L);
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        List<Education> fromDb = educationRepository.findByApplicantId(10L);
        assertThat(fromDb).isEmpty();
    }
}
