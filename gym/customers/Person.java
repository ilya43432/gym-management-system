package gym.customers;

import gym.Utils.AgeCalculator;
import gym.Utils.BalanceManagement;
import gym.Utils.DateFormatter;
import java.time.LocalDate;

/** The Person Class. this class represents a Person Object.
 * Here the person has attributes such as ID, name, gender and a birthDate.
 * While each person has a personal unique ID and balanceManagement.
 * each field is private so that it will maintain good encapsulation
 * adhering to the OOP principles we have learned in our class.
 */

public class Person {
    private final BalanceManagement balanceManagement;
    private static int ID_Generator = 1111;
    private final int ID;
    private final String name;
    private final Gender gender;
    private final String birthDate;

    /**
     * @param name  The name of the Person.
     * @param balance The balance of the Person.
     * @param gender The gender of the Person.
     * @param birthDate The birthDate of the Person.
     * A constructor that creates a person object with the specified arguments
     * Name,inital balance, gender, birthDate.
     * Each Person will have its own unique ID and balanceManagement.
     */
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.ID = ID_Generator++;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.balanceManagement = new BalanceManagement(ID, balance);
    }

    /**
     * @param person The person to copy, copy of the person's details.
     *  This is a copy constructor, which creates a copy of a given Person.
     *  we made this constructor field as protected. this will ensure that subclasses that are related to Person can
     *  create a copy of a person object. the subclasses are (Instructor, Secretary, Client).
     */
    protected Person(Person person) {
        this.ID = person.getID();
        this.name = person.getName();
        this.gender = person.getGender();
        this.birthDate = person.getBirthDate();
        this.balanceManagement = person.getBalanceManagement();
    }

    /**
     * @return returns the balanceManagement object that is related to a given Person.
     * this method is protected to ensure that it is avaible only for the subclasses that inherit the Person
     * class.
     */
    protected BalanceManagement getBalanceManagement() {
        return this.balanceManagement;
    }

    /**
     * @return returns the ID of a given Person.
     */
    public int getID() {
        return ID;
    }

    /**
     * @return returns the Name of a given Person.
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the Gender of a given Person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return returns the total balance of a given Person.
     */
    public int getBalance() {
        return balanceManagement.getBalance();
    }

    /**
     * @param amount Adds a given amount to the total balance of a person.
     */
    public void AddBalance(int amount) {
        balanceManagement.addBalance(amount);
    }

    /**
     * @param amount deducts the total balance of a given person by a given amount.
     */
    public void DecBalance(int amount) {
        balanceManagement.subBalance(amount);
    }

    /**
     * @return returns the birthDate of a Person.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @return returns the age of a person. this uses the AgeCalculator class. adheres to the SOP principle we have
     * learned in our class.
     */
    public int getAge() {
        return AgeCalculator.Age(birthDate);
    }

    /**
     * @return returns a simple string that states the information of the person in the
     * exact format given in our assigment.
     */
    @Override
    public String toString() {
        return "ID: " + ID + " | Name: " + name + " | Gender: " + gender +
                " | Birthday: " + birthDate + " | Age: " + getAge() + " | Balance: " + getBalance();
    }
}
