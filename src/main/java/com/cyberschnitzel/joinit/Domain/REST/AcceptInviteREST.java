package com.cyberschnitzel.joinit.Domain.REST;

import com.cyberschnitzel.joinit.Controller.Controller;
import com.cyberschnitzel.joinit.Domain.Invite;
import com.cyberschnitzel.joinit.Domain.Response.ConfirmResponse;
import com.cyberschnitzel.joinit.Domain.Response.EventResponse;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/acceptInvite")
public class AcceptInviteREST {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg(@QueryParam("guestemail") String guestemail,
                         @QueryParam("password") String password,
                         @QueryParam("hostemail") String hostemail,
                         @QueryParam("eventid") int eventid) {
        String filename = "/Users/vanpana/Documents/IntelliJ/joinit/data/joinit.db";
        Controller ctrl = new Controller(new UserRepository(filename), new EventRepository(filename));

        if (ctrl.checkLogin(guestemail, password)){
            System.out.println(guestemail + " accepted invitation from " + hostemail + " on event " + eventid);
            //TODO: Check invites crashes server
            if (ctrl.checkInvite(new Invite(eventid, ctrl.getUser(hostemail).getId(), ctrl.getUser(guestemail).getId(), -1))){
                ctrl.updateInvite(new Invite(eventid, ctrl.getUser(hostemail).getId(), ctrl.getUser(guestemail).getId(), 1));
                return new Gson().toJson(new ConfirmResponse(true));
            }
        }
        return new Gson().toJson(new ConfirmResponse(false));
    }
}
