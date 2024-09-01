/**
 * The `Client` class represents a client in a word-guessing game that connects to the server using sockets.
 * Clients can continuously input guesses to play the game, and the server provides feedback on each guess.
 *
 * The class includes a method for reading user input and sending guesses to the server.
 * Additionally, it features a nested `ClientThread` class that facilitates the creation of multiple client instances.
 *
 * @author Kapil Sharma
 * @author Chetan Chandane
 */
package HW13;

import java.io.*;
import java.net.Socket;

/**
 * The `ClientThread` class represents a thread for creating and running a `Client` instance.
 * This allows for the concurrent execution of multiple clients in the word-guessing game.
 */
class ClientThread extends Thread {
    String playerName;
    static String guess = "";


    /**
     * Constructs a new `ClientThread` with a player name.
     *
     * @param playerName The name of the player associated with this client thread.
     */
    ClientThread(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Reads user input from the console and sends guesses to the server.
     *
     * @param printWriter    The PrintWriter used for sending guesses to the server.
     * @param bufferedReader The BufferedReader used for reading user input from the console.
     * @return The guessed word entered by the user.
     * @throws IOException If an I/O error occurs during input/output operations.
     */
    public static String readUserInput(PrintWriter printWriter, BufferedReader bufferedReader) throws IOException {
        do {
            System.out.print("> ");
            if ((guess = bufferedReader.readLine()) != null) {
                printWriter.println(guess);
                printWriter.flush();
            }
        } while (guess.length() != 5);
        return guess;
    }

    /**
     * Runs the client thread, creating a new `Client` instance for the associated player.
     * Throws a RuntimeException if an IOException occurs during client creation.
     */
    public void run() {
        try {
            System.out.println("Client " + playerName + " is ready");
            while (true) {
                Socket socket = new Socket("127.0.0.1", 3000);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                readUserInput(printWriter, bufferedReader);
            }
        } catch (IOException e) {
            System.out.println("Game Over! Connection with server ended.");
        }
    }
}
