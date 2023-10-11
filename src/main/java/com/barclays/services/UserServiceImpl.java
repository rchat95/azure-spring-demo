package com.barclays.services;

import com.barclays.dto.RegisterModel;
import com.barclays.dto.UserDto;
import com.barclays.entity.User;
import com.barclays.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterModel userDto) {
        User user = new User();
        user.setUserId(userDto.getLastName()+"_123");
        user.setFirst_name(userDto.getFirstName());
        user.setLast_name(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword("password123");
        user.setPhone(userDto.getPhoneNumber());
        user.setAddress_1(userDto.getAddress1());
        user.setAddress_2(userDto.getAddress2());
        user.setPostcode(userDto.getZipCode());
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
    @Override
    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email);
    }

    public UserDto convertUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPassword(user.getPassword());
        userDto.setAddress_1(user.getAddress_1());
        userDto.setAddress_2(user.getAddress_2());
        userDto.setPostcode(user.getPostcode());
        return userDto;
    }
}
