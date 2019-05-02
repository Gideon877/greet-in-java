package greet;

import com.googlecode.lanterna.terminal.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    Map<String, Integer> map = new HashMap<>();
    @BeforeEach
    void setUp() {
        map.put("Thabang", 2);
        map.put("John", 2);
    }


    @AfterEach
    void cleanUp() {
        map.clear();
    }
    @Test
    void mapValues() throws IOException {
        System.out.println(map);
        System.out.println(map.containsKey("John"));
        System.out.println(getUser("John"));

//        Terminal terminal = new DefaultTerminalFactory().createTerminal();
//        sta

//        return terminal;
    }


    Map<String, Integer> getUser(String name) {
        Map<String, Integer> user = new HashMap<>();
        user.put(name, map.get(name));
        return user;

    }
}
