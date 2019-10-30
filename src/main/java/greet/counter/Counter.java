package greet.counter;

import greet.Language;
import greet.StringMethods;
import greet.greeter.Greet;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Counter implements Greet {

    final String USER_INSERT_SQL = "insert into users (name, count) values (?, ?)";
    final String FIND_ALL_USERS_SQL = "select * from users";
    final String FIND_NAME_SQL = "select name, count from users where name = ?";
    final String COUNT_UPDATE_SQL = "update users set count = count + 1 where name = ?";
    final String CLEAR_COUNT_SQL = "delete from users";
    final String CLEAR_USER_COUNT_SQL = "delete from users where name = ?";
    final String FIND_ALL_USERS_LIMIT_SQL = "SELECT * FROM users ORDER BY name OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY";

    final PreparedStatement insertNameAndCountPreparedStatement;
    final PreparedStatement findNamePreparedStatement;
    final PreparedStatement updateCountPreparedStatement;
    final PreparedStatement findAllPreparedStatement;
    final PreparedStatement findAllLimitPreparedStatement;
    final PreparedStatement clearAllPreparedStatement;
    final PreparedStatement clearUserPreparedStatement;

    final String GREET_DATABASE_URL = "jdbc:h2:./target/greet_db";
    Connection connection;
    StringMethods stringMethods = new StringMethods();

    public Counter() throws SQLException, ClassNotFoundException {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(GREET_DATABASE_URL, "sa", "");
            insertNameAndCountPreparedStatement = connection.prepareStatement(USER_INSERT_SQL);
            findNamePreparedStatement = connection.prepareStatement(FIND_NAME_SQL);
            findAllPreparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL);
            findAllLimitPreparedStatement = connection.prepareStatement(FIND_ALL_USERS_LIMIT_SQL);
            updateCountPreparedStatement = connection.prepareStatement(COUNT_UPDATE_SQL);
            clearAllPreparedStatement = connection.prepareStatement(CLEAR_COUNT_SQL);
            clearUserPreparedStatement = connection.prepareStatement(CLEAR_USER_COUNT_SQL);
    }

    @Override
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

    public Map<String, Integer> findUser(String userName) {
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

    @Override
    public Map<String, Integer> findAllUsers() {
        Map<String, Integer> map = new TreeMap<>();
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

    @Override
    public void clearUserByUsername(String userName) {
        try {
            clearUserPreparedStatement.setString(1, userName);
            clearUserPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int usersCounter() {
        return findAllUsers().size();
    }

    @Override
    public String greetPerson(String userName, String language) {
//        GreetBuilder builder = new GreetBuilder();
        if(language == null || language.isEmpty()) {
            language = "Zulu";
        }
        // capitalizing language and name.
        language = stringMethods.Capitalize(language);
        userName = stringMethods.Capitalize(userName);

        //checks if user/name is in the database
        if(findUser(userName).isEmpty()) {
            addNewUser(userName); // if name have not added, we add it
        } else {
            // updating existing name count by incrementing by 1.
            findAndUpdateUser(userName);
        }
        try {
            return String.format("%s, %s!", Language.valueOf(language).getExpression(), userName);
        } catch (IllegalArgumentException e) {
            return String.format("%s, %s!", Language.valueOf("Zulu").getExpression(), userName);
        }
//        return builder.greetPerson.greet(userName, language);
    }
}