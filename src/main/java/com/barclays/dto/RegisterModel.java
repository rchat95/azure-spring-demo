package com.barclays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModel {
    private String isReferral;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String email;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String zipCode;
    private String category;
    private String gpName;
}
