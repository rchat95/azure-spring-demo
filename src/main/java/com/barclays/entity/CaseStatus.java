package com.barclays.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "le_case_status")
public class CaseStatus {
    @Id
    @Column(name = "case_id")
    private String caseId;
    private String case_status;
    private Timestamp updated_date;
    @Column(name = "serviceprovider_id")
    private String serviceProvider_id;
    private String notes;
}
