package com.k15t.pat.service.impl;

import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.UserRepository;
import com.k15t.pat.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public UserDto registerUser(UserDto userDto) {
        String encryptedPassword = doBCrypt(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        User existingUser = userRepository.getUserByEmail(userDto.getEmail());
        if(null!=existingUser){
            throw new RegistrationException("User has already registered!");
        }
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

    private static String doBCrypt(String value) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(value);
    }
}
