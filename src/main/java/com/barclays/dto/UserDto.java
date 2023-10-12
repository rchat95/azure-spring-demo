package com.barclays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String address_1;
    private String address_2;
    private String postcode;
    private Date dob;
}
