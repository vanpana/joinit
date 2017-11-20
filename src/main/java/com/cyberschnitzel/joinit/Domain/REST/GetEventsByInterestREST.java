package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Response.EventResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("getEventsByInterests")
public class GetEventsByInterestREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEventsByInterests(@QueryParam("email") String email,
                                       @QueryParam("password") String password){
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)){
            System.out.println("Requested " + email + " to get events by interests.");
            return new Gson().toJson(new EventResponse(ctrl.getEventsByUserIntrests(ctrl.getUser(email))));
        }

        return "";
    }
}
