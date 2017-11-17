package com.cyberschnitzel.Repository;

import com.cyberschnitzel.Domain.Event;
import com.cyberschnitzel.Domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventRepository extends ARepository<Event> {
    public EventRepository(String filename){
        super(filename);
    }

    @Override
    public void add(Event item) {
        try{
            connectDB();
            String query =  "INSERT INTO Events " +
                    String.format("VALUES (%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',%d)",
                            item.getId(),
                            item.getName(),
                            item.getDescription(),
                            item.getDate(),
                            item.getTime(),
                            item.getLocation(),
                            item.getAdmin());
            stmt.execute(query);
        }
        catch (SQLException ex){
            System.out.print("Add repository: ");
            System.out.println(ex.getMessage());
        }
        finally {
            disconnectDB();
        }
    }

    public Event get(int id){
        Event event = null;
        try{
            connectDB();
            String query = "SELECT * FROM Events WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            event = getEvents(rs).get(0);
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return event;
    }

    private ArrayList<Event> getEvents(ResultSet rs){
        ArrayList<Event> events = new ArrayList<>();
        try
        {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String location = rs.getString("location");
                int admin = rs.getInt("admin");

                events.add(new Event(id, name, description, date, time, location, admin));
            }
        }
        catch (SQLException e){
            disconnectDB();
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public ArrayList<Event> getAll() {
        ArrayList<Event> events = new ArrayList<>();
        try{
            connectDB();
            String query = "SELECT * FROM Events ";
            ResultSet rs = stmt.executeQuery(query);
            events = getEvents(rs);
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return events;
    }
}
