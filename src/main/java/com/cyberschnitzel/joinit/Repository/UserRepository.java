package com.cyberschnitzel.joinit.Repository;

import com.cyberschnitzel.joinit.Domain.User;
import com.cyberschnitzel.joinit.Util.UserParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository extends ARepository<User> {
    public UserRepository(String filename){
        super(filename);
    }

    @Override
    public void add(User item){
        try{
            connectDB();

            String query =  "INSERT INTO Users " +
                    String.format("VALUES (%d,\'%s\',\'%s\',\'%s\')",
                            item.getId(),
                            item.getName(),
                            item.getSurname(),
                            item.getEmail(),
                            item.getPassword());
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

    public User get(int id){
        User u = null;
        try{
            connectDB();
            String query = "SELECT * FROM Users WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            u = getUsers(rs).get(0);
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return u;
    }

    public User get(String email){
        User u = null;
        try{
            connectDB();
            String query = "SELECT * FROM Users WHERE email = \"" + email + "\"";
            ResultSet rs = stmt.executeQuery(query);
            u = getUsers(rs).get(0);
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return u;
    }

    private ArrayList<User> getUsers(ResultSet rs){
        ArrayList<User> users = new ArrayList<>();
        try
        {
            while (rs.next()) {
                users.add(new UserParser(rs).getUser());
            }
        }
        catch (SQLException e){
            disconnectDB();
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try{
            connectDB();
            String query = "SELECT * FROM Users ";
            ResultSet rs = stmt.executeQuery(query);
//            users = getUsers(rs);
            for (User u : getUsers(rs))
                users.add(setInterests(u));
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return users;
    }

    public User setInterests(User u){
        u.setInterests(getInterests(u));
        return u;
    }

    private ArrayList<String> getInterests(User u) {
        ArrayList<String> interests= new ArrayList<>();
        try{
            connectDB();
            String query = "SELECT * FROM Interests WHERE userid = " + u.getId();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                interests.add(rs.getString("interest"));
            }
            rs.close();
        }
        catch (SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            disconnectDB();
        }
        return interests;
    }
}
