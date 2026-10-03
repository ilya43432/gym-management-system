package gym.Notifications;

/**
 * here we are using the Observer pattern. we are using this interface
 * to notify the gym clients with updates that occur in the gym.
 * the notification in this assigment consist of 3 things:
 * 1. a global notification for all the clients in the gym.
 * 2. a global session related notification for all the clients attending this specific session.
 * 3. a session notification at a specific date.
 */
public interface Observer {

    /**
     * @param message a string that contains the message update.
     */
    void update(String message);

}