package borisov_victor;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The class is designed to form an effective timetable.
 */
public class Timetable {
    private Bloggersville bloggersville;
    private final static String OUTPUT = "output.txt";
    private SimpleDateFormat formatDate;
    private ArrayList<BusCompany> busTimetable;

    public Timetable() {
        this.busTimetable = new ArrayList<>();
        formatDate = new SimpleDateFormat("HH:mm");
        bloggersville = new Bloggersville();
        this.setBloggersville(bloggersville);
    }

    public Bloggersville getBloggersville() {
        return bloggersville;
    }

    public void setBloggersville(Bloggersville bloggersville) {
        this.bloggersville = bloggersville;
    }

    /**
     * The method reads the source file. Moreover, the array is filled with the required values.
     * Adding an exit condition past midnight
     *
     * @throws ParseException           occurs due to the use SimpleDateFormat
     * @throws IllegalArgumentException occurs due to file use
     */
    public void readInput(String input) throws ParseException, IllegalArgumentException, IOException {
        try (BufferedReader buffReader =
                     new BufferedReader(new
                             FileReader(new File(input)))) {
            while (buffReader.ready()) {
                String readLine = buffReader.readLine();
                if (!readLine.equals("")) {
                    String[] split = readLine.split("\\s");
                    Date dateStart = formatDate.parse(split[1]);
                    Date dateFinish = formatDate.parse(split[2]);
                    if (dateFinish.after(formatDate.parse("00:00"))
                            && dateFinish.before(formatDate.parse("01:00"))) {
                        dateFinish = addDays(dateFinish);
                    }
                    bloggersville.getBuses().add(new BusCompany(split[0],
                            dateStart, dateFinish));
                }
            }
        }
    }

    public Date addDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * The method helps to sort the array in the appropriate order. Thus, three necessary conditions
     * for the effectiveness of the choice of the desired transport are fulfilled.
     * If the start time or finish time are the same, we choose transport with the least cost in time.
     * If both companies offer a service having the same departure and arrival times, it is preferable Posh
     * Bus Company
     * Method is based on another method {@link Date#compareTo(Date anotherDate)}
     * If this method gives "0" for start and finish time, company "Posh" will be selected
     * If method gives "0" either start or end time, bus with the shortest time will be selected.
     * If the time between start and end is more than an hour, this bus will be skipped
     * All unsuitable buses are added to the array, which will be deleted.
     *
     * @throws ParseException occurs due to the use SimpleDateFormat
     */
    public void sort() throws ParseException {
        bloggersville.getBuses().sort(new Comparator<BusCompany>() {
            @Override
            public int compare(BusCompany o1, BusCompany o2) {
                return o1.getFinishTime().compareTo(o2.getFinishTime());
            }
        });
        for (BusCompany o1 : bloggersville.getBuses()) {
            for (BusCompany o2 : bloggersville.getBuses()) {
                if (o1.equals(o2)) {
                    continue;
                }
                long differenceOne = Math.abs(o1.getFinishTime().getTime() - o1.getStartTime().getTime());
                long differenceTwo = Math.abs(o2.getFinishTime().getTime() - o2.getStartTime().getTime());
                if (o1.getStartTime().compareTo(o2.getStartTime())
                        == 0 && o1.getFinishTime().compareTo(o2.getFinishTime()) == 0) {
                    busTimetable.add((o1.getName().equals("Grotty")) ? o1 : o2);
                } else if (o1.getStartTime().equals(o2.getStartTime()) ||
                        o1.getFinishTime().equals(o2.getFinishTime())) {
                    if (differenceOne > differenceTwo) {
                        busTimetable.add(o1);
                    } else if (differenceOne < differenceTwo) {
                        busTimetable.add(o2);
                    }
                }
                Date oneHour = formatDate.parse("10:00"); //finding the time difference of one hour
                Date twoHour = formatDate.parse("11:00");
                long differenceHour = Math.abs(twoHour.getTime() - oneHour.getTime());
                if (differenceOne > differenceHour) {
                    busTimetable.add(o1);
                }
            }
        }
        bloggersville.getBuses().removeAll(busTimetable);
    }

    /**
     * The method implements sorting result writing to a file.
     *
     * @throws IOException occurs due to file use
     */
    public void writeOutput() throws IOException {
        ArrayList<BusCompany> grottyBus = new ArrayList<>();
        ArrayList<BusCompany> poshBus = new ArrayList<>();
        for (BusCompany bus : bloggersville.getBuses()) {
            if (bus.getName().equals("Grotty")) {
                grottyBus.add(bus);
            } else {
                poshBus.add(bus);
            }
        }
        Iterator<BusCompany> grottyIterator = grottyBus.iterator();
        Iterator<BusCompany> poshIterator = poshBus.iterator();
        try (BufferedWriter writer = new BufferedWriter(new
                FileWriter(OUTPUT))) {
            while (poshIterator.hasNext()) {
                writer.write(poshIterator.next().toString() + "\n");
            }
            writer.write("\n");
            while (grottyIterator.hasNext()) {
                writer.write(grottyIterator.next().toString() + "\n");
            }
        }
    }
}
