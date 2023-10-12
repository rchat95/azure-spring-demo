package com.barclays.repository;

import com.barclays.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(String userId);

    User findByFirstNameAndLastNameAndDob(String firstName, String lastName, Date dob);
//    User updateUserByEmail(String email);
}
