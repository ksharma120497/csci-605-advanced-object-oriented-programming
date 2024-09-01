package HW3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


//                ###
//                ###
//                 #
//               #####
//              # ### #
//                ###
//               #  #
//              ##   ##
//########################
//##                    ##
//##                    ##

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application is created to run the hangman game which allows
    user only to have 9 tries after which the game ends

    The words are taken randomly from the unordered_list.txt.txt file and then we get 9 tries
    to guess the word

    After each wrong letter entered one part of the hangman's body
    will be removed until we are out of tries

 */

public class BackToTheFuture {
    static final int numberOfTries = 9;
    static int soManyWordToPLayWith = 0;
    static String [] hashCodeForHangman = {"0011100","0011100","0001000","0111110","1011101", "0011100","0100010","1100011","0000000","0000000"};
    static String[] hashInHangman = new String[10];
    static String[] theWords =  new String[10231];
    static Scanner scanner = new Scanner(System.in);



    /*
        This function is used to initialize the hangman with the hashValues
        This is done because of hashInHangman is static and belongs to class
        which will get updated for every instance
     */
    public static void initializeHangman(){
        for(int i=0; i<10; i++)
           hashInHangman[i]=hashCodeForHangman[i];
    }

    public static String getWord() {
        return theWords[new Random().nextInt(soManyWordToPLayWith)];
    }


    /*

    This function creates the hangman drawing based on binary hashes where 0 represent spaces
    and 1 represents #
    The sequence of string 01's will create the necessary hangman drawing

    @params removeRampAfterNumberOfTries: count to know when to remove the ramp
     */

    public static void drawHangman(int removeRampAfterNumberOfTries){
        for (int i = 0; i < 8; i++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < 8 - 1; j++) {
                if (hashInHangman[i].charAt(j) == '0')
                    System.out.print(" ");
                else if (hashInHangman[i].charAt(j) == '1')
                    System.out.print("#");
            }
            System.out.println("");
    }
        drawRamp(removeRampAfterNumberOfTries);
    }

    /*
           This function creates the ramp for the hangman with
           for loops and their indexes

           if the count goes over 8 then it will start removing
           the ramp once the hangman disappears

           @params removeRampAfterNumberOfTries: count to know when to remove the ramp
     */
    public static void drawRamp(int removeRampAfterNumberOfTries){
        if(removeRampAfterNumberOfTries<8) {
            for (int j = 0; j <= 40; j++) {
                System.out.print("#");
            }
            System.out.println("");
        }

        if(removeRampAfterNumberOfTries<9) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j <= 40; j++) {
                    if (j < 2 || j > 38)
                        System.out.print("#");
                    else
                        System.out.print(" ");
                }
                System.out.println("");
            }
        }
    }

    /*
         This function is used to create strings of '.' based on the
         size of the answer string

         @returns string array that has series of '.'
     */

    public static String[] createStartingString(String answerString){
        String [] startingString = new String[answerString.length()];
        for(int i=0;i<answerString.length(); i++){
            startingString[i] = ".";
        }
        return startingString;
    }

    /*
       This function has the logic of how the game will run and process the
       inputs given by user

       It compares the character with the answerString and finds whether it
       exits in the string
       if it does then it will replace the '.' with the character
       else it will increment the wrong guess count and will call
       hangman function again with removed body part

       This is achieved by assigning sequence of 0's to hashInGame array which is
       equivalent to not having #

       @params numberOfTries: this takes number of tries as parameter
     */

    public static void playTheWordGame(int numberOfTries){
            int wrongGuessCount = 0;
            int wordCounter = 0;
            String answerString=getWord();
            int i;
            String[] userProvidedOutputString = createStartingString(answerString);
            for ( i = 0; i <= numberOfTries; i++) {
                System.out.println("Wrong guesses: " + wrongGuessCount + " -- Word to guess: " + printOutputOfTheGameFromArray(userProvidedOutputString));
                String typedLetter = scanner.nextLine();
                if (answerString.contains(typedLetter)) {
                    for (int j = 0; j < answerString.length(); j++) {
                        if (typedLetter.charAt(0) == answerString.charAt(j))
                            userProvidedOutputString[j] = typedLetter;
                    }
                    wordCounter++;
                } else {
                    wrongGuessCount++;
                    for (int j = 0; j <wrongGuessCount; j++)
                        hashInHangman[j] = "0000000";
                    drawHangman(wordCounter);
                }
                //If the answer is correct it should print the answer and come out of the loop
                if (!printOutputOfTheGameFromArray(userProvidedOutputString).contains(".")) {
                    System.out.println("Good Job! The word was " + printOutputOfTheGameFromArray(userProvidedOutputString));
                    break;
                }
            }
            if(i>=9){ // Should print this message after 9 tries
                System.out.println("Sorry! You are out of tries. The word was " + answerString);
            }
    }


    /*
        This function returns a string by converting arrays of string into a string
        by concatenating all the elements

        @params outputArray: string array that needs to be converted

        @returns a string that will act as the output string
     */
    public static String printOutputOfTheGameFromArray(String[] outputArray){
        String sendBackConvertedString ="";
        for(int i=0; i<outputArray.length; i++)
            sendBackConvertedString=sendBackConvertedString+outputArray[i];
        return sendBackConvertedString;
    }


    /*
        This function reads file from a given location and reads the text line by line
        It stores the text in the array from where we can read the texts and
        consider it as our answerString

        @params fileName: takes file name in string from where we need to read the data
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));
        ) {
            int counter = 0;
            while  ( ( theWords[counter++] = input.readLine() )  != null )
                soManyWordToPLayWith++;
        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }


    public static void main(String args[]){
        String prompt;
        Scanner isContinue = new Scanner(System.in);
        do {
        initializeHangman();
        readWordsFromFile("/Users/kapilsharma/School Work/AOOP HW/src/HW3/hangman_guess_words");
        drawHangman(0);
        playTheWordGame(numberOfTries);
        System.out.println("Do you want to play again (yes/no)?");
        prompt = isContinue.nextLine();
        }while(prompt.equalsIgnoreCase("yes"));

        //close scanner streams after use
        isContinue.close();
        scanner.close();
    }
}
