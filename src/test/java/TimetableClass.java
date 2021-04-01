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
    private List<BusCompany> bus;
    private SimpleDateFormat formatDate;


    @BeforeEach
    public void setUp() throws ParseException {
        timetable = new Timetable();
        bus = new ArrayList<>();
        formatDate = new SimpleDateFormat("HH:mm");
        bus.add(new BusCompany("Posh", formatDate.parse("10:00"),
                formatDate.parse("10:30")));
    }

    @Test
    public void checkTheSameCompany() throws IOException, ParseException {
        timetable.readInput("TestTheSameTime.txt");
        timetable.sort();
        assertEquals(timetable.getBloggersville().getBuses(), bus);
    }

    @Test
    public void checkTheSameStartTime() throws ParseException, IOException {
        timetable.readInput("TestTheSameStartTime.txt");
        timetable.sort();
        assertEquals(timetable.getBloggersville().getBuses(), bus);
    }

    @Test
    public void checkTheSameFinishTime() throws ParseException, IOException {
        timetable.readInput("TestTheSameFinishTime.txt");
        timetable.sort();
        assertEquals(timetable.getBloggersville().getBuses(), bus);
    }

    @Test
    public void checkWritten() throws ParseException, IOException {
        timetable.readInput("Example_output.txt");
        bus = timetable.getBloggersville().getBuses();
        timetable.readInput("Example_input.txt");
        timetable.sort();
        assertEquals(timetable.getBloggersville().getBuses(), bus);
    }
}
