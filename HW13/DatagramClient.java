/**
 * The `DatagramClient` class represents a client in a word-guessing game using connectionless UDP sockets.
 * Clients can continuously input guesses to play the game, and the guesses are sent to the server using DatagramSocket.
 *
 * The class includes a method for reading user input and sending guesses to the server using DatagramSocket.
 *
 * @author Kapil Sharma
 * @author Chetan Chandane
 */
package HW13;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramClient {

    static String guess = "";

    /**
     * Reads user input from the console and sends guesses to the server using DatagramSocket.
     *
     * @param datagramSocket The DatagramSocket used for sending guesses to the server.
     * @param bufferedReader The BufferedReader used for reading user input from the console.
     * @return The guessed word entered by the user.
     * @throws IOException If an I/O error occurs during input/output operations.
     */
    public static String readUserInput(DatagramSocket datagramSocket, BufferedReader bufferedReader) throws IOException {
        byte[] buffer = new byte[512];
        do {
            System.out.print("> ");
            if ((guess = bufferedReader.readLine()) != null) {
                System.out.println(guess.getBytes());
                buffer = guess.getBytes();
            }
        } while (guess.length() != 5);
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"),5000);
        datagramSocket.send(datagramPacket);
        return guess;
    }

    /**
     * The main entry point for the client. Creates a DatagramSocket and continuously reads user input
     * to send guesses to the server via UDP datagrams.
     *
     * @param args Command line arguments (not used in this implementation).
     * @throws IOException If an I/O error occurs during socket creation or communication.
     */
    public static void main(String args[]) throws IOException {
        System.out.println("Client is ready");
        DatagramSocket datagramSocket = new DatagramSocket();
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            readUserInput(datagramSocket, bufferedReader);
        }
    }
}
