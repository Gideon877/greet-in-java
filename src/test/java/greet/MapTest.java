//package greet;
//
//import com.googlecode.lanterna.terminal.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MapTest {
//    Map<String, Integer> map = new HashMap<>();
//    ArrayList<String> list = new ArrayList<>();
//    @BeforeEach
//    void setUp() {
//        map.put("Thabang", 2);
//        map.put("John", 2);
//        map.put("Jan", 2);
//        map.put("Dan", 2);
//
//        list.add("Anna");
//        list.add("Johan");
//        list.add("Devon");
//        list.add("Xhanti");
//
//    }
//
//
//    @AfterEach
//    void cleanUp() {
//        map.clear();
//    }
//    @Disabled
//    @Test
//    void mapValues() throws IOException, InterruptedException {
//        System.out.println(map);
//        System.out.println(map.containsKey("John"));
//
//        list.stream().sorted().forEach(System.out::println);
//
////        System.out.println(list);
//
////        System.out.println(getUser("John"));
//
////        Terminal terminal = new DefaultTerminalFactory().createTerminal();
////        sta
//
////        return terminal;
//    }
//
//
//    Map<String, Integer> getUser(String name) {
//        Map<String, Integer> user = new HashMap<>();
//        user.put(name, map.get(name));
//        return user;
//
//    }
//}
