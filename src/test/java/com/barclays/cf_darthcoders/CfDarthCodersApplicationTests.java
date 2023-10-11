package com.barclays.cf_darthcoders;

import com.barclays.CfDarthCodersApplication;
import com.barclays.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CfDarthCodersApplication.class)
public class CfDarthCodersApplicationTests {

    @Autowired
    public UserRepository userRepository;

    @Test
    void contextLoads() {
    }

}
