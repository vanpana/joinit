package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Domain.Response.EventResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/createEvent")
public class CreateEventREST {
    @POST
    @Consumes()
    public Response consumeJSON(@QueryParam("email") String email,
                                @QueryParam("password") String password,
                                String eventJSON) {

        EventResponse eventResponse = new Gson().fromJson(eventJSON, EventResponse.class);

        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)){
            System.out.println("New creation request.");
            ctrl.add(eventResponse.getEvents().get(0));
            return Response.status(200).entity(new Gson().toJson(new ConfirmResponse(true))).build();
        }
        return Response.status(200).entity(new Gson().toJson(new ConfirmResponse(false))).build();
    }
}
