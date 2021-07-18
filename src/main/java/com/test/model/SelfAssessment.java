package com.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.common.model.Entity;

import javax.persistence.Column;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "SelfAssessment")
@javax.persistence.SequenceGenerator(name = "default_gen", sequenceName = "selfassessment_id_seq", allocationSize = 1)
public class SelfAssessment extends Entity {
    @JsonProperty()
    @Column(name = "userId",nullable = false)
    public Long userId;

    @JsonProperty()
    @Column(name = "symptomsCount",nullable = false)
    public int symptomsCount;

    @JsonProperty()
    @Column(name = "travelHistory",nullable = false)
    public boolean travelHistory;

    @JsonProperty()
    @Column(name = "contactWithCovidPatient",nullable = false)
    public boolean contactWithCovidPatient;

    public Long getUserId() {
        return userId;
    }

    public int getSymptomsCount() {
        return symptomsCount;
    }

    public boolean isTravelHistory() {
        return travelHistory;
    }

    public boolean isContactWithCovidPatient() {
        return contactWithCovidPatient;
    }
}
