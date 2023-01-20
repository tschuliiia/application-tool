package com.jbv.lebenslauftool.model;

import com.jbv.lebenslauftool.model.helper.YearMonthConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.YearMonth;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobExperience")
public class JobExperience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    Long id;

    @NotNull
    String company;

    @NotNull
    String position;

    @Column(name = "start_date", columnDefinition = "date")
    @Convert(converter = YearMonthConverter.class)
    private YearMonth startDate;

    @Column(name = "end_date", columnDefinition = "date")
    @Convert(converter = YearMonthConverter.class)
    private YearMonth endDate;

    String tasks;

    @Getter(AccessLevel.NONE)
    @Column(name = "applicant_id")
    @NotNull
    Long applicantId;
}