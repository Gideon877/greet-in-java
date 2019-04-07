package greet.counter;

import java.sql.*;
import java.util.Map;

public class GreetCounter {

    Connection conn;
    final String GREET_DATABASE_URL = "jdbc:h2:./target/greet_db";


    public GreetCounter() throws Exception {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
    }

    public void updateUserData(String userName) throws SQLException{
        try {
            DbCounter gc = new DbCounter(userName, conn);
            gc.findAndUpdateUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNewUserData(String userName) throws SQLException{
        try {
            DbCounter gc = new DbCounter(userName, conn);
            gc.addNewUser();
//            System.out.println("User added!");
        } catch (SQLException e) {
            System.out.println("Failed to add: " + userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int findUsersCount() throws SQLException {
        return getUsersCount().size();
    }

    public Map<String, Integer> getUsersCount() throws SQLException {
        DbCounter counter = new DbCounter("", conn);
        return counter.findAllUsers();
    }

    public Map<String, Integer> findUserByUsername(String userName) throws SQLException {

        try {
            DbCounter dbCounter = new DbCounter(userName, conn);
            return dbCounter.findUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void clearAllUsers() throws SQLException{
        try {
            DbCounter cau = new DbCounter("", conn);
            cau.clearAllUsers();
            System.out.println("Users have been deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearUserByUsername(String userName) {
        try {
            DbCounter counter = new DbCounter("", conn);
            counter.clearUserByUsername(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
