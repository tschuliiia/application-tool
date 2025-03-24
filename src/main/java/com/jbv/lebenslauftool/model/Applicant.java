package com.jbv.lebenslauftool.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "applicant")
public class Applicant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @ElementCollection(targetClass = Language.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "applicant_languages")
    @Column(name = "language")
    private Collection<Language> languages;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String hobbies;

    @Email(message = "The email address must be valid")
    private String email;

    @Column(name = "experience_points")
    private Integer experiencePoints;

    @OneToMany(mappedBy = "applicantId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "applicantId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Education> educationList;

    @OneToMany(mappedBy = "applicantId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Certificate> certificateList;

    public Applicant(String firstName, String lastName, Collection<Language> languages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.languages = languages;
    }
}