package com.cyberschnitzel.joinit.Domain;

import com.google.gson.Gson;

public class Invite {
    private int eventid;
    private int hostid;
    private int guestid;

    public Invite(int eventid, int hostid, int guestid) {
        this.eventid = eventid;
        this.hostid = hostid;
        this.guestid = guestid;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public int getHostid() {
        return hostid;
    }

    public void setHostid(int hostid) {
        this.hostid = hostid;
    }

    public int getGuestid() {
        return guestid;
    }

    public void setGuestid(int guestid) {
        this.guestid = guestid;
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
