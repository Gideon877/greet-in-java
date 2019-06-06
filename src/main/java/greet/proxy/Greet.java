package greet.proxy;

import java.util.HashMap;
import java.util.Map;

public class Greet implements GreetInterface {
    Map<String, Integer> map = new HashMap<>();
    @Override
    public void greetPerson(String name) {
        System.out.println("Hello, " + name);
    }

    @Override
    public void addToMap(String name) {
        if(map.containsKey(name)) {
            map.put(name, map.get(name) + 1);
        } else {
            map.put(name, 1);
        }
        System.out.println(getMap() + " addToMap");
    }

    public Map<String, Integer> getMap() {
        return map;
    }
}
