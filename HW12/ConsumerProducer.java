package HW12;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;

/**
 * The ConsumerProducer class represents a multithreaded application simulating
 * a producer-consumer scenario. It creates and manages multiple producer and consumer threads
 * that interact with a shared storage to produce and consume items.
 *
 * @Author Kapil Sharma
 * @Author Chetan Chandane
 */
public class ConsumerProducer extends Thread {

    /**
     * The main method initializes the number of producers, consumers, and the shared storage.
     * It creates and starts producer and consumer threads.
     *
     * @param args Command-line arguments (not currently used).
     */
    public static void main(String args[]) {
        int soManyC = Integer.parseInt(args[0]); // Number of consumers
        int soManyP = Integer.parseInt(args[1]); // Number of producers
        Storage theStorage = new Storage();

        System.out.println("# producer = " + soManyP);
        System.out.println("# consumer = " + soManyC);

        for (int id = 1; id <= soManyP; id++) {
            new Producer(id, theStorage).start();
        }
        for (int id = 1; id <= soManyC; id++) {
            new Consumer(id, theStorage).start();
        }
    }
}


/**
 * The Storage class represents a shared storage used by producers and consumers
 * in the producer-consumer simulation. It includes methods to add items to the storage
 * and consume items from the storage in a synchronized manner.
 */
class Storage {
    static final int N = 100;
    static int soManyAreIn = 0;
    int soMany = 0;            // counter, used for verification
    private ArrayList theStorage = new ArrayList(N);
    private Object sync = new Object();

    private int sum = 0;

    /**
     * A method for testing the state of the storage and verifying the simulation.
     */
    void test() {
        if (sum > 20) {
            System.out.println("The total sum has crossed the threshold " + sum);
            System.exit(0);
        }
        if (soManyAreIn != 1) {
            System.out.println("soManyAreIn " + soManyAreIn);
            System.exit(0);
        }
        if (soMany > N) {
            System.out.println("overflow " + soMany);
            System.exit(0);
        }
        if (soMany < 0) {
            System.out.println("underflow " + soMany);
            System.exit(0);
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    /**
     * Adds items to the storage and prints related messages.
     *
     * @param addTheseItems A vector of items to be added to the storage.
     */
    void addItems(Vector addTheseItems) {
        synchronized (sync) {
            System.out.println("Producer --->");
            soManyAreIn++;
            while  ( soMany + addTheseItems.size() > N ) {
                try {
                    System.out.println("Producer waiting");
                    soManyAreIn--;
                    sync.wait();
                    System.out.println("Producer woke up");
                    soManyAreIn++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int index = 0; index < addTheseItems.size(); index++) {
                theStorage.add(addTheseItems.elementAt(index));
                sum++;
            }
            soMany += 1;
            test();
            soManyAreIn--;
            sync.notifyAll();
            System.out.println("Producer <---");
        }
    }

    /**
     * Consumes items from the storage and prints related messages.
     *
     * @param id The identifier of the consumer.
     * @return A vector of consumed items.
     */
    Vector consume(int id) {
        Vector aVector = new Vector(id);
        synchronized (sync) {
            System.out.println("Consumer ---->");
            soManyAreIn++;
            while (soMany - id < 0) {
                try {
                    soManyAreIn--;
                    System.out.println("Consumer waiting ");
                    sync.wait();
                    System.out.println("Consumer woke up");
                    soManyAreIn++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            soMany -= id;
            for (int index = 0; index < id; index++) {
                aVector.add(theStorage.remove(0));
            }
            test();
            soManyAreIn--;
            sync.notifyAll();
            System.out.println("Consumer <---");
        }
        return aVector;
    }
}


/**
 * The Consumer class represents a consumer thread in the producer-consumer simulation.
 * Each consumer has a unique identifier and consumes items from the shared storage.
 */
class Consumer extends Thread {
    int id;
    Storage thisStorage;
    final int SO_MANY;

    /**
     * Constructs a new Consumer with the specified identifier and reference to the storage.
     *
     * @param id          The unique identifier for the consumer.
     * @param thisStorage The shared storage used by producers and consumers.
     */
    Consumer(int id, Storage thisStorage) {
        this.id = id;
        this.thisStorage = thisStorage;
        SO_MANY = id * 3;
        setName("Consumer: " + id);
        System.out.println("C: " + id);
    }

    /**
     * The run method of the Consumer thread, which consumes items from the storage.
     */
    public void run() {
        Vector aVector = thisStorage.consume(id);
    }
}



/**
 * The Producer class represents a producer thread in the producer-consumer simulation.
 * Each producer has a unique identifier and produces items to be added to the shared storage.
 */
class Producer extends Thread {
    int id;
    final int SO_MANY;
    Storage thisStorage;

    /**
     * Constructs a new Producer with the specified identifier and reference to the storage.
     *
     * @param id          The unique identifier for the producer.
     * @param thisStorage The shared storage used by producers and consumers.
     */
     Producer(int id, Storage thisStorage) {
        this.id = id;
        this.thisStorage = thisStorage;
        SO_MANY = id * 7;
        setName("Producer: " + id);
        System.out.println("P: " + id);
    }

    /**
     * The run method of the Producer thread, which produces items and adds them to the storage.
     */
    public void run() {
        Vector aVector = new Vector();
        for (int counter = 0; counter < SO_MANY; counter++) {
            aVector.add(id + "_" + new Date());
        }
        thisStorage.addItems(aVector);
    }
}

    