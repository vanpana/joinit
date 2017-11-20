package com.cyberschnitzel.joinit.Util;

import com.cyberschnitzel.joinit.Domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserParser {
    private ResultSet rs;

    public UserParser(ResultSet rs) {
        this.rs = rs;
    }

    public User getUser(){
        User u = null;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            u = new User(id, name, surname, email, password);
        }
        catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return u;
    }
}
