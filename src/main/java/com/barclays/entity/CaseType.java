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
@Table(name = "le_casetype")
public class CaseType {
    @Id
    @Column(name = "case_type_id")
    private String caseTypeId;
    private String case_type;
    private int case_type_priority;
}
