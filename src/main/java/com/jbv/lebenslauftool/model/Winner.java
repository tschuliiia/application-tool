package com.jbv.lebenslauftool.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Winner {
    private Long id;

    private String name;

    private Integer experiencePoints;
}
