package com.barclays.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "le_cases")
public class Case {
    @Id
    @Column(name = "case_id")
    private String caseId;
    private String casetype_id;
    private String client_id;
    private String anonymous_name;
    private String gp_name;
    private int priority;
    private String referral_personname;
}
