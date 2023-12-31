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

@CrossOrigin(origins = "https://ashy-field-0b069a510.3.azurestaticapps.net/", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CasesService casesService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterModel registerModel) {
        User existingUser = userService.findUserByEmail(registerModel.getEmail());

        if(null!=existingUser && null!=existingUser.getEmail()) {
            return ResponseEntity.status(204).body("User with email id exists. please login");
        }
        Date userDob = Date.valueOf(registerModel.getDob());
        System.out.println("Sql date: " + userDob.toString());
        existingUser = userService.checkDuplicateUser(registerModel.getFirstName(),
                registerModel.getLastName(), userDob);
        if (null!=existingUser) {
            return ResponseEntity.status(204).body("User id already exists. please login");
        }
        User registeredUser = userService.registerUser(registerModel);
        Random random = new Random();
        if(registeredUser != null) {
            CasesController.createCase(registerModel, random, registeredUser, casesService);
        }
        return ResponseEntity.ok("Success");
    }
}
