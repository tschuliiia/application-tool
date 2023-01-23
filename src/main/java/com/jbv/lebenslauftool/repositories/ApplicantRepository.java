package com.jbv.lebenslauftool.repositories;

import com.jbv.lebenslauftool.model.Applicant;
import com.jbv.lebenslauftool.model.helper.LeaderShipBoardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    @Modifying
    @Transactional
    @Query("update Applicant a set a.experiencePoints = :experiencePoints where a.id = :id")
    void updateExperiencePoints(@Param(value = "id") long id, @Param(value = "experiencePoints") Integer experiencePoints);

    @Query("select new com.jbv.lebenslauftool.model.helper.LeaderShipBoardEntry(a.id, a.firstName, a.lastName, a.experiencePoints) " +
            "from Applicant a ORDER BY a.experiencePoints DESC")
    List<LeaderShipBoardEntry> getLeadershipBoard();
}
