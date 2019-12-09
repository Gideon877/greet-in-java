package greet.database;

import greet.greeter.Greet;
import greet.greeter.GreetBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PostgresDB implements Greet {
    private final Connection connection;

    public PostgresDB(Connection connection) {
        this.connection = connection;
    }

    public String greetUsers(String name, String language) {
        if(language == null || language.isEmpty()) {
            language = "Zulu";
        }

        language = Capitalize(language);
        name = Capitalize(name);

        if(findUser(name).isEmpty()) {
            System.out.println("greetUsers:addNewUser");
            addNewUser(name); // if name have not added, we add it
        } else {
            // updating existing name count by incrementing by 1.
            System.out.println("greetUsers:findAndUpdateUser");
            findAndUpdateUser(name);
        }
        return new GreetBuilder().greetPerson.greet(name, language);
    }

    public Map<String, Integer> findUser(String name) {
        Map<String, Integer> user = new HashMap<>();
        try {
            CallableStatement findUser = connection.prepareCall("select * from users where name = ?");
            findUser.setString(1, name);
            ResultSet rs = findUser.executeQuery();
            while (rs.next()) {
                user.put(rs.getString("name"), rs.getInt("count"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addNewUser(String name) {
        try {
            CallableStatement addUser = connection.prepareCall("insert into users (name, count) values(?, ?)");
            addUser.setString(1, name);
            addUser.setInt(2, 1);
            addUser.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findAndUpdateUser(String name) {
        try {
            CallableStatement updateUser = connection.prepareCall("update users set count = count + 1 where name = ?");
            updateUser.setString(1, name);
            updateUser.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int usersCounter() {
        return findAllUsers().size();
    }

    @Override
    public String greetPerson(String name, String language) throws SQLException {
        if(language == null || language.isEmpty()) {
            language = "Zulu";
        }

        language = Capitalize(language); name = Capitalize(name);

        if(findUser(name).isEmpty()) {
            addNewUser(name); // if name have not added, we add it
        } else {
            // updating existing name count by incrementing by 1.
            findAndUpdateUser(name);
        }
        return new GreetBuilder().greetPerson.greet(name, language);
    }

    @Override
    public Map<String, Integer> findAllUsers() {
        Map<String, Integer> users = new HashMap<>();
        try {
            CallableStatement findAll = connection.prepareCall("select * from users");
            ResultSet rs = findAll.executeQuery();
            while (rs.next()) {
                users.put(rs.getString("name"), rs.getInt("count"));
            }
        }catch (SQLException e) {
            System.out.println("Failed to find all users." + e);
        }
         return users;
    }


    @Override
    public void clearAllUsers() {
        try {
            CallableStatement clear = connection.prepareCall("delete from users");
            clear.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void clearUserByUsername(String userName) {
        try {
            CallableStatement clear = connection.prepareCall("delete from users where name = ?");
            clear.setString(1, userName);
            clear.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
