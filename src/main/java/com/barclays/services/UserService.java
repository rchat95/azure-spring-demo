package com.barclays.services;

import com.barclays.dto.RegisterModel;
import com.barclays.dto.UserDto;
import com.barclays.entity.User;

public interface UserService {
    public User registerUser(RegisterModel registerModel);

    public User findUserByEmail(String email);

    public User loginUser(String email, String password);

    public UserDto convertUserEntityToDto(User user);
}
