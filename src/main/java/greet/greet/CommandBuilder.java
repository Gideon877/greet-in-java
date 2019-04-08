package greet.greet;

import java.util.Scanner;

import static greet.greet.ConsoleColors.*;

public class CommandBuilder {

    public void menu() throws Exception {
        Scanner input =  new Scanner(System.in);

        boolean exitNow = false;

        while(!exitNow) {
            System.out.print("Please enter your command:");
            String commandInput = input.nextLine();
            if (commandInput.equals("exit")){
                exitNow = true;
                goodbye();
                return;
            }

            String[] commandParts = commandInput.split(" ");
            String command = commandParts[0];

            if (command.equalsIgnoreCase("greet")) {
                greet(commandInput);
            } else if(command.equalsIgnoreCase("greeted")) {
                greeted(commandInput);
            } else if(command.equalsIgnoreCase("clear")) {
                clear(commandInput);
            } else if (command.equalsIgnoreCase("counter")) {
                counter();
            } else if(command.equalsIgnoreCase("help")) {
                help();
            } else {
                invalid();
            }
        }
    }

    private static void greeted(String commandInput) throws Exception {
        StringMethods stringMethods = new StringMethods();
        GreetPeople greet = new GreetPeople();

        String[] commandParts = commandInput.split(" ");
        String countMessage = "";

        if(commandParts.length == 1) {
            stringMethods.Format(greet.getUsersCount());
        } else if(!commandParts[1].isEmpty()) {
            String name = stringMethods.Capitalize(commandParts[1]);
            countMessage = String.format("%s%s%s have been greeted %s%s%s time(s)!",BLUE_BOLD , name, RESET, CYAN_BOLD ,greet.getGreetCounter(name), RESET);
            System.out.println(countMessage);
        }
    }

    private static void counter() throws Exception {
        GreetPeople greet = new GreetPeople();
        try {
            String countMessage;
            countMessage =  String.format("Users greeted: %s%s%s", CYAN_BOLD ,greet.counter(), RESET);
            System.out.println("--------------------");
            System.out.printf("%18s", countMessage);
            System.out.println();
            System.out.println("--------------------");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void greet(String commandInput) throws Exception {
        String[] commandParts = commandInput.split(" ");
        String message = "", userName = "", language ="";
        GreetPeople greet = new GreetPeople();

        try {
            if(commandParts.length == 2) {
                userName = commandParts[1];
                language = null;
            } else if(!commandParts[2].isEmpty()) {
                userName = commandParts[1];
                language = commandParts[2];
            }
            message = greet.greetPerson(userName, language);
            System.out.println(message);
        } catch (Exception e) {
        }
    }

    private static void clear(String commandInput) throws Exception {
        String[] commandParts = commandInput.split(" ");
        GreetPeople greet = new GreetPeople();
        try {
            if(commandParts.length == 2) {
                String userName = commandParts[1];
                greet.clearUser(userName);
            } else if(commandParts.length == 1) {
                greet.clearAllUsers();
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

    public static void help() {
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

    public static String helpStringBuilder(String command, String sentence) {
        return String.format("%s%s%s %s", PURPLE_BOLD, command, RESET, sentence);
    }

    public static String helpStringBuilderTwo(String command, String sentence, String commandTwo, String sentenceTwo) {
        return String.format("%s%s%s %s %s%s%s %s", PURPLE_BOLD, command, RESET, sentence, PURPLE_BOLD, commandTwo, RESET, sentenceTwo);
    }

    public static void invalid() {
        System.out.println("Invalid command! Type help for all possible commands.\n");
    }

}
