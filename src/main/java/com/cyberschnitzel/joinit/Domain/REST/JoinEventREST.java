package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Invite;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.InviteRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/joinEvent")
public class JoinEventREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@QueryParam("email") String email,
                         @QueryParam("password") String password,
                         @QueryParam("eventid") int eventid) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)){
            System.out.println("User: " + email + " joined event with id " + eventid);
            //TODO: Check if not already joined.
            if (true) {
                ctrl.joinEvent(ctrl.getUser(email), eventid);
                return new Gson().toJson(new ConfirmResponse(true));
            }
        }
        return new Gson().toJson(new ConfirmResponse(false));
    }
}
