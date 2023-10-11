package com.barclays.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String first_name;
    private String last_name;
    private String address_1;
    private String address_2;
    private String postcode;
}
