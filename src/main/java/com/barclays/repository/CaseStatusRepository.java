package com.barclays.repository;

import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseStatusRepository extends JpaRepository<CaseStatus, String> {
    CaseStatus findByCaseId(String caseId);
}
