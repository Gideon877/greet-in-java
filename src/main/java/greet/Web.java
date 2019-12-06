package greet;

import greet.counter.Counter;
import greet.greeter.WebGreeter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Web {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Counter db = new Counter();
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> counter = new HashMap<>();

            counter.put("Number", db.usersCounter());
            model.put("counter", counter);
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "home.handlebars"));
        });

        get("/greet", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Language> languageList = Arrays.asList(Language.values());
            Map<String, Integer> counter = new HashMap<>();

            counter.put("Number", db.usersCounter());
            model.put("counter", counter);
            model.put("languages", languageList);
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greet.handlebars"));
        });

        post("/greet", (req, res) -> {
            WebGreeter command = new WebGreeter(req.body());
            List<Language> languageList = Arrays.asList(Language.values());

            String msg = "Hello, World!";  //default greeting
            System.out.println(req.body());

            if(command.isValid()) {
                msg = db.greetPerson(command.getName(), command.getLanguage());
            }

            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> counter = new HashMap<>();

            counter.put("Number", db.usersCounter());
            model.put("counter", counter);
            model.put("message", msg);
            model.put("languages", languageList);

            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greet.handlebars"));
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
                    .render(new ModelAndView(model, "greeted.handlebars"));
        });

        get("/greeted/:username", (req, res) -> {
            String userName = req.params("username");
            System.out.printf("username: %s", userName);
            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> userFound = db.findUser(userName);
            Map<String, Integer> counter = new HashMap<>();

            counter.put("Number", db.usersCounter());
            model.put("counter", counter);

            model.put("user", userFound);
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greeted.handlebars"));

        });
        get("/counter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> counter = new HashMap<>();

            counter.put("Number", db.usersCounter());
            model.put("counter", counter);

            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "counter.handlebars"));
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
    }
}


