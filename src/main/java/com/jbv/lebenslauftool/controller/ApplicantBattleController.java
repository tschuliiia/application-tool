package com.jbv.lebenslauftool.controller;

import com.jbv.lebenslauftool.errorhandling.BadRequestException;
import com.jbv.lebenslauftool.model.Winner;
import com.jbv.lebenslauftool.model.helper.LeaderShipBoardEntry;
import com.jbv.lebenslauftool.repositories.ApplicantRepository;
import com.jbv.lebenslauftool.services.BattleService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/battles")
public class ApplicantBattleController {

    @Resource(name = "battleService")
    private final BattleService battleService;

    private final ApplicantRepository applicantRepository;

    @PostMapping("/")
    Winner fightBattle(@RequestBody List<Long> ids) {
        if (ids.size() != 2) {
            throw new BadRequestException("Only 2 Applicant can battle against each other.");
        }
        if (Objects.equals(ids.get(0), ids.get(1))) {
            throw new BadRequestException("An Applicant can't battle against him/herself.");
        }

        return battleService.fightBattle(ids.get(0), ids.get(1));
    }

    @GetMapping("/leadershipBoard")
    List<LeaderShipBoardEntry> getLeadershipBoard() {
        return applicantRepository.getLeadershipBoard();
    }
}