package com.barclays.services;

import com.barclays.dto.RegisterModel;
import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import com.barclays.entity.CaseType;
import com.barclays.repository.CaseStatusRepository;
import com.barclays.repository.CaseTypeRepository;
import com.barclays.repository.CasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasesServiceImpl implements CasesService{

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private CaseStatusRepository caseStatusRepository;

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Override
    public Case findByCaseId(String caseId) {
        return casesRepository.findByCaseId(caseId);
    }

    @Override
    public Case createNewCase(Case caseDetail) {
        Case newCase = casesRepository.save(caseDetail);
        return newCase;
    }

    public CaseStatus updateCaseStatus(CaseStatus caseStatusDetail) {
        return caseStatusRepository.save(caseStatusDetail);
    }

    public CaseType getCaseTypeByCaseTypeId(String caseTypeId) {
        return caseTypeRepository.findByCaseTypeId(caseTypeId);
    }


}
