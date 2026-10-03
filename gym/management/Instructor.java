package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.Arrays;
import java.util.List;
/**
 * The Instructor class represents a gym instructor who is a worker and a person.
 * It manages their salary, the sessions they are certified to teach, and their total sessions.
 */
public class Instructor extends Person implements GymWorkers {
    private List<SessionType> sessionList;
    private int hourlySalary;
    private int TotalSessions;

    /**
     * Constructor for the Instructor class.
     *
     * @param p           The person's information.
     * @param hourlySalary The instructor's hourly salary.
     * @param sessionList  The list of session types the instructor is certified to teach.
     */
    public Instructor(Person p, int hourlySalary, List<SessionType> sessionList) {
        super(p);
        this.hourlySalary = hourlySalary;
        this.sessionList = sessionList;
        this.TotalSessions = 0;
    }

    /**
     * @return The list of session types the instructor is certified to teach.
     */
    public List<SessionType> getSessionList() {
        return sessionList;
    }

    /**
     * Increment the total number of sessions the instructor has taught.
     */
    public void incrementTotalSessions() {
        TotalSessions++;
    }

    /**
     * @return The instructor's hourly salary.
     */
    public int getHourlySalary() {
        return hourlySalary;
    }

    /**
     * Calculate and receive the instructor's salary based on the number of sessions taught.
     *
     * @return The total salary earned for the sessions taught.
     */
    @Override
    public int ReceiveSalary() {
        int total = TotalSessions * hourlySalary;
        AddBalance(total);
        TotalSessions = 0;
        return total;
    }

    /**
     * @return The role of the worker.
     */
    @Override
    public String WorkerRole() {
        return "Instructor";
    }

    /**
     * @return A string representation of the instructor, including their certified classes and salary information.
     */
    @Override
    public String toString() {
        return super.toString() + " | Role: " + WorkerRole() + " | Salary per Hour: " + hourlySalary + " | " + "Certified Classes: " + Arrays.toString(sessionList.toArray()).replace('[', ' ').replace(']', ' ').trim();
    }



}