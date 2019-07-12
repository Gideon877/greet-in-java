package greet;

import com.google.gson.Gson;
import com.google.gson.*;
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
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greet.handlebars"));
        });
        post("/greet", (req, res) -> {
            String[] data = req.body().split("=");
            String username = "";
            String msg = "Hello, World!";  //default greeting
            System.out.println(req.body());

            if(data.length > 1) {
                username = data[1];
                msg = db.greetPerson(username, "English");
            }

            System.out.println(msg);

            Map<String, String> model = new HashMap<>();
            model.put("message", msg);

            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greet.handlebars"));

        });

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
        get("/counter", (req, res) -> {
            System.out.println(req.attributes());
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
//        delete("/clear/:username", (req, res) -> "Hello World");
//        get("/exit", (req, res) -> "Hello World");
//        get("/help", (req, res) -> "Hello World");
    }

//    public static class Message {
//        Message
//    }
}


