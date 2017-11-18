package com.cyberschnitzel.joinit.Repository;

import com.cyberschnitzel.joinit.Domain.Invite;
import com.cyberschnitzel.joinit.Domain.User;
import com.cyberschnitzel.joinit.Util.UserParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InviteRepository extends ARepository<Invite> {
    public InviteRepository(String filename){
        super(filename);
    }

    @Override
    public void add(Invite item) {
        try{
            connectDB();
            String query =  "INSERT INTO Invites " +
                    String.format("VALUES (%d,%d,%d,%d)",
                            item.getEventid(),
                            item.getHostid(),
                            item.getGuestid(),
                            item.getResponse());
            stmt.execute(query);
        }
        catch (SQLException ex){
            System.out.print("Invite add repository: ");
            System.out.println(ex.getMessage());
        }
        finally {
            disconnectDB();
        }
    }

    public Invite getId(String type, int id){
        Invite event = null;
        try{
            connectDB();
            String query = "SELECT * FROM Invites WHERE " + type + " = " + id;
            ResultSet rs = stmt.executeQuery(query);
            event = getInvites(rs).get(0);
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

    private ArrayList<Invite> getInvites(ResultSet rs){
        ArrayList<Invite> invites = new ArrayList<>();
        try
        {

            while (rs.next()) {
                int eventid = rs.getInt("eventid");
                int hostid = rs.getInt("hostid");
                int guestid = rs.getInt("guestid");
                int response = rs.getInt("response");

                invites.add(new Invite(eventid, hostid, guestid, response));
            }
        }
        catch (SQLException e){
            disconnectDB();
            System.out.println(e.getMessage());
        }
        return invites;
    }

    @Override
    public ArrayList<Invite> getAll() {
        ArrayList<Invite> events = new ArrayList<>();
        try{
            connectDB();
            String query = "SELECT * FROM Invites ";
            ResultSet rs = stmt.executeQuery(query);
            events = getInvites(rs);
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

    public ArrayList<Invite> getAll(String type, int id){
        ArrayList<Invite> invites = new ArrayList<>();

        try{
            connectDB();
            String query = "SELECT * FROM Invites WHERE " + type + " = " + id;
            ResultSet rs = stmt.executeQuery(query);

            invites = getInvites(rs);

            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }

        return invites;
    }
}
