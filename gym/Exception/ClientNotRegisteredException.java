package gym.Exception;

/**
 * the "ClientNotRegisteredException" class is responsible for throwing necessary
 * exceptions corresponding to the exact error which is of course - When a Client isn't registered to the gym.
 * It extends the Exception class which will help us throw the exact custom messages that are strict to
 * the format given in our Assignment.
 *
 */
public class ClientNotRegisteredException extends Exception {

    /**
     * @param message the message that states the error.
     *  this is a basic constructor that helps us create a custom message as mentioned above.
     */
    public ClientNotRegisteredException(String message) {
        super(message);
    }

}