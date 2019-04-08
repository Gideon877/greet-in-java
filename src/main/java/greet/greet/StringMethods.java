package greet.greet;

import java.util.*;

public class StringMethods {
    ConsoleColors consoleColors = new ConsoleColors();

    public String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void Format(Map<String, Integer> users){
        String lines = "------------------------";
        System.out.println(lines);
        System.out.printf("%s%10s : %2s%s",consoleColors.BLUE_BOLD_BRIGHT, "NAME", "COUNT", consoleColors.RESET);
        System.out.println();
        System.out.println(lines);

        for (Map.Entry user: users.entrySet()) {
            System.out.format("%s %10s %s | %s%2d%s",consoleColors.BLACK_BOLD, user.getKey(),consoleColors.RESET ,consoleColors.PURPLE_BOLD_BRIGHT, user.getValue(), consoleColors.RESET);
            System.out.println();
        }
        System.out.println(lines);
    }
}
