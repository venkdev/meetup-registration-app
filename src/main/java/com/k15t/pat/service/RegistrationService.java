package com.k15t.pat.service;

import com.k15t.pat.dto.UserDto;

import java.util.List;

public interface RegistrationService {
    UserDto registerUser(UserDto userDto);

    List<UserDto> getUsers();
}
