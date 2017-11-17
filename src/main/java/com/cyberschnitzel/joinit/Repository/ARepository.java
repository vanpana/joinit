package com.cyberschnitzel.joinit.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class ARepository<T> {
    private String filename;
    protected Connection conn = null;
    protected Statement stmt = null;

    public ARepository(String filename) {
        this.filename = filename;
    }

    protected void connectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + filename);

            conn.setAutoCommit(true);
            stmt = conn.createStatement();
        } catch (SQLException |ClassNotFoundException e) { //
            System.out.print("ConnectDB SQL error: ");
            System.out.println(e.getMessage());
        }
    }

    protected void disconnectDB(){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) { /* ignored */}
        }
        stmt = null;
        conn = null;
    }


    abstract void add(T item);
    abstract ArrayList<T> getAll();
}
