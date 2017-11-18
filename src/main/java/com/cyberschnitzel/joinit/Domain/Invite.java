package com.cyberschnitzel.joinit.Domain;

import com.google.gson.Gson;

public class Invite {
    private int eventid;
    private int hostid;
    private int guestid;
    private int response;

    public Invite(int eventid, int hostid, int guestid, int response) {
        this.eventid = eventid;
        this.hostid = hostid;
        this.guestid = guestid;
        this.response = response;
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

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
