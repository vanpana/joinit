package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/createEvent")
public class CreateEventREST {
    @GET
    @Path("/auth?")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@QueryParam("email") String email,
                         @QueryParam("password") String password,
                         @QueryParam("eventname") String eventname,
                         @QueryParam("eventdescription") String eventdescription,
                         @QueryParam("eventcategory") String eventcategory,
                         @QueryParam("eventdate") String eventdate,
                         @QueryParam("eventtime") String eventtime,
                         @QueryParam("eventlocation") String eventlocation,
                         @QueryParam("eventopen") int eventopen) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(email, password)){
            boolean open;
            if (eventopen == 1) open = true;
            else open = false;
            ctrl.add(new Event(-1, eventname, eventdescription, eventcategory, eventdate, eventtime, eventlocation,
                    ctrl.getUser(email).getId(), open));
            return new Gson().toJson(new ConfirmResponse(true));

        }
        return new Gson().toJson(new ConfirmResponse(false));
    }
}
