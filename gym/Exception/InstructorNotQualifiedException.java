package gym.Exception;

/**
 * the "InstructorNotQualifiedException" class is responsible for throwing necessary
 * exceptions corresponding to the exact error which is of course - When there's an attempt to register an instructor
 * to a specific session where he's not qualified for it.
 * It extends the Exception class which will help us throw the exact custom messages that is given in our Assignment.
 */
public class InstructorNotQualifiedException extends Exception{

    /** As mentioned above, we can provide the user with custom messages because we are extending the Exception class.
     * According to the Assigment output format, we created a fixed error message that states the problem.
     */
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type.");
    }
}
