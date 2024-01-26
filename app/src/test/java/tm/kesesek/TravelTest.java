package tm.kesesek;

import junit.framework.TestCase;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;


public class TravelTest extends TestCase {



    @Test
    public void testCalculator1(){
        Travel t = new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd");
        assertEquals(30, t.lateCalculator(t.isNextDay()));
        System.out.println(t);
    }

    @Test
    public void testCalculator2(){
        Travel t = new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd");
        assertEquals(23, t.lateCalculator(t.isNextDay()));
        System.out.println(t);
    }

}