package com.cyberschnitzel.Controller;

import com.cyberschnitzel.Domain.Event;
import com.cyberschnitzel.Domain.User;
import com.cyberschnitzel.Repository.EventRepository;
import com.cyberschnitzel.Repository.UserRepository;

public class Controller {
    private UserRepository userrepo;
    private EventRepository eventrepo;

    public Controller(UserRepository userrepo, EventRepository eventrepo) {
        this.userrepo = userrepo;
        this.eventrepo = eventrepo;
    }

    public void add(User user){ userrepo.add(user); }
    public void add(Event event){ eventrepo.add(event); }

    public void del(User user){ userrepo.del(user); }
    public void del(Event event) { eventrepo.del(event); }
}
