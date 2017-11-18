package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/createEvent")
public class CreateEventREST {
    @GET
    @Path("/{email},{password},{eventname},{eventdescription},{eventcategory},{eventdate},{eventtime},{eventlocation},{eventopen}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@PathParam("email") String email, @PathParam("password") String password,
                         @PathParam("eventname") String eventname,
                         @PathParam("eventdescription") String eventdescription,
                         @PathParam("eventcategory") String eventcategory,
                         @PathParam("eventdate") String eventdate,
                         @PathParam("eventtime") String eventtime,
                         @PathParam("eventlocation") String eventlocation,
                         @PathParam("eventopen") int eventopen) {
        String output = "[";

        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        //TODO: If successful, add admin to attending
        if (ctrl.checkLogin(email, password)){
            boolean open;
            if (eventopen == 1) open = true;
            else open = false;
            ctrl.add(new Event(-1, eventname, eventdescription, eventcategory, eventdate, eventtime, eventlocation,
                    ctrl.getUser(email).getId(), open));

        }
        else output = "bad login";

        output += "]";

        return output;
    }
}
