package gym.Utils;

/** this class helps us with managing the balance of each Person.
 * every balanceManager will have a unique ID, and it basically
 * tracks the current balance of a person at any given moment.
 * this class provides a good definition of the SRP principle.
 */

public class BalanceManagement {
    private final int ID;
    private int balance;

    /**
     * @param ID ID of a given Person.
     * @param balance Balance of a given Person.
     */
    public BalanceManagement(int ID, int balance) {
        this.ID = ID;
        this.balance = balance;
    }

    /**
     * @return returns the current balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param amount adds the given specified amount to the current balance.
     */
    public void addBalance(int amount) {
        this.balance += amount;
    }

    /**
     * @param amount subtracts the specified amount from the current balance.
     */
    public void subBalance(int amount) {
        this.balance -= amount;
    }
}
