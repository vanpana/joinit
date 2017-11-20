package com.cyberschnitzel.joinit.Domain.Response;

import com.cyberschnitzel.joinit.Domain.Event;

import java.util.ArrayList;

public class EventResponse {
    private ArrayList<Event> events;

    public EventResponse(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
