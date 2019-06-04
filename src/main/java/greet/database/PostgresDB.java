package greet.database;

import java.sql.*;

public class PostgresDB {

    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/greet_db ";
    Connection connection;


    String FIND_ALL_USERS_SQL = "select * from users";

    PreparedStatement findAllUsersPreparedStatement;

    public PostgresDB() {
        try{
//            Class.forName("org.postgres.Driver");
            connection = DriverManager.getConnection(POSTGRES_URL, "gideon877","root");
            findAllUsersPreparedStatement = connection.prepareStatement(FIND_ALL_USERS_SQL);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int usersCounter() {
        try {
            ResultSet rs = findAllUsersPreparedStatement.executeQuery();
//            return rs.getInt("count");
            while (rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getInt("count"));
            }

            return 0;

        } catch (SQLException e) {
            return 0;
        }
    }
}
