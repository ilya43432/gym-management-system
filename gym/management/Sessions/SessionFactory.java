package gym.management.Sessions;

import gym.management.Instructor;
/**
 * A factory class to create instances of different session types.
 * The factory uses predefined configuration values from SessionConfig for pricing and group size.
 */
public class SessionFactory {

    private SessionFactory() {}

    /**
     * Creates a session instance based on the given session type.
     *
     * @param sessionType The type of session to create.
     * @param date The date and time of the session as a string.
     * @param forumType The forum type for the session.
     * @param instructor The instructor leading the session.
     * @return A specific implementation of the Session class based on the session type.
     */
    public static Session CreateSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case ThaiBoxing -> {
                return new ThaiBoxing(sessionType, date, forumType, instructor,SessionConfig.THAI_BOXING_PRICE, SessionConfig.THAI_BOXING_GROUP_SIZE);
            }
            case Pilates -> {
                return new Pilates(sessionType, date, forumType, instructor, SessionConfig.PILATES_PRICE, SessionConfig.PILATES_GROUP_SIZE);
            }
            case MachinePilates -> {
                return new MachinePilates(sessionType, date, forumType, instructor, SessionConfig.MACHINE_PILATES_PRICE, SessionConfig.MACHINE_PILATES_GROUP_SIZE);
            }
            case Ninja -> {
                return new Ninja(sessionType, date, forumType, instructor, SessionConfig.NINJA_PRICE, SessionConfig.NINJA_GROUP_SIZE);
            }
            default -> throw new IllegalArgumentException("Invalid session type");
        }
    }
}
