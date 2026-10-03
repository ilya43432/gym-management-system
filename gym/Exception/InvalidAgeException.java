package gym.Exception;

/**
 * the "InvalidAgeException" class is responsible for throwing necessary
 * exceptions corresponding to the exact error which is of course - When there's an attempt to register a Person to the gym
 * that is under the age of 18.
 * It extends the Exception class which will help us throw the exact custom messages that is given in our Assignment.
 */
public class InvalidAgeException extends Exception {

    /** As mentioned above, we can provide the user with custom messages because we are extending the Exception class.
     * According to the Assigment output format, we created a fixed error message that states the problem.
     */
    public InvalidAgeException() {
        super("Error: Client must be at least 18 years old to register");
    }

}
