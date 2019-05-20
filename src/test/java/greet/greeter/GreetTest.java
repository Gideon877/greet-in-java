package greet.greeter;

import greet.GreetPeople;
import greet.Language;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.*;
import java.util.List;

import static org.mockito.Mockito.*;

class GreetTest {
//    @Test
//    public void whenAddCalledRealMethodCalled() {
//        MyList myList = mock(MyList.class);
//        doCallRealMethod().when(myList).add(any(Integer.class), any(String.class));
//        myList.add(1, "real");
//
//        verify(myList, times(1)).add(1, "real");
//    }

    @Test
    void spies() {
        String language = "English";
        try{
            System.out.println(Language.valueOf(language).getExpression() + ", Thabang");
        } catch (Exception e) {
            System.out.println(Language.valueOf("Zulu").getExpression() + ", Thabang");
        }

    }


}
