package greet.greet;

import java.util.*;

public class StringMethods {

    public String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void Format(Map<String, Integer> users){
        String lines = "------------------------";
        System.out.println(lines);
        System.out.printf("%10s : %2s", "Name", "Count");
        System.out.println();
        System.out.println(lines);

        for (Map.Entry user: users.entrySet()) {
            System.out.format("%10s | %2d", user.getKey(), user.getValue());
            System.out.println();
        }
        System.out.println(lines);
    }
}
