package com.barclays.controllers;

import com.barclays.dto.UserDto;
import com.barclays.entity.User;
import com.barclays.services.CasesService;
import com.barclays.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(null!=existingUser && null!=existingUser.getEmail()) {
            return ResponseEntity.badRequest().build();
        }
        int registeredUser = userService.registerUser(userDto);
        if(registeredUser==1) {
//            casesService.createNewCase()
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
