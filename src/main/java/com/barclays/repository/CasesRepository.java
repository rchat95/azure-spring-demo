package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barclays.entity.Case;

public interface CasesRepository extends JpaRepository<Case, String> {
    Case findByCaseId(String caseId);

    @Query("SELECT c FROM Case c WHERE c.client_Id = :clientId")
    List<Case> findByClientId(@Param("clientId") String clientId);
}
