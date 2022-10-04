package model;

import java.sql.*;

public class DBTrello {
    private static String url = "jdbc:sqlite:dbtrello.db";

    public DBTrello() {
        try {
            Connection conn = DriverManager.getConnection(url);
            configDB(conn);

                createTables(conn);
                initData(conn);


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void configDB(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql;

        // Activation of checks FK
        sql = "PRAGMA foreign_keys = ON;";
        stmt.execute(sql);
    }



    private static void createTables(Connection conn) throws SQLException {

        String sql;
        Statement stmt = conn.createStatement();

        // SQL statement for boards table
        sql = "CREATE TABLE IF NOT EXISTS boards ("
                + "	idBoard integer PRIMARY KEY,"
                + "	boardName text NOT NULL);";
        stmt.execute(sql);
        // SQL statement for columns table
        sql = "CREATE TABLE IF NOT EXISTS columns ("
                + "	idColumn integer PRIMARY KEY,"
                + "	idBoard integer NOT NULL,"
                + "	columnName text NOT NULL,"
                + " position int NOT NULL,"
                + " CONSTRAINT fk_board FOREIGN KEY (idBoard) "
                + " REFERENCES boards(idBoard));";
        stmt.execute(sql);
        //SQL statement for cards table
        sql = "CREATE TABLE IF NOT EXISTS cards ("
                + " idCard integer PRIMARY KEY,"
                + " idColumn integer NOT NULL,"
                + " cardName text NOT NULL,"
                + " position int NOT NULL,"
                + "CONSTRAINT fk_column FOREIGN KEY (idColumn) "
                + " REFERENCES columns(idColumn));";
        stmt.execute(sql);
    }
    public void initData(Connection conn) throws SQLException {
        clearDB(conn);
        seedBoard(conn);
        seedColumn(conn);
        seedCard(conn);


    }
    private static void seedBoard(Connection conn) throws SQLException {
        String sql = "INSERT INTO boards(idBoard, boardName) VALUES(?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,1);
        preparedStatement.setString(2, "Board 1");
        preparedStatement.execute();

    }

    private static void seedColumn(Connection conn) throws SQLException {
        String sql = "INSERT INTO columns(idColumn, idBoard, columnName, position) VALUES(?,?,?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,1);
        preparedStatement.setInt(2,1);
        preparedStatement.setString(3, "Column 1");
        preparedStatement.setInt(4,0);
        preparedStatement.execute();

        preparedStatement.setInt(1,2);
        preparedStatement.setInt(2,1);
        preparedStatement.setString(3, "Column 2");
        preparedStatement.setInt(4,1);
        preparedStatement.execute();

        preparedStatement.setInt(1,3);
        preparedStatement.setInt(2,1);
        preparedStatement.setString(3, "Column 3");
        preparedStatement.setInt(4,2);
        preparedStatement.execute();
    }

    private static void seedCard(Connection conn) throws SQLException {
        String sql = "INSERT INTO cards(idCard, idColumn, cardName, position) VALUES(?,?,?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,1);
        preparedStatement.setInt(2,1);
        preparedStatement.setString(3, "Card 1");
        preparedStatement.setInt(4,0);
        preparedStatement.execute();

        preparedStatement.setInt(1,2);
        preparedStatement.setInt(2,2);
        preparedStatement.setString(3, "Card 2");
        preparedStatement.setInt(4,0);
        preparedStatement.execute();

        preparedStatement.setInt(1,3);
        preparedStatement.setInt(2,2);
        preparedStatement.setString(3, "Card 3");
        preparedStatement.setInt(4,1);
        preparedStatement.execute();

        preparedStatement.setInt(1,4);
        preparedStatement.setInt(2,2);
        preparedStatement.setString(3, "Card 4");
        preparedStatement.setInt(4,2);
        preparedStatement.execute();

        preparedStatement.setInt(1,5);
        preparedStatement.setInt(2,3);
        preparedStatement.setString(3, "Card 5");
        preparedStatement.setInt(4,0);
        preparedStatement.execute();

        preparedStatement.setInt(1,6);
        preparedStatement.setInt(2,3);
        preparedStatement.setString(3, "Card 6");
        preparedStatement.setInt(4,1);
        preparedStatement.execute();
    }
    private static void clearDB(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        String sql;

        sql = "DELETE FROM cards;";
        statement.execute(sql);

        String sqlcol = "DELETE FROM columns;";
        statement.execute(sqlcol);
        String sqlboard = "DELETE FROM boards;";
        statement.execute(sqlboard);
    }


}
