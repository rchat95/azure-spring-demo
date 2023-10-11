package com.barclays.services;

import com.barclays.entity.Case;
import com.barclays.repository.CasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasesServiceImpl implements CasesService{

    @Autowired
    private CasesRepository casesRepository;

    @Override
    public Case findByCaseId(String caseId) {
        return casesRepository.findByCaseId(caseId);
    }

    @Override
    public Case createNewCase(Case caseDetail) {
        Case newCase = casesRepository.save(caseDetail);
        return newCase;
    }


}
