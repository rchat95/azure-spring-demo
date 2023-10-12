package com.barclays.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaseDetailDto {
    private String caseId;
    private String casetype;
    private String client_id;
    private String anonymous_name;
    private String gp_name;
    private int priority;
    private String referral_personname;
    private String case_status;
    private Timestamp updated_date;
    private String serviceProvider_id;
    private String notes;
}
