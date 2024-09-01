
/**
 * The Line class represents a simulation of firefighters passing a bucket in a line.
 * It creates a specified number of threads (firefighters) and simulates passing a bucket
 * from one firefighter to another in a synchronized manner.
 *
 * The simulation runs for a specified number of iterations, with each iteration involving
 * each firefighter receiving and passing the bucket once. The output is printed to the console
 * to show the sequence of actions.
 */
public class Line {

    /**
     * The total number of firefighter threads in the simulation.
     */
    static int numberOfThreads;

    /**
     * The number of times the simulation should run, representing the iterations.
     */
    static int soOften;

    /**
     * The main method that initializes the simulation parameters, creates firefighter threads,
     * and runs the simulation.
     *
     * @param args Command-line arguments (not currently used).
     * @throws InterruptedException If an interrupt occurs while waiting for threads to complete.
     */
    public static void main(String args[]) throws InterruptedException {

        numberOfThreads = Integer.parseInt(args[1]); // Set the total number of firefighter threads
        soOften = Integer.parseInt(args[3]);        // Set the number of simulation iterations

        for (int j = 0; j < soOften; j++) {
            for (int i = 1; i <= numberOfThreads; i++) {
                FireFighter fireFighter = new FireFighter(i);
                fireFighter.start();
                fireFighter.join();
            }
            System.out.println("            and firefighter " + numberOfThreads + " empties bucket and\n" +
                    "                  drops bucket and firefighter 1 catches the bucket.");
        }
    }
}



/**
 * The FireFighter class represents a firefighter thread in the simulation.
 * Each firefighter has a unique identifier and participates in passing a bucket
 * in a synchronized manner as part of the simulation.
 */
class FireFighter extends Thread {

    /**
     * The unique identifier for the firefighter.
     */
    int number;

    /**
     * A static count variable to track the number of instances created.
     */
    static int count = 0;

    /**
     * Constructs a new FireFighter with the specified identifier.
     *
     * @param number The unique identifier for the firefighter.
     */
    public FireFighter(int number) {
        this.number = number;
    }

    /**
     * The run method of the firefighter thread, which prints messages indicating the
     * actions of the firefighter (filling or passing the bucket) in a synchronized manner.
     */
    public void run() {
        synchronized (this) {
            if (this.number == 1)
                System.out.println("Bucket is filled by firefighter " + this.number);
            else {
                System.out.println("    bucket  is handed to firefighter " + this.number);
            }
        }
    }
}
