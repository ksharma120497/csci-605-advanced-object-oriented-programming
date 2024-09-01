/**
 * The `Server` class implements a simple word-guessing game server using a client-server architecture.
 * It communicates with clients over sockets, allowing them to guess a hidden word within a limited number of attempts.
 * The server provides feedback on each guess, indicating correct letters in the correct position with '*', correct letters
 * in the wrong position with '_', and incorrect letters with 'x'. The game continues until the correct word is guessed
 * or the allowed attempts are exhausted.
 *
 * The class includes methods for reading words from a file, selecting a random word for the game, and managing the game logic.
 *
 * @author Kapil Sharma
 * @author Chetan Chandane
 */
package HW13;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    static int soManyWordToPLayWith = 0;
    static String[] theWords = new String[10231];
    static String outputSigns;
    static String guess;
    static int attempts = 5;

    static String finalAnswerString = "";

    /**
     * Reads words from a file and populates theWords array with them.
     * Updates the soManyWordToPLayWith variable accordingly.
     *
     * @param fileName The name of the file containing words.
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(fileName));
        ) {
            int counter = 0;
            while ((theWords[counter++] = input.readLine()) != null)
                soManyWordToPLayWith++;
        } catch (Exception e) {
            System.out.println("ExceptionType occurred: " + e.getMessage());
        }
    }

    /**
     * Retrieves a random word from theWords array.
     *
     * @return A randomly selected word from theWords array.
     */
    public static String getWord() {
        finalAnswerString = theWords[new Random().nextInt(soManyWordToPLayWith)];
        return finalAnswerString;
    }

    /**
     * Manages the word-guessing game logic. Listens for client connections,
     * receives guesses, provides feedback, and continues the game until a correct guess
     * or the allowed attempts are exhausted.
     *
     * @param serverSocket The ServerSocket used for accepting client connections.
     * @throws IOException If an I/O error occurs.
     */
    public static void playWordle(ServerSocket serverSocket) throws IOException {
        System.out.println("_ indicates the letter is in the word but in the wrong spot.");
        System.out.println("* indicates the letter is in the word and correct spot.");
        System.out.println("x indicates the letter is not in the word.");
        System.out.println("Try to guess the word in 5 tries.");
        getWord();
        do {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            guess = bufferedReader.readLine();
            System.out.println(guess);    // reads user input, the input has to be 5 characters long
            outputSigns = wordleGame(guess);
            System.out.println(outputSigns);
            if (outputSigns.equals("*****"))
                System.out.println("Well done");
            attempts--;
        } while (!outputSigns.equals("*****") && attempts > 0);
        System.out.println("The word was "+ finalAnswerString);
        System.out.println("Let's play again ...");
        System.out.println("No words left to guess");
    }

    /**
     * Provides feedback on a guess, indicating correct letters in the correct position with '*',
     * correct letters in the wrong position with '_', and incorrect letters with 'x'.
     *
     * @param guess The guessed word from the client.
     * @return A string representing the feedback signs for the guessed word.
     */
    public static String wordleGame(String guess) {
        String[] outputSignsOfTheGame = {"*", "*", "*", "*", "*"};
        String answerString = finalAnswerString;

        for (int i = 0; i < guess.length(); i++) {
            if (answerString.equals(guess))
                return printOutputOfTheGameFromArray(outputSignsOfTheGame);
            if (guess.charAt(i) == answerString.charAt(i))
                outputSignsOfTheGame[i] = "*";
            else if (isAlphabetExist(guess.charAt(i)))
                outputSignsOfTheGame[i] = "_";
            else if (!isAlphabetExist(guess.charAt(i)))
                outputSignsOfTheGame[i] = "x";
        }
        return printOutputOfTheGameFromArray(outputSignsOfTheGame);
    }

    /**
     * Checks if a given letter exists in the correct word.
     *
     * @param currentLetterFromIteration The letter to check.
     * @return True if the letter exists in the correct word, false otherwise.
     */
    public static boolean isAlphabetExist(char currentLetterFromIteration) {
        String answerString = theWords[0];
        for (int i = 0; i < 5; i++) {
            if (currentLetterFromIteration == answerString.charAt(i))
                return true;
        }
        return false;
    }

    /**
     * Converts an array of output signs into a single string.
     *
     * @param outputArray The array of output signs.
     * @return A string representation of the output signs.
     */
    public static String printOutputOfTheGameFromArray(String[] outputArray) {
        String sendBackConvertedString = "";
        for (int i = 0; i < 5; i++)
            sendBackConvertedString = sendBackConvertedString + outputArray[i];
        return sendBackConvertedString;
    }

    /**
     * The main entry point for the server. Creates a ServerSocket, starts the server,
     * and initiates the word-guessing game.
     *
     * @param args Command line arguments (not used in this implementation).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Server is ready");
        readWordsFromFile("5_char_word.txt");		// reads the text file - this file has to be in the local directory
        playWordle(serverSocket);
    }
}
