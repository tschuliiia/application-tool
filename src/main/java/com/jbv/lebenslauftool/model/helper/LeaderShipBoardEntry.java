package com.jbv.lebenslauftool.model.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaderShipBoardEntry {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Integer experiencePoints;

}
