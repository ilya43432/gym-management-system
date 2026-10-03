package gym.management;

/**
 * A GymWorkers interface. Worker classes who implement this interface
 * have their own Role and a Payment salary system.
 * we have added this as an interface, and it's a good definition of adhering to the SOLID
 * principles. we will explain why:
 * 1. this interface's is only focused on the Gym workers(SRP).
 * 2. we can easily add new Workers, which is open for extension(Open closed principle)
 * 3. each worker class (Instructor, Secretary) can be used for GymWorkers, for example when were paying salaries,
 * we don't need to check if the Worker is an instructor or secretary. we can also print the all the
 * GymWorkers details without checking what's the role of each Worker.
 * 4. this interface includes methods that are relevant only to the gym workers(ISP).
 * 5. this design can help the gym workers rely on the abstraction of this GymWorker Interface,
 * and not specific worker types.
 */

public interface GymWorkers {

    /**
     * @return this will return a String that states the role of the Worker.
     */
    String WorkerRole();

    /**
     * @return calculates the total worker's salary and returns the amount.
     */
    int ReceiveSalary();

}
