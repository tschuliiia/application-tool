package com.jbv.lebenslauftool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Winner {
    private Long id;

    private String name;

    private Integer experiencePoints;
}
