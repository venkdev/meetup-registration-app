package com.k15t.pat.registration;

import com.k15t.pat.constants.AppConstants;
import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.service.RegistrationService;
import com.k15t.pat.util.RegistrationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RegistrationController {

    private static final Logger LOGGER=LoggerFactory.getLogger(RegistrationController.class);

    @Autowired private VelocityEngine velocityEngine;

    @Autowired private RegistrationService registrationService;

    private final String REGISTRATION_TEMPLATE_PATH = "templates/registration.vm";

    @RequestMapping("/registration.html")
    public String registration() {
        VelocityContext context = new VelocityContext();
        context.put(AppConstants.REG_FORM, AppConstants.TRUE);
        return RegistrationUtils.populateTemplateString(velocityEngine,
                REGISTRATION_TEMPLATE_PATH, context);
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.POST)
    public String registerUser(UserDto userDto){
        VelocityContext context = new VelocityContext();
        try {
            UserDto createdUser = registrationService.registerUser(userDto);
            context.put(AppConstants.ID, createdUser.getId());
        }
        catch (RegistrationException re){
            context.put(AppConstants.ERROR, re.getMessage());
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            context.put(AppConstants.ERROR, AppConstants.CREATION_FAILED_MSG);
        }
        return RegistrationUtils.populateTemplateString(velocityEngine,
                REGISTRATION_TEMPLATE_PATH, context);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<UserDto> getUserList(){
        return registrationService.getUsers();
    }
}
