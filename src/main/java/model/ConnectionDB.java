package model;

import java.sql.*;

public class ConnectionDB {
    private static String url = "jdbc:sqlite:dbtrello.db";
    private static Connection connect;
    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
