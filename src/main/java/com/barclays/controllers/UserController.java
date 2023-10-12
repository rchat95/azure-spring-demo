package com.barclays.controllers;

import com.barclays.dto.RegisterModel;
import com.barclays.dto.UserDto;
import com.barclays.entity.Case;
import com.barclays.entity.CaseStatus;
import com.barclays.entity.CaseType;
import com.barclays.entity.User;
import com.barclays.services.CasesService;
import com.barclays.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CasesService casesService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterModel registerModel, Model model) {
        User existingUser = userService.findUserByEmail(registerModel.getEmail());

        if(null!=existingUser && null!=existingUser.getEmail()) {
            return ResponseEntity.status(204).body("User with email id exists. please login");
        }
        User registeredUser = userService.registerUser(registerModel);
        Random random = new Random();
        if(registeredUser != null) {
            Case newCase = new Case();
            newCase.setCaseId(String.format("%04d", random.nextInt(1000)));
            newCase.setCasetype_id(registerModel.getCategory());
            newCase.setClient_id(registeredUser.getUserId());
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
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String userName, @RequestParam String password) {
        User loginUser = userService.loginUser(userName, password);
        if(null != loginUser) {
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
