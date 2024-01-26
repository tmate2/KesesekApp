package tm.kesesek;

import junit.framework.TestCase;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TravelsStackTest extends TestCase {

    @Test
    public void testTestToString() {
        TravelsStack travelsStack = new TravelsStack();
        assertTrue("Még nem vettél fel utazást".equals(travelsStack.toString()));
    }

    @Test
    public void testRemoveLastTravel() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.removeLastTravel();
        assertEquals(1,ts.getNumberOfTravels());
    }

    @Test
    public void testRemoveLastTravel2() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.removeLastTravel();
        ts.removeLastTravel();
        ts.removeLastTravel();
        assertEquals(0,ts.getNumberOfTravels());
    }

    @Test
    public void testAddNewTravel() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.removeLastTravel();
        ts.removeLastTravel();
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,23), LocalDate.of(2024,2,6),false, "gggg"));
        assertEquals(1,ts.getNumberOfTravels());
    }

    @Test
    public void testArrivalDifferences() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,23), LocalDate.of(2024,2,6),false, "gggg"));
        assertEquals(ts.getArrivalDifferences(), ts.getOnlyLateMin());
    }

    @Test
    public void testArrivalDifferences2() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,22), LocalDate.of(2024,2,6),false, "gggg"));
        assertEquals(ts.getArrivalDifferences(), ts.getOnlyLateMin() - 1);
    }

    @Test
    public void testArrivalDifferences3() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        assertEquals(53, ts.getArrivalDifferences());
    }

    @Test
    public void testArrivalDifferences4() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.removeLastTravel();
        assertEquals(30, ts.getArrivalDifferences());
    }

    @Test
    public void testGetOnlyLateTotal() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,22), LocalDate.of(2024,2,6),false, "gg"));
        assertEquals(2, ts.getOnlyLateTotal());
    }

    @Test
    public void testGreatestLateWithDate() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,22), LocalDate.of(2024,2,6),false, "gg"));
        ts.addNewTravel(new Travel(LocalTime.of(12,0), LocalTime.of(13,15), LocalDate.of(2024,1,23), false, "max"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), false, "asd"));
        assertEquals(75, ts.getGreatestLate());
        assertEquals(LocalDate.of(2024,1,23).toString().replace("-", "."), ts.getGreatestLateDate());
    }

    @Test
    public void testGreatestLateWithDateAfterRemove() {
        TravelsStack ts = new TravelsStack();
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), true, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(8,30), LocalTime.of(9,0), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(11,23), LocalTime.of(11,22), LocalDate.of(2024,2,6),false, "gg"));
        ts.addNewTravel(new Travel(LocalTime.of(23,51), LocalTime.of(0,14), LocalDate.of(2022,1,2), false, "asd"));
        ts.addNewTravel(new Travel(LocalTime.of(12,0), LocalTime.of(13,15), LocalDate.of(2024,1,23), false, "max"));
        ts.removeLastTravel();
        assertEquals(30, ts.getGreatestLate());
        assertEquals(LocalDate.of(2022,1,2).toString().replace("-", "."), ts.getGreatestLateDate());
    }
}