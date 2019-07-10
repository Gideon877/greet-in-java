package greet;

import greet.counter.Counter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Web {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Counter db = new Counter();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "home.handlebars"));
        });

        get("/greet", (req, res) -> {
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(new HashMap<>(), "greet.handlebars"));
        });
        post("/greet", (req, res) -> "Hello World");

        get("/greeted", (req, res) -> {
            Map<String, Integer> people = db.findAllUsers();
            Map<String, Object> model = new HashMap<>();
            model.put("greeted", people);
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greeted.handlebars"));
        });

        get("/greeted/:username", (req, res) -> {
            String userName = req.params("username");
            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> userFound = db.findUser(userName);

            model.put("user", userFound);

            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greeted.handlebars"));

        });
        get("/counter", (req, res) -> "Hello World");
        delete("/clear", (req, res) -> "Hello World");
        delete("/clear/:username", (req, res) -> "Hello World");
        get("/exit", (req, res) -> "Hello World");
        get("/help", (req, res) -> "Hello World");
    }
}


