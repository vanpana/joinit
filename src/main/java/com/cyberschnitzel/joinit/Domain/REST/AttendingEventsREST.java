package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.Response.EventResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/attendingEvents")
public class AttendingEventsREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@QueryParam("email") String email, @QueryParam("password") String password) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)){
            System.out.println("Requested " + email + " attending events.");
            return new Gson().toJson(new EventResponse(ctrl.getEventsAttendedByUser(ctrl.getUser(email))));
        }
        return "";
    }
}
