package HW2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;



public class Wordle {
    static int soManyWordToPLayWith = 1;

    static final String[] theWords = {"hello","abcde"};

    static String outputSigns;

    static final Scanner readGuess = new Scanner(System.in);

    static String guess = "";

    static int attempts=5;

    static int test = 0;

    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));
        ) {
            String word;
            int counter = 0;
            while  ( ( theWords[counter++] = input.readLine() )  != null )
                soManyWordToPLayWith ++;
        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }
    public static String readUserInput() {	//
        do {
            System.out.print("> ");
            if  ( readGuess.hasNext() )	{
                guess = readGuess.nextLine();
            }
        } while ( guess.length() != 5 );
        return guess;
    }
    public static String getWord() {
        return theWords[new Random().nextInt(soManyWordToPLayWith)];
    }
    public static void playWordle() {
        System.out.println("_ indicates the letter is in the word but in the wrong spot.");
        System.out.println("* indicates the letter is in the word and correct spot.");
        System.out.println("x indicates the letter is not in the word.");
        System.out.println("Try to guess the word in 5 tries.");
        do {
            System.out.println(readUserInput());    // reads user input, the input has to be 5 characters long
            outputSigns = wordleGame();
            System.out.println(outputSigns);
            if(outputSigns.equals("*****"))
                System.out.println("Well done");
            attempts--;
        } while(!outputSigns.equals("*****") && attempts>0);
        System.out.println(getWord() );		// get a random word from the read file
        System.out.println("Let's play again ...");
        System.out.println("No words left to guess");
    }

    public static String wordleGame() {
        String[] outputSignsOfTheGame = {"*", "*", "*", "*", "*"};
        String answerString = theWords[0];

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

    public static boolean isAlphabetExist(char currentLetterFromIteration){
        String answerString = theWords[0];
       for(int i=0; i<5; i++){
           if(currentLetterFromIteration==answerString.charAt(i))
               return true;
       }
        return false;
    }

    public static String printOutputOfTheGameFromArray(String[] outputArray){
        String sendBackConvertedString ="";
        for(int i=0; i<5; i++)
            sendBackConvertedString=sendBackConvertedString+outputArray[i];
        return sendBackConvertedString;
    }

    public static void main( String args[] ) {
        readWordsFromFile("5_char_word.txt");		// reads the text file - this file has to be in the local directory
        playWordle();
    }
}