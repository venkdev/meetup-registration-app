package com.k15t.pat.service;

import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;

import java.util.List;

public interface RegistrationService {

    UserDto registerUser(UserDto userDto) throws RegistrationException;

    List<UserDto> getUsers();
}
