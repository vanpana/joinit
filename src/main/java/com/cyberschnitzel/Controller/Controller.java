package com.cyberschnitzel.Controller;

import com.cyberschnitzel.Domain.Event;
import com.cyberschnitzel.Domain.User;
import com.cyberschnitzel.Repository.EventRepository;
import com.cyberschnitzel.Repository.UserRepository;

import java.util.ArrayList;

public class Controller {
    private UserRepository userrepo;
    private EventRepository eventrepo;

    public Controller(UserRepository userrepo, EventRepository eventrepo) {
        this.userrepo = userrepo;
        this.eventrepo = eventrepo;
    }

    public void add(User user){ userrepo.add(user); }
    public void add(Event event){ eventrepo.add(event); }

    private User getUserWithEvents(User u){
        ArrayList<Event> userevents = new ArrayList<>();
        for (Event event : getAllEvents()){
            if (event.getAdmin() == u.getId()) userevents.add(event);
        }
        u.setEvents(userevents);
        return u;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User u : userrepo.getAll()){
            users.add(getUserWithEvents(u));
        }
        return users;
    }
    
    public ArrayList<Event> getAllEvents() { return eventrepo.getAll(); }
}
