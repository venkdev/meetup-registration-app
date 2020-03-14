package com.k15t.pat.service.impl;

import com.k15t.pat.dto.UserDto;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.UserRepository;
import com.k15t.pat.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getUsers() {
        Iterable<User> userList = userRepository.findAll();
        List<UserDto> responseList = new ArrayList<>();
        Iterator<User> userIterator = userList.iterator();
        userIterator.forEachRemaining(user -> responseList.add(modelMapper.map(userIterator.next(), UserDto.class)));
        return responseList;
    }
}
