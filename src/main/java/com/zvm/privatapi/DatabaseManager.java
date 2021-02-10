package com.zvm.privatapi;

import java.sql.*;

public class DatabaseManager {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3307/prog1db?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "ZVMzvm8252133!";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void restartDB() throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Rates");
            st.execute("""
                     CREATE TABLE Rates 
                     (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     date VARCHAR(10) NOT NULL,
                     usd FLOAT NOT NULL)
                     """);
        }
    }

    protected void insertData(String date, float usd) {
        try (PreparedStatement ps = connection.prepareStatement("""
                INSERT 
                INTO Rates (date, usd) 
                VALUES (?, ?)
                """)) {
            ps.setString(1, date);
            ps.setFloat(2, usd);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected float getAvarage() throws SQLException {

        ResultSet rs = getMetaData();
        assert rs != null;
        ResultSetMetaData md = rs.getMetaData();

        float max = 0;

        while (rs.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                float a = rs.getFloat(0);
                if (max < a) max = a;
            }
        }

        return max;
    }

    protected float getMax() throws SQLException {

        ResultSet rs = getMetaData();
        assert rs != null;
        ResultSetMetaData md = rs.getMetaData();

        int x = 0;
        float sum = 0;

        while (rs.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                x++;
                sum += rs.getFloat(0);
            }
        }

        return sum / x;
    }

    private ResultSet getMetaData() {

        try (PreparedStatement ps = connection.prepareStatement("""
                SELECT usd 
                FROM Rates
                """)) {

            try (ResultSet rs = ps.executeQuery()) {

                return rs;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
