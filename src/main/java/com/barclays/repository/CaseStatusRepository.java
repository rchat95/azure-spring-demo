package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barclays.entity.CaseStatus;

public interface CaseStatusRepository extends JpaRepository<CaseStatus, String> {
    CaseStatus findByCaseId(String caseId);

    @Query("SELECT cs FROM CaseStatus cs WHERE cs.serviceprovider_Id = :providerId")
    List<CaseStatus> findByProviderId(@Param("providerId") String providerId);
}
