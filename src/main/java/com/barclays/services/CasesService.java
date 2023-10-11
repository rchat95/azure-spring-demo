package com.barclays.services;

import com.barclays.dto.CasesDto;
import com.barclays.entity.Case;

public interface CasesService {
    public Case findByCaseId(String case_id);

    public Case createNewCase(Case caseDetail);
}
