package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/createEvent")
public class CreateEventREST {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{email}/{password}")
    public String doPost(@QueryParam("email") String email, @QueryParam("password") String password,JsonObject event) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)) {
            Event eventClass = new Gson().fromJson(event, Event.class);
            eventClass.setAdmin(ctrl.getUser(email).getId());
            return new Gson().toJson(new ConfirmResponse(true));

        }
        return new Gson().toJson(new ConfirmResponse(false));
    }
}
