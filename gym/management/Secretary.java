package gym.management;

import gym.Exception.*;
import gym.Utils.DateFormatter;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Secretary who manages all the administrative tasks in the gym.
 */
public class Secretary extends Person implements GymWorkers {
    private Gym gym = Gym.getInstance();
    private int Salary;

    /**
     * Constructor for creating a Secretary.
     *
     * @param p      Person object containing details of the secretary.
     * @param salary Monthly salary of the secretary.
     */
    public Secretary(Person p, int salary) {
        super(p);
        this.Salary = salary;
    }

    /**
     * Sets the gym instance where the secretary works.
     * Will be Null when the current secretary will be replaced
     *
     * @param gymToSet Gym instance to be set.
     */
    void setGym(Gym gymToSet) {
        gym = gymToSet;
    }

    /**
     * Registers a new client to the gym if it complies with the conditions of the gym.
     *
     * @param p Person object representing the client to be registered.
     * @return Registered Client object.
     * @throws InvalidAgeException      if the client's age is below the minimum requirement.
     * @throws DuplicateClientException if the client is already registered.
     */
    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {

        if (p.getAge() < 18) {
            throw new InvalidAgeException();
        }

        for (int i = 0; i < gym.getClients().size(); i++) {
            Client client = gym.getClients().get(i);
            if (client.getID() == p.getID()) {
                throw new DuplicateClientException();
            }
        }

        Client client = new Client(p);
        gym.addClient(client);
        return client;
    }

    /**
     * Unregisters a client from the gym.
     *
     * @param c Client to be unregistered.
     * @throws ClientNotRegisteredException if the client is not registered in the gym.
     */
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(c)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        gym.removeClient(c);

    }

    /**
     * Hires a new instructor for the gym .
     *
     * @param person       Person object representing the instructor.
     * @param salary       Hourly salary of the instructor.
     * @param sessionTypes List of session types the instructor is qualified to teach.
     * @return Hired Instructor object.
     */
    public Instructor hireInstructor(Person person, int salary, List<SessionType> sessionTypes) {

        Instructor instructor = new Instructor(person, salary, sessionTypes);
        gym.addWorker(instructor);
        gym.addAction("Hired new instructor: " + instructor.getName() + " with salary per hour: " + instructor.getHourlySalary());
        return instructor;
    }

    /**
     * Adds a new session to the gym's schedule.
     *
     * @param sessionType Type of the session.
     * @param date        Date and time of the session.
     * @param forumType   Forum type of the session.
     * @param instructor  Instructor for the session.
     * @return Created Session object.
     * @throws InstructorNotQualifiedException if the instructor is not qualified to teach the session type.
     */
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {

        if (!instructor.getSessionList().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        Session session = SessionFactory.CreateSession(sessionType, date, forumType, instructor);
        LocalDateTime formattedDate = DateFormatter.parseDateTime(date);
        gym.addSessions(session);
        instructor.incrementTotalSessions();
        gym.addAction("Created new session: " + sessionType + " on " + formattedDate + " with instructor: " + instructor.getName());

        return session;
    }
    /**
     * Registers a client to a lesson if it complies with the conditions of the gym..
     *
     * @param client  Client to be registered.
     * @param session Session to register the client for.
     * @throws DuplicateClientException     if the client is already registered for the session.
     * @throws ClientNotRegisteredException if the client is not registered with the gym.
     */
    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        boolean isValidToRegister = true;

        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        if (session.getObservers().contains(client)) {
            System.out.println("Error: The client is already registered for this lesson");
            return;
        }

        if (!session.isSessionInTheFuture()) {
            gym.addAction("Failed registration: Session is not in the future");
            isValidToRegister = false;
        }

        if (!validateForumType(session.getForumType(), client)) {
            isValidToRegister = false;
        }

        if (session.getObservers().size() >= session.getSessionMaxCapacity()) {
            gym.addAction("Failed registration: No available spots for session");
            isValidToRegister = false;
        }

        if (client.getBalance() < session.getSessionPrice()) {
            gym.addAction("Failed registration: Client doesn't have enough balance");
            isValidToRegister = false;
        }

        if (isValidToRegister) {
            client.DecBalance(session.getSessionPrice());
            session.addObserver(client);
            gym.AddGymBalance(session);
            gym.addAction("Registered client: " + client.getName() + " to session: " + session.getSessionName() + " on " + session.getSessionDateAndTime() + " for price: " + session.getSessionPrice());
        }
    }

    /**
     * Validates if the client meets the forum requirements for a session.
     *
     * @param forum  Forum type of the session.
     * @param client Client to validate.
     * @return True if the client meets the requirements, false otherwise.
     */
    private boolean validateForumType(ForumType forum, Client client) {
        switch (forum) {
            case Seniors:
                if (client.getAge() < 65) {
                    gym.addAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                    return false;
                }
            case Male:
                if (!(client.getGender().equals(Gender.Male))) {
                    gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
                    return false;
                }
            case Female:
                if (!(client.getGender().equals(Gender.Female))) {
                    gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
                    return false;
                }
            default: // default case is forumType - All.
                break;
        }
        return true;
    }

    /**
     * Notifies all clients/Observers registered for a specific session with a message.
     *
     * @param session Session for which clients should be notified.
     * @param message Message to be sent to the clients.
     */
    public void notify(Session session, String message) {
        gym.notifyBySession(session, message);
    }

    /**
     * Notifies all clients/Observers registered for sessions on a specific date.
     *
     * @param date    Date of the sessions (in the format "yyyy-MM-dd").
     * @param message Message to be sent to the clients.
     */
    public void notify(String date, String message) {
        gym.notifyByDate(date, message);
    }

    /**
     * Notifies all clients in the gym with a message.
     *
     * @param message Message to be sent to all clients.
     */
    public void notify(String message) {
        gym.notifyAllClients(message);
    }

    /**
     * Prints the action history of the gym.
     */
    public void printActions() {
        gym.printActionHistory();
    }

    /**
     * Pays the salaries of all gym workers and updates the gym's balance accordingly.
     */
    public void paySalaries() {
        int totalAmount = 0;

        for (GymWorkers worker : gym.getWorkers()) {
            totalAmount += worker.ReceiveSalary();
        }
        gym.WorkerPayments(totalAmount);
        gym.addAction("Salaries have been paid to all employees");
    }

    /**
     * Returns the role of the secretary.
     *
     * @return "Secretary" as the role.
     */
    @Override
    public String WorkerRole() {
        return "Secretary";
    }

    /**
     * Calculate and receive the secretary's salary and updates the balance.
     *
     * @return The amount of salary paid to the secretary.
     */
    @Override
    public int ReceiveSalary() {
        AddBalance(Salary);
        return Salary;
    }

    /**
     * Returns a string representation of the Secretary, including their role and salary.
     *
     * @return A string with details about the Secretary.
     */
    @Override
    public String toString() {
        return super.toString() + " | Role: " + WorkerRole() + " | Salary per Month: " + Salary;
    }
}
