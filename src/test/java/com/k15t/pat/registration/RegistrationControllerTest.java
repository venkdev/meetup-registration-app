package com.k15t.pat.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.service.RegistrationService;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationBootstrap.class})
public class RegistrationControllerTest {

    @InjectMocks
    RegistrationController registrationController;

    @Mock
    private VelocityEngine velocityEngine;

    @Mock
    private RegistrationService registrationService;

    MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
                .setMessageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void registration() throws Exception {
        Template template = Mockito.mock(Template.class);
        Mockito.doReturn(template).when(velocityEngine).getTemplate(Mockito.anyString());
        mockMvc.perform(get("/registration.html")).
                andExpect(status().isOk());
    }

    @Test
    public void registerUser() throws Exception {
        UserDto user = new UserDto();
        String userJson = new ObjectMapper().writeValueAsString(user);

        Mockito.doReturn(user).when(registrationService).registerUser(Mockito.any());
        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().isOk());

        Mockito.doThrow(new RegistrationException("Mock exception")).when(registrationService).
                registerUser(Mockito.any());
        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().is4xxClientError());

        Mockito.doThrow(new RuntimeException("Mock run time exception")).when(registrationService).
                registerUser(Mockito.any());
        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().is5xxServerError());
    }

    @Test
    public void getUserList() throws Exception {
        mockMvc.perform(get("/users")).
                andExpect(status().isOk());
    }
}