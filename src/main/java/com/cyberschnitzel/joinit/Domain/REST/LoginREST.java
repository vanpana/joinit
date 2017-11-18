package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginREST {
    @GET
    @Path("/{email},{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password) {
        String output = "[";

        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)) output += "true"; //TODO: Check if not null
        else output = "bad login";

        output += "]";

        return output;
    }
}