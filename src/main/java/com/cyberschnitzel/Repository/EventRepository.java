package com.cyberschnitzel.Repository;

import com.cyberschnitzel.Domain.Event;

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

    //TODO rest of crud
}
