package greet;
import greet.database.PostgresDB;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import greet.greeter.GreetBuilder;
import greet.greeter.GreetPerson;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;
import static spark.route.HttpMethod.post;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if(processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    static Connection getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return DriverManager.getConnection(url, username, password);

        }

        return DriverManager.getConnection(defualtJdbcUrl);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getDatabaseConnection("jdbc:postgresql://localhost/greet_db");

            PostgresDB greeter = new PostgresDB(connection);
            GreetBuilder greet = new GreetBuilder();

            staticFiles.location("/public"); // Static files

            port(getHerokuAssignedPort());

            get("/", (req, res) -> {
                Map<String, Object> dataMap = new HashMap<>();
                List<Language> languageList = Arrays.asList(Language.values());
                dataMap.put("languages", languageList);
                dataMap.put("counter", Integer.toString(greeter.usersCounter()));

                return new ModelAndView(dataMap, "index.hbs");
            }, new HandlebarsTemplateEngine());

            post("/", (req, res) -> {

                // get form data values
                String name = req.queryParams("username");
                String language = req.queryParams("language");
                Map<String, Object> dataMap = new HashMap<>();
                List<Language> languageList = Arrays.asList(Language.values());
                dataMap.put("languages", languageList);

                if (language == null) {
                    dataMap.put("error", "Language not selected!");
                } else {
                    String greeting = greeter.greetUsers(name, language);
                    // put the values from the form for Handlebars to use
                    int count = greeter.usersCounter();
                    dataMap.put("counter", Integer.toString(count));
                    dataMap.put("greeting", greeting);
                }
                //
                return new ModelAndView(dataMap, "index.hbs");

            }, new HandlebarsTemplateEngine());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
