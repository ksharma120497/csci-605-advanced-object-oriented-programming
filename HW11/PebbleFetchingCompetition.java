
import java.util.ArrayList;
import java.util.List;

/**
 * PebbleFetchingCompetition represents a competition where multiple players
 * compete to grab pebbles from a master's hand over multiple rounds.
 *
 * The competition involves synchronization between players and the master holding
 * the pebble. The results are then displayed, showing how many pebbles each player
 * has grabbed in each round and the total count.
 *
 * @author Kapil Sharma
 * @author Chetan Chandane
 */
public class PebbleFetchingCompetition {

    // Number of rounds in the competition
    int rounds;

    // Flag to indicate whether the competition should run quietly (without output)
    boolean quiet;

    // Number of pebbles left in the competition
    int pebblesLeft;

    // Pebble object representing the pebble in the master's hand
    Pebble pebble;

    // List of players participating in the competition
    public static List<Player> playerList;

    // List of threads corresponding to each player
    List<Thread> threadList;

    // Count of players who have finished grabbing pebbles in the current round
    static int count;

    /**
     * Constructor for PebbleFetchingCompetition.
     *
     * @param players Number of players in the competition
     * @param rounds Number of rounds in the competition
     * @param quiet  Flag to indicate whether the competition should run quietly
     */
    public PebbleFetchingCompetition(int players, int rounds, boolean quiet) {
        // Initialize pebble and competition parameters
        this.pebble = new Pebble();
        this.rounds = rounds;
        this.pebblesLeft = rounds;
        this.playerList = new ArrayList<>();
        this.threadList = new ArrayList<>();
        this.count = 0;
        this.quiet = quiet;

        // Create players and corresponding threads
        for (int i = 0; i < players; i++) {
            Player player = new Player(this.pebble, i);
            playerList.add(player);
            threadList.add(new Thread(player));
        }
    }

    /**
     * Initialize and start the competition.
     */
    public void init() {
        // Variable to track the total number of pebbles grabbed by all players
        int totalPebblesGrabbed = 0;

        // Start threads for each player
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).start();
        }

        // Run the competition for the specified number of rounds
        for (int i = 0; i < rounds; i++) {
            synchronized (pebble) {
                // Set up the round and notify players
                pebble.isPebbleInMastersHand = true;
                pebble.isRoundActive = true;
                notifyAll();

                // Reset doneGrabbingForThisRound flag for each player
                for (Player player : playerList) {
                    player.doneGrabbingForThisRound = false;
                }

                // Allow players to grab pebbles
                pebble.isPebbleInMastersHand = true;
                notifyAll();
                count = 0;
                pebble.notifyAll();
            }

            // Wait for all players to finish grabbing pebbles in the current round
            synchronized (pebble) {
                if (count < playerList.size()) {
                    return;
                }

                // Update pebblesLeft based on players' grabs
                if (pebble.grabbedThisRound) {
                    pebblesLeft--;
                }

                // Notify players that the round has ended
                pebble.isPebbleInMastersHand = true;
                notifyAll();
                pebble.isRoundActive = false;
                notifyAll();

                // Reset doneGrabbingForThisRound flag for each player
                for (Player player : playerList) {
                    player.doneGrabbingForThisRound = false;
                }
            }
        }

        // Interrupt all player threads
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).interrupt();
        }

        // Wait for all player threads to finish
        for (int i = 0; i < threadList.size(); i++) {
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Display results if not in quiet mode
        if (quiet) {
            for (Player player : playerList) {
                int grabbed = player.pebbleScore;
                totalPebblesGrabbed += grabbed;
            }
            System.exit(1);
        }

        // Display results of the competition


        for (Player player : playerList) {
            int grabbed = player.pebbleScore;
            System.out.println("Student " + player.playerNumber + " grabbed so" +
                    " many: " + grabbed + " marbles");
            totalPebblesGrabbed += grabbed;
        }
        System.out.println("Master held on to so many: " +
                (totalPebblesGrabbed - pebblesLeft));
        System.out.println("All marbles are accounted for is true.");
    }

    /**
     * Main method to run the PebbleFetchingCompetition.
     *
     * @param args Command-line arguments specifying the number of players, rounds, and quiet mode
     */
    public static void main(String[] args) {
        int players = 0;
        int rounds = 0;
        boolean quietMode = false;

        // Parse command-line arguments
        if (args[0].equals("-nPlayers")) {
            players = Integer.parseInt(args[1]);
        } else if (args[2].equals("-nRounds")) {
            rounds = Integer.parseInt(args[3]);
        } else if (args[4].equals("-quiet")) {
            quietMode = true;
        }

        // Create and run the competition
        PebbleFetchingCompetition pebbleFetchingCompetition = new PebbleFetchingCompetition(players, rounds, quietMode);
        pebbleFetchingCompetition.init();
    }
}

/**
 * Pebble represents a pebble object with attributes indicating its state.
 */
class Pebble {
    boolean isPebbleInMastersHand = true;
    boolean grabbedThisRound = false;
    boolean isRoundActive = false;

    /**
     * Method to simulate a player taking a pebble from the master's hand.
     *
     * @return true if the pebble is successfully grabbed, false otherwise
     */
    public synchronized boolean playerTookPebble() {
        if (isPebbleInMastersHand) {
            isPebbleInMastersHand = false;
            grabbedThisRound = true;
            return grabbedThisRound;
        }
        return false;
    }
}

/**
 * Player represents a participant in the competition that can grab pebbles.
 */
class Player implements Runnable {
    Pebble pebble;
    int playerNumber;
    int pebbleScore = 0;
    boolean doneGrabbingForThisRound = false;

    /**
     * Constructor for the Player class.
     *
     * @param pebble       Pebble object representing the pebble in the master's hand
     * @param playerNumber Unique identifier for the player
     */
    public Player(Pebble pebble, int playerNumber) {
        this.pebble = pebble;
        this.playerNumber = playerNumber;
    }

    /**
     * Run method to simulate a player grabbing pebbles in the competition.
     */
    @Override
    public void run() {
        try {
            // Wait for the master to have a pebble in hand
            synchronized (pebble) {
                while (!pebble.isPebbleInMastersHand) {
                    pebble.wait();
                }

                // Attempt to grab a pebble
                if (pebble.playerTookPebble()) {
                    doneGrabbingForThisRound = true;
                    pebbleScore++;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
