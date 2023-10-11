package com.barclays.services;

import com.barclays.dto.CasesDto;
import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;

public interface CasesService {
    public Case findByCaseId(String case_id);

    public Case createNewCase(Case caseDetail);

    public CaseStatus updateCaseStatus(CaseStatus caseDetail);
}
