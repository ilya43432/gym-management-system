package gym.Notifications;

import java.util.List;

/**
 * a publisher interface, the subject for the observers in the Observer design pattern.
 * this interface will help us to notify the gym clients with updates.
 * the notification in this assigment consist of 3 things:
 * 1. a global notification for all the clients in the gym.
 * 2. a global session related notification for all the clients attending this specific session.
 * 3. a session notification at a specific date.
 * we wanted to add 2 methods:  1. to add an observer 2. remove an observer just for clarity.
 * but we already have a field of observers list in the session class, so it's redundant.
 * public void addObserver(Observer o);
 * public void removeObserver(Observer o);
 * but according to the assigment rules, when a client is unregistered from the gym, he is not unregistered from his
 * Sessions.
 * overall this adheres to the Observer Design pattern we have learned in class.
 */
public interface Publisher {

    /**
     * @param observers the list of observers. in this case these can participants at specifics sessions.
     * @param message   the message that is sent to the observers.
     */
    void notifyObserver(List<Observer> observers, String message);

}
