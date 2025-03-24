package com.jbv.lebenslauftool.model;

import com.jbv.lebenslauftool.model.helper.YearMonthConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.YearMonth;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "education")
public class Education implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    Long id;

    @Column(name = "educational_institution")
    @NotNull
    String educationalInstitution;

    String specification;

    @Column(name = "applicant_id")
    @Getter(AccessLevel.NONE)
    @NotNull
    Long applicantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_level")
    EducationLevel educationLevel;

    @Column(name = "start_date", columnDefinition = "date")
    @Convert(converter = YearMonthConverter.class)
    private YearMonth startDate;

    @Column(name = "end_date", columnDefinition = "date")
    @Convert(converter = YearMonthConverter.class)
    private YearMonth endDate;
}