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
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

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
            String data = req.body();

            JsonElement responseData = parser.parse(String.valueOf(res));
//            data = gson.toJson(data);
//            JsonObject convtObj = new Gson().fromJson(data, JsonObject.class);

            System.out.println(responseData);
            System.out.println(data);
            System.out.println(!responseData.isJsonObject());
            JsonObject obj = responseData.getAsJsonObject();

//            System.out.println(!obj.has("username"));
//            System.out.println(convtObj.get("username").getAsString());

            Map<String, String> model = new HashMap<>();
            model.put("message", data);

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
            Map<String, Integer> people = db.findAllUsers();
            Map<String, Integer> userFound = db.findUser(userName);

            model.put("user", userFound);
//            model.put("greeted", people);

            return new HandlebarsTemplateEngine()
                    .render(new ModelAndView(model, "greeted.handlebars"));

        });
        get("/counter", (req, res) -> "Hello World");

        get("/clear/:username", (req, res) -> {
            db.clearUserByUsername(req.params("username"));
            res.redirect("/greeted");
            return "";
        });

        delete("/clear", (req, res) -> "Hello World");
//        delete("/clear/:username", (req, res) -> "Hello World");
        get("/exit", (req, res) -> "Hello World");
        get("/help", (req, res) -> "Hello World");
    }

//    public static class Message {
//        Message
//    }
}


