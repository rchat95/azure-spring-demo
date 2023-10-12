package com.barclays.services;

import java.util.List;

import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;

public interface CasesService {
    public Case findByCaseId(String case_id);

    public CaseStatus findStatusByCaseId(String case_id);

    public Case createNewCase(Case caseDetail);

    public CaseStatus updateCaseStatus(CaseStatus caseDetail);

    public List<CaseStatus> findByProviderId(String providerId);

    public List<Case> findByClientId(String clientId);

}
