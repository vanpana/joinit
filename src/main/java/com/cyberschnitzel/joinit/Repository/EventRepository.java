package com.cyberschnitzel.joinit.Repository;

import com.cyberschnitzel.joinit.Domain.Event;
import com.cyberschnitzel.joinit.Domain.User;
import com.cyberschnitzel.joinit.Util.UserParser;

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
            String query =  "INSERT INTO Events (Name, Description, Category, date, time, location, admin, open) " +
                    String.format("VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',%d,%d)",
                            item.getName(),
                            item.getDescription(),
                            item.getCategory(),
                            item.getDate(),
                            item.getTime(),
                            item.getLocation(),
                            item.getAdmin(),
                            (item.isOpen()) ? 1 : 0);
            stmt.execute(query);

            query = "INSERT INTO UserEvent " +
                    String.format("VALUES (%d, %d)",
                            item.getAdmin(), item.getId());
            stmt.execute(query);
        }
        catch (SQLException ex){
            System.out.print("Event add repository: ");
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

    public Event getAttendingUsers(Event ev){
        ArrayList<User> attendingUsers = new ArrayList<>();
        try{
            connectDB();
            String query = "SELECT * FROM UserEvent " +
                    "INNER JOIN Users ON UserEvent.userid = Users.id " +
                    "WHERE eventid = " + ev.getId();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                attendingUsers.add(new UserParser(rs).getUser());
            }
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        ev.setUsers(attendingUsers);
        return ev;
    }

    private ArrayList<Event> getEvents(ResultSet rs){
        ArrayList<Event> events = new ArrayList<>();
        try
        {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String location = rs.getString("location");
                int admin = rs.getInt("admin");
                int openint = rs.getInt("open");
                boolean open;
                if (openint == 1) open = true;
                else open = false;

                events.add(new Event(id, name, description, category, date, time, location, admin, open));
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
