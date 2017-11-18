package com.cyberschnitzel.joinit.Controller;

import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.Invite;
import com.cyberschnitzel.joinit.Domain.User;
import com.cyberschnitzel.joinit.Repository.EventRepository;
import com.cyberschnitzel.joinit.Repository.InviteRepository;
import com.cyberschnitzel.joinit.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private UserRepository userrepo;
    private EventRepository eventrepo;
    private InviteRepository inviterepo;

    public Controller(UserRepository userrepo, EventRepository eventrepo) {
        this.userrepo = userrepo;
        this.eventrepo = eventrepo;
    }

    public Controller(UserRepository userrepo, EventRepository eventrepo, InviteRepository inviterepo) {
        this.userrepo = userrepo;
        this.eventrepo = eventrepo;
        this.inviterepo = inviterepo;
    }

    public void add(User user){ userrepo.add(user); }
    public void add(Event event){ eventrepo.add(event); }
    public void add(Invite invite){ inviterepo.add(invite); }

    public boolean checkLogin(String email, String password){
        User user = userrepo.get(email);
        if(user != null)
            if(user.getPassword().equals(password))
                return true;
        return false;
    }

    public boolean checkInvite(Invite invite){
        return inviterepo.getAll().contains(invite);

    }

    public ArrayList<Event> getEventsAttendedByUser(User u){
        ArrayList<Event> userevents = new ArrayList<>();
        for (Event event : getAllEvents()){
            if (event.getUsers().contains(u)) userevents.add(event);
        }
        u.setEvents(userevents);
        return userevents;
    }


    public User getUser(String email) { return userrepo.setInterests(addAdminedEvents(userrepo.get(email))); }
    public User getUser(int id) { return userrepo.setInterests(addAdminedEvents(userrepo.get(id))); }

    public ArrayList<String> getUserInterests(User u) { return userrepo.getInterests(u); }

    public ArrayList<Event> getAdminedEvents(User u){
        ArrayList<Event> adminedevents = new ArrayList<>();
        for (Event event : getAllEvents()){
            if (event.getAdmin() == u.getId()) adminedevents.add(event);
        }
        return adminedevents;
    }

    public User addAdminedEvents(User u){
        u.setEvents(getAdminedEvents(u));
        return u;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User u : userrepo.getAll()) users.add(userrepo.setInterests(addAdminedEvents(u)));
        return users;
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        for (Event ev : eventrepo.getAll()) events.add(eventrepo.getAttendingUsers(ev));
        return events;
    }

    public ArrayList<Event> getEventsByUserIntrests(User user){
        List<String> interests = user.getInterests();
        return new ArrayList<>(getAllEvents().stream().filter(e ->  interests.contains(e.getCategory())).collect(Collectors.toList())) ;
    }

    public ArrayList<Invite> getAll(){
        return inviterepo.getAll();
    }

    public ArrayList<Invite> getAllByUserGuest(User u){
        return inviterepo.getAll("guestid", u.getId());
    }

    public ArrayList<Invite> getAllByHostGuest(User u){
        return inviterepo.getAll("hostid", u.getId());
    }

    public ArrayList<Invite> getAllByEvent(Event ev){
        return inviterepo.getAll("eventid", ev.getId());
    }

    public void updateInvite(Invite inv){
        inviterepo.update(inv);
    }

    public void joinEvent(User u, int eventid){
        userrepo.joinEvent(u, eventid);
    }
}
