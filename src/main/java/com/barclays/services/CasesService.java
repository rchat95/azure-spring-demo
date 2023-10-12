package com.barclays.services;


import java.util.List;

import com.barclays.dto.CasesDto;
import com.barclays.dto.RegisterModel;

import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import com.barclays.entity.CaseType;

public interface CasesService {
    public Case findByCaseId(String case_id);

    public CaseStatus findStatusByCaseId(String case_id);

    public Case createNewCase(Case caseDetail);

    public CaseStatus updateCaseStatus(CaseStatus caseDetail);

    public List<CaseStatus> findByProviderId(String providerId);

    public List<Case> findByClientId(String clientId);

    public CaseType getCaseTypeByCaseTypeId(String caseTypeId);

}
