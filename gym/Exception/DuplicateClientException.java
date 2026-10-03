package gym.Exception;

/**
 * the "DuplicateClientException" class is responsible for throwing necessary
 * exceptions corresponding to the exact error which is of course - When there's an attempt to register an
 * already registered client to the gym / already registered client to a Session.
 * It extends the Exception class which will help us throw the exact custom messages that are strict to
 * the format given in our Assignment.
 */
public class DuplicateClientException extends Exception{

    /** As mentioned above, we can provide the user with custom messages because we are extending the Exception class.
     * According to the Assigment output format, we created a fixed error message that states the problem.
     */
    public DuplicateClientException() {
        super("Error: The client is already registered");
    }
}
