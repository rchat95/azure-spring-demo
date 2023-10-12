package com.barclays.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.barclays.dto.RegisterModel;
import com.barclays.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barclays.dto.CaseDetailDto;
import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import com.barclays.entity.CaseType;
import com.barclays.services.CasesService;
import com.barclays.services.UserService;

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/cases")
public class CasesController {

    @Autowired
    private UserService userService;

    @Autowired
    private CasesService casesService;

    @GetMapping("/getDetailsByCaseId/{id}")
    public ResponseEntity<CaseDetailDto> showCasesByCaseId(@PathVariable String id) {
        Case target_case = casesService.findByCaseId(id);
        CaseStatus target_CaseStatus = casesService.findStatusByCaseId(id);
        CaseType target_casetype = casesService.getCaseTypeByCaseTypeId(target_case.getCasetype_id());
        CaseDetailDto target_casedetails= new CaseDetailDto(
            target_case.getCaseId(),
            target_casetype.getCase_type(),
            target_case.getClient_id(),
            target_case.getAnonymous_name(),
            target_case.getGp_name(),
            target_case.getPriority(),
            target_case.getReferral_personname(),
            target_CaseStatus.getCase_status(),
            target_CaseStatus.getUpdated_date(),
            target_CaseStatus.getServiceProvider_id(),
            target_CaseStatus.getNotes()
            );
        return ResponseEntity.ok(target_casedetails);
    }

    @GetMapping("/getCaseIdByProviderId/{id}")
    public ResponseEntity<List<String>> showCaseIdByProviderId(@PathVariable String id) {
        List<CaseStatus> target_cases = casesService.findByProviderId(id);
        List<String> CaseIdList = target_cases.stream()
                                        .map(CaseStatus::getCaseId)
                                        .collect(Collectors.toList());

        return ResponseEntity.ok(CaseIdList);
    }

    @GetMapping("/getCaseIdByClientId/{id}")
    public ResponseEntity<List<String>> showCaseIdByClientId(@PathVariable String id) {
        List<Case> target_cases = casesService.findByClientId(id);
        List<String> CaseIdList = target_cases.stream()
                                        .map(Case::getCaseId)
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(CaseIdList);
    }

    @PostMapping("/addCase")
    public ResponseEntity<String> createNewCase(@RequestBody RegisterModel registerModel) {
        Random random = new Random();
        User user = userService.findUserByEmail(registerModel.getEmail());
        createCase(registerModel, random, user, casesService);
        return ResponseEntity.ok("Success");
    }

    static void createCase(@RequestBody RegisterModel registerModel, Random random, User user, CasesService casesService) {
        Case newCase = new Case();
        newCase.setCaseId(String.format("%04d", random.nextInt(1000)));
        newCase.setCasetype_id(registerModel.getCategory());
        newCase.setClient_id(user.getUserId());
        newCase.setGp_name(registerModel.getGpName());
        CaseType caseType = casesService.getCaseTypeByCaseTypeId(registerModel.getCategory());
        newCase.setPriority(caseType.getCase_type_priority());
        Case savedCase = casesService.createNewCase(newCase);
        CaseStatus newCaseStatus = new CaseStatus();
        newCaseStatus.setCaseId(savedCase.getCaseId());
        newCaseStatus.setCase_status("Open");
        long milis = System.currentTimeMillis();
        Timestamp date = new Timestamp(milis);
        newCaseStatus.setUpdated_date(date);
        casesService.updateCaseStatus(newCaseStatus);
    }
}
