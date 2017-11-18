package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Invite;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Domain.Response.InterestResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.InviteRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("invite")
public class InviteREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@QueryParam("hostemail") String hostemail,
                         @QueryParam("password") String password,
                         @QueryParam("eventid") int eventid,
                         @QueryParam("guestemail") String guestemail) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename),
                new InviteRepository(filename));

        if (ctrl.checkLogin(hostemail, password)){
            System.out.println("User: " + hostemail + " invited " + guestemail + " at event with id " + eventid);
            ctrl.add(new Invite(eventid, ctrl.getUser(hostemail).getId(), ctrl.getUser(guestemail).getId()));
            return new Gson().toJson(new ConfirmResponse(true));
        }
        return new Gson().toJson(new ConfirmResponse(false));
    }
}
