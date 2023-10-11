package com.barclays.services;

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

    public int registerUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAddress_1(userDto.getAddress_1());
        user.setAddress_2(userDto.getAddress_2());
        user.setPostcode(userDto.getPostcode());
        userRepository.save(user);
        return 1;
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

    private UserDto convertUserEntityToDto(User user) {
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
