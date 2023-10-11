package com.barclays.repository;

import com.barclays.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasesRepository extends JpaRepository<Case, String> {
    Case findByCaseId(String caseId);
}
