package com.barclays.services;

import com.barclays.dto.UserDto;
import com.barclays.entity.User;

public interface UserService {
    public User registerUser(UserDto userDto);

    public User findUserByEmail(String email);

    public User loginUser(String email, String password);
}
