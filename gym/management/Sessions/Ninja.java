package gym.management.Sessions;

import gym.management.Instructor;

/** this class is a specific session in our gym project.
 * it extends the Session class meaning that it inherits all of its attributes,
 *  we use the constructor in this class to initialize the Ninja session in our gym.
 *  such attributes are: session name, session date, price of the session, forumType of the session,
 * Sessions instructor, Session Price, Session max capacity.
 */
public class Ninja extends Session{

    /**
     * @param sessionName the session name.
     * @param date the session date.
     * @param forumType the forumType of the session.
     * @param instructor the instructor for the session.
     * @param sessionPrice the session's price.
     * @param sessionMaxCapacity the session's max capacity.
     * the super is used here to call the parent's constructor so that all the attributes in the Session's constructor
     * class are initialized in the Ninja session type.
     */
    public Ninja(SessionType sessionName, String date, ForumType forumType, Instructor instructor, int sessionPrice, int sessionMaxCapacity) {
        super(sessionName, date, forumType, instructor, sessionPrice, sessionMaxCapacity);
    }
}
