package com.barclays.repository;

import com.barclays.entity.CaseStatus;
import com.barclays.entity.CaseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseTypeRepository extends JpaRepository<CaseType, String> {
    CaseType findByCaseTypeId(String caseTypeId);
}
