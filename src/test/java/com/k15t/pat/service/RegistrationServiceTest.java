package com.k15t.pat.service;

import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.UserRepository;
import com.k15t.pat.service.impl.RegistrationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationBootstrap.class})
public class RegistrationServiceTest {

    @InjectMocks
    RegistrationServiceImpl registrationService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        ReflectionTestUtils.setField(registrationService, "modelMapper", modelMapper);
    }

    @Test
    public void testRegisterUser(){
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setPassword("test");
        User user = new User();
        user.setId(1);
        Mockito.when(userRepository.getUserByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Assert.assertNotNull(registrationService.registerUser(userDto));

    }

    @Test(expected = RegistrationException.class)
    public void testRegisterUserDuplicateRegistration(){
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setPassword("test");
        User user = new User();
        user.setId(1);
        Mockito.when(userRepository.getUserByEmail(Mockito.anyString())).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Assert.assertNotNull(registrationService.registerUser(userDto));
    }

    @Test
    public void testGetUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(registrationService.getUsers());
    }

}
