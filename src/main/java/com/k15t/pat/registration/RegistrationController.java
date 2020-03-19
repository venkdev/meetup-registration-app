package com.k15t.pat.registration;

import com.k15t.pat.constants.AppConstants;
import com.k15t.pat.dto.UserDto;
import com.k15t.pat.dto.UsersResponseDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.service.RegistrationService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;


@RestController
public class RegistrationController {

    private static final Logger LOGGER=LoggerFactory.getLogger(RegistrationController.class);

    @Autowired private VelocityEngine velocityEngine;

    @Autowired private RegistrationService registrationService;

    @RequestMapping("/registration.html")
    public String registration() {

        Template template = velocityEngine.getTemplate("templates/registration.vm");
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        return writer.toString();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto userRespDto;
        try {
            userRespDto = registrationService.registerUser(userDto);
            return ResponseEntity.ok(userRespDto);
        }catch (RegistrationException re){
            userRespDto = new UserDto();
            userRespDto.setErrorMessage(re.getMessage());
            return ResponseEntity.badRequest().body(userRespDto);
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            userRespDto = new UserDto();
            userRespDto.setErrorMessage(AppConstants.CREATION_FAILED_MSG);
            return ResponseEntity.unprocessableEntity().body(userRespDto);
        }
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public ResponseEntity<UsersResponseDto> getUserList(){
        UsersResponseDto usersResponse = new UsersResponseDto();
        usersResponse.setUserDtoList(registrationService.getUsers());
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }
}
