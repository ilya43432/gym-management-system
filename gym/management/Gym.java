package gym.management;

import gym.Notifications.Observer;
import gym.Notifications.Publisher;
import gym.Utils.DateFormatter;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class representing a Gym. It manages clients, workers, sessions informations
 * and notifications, while keeping track of gym actions and balance.
 */
public class Gym implements Publisher {
    private static final Gym gymInstance = new Gym();
    private Secretary secretary;
    private String name;
    private final List<String> actionHistory;
    private final List<GymWorkers> Workers;
    private final List<Client> Clients;
    private final List<Session> Sessions;
    private int GymBalance;

    /**
     * Private constructor to ensure singleton pattern.
     */
    private Gym() {
        this.GymBalance = 0;
        this.Workers = new ArrayList<>();
        this.Clients = new ArrayList<>();
        this.Sessions = new ArrayList<>();
        this.actionHistory = new ArrayList<>();
        this.secretary = null;
    }

    /**
     * @return The single instance of the Gym class.
     */
    public synchronized static Gym getInstance() {
        return gymInstance;
    }

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public List<GymWorkers> getWorkers() {
        return Workers;
    }

    public List<Client> getClients() {
        return Clients;
    }

    public List<Session> getSessions() {
        return Sessions;
    }

    /**
     * Assign a new secretary to the gym. Removes the old secretary if one exists
     * and block access to gym information.
     *
     * @param person The new secretary's person object.
     * @param salary The salary of the new secretary.
     */
    public void setSecretary(Person person, int salary) {
        if (secretary != null) {
            secretary.setGym(null);//לחסום גישה לחדר כושר עבור המזכירה הקודמת
            Workers.remove(secretary);//למחוק מהרשימה של העובדים את המזכירה הקודמת
        }
        this.secretary = new Secretary(person, salary);
        secretary.setGym(this);
        GymWorkers secretary1 = this.secretary;
        addWorker(secretary1);
        addAction("A new secretary has started working at the gym: " + secretary.getName());
    }

    /**
     * Updates the gym balance by adding the session's price.
     *
     * @param session The session for which payment is being added.
     */
    public void AddGymBalance(Session session) {
        GymBalance += session.getSessionPrice();
    }

    /**
     * Deduct payment from the gym balance for workers.
     *
     * @param payment The payment amount.
     */
    public void WorkerPayments(int payment) {
        GymBalance -= payment;
    }

    /**
     * Adds an action to the action history.
     *
     * @param action A string describing the action.
     */
    public void addAction(String action) {
        actionHistory.add(action);
    }

    /**
     * Prints all actions in the gym's history.
     */
    public void printActionHistory() {
        for (String action : actionHistory) {
            System.out.println(action);
        }
    }

    /**
     * Client Management.
     */
    public void addClient(Client client) {
        Clients.add(client);
        addAction("Registered new client: " + client.getName());
    }
    public void removeClient(Client client) {
        Clients.remove(client);
        addAction("Unregistered client: " + client.getName());
    }

    /**
     * Session Management.
     */
    public void addSessions(Session session) {
        Sessions.add(session);
    }
    /**
     *  Worker Management.
    */
    public void addWorker(GymWorkers worker) {
        Workers.add(worker);
    }

/**
 * Notification method by using Observer design pattern.
 * Override method implemented from publisher.
 */
    @Override
    public void notifyObserver(List<Observer> observers, String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }

    }
    public void notifyBySession(Session session, String message) {
        notifyObserver(session.getObservers(),message);
        addAction("A message was sent to everyone registered for session " + session.getSessionName() + " on " + session.getSessionDateAndTime() + " : " + message);

    }

    /**
     * 3 notification methods for 3 different Observers groups.
     */
    public void notifyByDate(String date, String message) {
        LocalDate formattedDate = DateFormatter.parseDate(date);
        for (Session session : Sessions) {
            if (session.getSessionDate().equals(formattedDate)) {

                notifyObserver(session.getObservers(),message);
            }
        }

        String reversedDate = DateFormatter.formatReverseDate(formattedDate);

        addAction("A message was sent to everyone registered for a session on " + reversedDate + " : " + message);
    }

    public void notifyAllClients(String message) {
        for (Client client : Clients) {
            client.update(message);
        }
        addAction("A message was sent to all gym clients: " + message);
    }

    /**
     * @return returns a string that will represent all the Gym information. we used a StringBuilder that makes it easier for
     * us to construct the gym information adhering to the given format and to avoid any bugs related to the output.
     */
    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("Gym Name: ").append(getName()).append("\n");
        ans.append("Gym Secretary: ").append(secretary.toString()).append("\n");
        ans.append("Gym Balance: ").append(GymBalance).append("\n");

        ans.append("\nClients Data:").append("\n");
        for (int i = 0; i < Clients.size(); i++) {
            Client client = getClients().get(i);
            ans.append(client.toString()).append("\n");
        }
        ans.append("\n");
        ans.append("Employees Data:").append("\n");
        for (GymWorkers employee : Workers) {
            ans.append(employee.toString()).append("\n");
        }
        ans.append("\n");
        ans.append("Sessions Data:").append("\n");
        for (int i = 0; i < Sessions.size(); i++) {
            Session session = getSessions().get(i);
            ans.append(session.toString()).append("\n");
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }
}

