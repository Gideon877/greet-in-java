package greet;

import java.util.*;

import static greet.Colors.*;

public class StringMethods {
    /**
     * @param string
     * @return
     * @author: Thabang Gideon Magaola
     * email: gideon877@live.com
     */

    StringBuilder builder = new StringBuilder();
    private final String lines = "------------------------------------------------\n";

    public String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void Format(Map<String, Integer> users){
        String lines = "------------------------------------------------";
        builder.append(lines);
        System.out.println(lines);

        if(users.size() == 0) {
            System.out.printf("%20s" ,"No users found");
            System.out.println();
        } else {
            System.out.printf("%s%10s : %2s%s", BLUE_BOLD_BRIGHT, "NAME", "COUNT", RESET);
            System.out.println();
            System.out.println(lines);

            for (Map.Entry user: users.entrySet()) {
                System.out.format("%s %10s %s | %s%2d%s", BLACK_BOLD, user.getKey(), RESET, PURPLE_BOLD_BRIGHT, user.getValue(), RESET);
                System.out.println();
            }
        }
        System.out.println(lines);
    }

    protected static void goodbye() {
        System.out.println("------------------------------------------------");
        System.out.printf("%s%15s%s\n", BLUE_BOLD_BRIGHT, "goodbye!", RESET);
        System.out.println("------------------------------------------------");
    }

    protected static String help() {
        String help, title, greet, greeted, greetedName, counter, clear, clearUsers, exit, langages;
        title = GREEN_BOLD + "Valid commands are:\n\n";
        greet = PURPLE_BOLD + "greet " + RESET + "followed by the " + YELLOW_BOLD + "name"+ RESET + " and the " + CYAN_BOLD + "language" + RESET + " the user is to be greeted in,\n";
        greeted = helpStringBuilder("greeted", "display a list of all users that has been greeted and how many time each user has been greeted,\n");
        greetedName = helpStringBuilderTwo("greeted", "followed by a","username", "returns how many times that username have been greeted,\n");
        clear = helpStringBuilder("clear", "deletes of all users greeted and the reset the greet counter to 0,\n");
        counter = helpStringBuilder("counter", "returns a count of how many unique users has been greeted,\n");
        clearUsers = helpStringBuilderTwo("clear", "followed by a","username","delete the greet counter for the specified user and decrement the greet counter by 1,\n");
        langages = helpStringBuilder("x", "shows all available languages\n");
        exit = helpStringBuilder("exit", "exits the application,\n");
        help = helpStringBuilder("help", "shows a user an overview of all possible commands.\n\n");

        return String.format("%s%s%s%s%s%s%s%s%s%s%s", title, RESET, greet, greeted, greetedName, counter, clear, clearUsers, langages, exit, help);
    }

    private static String helpStringBuilder(String command, String sentence) {
        return String.format("%s%s%s %s", PURPLE_BOLD, command, RESET, sentence);
    }

    private static String helpStringBuilderTwo(String command, String sentence, String commandTwo, String sentenceTwo) {
        return String.format("%s%s%s %s %s%s%s %s", PURPLE_BOLD, command, RESET, sentence, PURPLE_BOLD, commandTwo, RESET, sentenceTwo);
    }

    protected String invalid(String getCommand) {
        String appName = WHITE_BOLD + "greet-in-java:" + RESET;
        String command = RED_UNDERLINED + getCommand + RESET;
        return String.format("%s %s%s %s%s %s%s%s ", appName, command, RED, "command not found:", RESET, GREEN ,"\ntype help for all possible commands", RESET);
    }

    public String FormatLanguage(){
        StringBuilder sBuilder = new StringBuilder();
        List<Language> languageList = Arrays.asList(Language.values());
        sBuilder.append(lines);
        if(languageList.size() == 0) {
            sBuilder.append(String.format("%20s\n" ,"No language found"));
        } else {

            sBuilder.append(String.format("%s %2s %s\n", BLUE_BOLD_BRIGHT, "LANGUAGES", RESET));
            sBuilder.append(lines);
            for (Language language: languageList) {
                sBuilder.append(String.format("%s * %s %s%2s%s\n", BLACK_BOLD, RESET, PURPLE_BOLD_BRIGHT, language, RESET));
            }
        }
        sBuilder.append("------------------------------------------------");
        return sBuilder.toString();
    }

    public String counter(int count) {
        try {
            return String.format("Users greeted: %s%s%s", CYAN_BOLD , count, RESET);
        } catch (Exception e){
            return String.format("Users greeted: %s%s%s", CYAN_BOLD , 0, RESET);
        }
    }
}
