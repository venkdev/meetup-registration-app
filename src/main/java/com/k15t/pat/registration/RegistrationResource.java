package com.k15t.pat.registration;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/registration")
@Component
public class RegistrationResource {

    // Extend the current resource to receive and store the data in memory.
    // Return a success information to the user including the entered information.
    // The address should be parsed and transformed an a better format.
    public Response save() {
        return Response.ok().build();
    }

}
