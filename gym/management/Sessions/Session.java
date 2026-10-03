package gym.management.Sessions;

import gym.Notifications.Publisher;
import gym.Notifications.Observer;
import gym.Utils.DateFormatter;
import gym.management.Instructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * The abstract Session class represents a gym session in the system.
 * Each session is defined by its type, date, forum type, instructor, price, maximum capacity,
 * and a list of participants (observers, this list is used also for share notifications to participants).
 * The class provides methods to manage participants and retrieve session details.
 */

public abstract class Session {
    private final SessionType sessionName;
    private final LocalDateTime FormattedDateTime;
    private final ForumType forumType;
    private final Instructor instructor;
    private final int sessionPrice;
    private final int sessionMaxCapacity;
    private final List<Observer> observers;//מגדירים ליסט מסוג observer כדי לחסוך יצירה כפולה של ליסט של צופים ושל לקוחות

    /**
     * Constructs a Session instance with the specified parameters.
     *
     * @param sessionName       The type of the session (e.g., Pilates, ThaiBoxing).
     * @param date              The date and time of the session in String format.
     * @param forumType         The type of forum where the session takes place.
     * @param instructor        The instructor leading the session.
     * @param sessionPrice      The price of the session.
     * @param sessionMaxCapacity The maximum number of participants allowed in the session.
     */
    protected Session(SessionType sessionName, String date, ForumType forumType, Instructor instructor, int sessionPrice, int sessionMaxCapacity) {
        this.sessionName = sessionName;
        this.FormattedDateTime = DateFormatter.parseDateTime(date);
        this.forumType = forumType;
        this.instructor = instructor;
        this.sessionPrice = sessionPrice;
        this.sessionMaxCapacity = sessionMaxCapacity;
        this.observers = new ArrayList<>();
    }

    /**
     * @return The name/type of the session.
     */
    public SessionType getSessionName() {
        return sessionName;
    }

    /**
     * @return The date and time of the session as a LocalDateTime object.
     */
    public LocalDateTime getSessionDateAndTime() {
        return FormattedDateTime;
    }

    /**
     * @return The date of the session as a LocalDate object.
     */
    public LocalDate getSessionDate() {
        return FormattedDateTime.toLocalDate();
    }

    /**
     * @return The type of forum where the session takes place.
     */
    public ForumType getForumType() {
        return forumType;
    }

    /**
     * @return The instructor leading the session.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * @return The price of the session.
     */
    public int getSessionPrice() {
        return sessionPrice;
    }

    /**
     * @return The maximum number of participants allowed in the session.
     */
    public int getSessionMaxCapacity() {
        return sessionMaxCapacity;
    }

    /**
     * @return The current number of participants in the session.
     */
    public int getNumberOfClients() {
        return observers.size();
    }

    /**
     * @return A list of observers (participants) in the session.
     */
    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * @return True if the session is scheduled for a future date, false otherwise.
     */
    public boolean isSessionInTheFuture() {
        return FormattedDateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Adds an observer (participant) to the session.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer (participant) from the session.
     *
     * @param observer The observer to be removed.
     */

    public void removeObserver(Observer observer) {
        observers.remove(observer);// במטלה עצמה אין דרישה אבל כעקרון צריך
    }

    /**
     * @return A string representation of the session, including its type, date, forum, instructor,
     * and the current number of participants.
     */
    @Override
    public String toString() {
        return "Session Type: " + sessionName + " | Date: " + DateFormatter.formatDateTime(FormattedDateTime) + " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() + " | Participants: " + getNumberOfClients() + "/" + sessionMaxCapacity;
    }
}
