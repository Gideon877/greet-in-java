package greet.database;

import greet.Language;
import greet.greeter.GreetBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PostgresDB {
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
        try {
            CallableStatement getCount = connection.prepareCall("select count(*) as count from users");
            ResultSet rs = getCount.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

     static String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
