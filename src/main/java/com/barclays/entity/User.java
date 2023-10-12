package com.barclays.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "le_registered_user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String email;
    private String phone;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address_1;
    private String address_2;
    private String postcode;
    private Date dob;
}
