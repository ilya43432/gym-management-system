package gym.customers;

import gym.Notifications.Observer;

import java.util.ArrayList;
import java.util.List;

/** The Client Class. this class simply represents the customer in our Gym system.
 * as we can see the Client extends the Person class, meaning that it inherits all
 * the Persons attributes, and it also implements the Observer interface, which
 * allows the client to receive updates/notifications and store them in a list.
 * in this case, the client acts as an observer and the gym is the subject that the
 * client is observing.
 */

public class Client extends Person implements Observer {
    private final List<String> updates = new ArrayList<>();

    /**
     * @param p The Client constructor receives A person as an argument. we use the super() key word
     * to call the copy constructor in the Person class which will copy the Persons attributes to
     * the Client. because at the end of the day the client is person, and so all of its attributes are
     * the same but the client will of course have extra behaviours.
     */
    public Client(Person p) {
        super(p);
    }

    /**
     * @param message A string message. we add it to the Clients update list.
     */
    @Override
    public void update(String message) {
        updates.add(message);
    }

    /**
     * @return returns all the updates the client had received so far.
     */
    public String getNotifications() {
        return updates.toString();
    }

    /**
     * @return returns the Client information, Since Client extends the Person class we can simply
     * use the super() keyword and reuse the Person's toString function.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

