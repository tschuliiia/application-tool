package com.jbv.lebenslauftool.services;

import com.jbv.lebenslauftool.errorhandling.ApplicantNotFoundException;
import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.Education;
import com.jbv.lebenslauftool.model.EducationLevel;
import com.jbv.lebenslauftool.model.JobExperience;
import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

@AllArgsConstructor
@Component("battleService")
public class BattleService {

    private final ApplicantRepository applicantRepository;
    private final Random rand = new Random();


    public Winner fightBattle(Long applicantId1, Long applicantId2) {

        var applicant1 = applicantRepository.findById(applicantId1)
                                            .orElseThrow(() -> new ApplicantNotFoundException(applicantId1));

        var applicant2 = applicantRepository.findById(applicantId2)
                                            .orElseThrow(() -> new ApplicantNotFoundException(applicantId2));

        var skillApplicant1 = calculateSkillLevel(applicant1);
        var skillApplicant2 = calculateSkillLevel(applicant2);

        int upperbound = 6;
        int intRandom1 = this.rand.nextInt(upperbound);
        int intRandom2 = this.rand.nextInt(upperbound);

        Applicant winner;
        if (skillApplicant1 + intRandom1 > skillApplicant2 + intRandom2) {
            winner = applicant1;
        } else {
            winner = applicant2;
        }
        winner.setExperiencePoints(winner.getExperiencePoints() + 1);
        applicantRepository.updateExperiencePoints(winner.getId(), winner.getExperiencePoints());

        return new Winner(winner.getId(), winner.getFirstName(), winner.getExperiencePoints());
    }


    protected int calculateSkillLevel(Applicant applicant) {
        int skillevel = 0;
        Map<EducationLevel, Integer> map = new EnumMap<>(EducationLevel.class);
        map.put(EducationLevel.HIGH_SCHOOL, 2);
        map.put(EducationLevel.BACHELOR, 3);
        map.put(EducationLevel.MASTER, 3);
        map.put(EducationLevel.PHD, 3);
        if (applicant.getEducationList() != null) {
            for (Education education : applicant.getEducationList()) {
                skillevel += map.get(education.getEducationLevel());
            }
        }

        if (applicant.getCertificateList() != null) {
            skillevel += applicant.getCertificateList()
                                  .size();
        }

        if (applicant.getJobExperiences() != null) {
            for (JobExperience jobExperience : applicant.getJobExperiences()) {
                skillevel += (int) ChronoUnit.YEARS.between(jobExperience.getStartDate(), jobExperience.getEndDate());
            }
        }

        skillevel += applicant.getExperiencePoints();
        return skillevel;
    }
}
