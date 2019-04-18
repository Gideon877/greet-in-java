package greet.counter;

import greet.Language;
import greet.StringMethods;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static greet.ConsoleColors.*;

public class Counter {

    final String USER_INSERT_SQL = "insert into users (name, count) values (?, ?)";
    final String FIND_ALL_USERS_SQL = "select * from users";
    final String FIND_NAME_SQL = "select name, count from users where name = ?";
    final String COUNT_UPDATE_SQL = "update users set count = count + 1 where name = ?";
    final String CLEAR_COUNT_SQL = "delete from users";
    final String CLEAR_USER_COUNT_SQL = "delete from users where name = ?";
//    final String COUNT_USER_SQL = "select count(*) as count from users";

    final PreparedStatement insertNameAndCountPreparedStatement;
    final PreparedStatement findNamePreparedStatement;
    final PreparedStatement updateCountPreparedStatement;
    final PreparedStatement findAllPreparedStatement;
    final PreparedStatement clearAllPreparedStatement;
    final PreparedStatement clearUserPreparedStatement;
//    final PreparedStatement countUserPreparedStatement;

    final String GREET_DATABASE_URL = "jdbc:h2:./target/greet_db";
    Connection connection;
    StringMethods stringMethods = new StringMethods();

    public Counter() throws SQLException, ClassNotFoundException {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
            insertNameAndCountPreparedStatement = connection.prepareStatement(USER_INSERT_SQL);
            findNamePreparedStatement = connection.prepareStatement(FIND_NAME_SQL);
            findAllPreparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL);
            updateCountPreparedStatement = connection.prepareStatement(COUNT_UPDATE_SQL);
            clearAllPreparedStatement = connection.prepareStatement(CLEAR_COUNT_SQL);
            clearUserPreparedStatement = connection.prepareStatement(CLEAR_USER_COUNT_SQL);
//            countUserPreparedStatement = connection.prepareStatement(COUNT_USER_SQL);
    }

    public void clearAllUsers() {
        try {
            clearAllPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(String userName) {
        try {
            insertNameAndCountPreparedStatement.setString(1, userName);
            insertNameAndCountPreparedStatement.setInt(2, 1);
            insertNameAndCountPreparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error occured! " + e);
        } catch (Exception e) {
            System.out.println("Failed to create user: " + userName);
        }
    }

    public Map<String, Integer> findUser(String userName) throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        try {
            findNamePreparedStatement.setString(1, userName);
            ResultSet rs = findNamePreparedStatement.executeQuery();
            if(rs.next()) {
                map.put(rs.getString("name"), rs.getInt("count"));
            }
            return map;
        } catch (SQLException e) {
            System.out.println("Error occured! " + e);
        } catch (Exception e) {
            System.out.println("Failed to find user: " + userName);
        }
        return map;
    }

    public int findAndUpdateUser(String userName){
        try {
            updateCountPreparedStatement.setString(1, userName);

            return updateCountPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error occured! " + e);
        } catch (Exception e) {
            System.out.println("Failed to update user: " + userName);
        }
        return 0;
    }

    public Map<String, Integer> findAllUsers() {
        Map<String, Integer> map = new HashMap<>();
        try {
            findAllPreparedStatement.execute();
            ResultSet rs = findAllPreparedStatement.executeQuery();

            while(rs.next()) {
                map.put(rs.getString("name"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to find all users." + e);

        }
        return map;
    }

    public void clearUserByUsername(String userName) {
        try {
            clearUserPreparedStatement.setString(1, userName);
            clearUserPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int usersCounter() {
        return findAllUsers().size();
    }

    public String greetPerson(String userName, String language) throws SQLException {
        if(language == null || language.isEmpty()) {
            language = "English";
        }
        language = stringMethods.Capitalize(language);
        userName = stringMethods.Capitalize(userName);

        if(findUser(userName).isEmpty()) {
            addNewUser(userName);
        } else {
            findAndUpdateUser(userName);
        }

        return greetBuilder(userName, language);

    }

    private String greetBuilder(String Name, String language) {
        try {
            return greetFormat( BLACK_BOLD, Language.valueOf(language).getExpression(), Name);
        } catch (IllegalArgumentException e) {
            return greetFormat(RED_BOLD_BRIGHT, Language.valueOf("English").getExpression(), Name);
        }
    }

    private String  greetFormat(String color, String language, String userName) {
        return String.format("%s%s, %s!%s", color, language, userName, RESET);
    }
}