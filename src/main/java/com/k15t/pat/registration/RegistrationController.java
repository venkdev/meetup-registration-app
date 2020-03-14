package com.k15t.pat.registration;

import com.k15t.pat.dto.UserDto;
import com.k15t.pat.exception.RegistrationException;
import com.k15t.pat.service.RegistrationService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.List;


@RestController
public class RegistrationController {

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

    @RequestMapping(value = "/user.html", method = RequestMethod.POST)
    public String registerUser(UserDto userDto){
        Template template = velocityEngine.getTemplate("templates/result-page.vm");
        VelocityContext context = new VelocityContext();
        try {
            UserDto createdUser = registrationService.registerUser(userDto);
            context.put("id", createdUser.getId());
        }
        catch (RegistrationException re){
            context.put("error", re.getMessage());
        }
        catch(Exception e){
            context.put("error", "Creation failed");
        }
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<UserDto> getUserList(){
        return registrationService.getUsers();
    }
}
