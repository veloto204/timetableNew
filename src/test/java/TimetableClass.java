import borisov_victor.Bloggersville;
import borisov_victor.BusCompany;
import borisov_victor.Timetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableClass {
    private Timetable timetable;
    private Bloggersville bloggersville;
    private List<BusCompany> bus;
    private SimpleDateFormat formatter;


    @BeforeEach
    public void setUp() throws ParseException {
        formatter = new SimpleDateFormat("HH:mm");
        timetable = new Timetable();
        bloggersville = new Bloggersville();
        timetable.setBloggersville(bloggersville);
        bus = new ArrayList<>();
        bloggersville.getBuses().add(new BusCompany("Posh", formatter.parse("10:10"),
                formatter.parse("11:00")));
    }

    @Test
    public void checkTheSameCompany() throws ParseException {
        bloggersville.getBuses().add(new BusCompany("Grotty", formatter.parse("10:10"),
                formatter.parse("11:00")));
        timetable.sort();
        bus.add(new BusCompany("Posh", formatter.parse("10:10"),
                formatter.parse("11:00")));
        assertEquals(bloggersville.getBuses(), bus);
    }

    @Test
    public void checkTheSameStartTime() throws ParseException {
        bloggersville.getBuses().add(new BusCompany("Grotty", formatter.parse("10:10"),
                formatter.parse("11:01")));
        timetable.sort();
        bus.add(new BusCompany("Posh", formatter.parse("10:10"),
                formatter.parse("11:00")));
        assertEquals(bloggersville.getBuses(), bus);
    }

    @Test
    public void checkTheSameFinishTime() throws ParseException {
        bloggersville.getBuses().add(new BusCompany("Grotty", formatter.parse("10:50"),
                formatter.parse("11:00")));
        timetable.sort();
        bus.add(new BusCompany("Grotty", formatter.parse("10:50"),
                formatter.parse("11:00")));
        assertEquals(bloggersville.getBuses(), bus);
    }

    @Test
    public void checkWritten() throws ParseException, IOException {
        bloggersville.getBuses().remove(0);
        bus.add(new BusCompany("Posh", formatter.parse("10:10"),
                formatter.parse("11:00")));
        bus.add(new BusCompany("Posh", formatter.parse("10:15"),
                formatter.parse("11:10")));
        bus.add(new BusCompany("Posh", formatter.parse("12:05"),
                formatter.parse("12:30")));
        bus.add(new BusCompany("Grotty", formatter.parse("12:45"),
                formatter.parse("13:25")));
        bus.add(new BusCompany("Posh", formatter.parse("17:25"),
                formatter.parse("18:01")));
        timetable.readInput();
        timetable.sort();
        timetable.writeOutput();
        assertEquals(bloggersville.getBuses(), bus);
    }
}
