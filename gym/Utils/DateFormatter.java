package gym.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * A date formatter class. this would help us to format dates such as session dates, format the Birthdate of a person
 * to calculate the age. the reason is to not repeat ourselves with duplicate calculations.
 */

public class DateFormatter {

    /**
     * this represents the formats specified in this Assignment.
     * 1. a regular date format which is "dd-MM-yyyy".
     * 2. a date time format which is "dd-MM-yyyy HH:mm".
     * 3. a reversed date format which is "yyyy-MM-dd".
     */
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String REVERSE_DATE_FORMAT = "yyyy-MM-dd";


    /** here we are using the DateTimeFormatter and for each String date format we
     * create the pattern required in the Assigment.
     */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final DateTimeFormatter reverseDateFormatter = DateTimeFormatter.ofPattern(REVERSE_DATE_FORMAT);


    /**
     * @param dateTime a LocalDateTime argument which will be formatted.
     * @return we parse the LocalDateTime into a LocalDate format using our static calculated DateTimeFormats
     * and return it as a String.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    /**
     * @param dateString a String that contains a date.
     * @return we parse the String into a LocalDate format using our static calculated DateTimeFormats
     * and return it as a LocalDate.
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }

    /**
     * @param dateTimeString a string that contains a date and a time.
     * @return we parse the String into a LocalDateTime format using our static calculated DateTimeFormats
     * and return it as a LocalDateTime.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
    }

    /**
     * @param date a LocalDate that contains a date.
     * @return we parse the LocalDate into a reversed format and return it as a string.
     */
    public static String formatReverseDate(LocalDate date) {
        return date.format(reverseDateFormatter);
    }
}