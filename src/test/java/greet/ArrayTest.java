package greet;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {

    @Test
    void dateFormat() {
        Date date = new Date();

        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");


//        System.out.println(simpleDateFormat.format(date));

//        assertEquals(simpleDateFormat.format(date),"Monday, 18 November 2019");
    }

    @Test
    void getValueFrom3d () {
        int[][][] arrays = new int[2][2][2];

        int val = 0;

        for (int i = 0; i < arrays.length ; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                for (int k = 0; k < arrays[j].length; k++) {
                    val += 1;
                    arrays[i][j][k] = val;
                }

            }
        }

        System.out.printf("length: %s\n", arrays.length);
        System.out.printf("value: %s", arrays[0][0][0]);
    }

    @Test
    void getValueFrom2d() {
        String[][] arrays = new String[2][3];

        arrays[0][0] = "a";
        arrays[0][1] = "b";
        arrays[0][2] = "c";

        arrays[1][0] = "d";
        arrays[1][1] = "e";
        arrays[1][2] = "f";


        for (int row = 0; row< arrays.length; row++) {

            for (int col = 0; col < arrays[row].length; col++) {
                if (arrays[row][col].equals("d")) {
                    System.out.println(arrays[row][col]);
                }
            }
        }

    }
}
