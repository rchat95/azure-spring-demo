package com.barclays.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import com.barclays.repository.CaseStatusRepository;
import com.barclays.repository.CasesRepository;

@Service
public class CasesServiceImpl implements CasesService{

    @Autowired
    private CasesRepository casesRepository;

    private CaseStatusRepository caseStatusRepository;

    @Override
    public Case findByCaseId(String caseId) {
        return casesRepository.findByCaseId(caseId);
    }

    @Override
    public Case createNewCase(Case caseDetail) {
        Case newCase = casesRepository.save(caseDetail);
        return newCase;
    }

    @Override
    public CaseStatus updateCaseStatus(CaseStatus caseStatusDetail) {
        return caseStatusRepository.save(caseStatusDetail);
    }

    @Override
    public List<CaseStatus> findByProviderId(String providerId){
        return caseStatusRepository.findByProviderId(providerId);
    }

    @Override
    public List<Case> findByClientId(String clientId){
        return casesRepository.findByClientId(clientId);
    }

    @Override
    public CaseStatus findStatusByCaseId(String case_id){
        return caseStatusRepository.findByCaseId(case_id);
    }


}
