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

    public String Capitalize(String string) {
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

}
