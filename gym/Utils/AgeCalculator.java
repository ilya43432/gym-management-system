package gym.Utils;

import java.time.LocalDate;
import java.time.Period;

/** this class calculates the age of a given Person. we use the DateFormatter class to firstly parse the given Birthdate string, to
 * a localDate object.
 * next we use the method Period.between(),which calculates the exact age based on year,month,day.
 * the period will be of course between the formatted localDate birthDate of the person and the
 * Current date(by that we mean the localDate of now as you're reading this).
 */
public class AgeCalculator {
    /**
     * @param birthDate A string that represents the person's birthdate.
     * @return returns the age of the person(in years).
     * as mentioned above, we calculate by precision by using the method period.between()
     * this will ensure that we calculate the age precisely and stick to the rules given in
     * the Assigment.
     */
    public static int Age(String birthDate) {
        LocalDate birthDateLocal = DateFormatter.parseDate(birthDate);
        return Period.between(birthDateLocal, LocalDate.now()).getYears();
    }
}