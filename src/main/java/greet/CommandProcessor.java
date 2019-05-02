package greet;

import greet.counter.Counter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static greet.ConsoleColors.*;

public class CommandProcessor {
    private final CommandExtractor commandExtractor;
//    Counter db;
    StringMethods stringMethods;
    GreetPeople db;

    public CommandProcessor(CommandExtractor commandExtractor) throws SQLException, ClassNotFoundException {
        this.commandExtractor = commandExtractor;
//        db = new Counter();
        stringMethods = new StringMethods();
        db = new GreetPeople();
    }

    public String menu() throws Exception {
        if (getCommand().equalsIgnoreCase("greet")) {
            return greet();
        } else if(getCommand().equalsIgnoreCase("greeted")) {
            return greeted();
        } else if(getCommand().equalsIgnoreCase("clear")) {
            clear();
            return "";
        } else if (getCommand().equalsIgnoreCase("counter")) {
            counter();
            return "";
        } else if(getCommand().equalsIgnoreCase("help")) {
            help();
            return "";
        } else {
            return invalid();
        }
    }

    private String greeted() throws Exception {
        System.out.println(getName().isEmpty());
        if(getName().isEmpty()) {
            stringMethods.Format(db.findAllUsers());
            return "";
        } else  {
            int counter = 0;

            if(!db.findUser(getName()).isEmpty()) {
                counter = db.findUser(getName()).get(getName());
            }
            return String.format("%s%s%s have been greeted %s%s%s time(s)!",BLUE_BOLD , getName(), RESET, CYAN_BOLD ,counter, RESET);
        }
    }

    public void counter() throws Exception {
        try {
            System.out.println("--------------------------");
            System.out.printf("%20s %s%s%s", "Users greeted:", CYAN_BOLD , db.usersCounter(), RESET);
            System.out.println();
            System.out.println("--------------------------");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String greet() throws Exception {
        try {
            return db.greetPerson(getName(), getLanguage()); //Language.valueOf(language).getExpression() + ", " + userName;
        } catch (Exception e) {
            return invalid();
        }
    }

    private void clear() throws Exception {
        try {
            if(getName().isEmpty()) {
                db.clearAllUsers();
            } else {
                db.clearUserByUsername(getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void goodbye() {
        System.out.println("--------------------");
        System.out.printf("%s%15s%s\n", BLUE_BOLD_BRIGHT, "goodbye!", RESET);
        System.out.println("--------------------");
    }

    private static void help() {
        String help, title, greet, greeted, greetedName, counter, clear, clearUsers, exit;

        title = GREEN_BOLD + "Valid commands are:\n\n";
        greet = PURPLE_BOLD + "greet " + RESET + "followed by the " + YELLOW_BOLD + "name"+ RESET + " and the " + CYAN_BOLD + "language" + RESET + " the user is to be greeted in,\n";
        greeted = helpStringBuilder("greeted", "display a list of all users that has been greeted and how many time each user has been greeted,\n");
        greetedName = helpStringBuilderTwo("greeted", "followed by a","username", "returns how many times that username have been greeted,\n");
        clear = helpStringBuilder("clear", "deletes of all users greeted and the reset the greet counter to 0,\n");
        counter = helpStringBuilder("counter", "returns a count of how many unique users has been greeted,\n");
        clearUsers = helpStringBuilderTwo("clear", "followed by a","username","delete the greet counter for the specified user and decrement the greet counter by 1,\n");
        exit = helpStringBuilder("exit", "exits the application,\n");
        help = helpStringBuilder("help", "shows a user an overview of all possible commands.\n\n");

        System.out.printf("%s%s%s%s%s%s%s%s%s%s", title, RESET, greet, greeted, greetedName, counter, clear, clearUsers, exit, help);
    }

    private static String helpStringBuilder(String command, String sentence) {
        return String.format("%s%s%s %s", PURPLE_BOLD, command, RESET, sentence);
    }

    private static String helpStringBuilderTwo(String command, String sentence, String commandTwo, String sentenceTwo) {
        return String.format("%s%s%s %s %s%s%s %s", PURPLE_BOLD, command, RESET, sentence, PURPLE_BOLD, commandTwo, RESET, sentenceTwo);
    }

    private String invalid() {
        String appName = WHITE_BOLD + "greet-in-java:" + RESET;
        String command = RED_UNDERLINED + getCommand() + RESET;
        return String.format("%s %s%s %s%s %s%s%s ", appName, command, RED, "command not found:", RESET, GREEN ,"\ntype help for all possible commands", RESET);
    }

    public String getCommand() {
        return this.commandExtractor.getCommand();
    }

    public String getName() {
        return this.commandExtractor.getName();
    }

    public String getLanguage() {
        return this.commandExtractor.getLanguage();
    }
}
