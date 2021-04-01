package borisov_victor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


/**
 *The ConsoleApplication class allows one to enter file path to sort.
 * The final file is located in project root folder with name "output.txt"
 */
public class ConsoleApplication {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        System.out.println("Specify a path for a file: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        try {
            timetable.readInput(filePath);
            timetable.sort();
            timetable.writeOutput();
        } catch (ParseException | IOException e) {
            logger.error(e);
        }
        System.out.println("Your file is available at the following path: project-maven location + output.txt");
    }
}
