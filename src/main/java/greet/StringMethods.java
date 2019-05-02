package greet;

import java.util.*;

import static greet.ConsoleColors.*;

public class StringMethods {
    /**
     * @param string
     * @return
     */
    public String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void Format(Map<String, Integer> users){
        String lines = "------------------------";
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
}
