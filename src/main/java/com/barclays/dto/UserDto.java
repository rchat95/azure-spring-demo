package com.barclays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String surname;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
