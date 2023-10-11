package com.barclays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CasesDto {
    private String caseId;
    private String casetype_id;
    private String client_id;
    private String anonymous_name;
    private String gp_name;
    private int priority;
    private String referral_personname;

}
