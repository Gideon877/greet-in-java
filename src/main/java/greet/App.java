package greet;
import greet.database.PostgresDB;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;
import static spark.route.HttpMethod.post;

public class App {
    static Api api = new Api();

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

            PostgresDB db = new PostgresDB(connection);

            staticFiles.location("/public"); // Static files

            port(getHerokuAssignedPort());

            get("/", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                Map<String, Integer> counter = new HashMap<>();

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);
                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "home.hbs"));
            });

            get("/greet", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                List<Language> languageList = Arrays.asList(Language.values());
                Map<String, Integer> counter = new HashMap<>();

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);
                model.put("languages", languageList);
                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "greet.hbs"));
            });

            post("/greet", (req, res) -> {
                List<Language> languageList = Arrays.asList(Language.values());
                Map<String, Object> model = new HashMap<>();
                Map<String, Integer> counter = new HashMap<>();

                String name = req.queryParams("username");
                String language = req.queryParams("language");

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);
                model.put("message", db.greetPerson(name, language));
                model.put("languages", languageList);

                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "greet.hbs"));
            });

            get("/greeted", (req, res) -> {
                Map<String, Integer> people = db.findAllUsers();
                Map<String, Object> model = new HashMap<>();
                Map<String, Integer> counter = new HashMap<>();

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);

                if(people.size() > 0) {
                    model.put("greeted", people);
                }
                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "greeted.hbs"));
            });

            get("/greeted/:username", (req, res) -> {
                String userName = req.params("username");

                Map<String, Object> model = new HashMap<>();
                Map<String, Integer> userFound = db.findUser(userName);
                Map<String, Integer> counter = new HashMap<>();

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);

                model.put("user", userFound);
                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "greeted.hbs"));

            });
            get("/counter", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                Map<String, Integer> counter = new HashMap<>();

                counter.put("Number", db.usersCounter());
                model.put("counter", counter);

                return new HandlebarsTemplateEngine()
                        .render(new ModelAndView(model, "counter.hbs"));
            });

            get("/clear/:username", (req, res) -> {
                db.clearUserByUsername(req.params("username"));
                res.redirect("/greeted");
                return "";
            });

            get("/clear", (req, res) -> {
                try {
                    db.clearAllUsers();
                    res.redirect("/counter");
                } catch (Exception e) {
                    System.out.println(e);
                }
                return "";
            });

            get("/api/users", (req, res) -> api.getUsers(), new JsonTransformer());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
