package com.cyberschnitzel.joinit.Repository;

import com.cyberschnitzel.joinit.Domain.User;
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
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("email");
                String password = rs.getString("password");

                users.add(new User(id, name, surname, email, password));
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
            users = getUsers(rs);
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
}
