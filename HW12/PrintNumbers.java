

/**
 * The PrintNumbers class is an application used to execute code with a specified number of threads.
 * It creates a given number of threads and prints their numbers in a synchronized manner.
 * The application runs for a specified number of iterations, printing the numbers of each thread
 * in the order of their creation for each iteration.
 *
 * @author Kapil Sharma
 * @author Chetan Chandane
 */
public class PrintNumbers {

    /**
     * The total number of threads to be created in the simulation.
     */
    static int numberOfThreads;

    /**
     * The number of times the simulation should run, representing the iterations.
     */
    static int soOften;

    /**
     * The main method that initializes the simulation parameters, creates thread instances,
     * and prints the numbers of each thread in a synchronized manner for each iteration.
     *
     * @param args Command-line arguments, where args[1] is the number of threads and args[3]
     *             is the number of iterations.
     * @throws InterruptedException If an interrupt occurs while waiting for threads to complete.
     */
    public static void main(String args[]) throws InterruptedException {

        numberOfThreads = 5; // Set the total number of threads
        soOften = 2;       // Set the number of simulation iterations

        Numbers number_1 = new Numbers(1);
        Numbers number_2 = new Numbers(2);
        Numbers number_3 = new Numbers(3);

        number_1.start();
        number_1.join();
        number_2.start();

//
        number_3.start();
        number_2.join();


    }
}



/**
 * The Numbers class represents a thread in the simulation that prints its assigned number.
 * Each thread has a unique identifier, and their numbers are printed in a synchronized manner.
 */
class Numbers extends Thread {

    /**
     * The unique identifier for the thread.
     */
    int number;

    /**
     * A static count variable to track the number of instances created.
     */
    static int count = 0;

    /**
     * Constructs a new Numbers thread with the specified identifier.
     *
     * @param number The unique identifier for the thread.
     */
    public Numbers(int number) {
        this.number = number;
    }

    /**
     * The run method of the Numbers thread, which prints the assigned number in a synchronized manner.
     */
    public void run() {
        synchronized (this) {
            System.out.println(this.number);
        }
    }
}
